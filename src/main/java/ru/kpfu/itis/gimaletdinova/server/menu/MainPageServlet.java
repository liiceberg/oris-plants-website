package ru.kpfu.itis.gimaletdinova.server.menu;

import ru.kpfu.itis.gimaletdinova.KeyNames;
import ru.kpfu.itis.gimaletdinova.Messages;
import ru.kpfu.itis.gimaletdinova.dao.implementations.PlantDao;
import ru.kpfu.itis.gimaletdinova.model.Plant;
import ru.kpfu.itis.gimaletdinova.model.Post;
import ru.kpfu.itis.gimaletdinova.model.enam.Category;
import ru.kpfu.itis.gimaletdinova.model.enam.IllegalEnumValueException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "mainPageServlet", urlPatterns = "/main")
public class MainPageServlet extends HttpServlet {
    private PlantDao plantDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        plantDao = (PlantDao) getServletContext().getAttribute(KeyNames.PLANT_DAO);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Plant> plants = null;
        try {
            plants = plantDao.getAll();
        } catch (SQLException | IllegalEnumValueException ignored) {
        }
        req.setAttribute("plants", plants);
        req.setAttribute("category", Category.values());

        Object userId = req.getSession().getAttribute("user_id");
        if (userId != null) {
            List<Integer> fav = new ArrayList<>();
            try {
                fav = plantDao.getFavourites(Integer.parseInt(userId.toString()));
            } catch (SQLException ignored) {
            }
            req.setAttribute("fav", fav);
        }
        req.getRequestDispatcher("/menu/main.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object user = req.getSession().getAttribute("user_id");
        if (user == null) {
            resp.getWriter().write(Messages.LIKE_BTN_NOT_AVAILABLE_MESSAGE);
            return;
        }

        int plantId = Integer.parseInt(req.getParameter("id"));
        int userId = Integer.parseInt(user.toString());
        List<Integer> fav = new ArrayList<>();
        try {
            fav = plantDao.getFavourites(userId);
        } catch (SQLException ignored) {
        }
        if (fav.contains(plantId)) {
            try {
                plantDao.removeFavourite(userId, plantId);
            } catch (SQLException ignored) {
                return;
            }
            resp.getWriter().write("del_fav");
        } else {
            try {
                plantDao.addFavourite(userId, plantId);
            } catch (SQLException ignored) {
                return;
            }
            resp.getWriter().write("add_fav");
        }
    }
}
