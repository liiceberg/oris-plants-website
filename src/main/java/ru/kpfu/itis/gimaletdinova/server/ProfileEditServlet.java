package ru.kpfu.itis.gimaletdinova.server;

import ru.kpfu.itis.gimaletdinova.Const;
import ru.kpfu.itis.gimaletdinova.KeyNames;
import ru.kpfu.itis.gimaletdinova.dao.implementations.UserDao;
import ru.kpfu.itis.gimaletdinova.dto.UserDto;
import ru.kpfu.itis.gimaletdinova.model.User;
import ru.kpfu.itis.gimaletdinova.service.UserService;
import ru.kpfu.itis.gimaletdinova.util.FileDownloadUtil;
import ru.kpfu.itis.gimaletdinova.util.PasswordUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.sql.SQLException;
import java.util.Map;

@WebServlet(name = "profileEditServlet", urlPatterns = "/edit")
@MultipartConfig(
        maxFileSize = Const.maxFileSize,
        maxRequestSize = Const.maxRequestSize
)
public class ProfileEditServlet extends HttpServlet {

    private UserDao userDao;
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userDao = (UserDao) getServletContext().getAttribute(KeyNames.USER_DAO);
        userService = (UserService) getServletContext().getAttribute(KeyNames.USER_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getUser(req);
        req.getRequestDispatcher("/profile_edit.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getUser(req);
        Part part = req.getPart("img");
        String img = FileDownloadUtil.saveImg(part);
        String name = req.getParameter("name");
        String lastname = req.getParameter("lastname");
        String oldPassword = req.getParameter("old_password");
        String newPassword = req.getParameter("new_password");
        String newPassword2 = req.getParameter("new_password_2");
        try {
            if (!isFormCorrect(req, name, lastname, oldPassword, newPassword, newPassword2)) {
                req.getRequestDispatcher("/profile_edit.ftl").forward(req, resp);
            } else {

                User user = userDao.get(getUserId(req));
                user.setName(name);
                user.setLastname(lastname);
                if (img != null) {
                    user.setImg(img);
                }
                if (!newPassword.isEmpty()) {
                    user.setPassword(PasswordUtil.encrypt(newPassword));
                }
                userDao.update(user);


                resp.sendRedirect(req.getContextPath() + "/profile");
            }
        } catch (SQLException ex) {
            req.setAttribute("db_error", true);
            req.getRequestDispatcher("/profile_edit.ftl").forward(req, resp);
        }
    }

    private Boolean isFormCorrect(HttpServletRequest req, String name, String lastname,
                                  String oldPassword, String newPassword, String newPassword2) throws SQLException {
        if (name.isEmpty() || lastname.isEmpty()) {
            req.setAttribute("empty_field_error", true);
            return false;
        }
        if (!(oldPassword.isEmpty() && newPassword.isEmpty() && newPassword2.isEmpty())) {
            if (!PasswordUtil.encrypt(oldPassword).equals(userDao.get(getUserId(req)).getPassword())) {
                req.setAttribute("old_password_error", true);
                return false;
            }
            Map<String, Boolean> map = PasswordUtil.verify(newPassword);
            for (String error : map.keySet()) {
                if (map.get(error)) {
                    req.setAttribute(error, true);
                    return false;
                }
            }
            if (!PasswordUtil.verifyEquals(newPassword, newPassword2)) {
                req.setAttribute("passwords_not_equal_error", true);
                return false;
            }
        }
        return true;
    }

    private int getUserId(HttpServletRequest req) {
        return Integer.parseInt(req.getSession().getAttribute("user_id").toString());
    }

    private void getUser(HttpServletRequest req) {
        int userId = getUserId(req);
        UserDto userDto = userService.get(userId);
        req.setAttribute("user", userDto);
    }
}
