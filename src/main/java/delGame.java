/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import HibernateUtil.HibernateUtil;
import com.mycompany.mavenproject1.SystemUser;
import com.mycompany.mavenproject1.WishListItem;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Max Huhman
 */
@WebServlet(urlPatterns = {"/delGame"})
public class delGame extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String errorMsg = "";
            String title = request.getParameter("title");
            if(getGame(title) != null){
                WishListItem wli = getGame(title);
                delete(wli);
                RequestDispatcher rdp = request.getRequestDispatcher("/success.jsp");
                rdp.forward(request, response);
            } else{
                //fail
                errorMsg = "Could not find game";
                request.setAttribute("errorMsg", errorMsg);
                RequestDispatcher rdp = request.getRequestDispatcher("/fail.jsp");
                rdp.forward(request, response);
            }
            
            //forward to update servlet later
            
        }
    }
    public static WishListItem getGame(String title){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Criteria crit = session.createCriteria(WishListItem.class);
        Criterion cond1 = Restrictions.eq("title", title);
        crit.add(Restrictions.and(cond1));
        return (WishListItem) crit.uniqueResult();
    }
    private static void delete(WishListItem wli){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        session.delete(wli);
        session.getTransaction().commit();
        session.close();
    }
    private static WishListItem read(int id){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        WishListItem su = (WishListItem) session.get(WishListItem.class, id);
        session.close();
        return su;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
