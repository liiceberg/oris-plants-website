package ru.kpfu.itis.gimaletdinova.handler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.HttpURLConnection;

@WebServlet(name = "exceptionHandler", urlPatterns = "/handle")
public class ExceptionHandler extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleException(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleException(req, resp);
    }

    private void handleException(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Throwable throwable = (Throwable) req.getAttribute("java.servlet.error.exception");
        Integer code = (Integer) req.getAttribute("javax.servlet.error.status_code");
        String uri = (String) req.getAttribute("javax.servlet.error.request_uri");

        req.setAttribute("status_code", code);
        req.setAttribute("uri", uri == null?"":uri);
        if (code == HttpURLConnection.HTTP_INTERNAL_ERROR) {
            req.setAttribute("message", throwable.getMessage());
        }
        req.getRequestDispatcher("exception.ftl").forward(req, resp);
    }
}
