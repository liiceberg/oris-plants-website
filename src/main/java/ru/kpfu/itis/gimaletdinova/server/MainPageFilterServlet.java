package ru.kpfu.itis.gimaletdinova.server;

import ru.kpfu.itis.gimaletdinova.model.enam.Category;
import ru.kpfu.itis.gimaletdinova.model.enam.IllegalEnumValueException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="mainPageFilterServlet", urlPatterns = "/filter")
public class MainPageFilterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Category category = null;
        try {
            category = Category.getCategory(req.getParameter("category"));
        } catch (IllegalEnumValueException ignored) {}

    }
}
