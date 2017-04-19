package com.aflb.debttracker;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BarnesServlet extends HttpServlet {
    /**
     * Default serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        resp.setContentType("text/plain");
        resp.getWriter().println("{ \"name\": \"" + name + "\" ,");
        resp.getWriter().println(" \"class\": \"" + BarnesServlet.class.toString() + "\" }");
    }
}
