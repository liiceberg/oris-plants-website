package ru.kpfu.itis.gimaletdinova.server.menu;

import ru.kpfu.itis.gimaletdinova.KeyNames;
import ru.kpfu.itis.gimaletdinova.dao.implementations.PlantDao;
import ru.kpfu.itis.gimaletdinova.model.Plant;
import ru.kpfu.itis.gimaletdinova.model.enam.IllegalEnumValueException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name="favPageServlet", urlPatterns = "/favourites")
public class FavPadeServlet extends HttpServlet {

    private PlantDao plantDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        plantDao = (PlantDao) getServletContext().getAttribute(KeyNames.PLANT_DAO);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getSession().getAttribute("user_id").toString());
        List<Plant> plants = null;
        try {
            plants = plantDao.getAll(userId);
        } catch (SQLException | IllegalEnumValueException ignored) {}
        req.setAttribute("plants", plants);
        req.getRequestDispatcher("/menu/favourites.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int cardId = Integer.parseInt(req.getParameter("id"));
        int userId = Integer.parseInt(req.getSession().getAttribute("user_id").toString());
        try {
            plantDao.removeFavourite(userId, cardId);
        } catch (SQLException e) {
            resp.getWriter().write("exception");
        }
    }
}
