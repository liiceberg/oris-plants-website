package ru.kpfu.itis.gimaletdinova.server.menu;

import ru.kpfu.itis.gimaletdinova.KeyNames;
import ru.kpfu.itis.gimaletdinova.dao.implementations.PlantDao;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="mainPageServlet", urlPatterns = "/main")
public class MainPageServlet extends HttpServlet {
    private PlantDao plantDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        plantDao = (PlantDao) getServletContext().getAttribute(KeyNames.PLANT_DAO);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("plants", plantDao.getAll());

        Object userId = req.getSession().getAttribute("user_id");
        if (userId != null) {
            req.setAttribute("fav", plantDao.getFavourites(Integer.parseInt(userId.toString())));
        }
        req.getRequestDispatcher("/menu/main.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/menu/main.ftl");
    }
}
