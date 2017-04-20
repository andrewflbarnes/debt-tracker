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
    
//    private AccountingDao accountingDao;
//    private int numberMonths;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
//        
//        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.MONTH, -numberMonths);
//        
//        AccountingDaoFilter filter = new AccountingDaoFilter()
//                .reset()
//                .setUser(name)
//                .setFromDate(cal.getTime());
//        List<AccountEntry> entries = accountingDao.getEntries(filter);
        
        resp.setContentType("text/plain");
        resp.getWriter().println("{ \"name\": \"" + name + "\" ,");
        resp.getWriter().println(" \"class\": \"" + BarnesServlet.class.toString() + "\" }");
    }
    
//    public void setAccountingDao(AccountingDao accountingDao) {
//        this.accountingDao = accountingDao;
//    }
//    
//    public void setMonthsAg0(int numberMonths) {
//        this.numberMonths = numberMonths;
//    }
}
