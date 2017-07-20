package com.aflb.debttracker;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aflb.debttracker.data.User;
import com.aflb.debttracker.data.dao.UserDao;
import com.aflb.debttracker.data.dao.memory.UserDaoMemory;


public class BarnesServlet extends HttpServlet {
    /**
     * Default serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    
    // TODO Dependency injection on this
    private UserDao userDao = new UserDaoMemory();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String pass = req.getParameter("password");
        
        
        userDao.init();
        userDao.begin();
        List<User> users = userDao.getUsers();
        userDao.commit();
        userDao.close();
        
        resp.setContentType("text/plain");
        PrintWriter writer = resp.getWriter();
        writer.println("{");
        writer.println("\"name\": \"" + name + "\" ,");
        writer.println("\"pass\": \"" + pass + "\" ,");
        writer.println("\"class\": \"" + BarnesServlet.class.toString() + "\"");
        writer.println("\"allusers\": [");
        for (int i = 0; i < users.size(); i++) {
        	writer.print(users.get(i).toJson());
        	if (i < users.size() - 1) {
        		writer.print(",");
        	}
        }
        resp.getWriter().println("]");
        resp.getWriter().println("}");
    }
    
//    public void setAccountingDao(AccountingDao accountingDao) {
//        this.accountingDao = accountingDao;
//    }
//    
//    public void setMonthsAg0(int numberMonths) {
//        this.numberMonths = numberMonths;
//    }
}
