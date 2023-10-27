package ru.kpfu.itis.gimaletdinova.server;

import ru.kpfu.itis.gimaletdinova.KeyNames;
import ru.kpfu.itis.gimaletdinova.dao.Dao;
import ru.kpfu.itis.gimaletdinova.dao.implementations.PlantDao;
import ru.kpfu.itis.gimaletdinova.dto.DamageDto;
import ru.kpfu.itis.gimaletdinova.model.Plant;
import ru.kpfu.itis.gimaletdinova.model.enam.IllegalEnumValueException;
import ru.kpfu.itis.gimaletdinova.service.DamageService;

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

@WebServlet(name="plantViewingServlet", urlPatterns = "/plant/*")
public class PlantViewingServlet extends HttpServlet {
    private PlantDao plantDao;
    private DamageService damageService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        plantDao = (PlantDao) getServletContext().getAttribute(KeyNames.PLANT_DAO);
        damageService = (DamageService) getServletContext().getAttribute(KeyNames.DAMAGE_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int plantId = Integer.parseInt(req.getParameter("id"));
        Plant plant = null;
        List<DamageDto> damages = new ArrayList<>();
        try {
            plant = plantDao.get(plantId);
            for (Integer id: plantDao.getDamages(plantId)) {
                damages.add(
                        damageService.get(id)
                );
            }
        } catch (SQLException | IllegalEnumValueException ignored) {}
        req.setAttribute("plant", plant);
        req.setAttribute("damages", damages);
        req.getRequestDispatcher("/plant.ftl").forward(req, resp);
    }
}
