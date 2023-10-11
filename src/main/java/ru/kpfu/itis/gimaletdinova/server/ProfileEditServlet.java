package ru.kpfu.itis.gimaletdinova.server;

import com.cloudinary.Cloudinary;
import ru.kpfu.itis.gimaletdinova.Const;
import ru.kpfu.itis.gimaletdinova.dao.implementations.UserDao;
import ru.kpfu.itis.gimaletdinova.dto.UserDto;
import ru.kpfu.itis.gimaletdinova.model.User;
import ru.kpfu.itis.gimaletdinova.service.UserService;
import ru.kpfu.itis.gimaletdinova.service.UserServiceImplementation;
import ru.kpfu.itis.gimaletdinova.util.CloudinaryUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "profileEditServlet", urlPatterns = "/edit")
@MultipartConfig(
        maxFileSize = Const.maxFileSize,
        maxRequestSize = Const.maxRequestSize
)
public class ProfileEditServlet extends HttpServlet {
    private final Cloudinary cloudinary = CloudinaryUtil.getInstance();
    private final String FILE_PATH_PREFIX = "/tmp";
    private static final Integer DIRECTORIES_COUNT = 100;
    private final UserService userService = new UserServiceImplementation();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = getUserId(req);
        UserDto userDto = userService.get(userId);
        req.setAttribute("user", userDto);
        req.getRequestDispatcher("profile_edit.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String lastname = req.getParameter("lastname");
        String img = saveImg(req);

        if (name.isEmpty() || lastname.isEmpty()) {
            req.setAttribute("empty_field_error", true);
            req.getRequestDispatcher("profile_edit.ftl").forward(req, resp);
        } else {

            UserDao userDao = new UserDao();
            User user = userDao.get(getUserId(req));
            user.setName(name);
            user.setLastname(lastname);
            user.setImg(img);
            userDao.update(user);

            resp.sendRedirect("/profile");
        }
    }

    private String saveImg(HttpServletRequest req) throws ServletException, IOException {
        Part part = req.getPart("img");

        String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
        File file = new File(FILE_PATH_PREFIX +
                File.separator +
                fileName.hashCode() % DIRECTORIES_COUNT +
                File.separator +
                fileName);
        file.getParentFile().mkdirs();
        file.createNewFile();

        try (InputStream inputStream = part.getInputStream();
             OutputStream outputStream = Files.newOutputStream(file.toPath())) {

            byte[] buffer = new byte[inputStream.available() / 10];

            while (inputStream.read(buffer) != -1) {
                outputStream.write(buffer);
            }
        }

        Map uploadResult = cloudinary.uploader().upload(file, new HashMap<>());
        String url = uploadResult.get("url").toString();

        file.delete();

        return url;
    }

    private int getUserId(HttpServletRequest req) {
        return Integer.parseInt(req.getSession().getAttribute("user_id").toString());
    }
}
