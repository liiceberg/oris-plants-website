package ru.kpfu.itis.gimaletdinova.server;

import ru.kpfu.itis.gimaletdinova.KeyNames;
import ru.kpfu.itis.gimaletdinova.dao.Dao;
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

@WebServlet(name="plantViewingServlet", urlPatterns = "/plant/*")
public class PlantViewingServlet extends HttpServlet {
    private Dao<Plant> dao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        dao = (PlantDao) getServletContext().getAttribute(KeyNames.PLANT_DAO);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int plantId = Integer.parseInt(req.getParameter("id"));
        Plant plant = null;
        try {
            plant = dao.get(plantId);
        } catch (SQLException | IllegalEnumValueException ignored) {}
        req.setAttribute("plant", plant);
        req.getRequestDispatcher("/plant.ftl").forward(req, resp);
    }
}
