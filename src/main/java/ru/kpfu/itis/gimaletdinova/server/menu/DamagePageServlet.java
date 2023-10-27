package ru.kpfu.itis.gimaletdinova.server.menu;

import ru.kpfu.itis.gimaletdinova.KeyNames;
import ru.kpfu.itis.gimaletdinova.service.DamageService;
import ru.kpfu.itis.gimaletdinova.service.implementations.DamageServiceImp;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "damagePageServlet", urlPatterns = "/illnesses")
public class DamagePageServlet extends HttpServlet {

    private DamageService damageService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        damageService = (DamageServiceImp) getServletContext().getAttribute(KeyNames.DAMAGE_SERVICE);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("items", damageService.getAll());
        req.getRequestDispatcher("/menu/illnesses.ftl").forward(req, resp);
    }
}
