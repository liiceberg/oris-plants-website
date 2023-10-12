package ru.kpfu.itis.gimaletdinova.server;

import com.cloudinary.Cloudinary;
import ru.kpfu.itis.gimaletdinova.Const;
import ru.kpfu.itis.gimaletdinova.util.CloudinaryUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "fileDownloaderServlet", urlPatterns = "/upload")
@MultipartConfig(
        maxFileSize = Const.maxFileSize,
        maxRequestSize = Const.maxRequestSize
)
public class FileDownloaderServlet extends HttpServlet {
    private final Cloudinary cloudinary = CloudinaryUtil.getInstance();
    private final String FILE_PATH_PREFIX = "/tmp";
    private static final Integer DIRECTORIES_COUNT = 100;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String img = saveImg(req);
        req.setAttribute("img_url", img);
        req.getRequestDispatcher("/edit").forward(req, resp);
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
             FileOutputStream outputStream = new FileOutputStream(file)) {

            int b;
            while ((b = inputStream.read()) != -1) {
                outputStream.write(b);
            }
        }

        if (file.length() == 0) {
            file.delete();
            return null;
        }

        Map uploadResult = cloudinary.uploader().upload(file, new HashMap<>());
        String url = uploadResult.get("url").toString();

        file.delete();

        return url;
    }
}
