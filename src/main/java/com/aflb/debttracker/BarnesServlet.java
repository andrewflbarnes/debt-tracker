package com.aflb.debttracker;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BarnesServlet extends HttpServlet {
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    resp.setContentType("text/plain");
    resp.getWriter().println("{ \"name\": \"Barnesly\" }");
    resp.getWriter().println("{ \"class\": \"" + BarnesServlet.class.toString() + "\" }");
  }
}