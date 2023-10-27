package ru.kpfu.itis.gimaletdinova.server;


import ru.kpfu.itis.gimaletdinova.KeyNames;
import ru.kpfu.itis.gimaletdinova.dao.Dao;
import ru.kpfu.itis.gimaletdinova.dao.implementations.DamageDao;
import ru.kpfu.itis.gimaletdinova.model.Damage;
import ru.kpfu.itis.gimaletdinova.model.enam.IllegalEnumValueException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "damageViewingServlet", urlPatterns = "/damage/*")
public class DamageViewingServlet extends HttpServlet {
    private Dao<Damage> dao;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        dao = (DamageDao) getServletContext().getAttribute(KeyNames.DAMAGE_DAO);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Damage d = null;
        try {
            d = dao.get(id);
        } catch (SQLException | IllegalEnumValueException ignored) {}
        req.setAttribute("damage", d);
        req.getRequestDispatcher("/damage.ftl").forward(req, resp);
    }
}
