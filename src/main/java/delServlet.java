/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import HibernateUtil.HibernateUtil;
import com.mycompany.mavenproject1.SystemUser;
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
@WebServlet(urlPatterns = {"/delServlet"})
public class delServlet extends HttpServlet {

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
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            int id;
            SessionFactory sf = HibernateUtil.getSessionFactory();
            Session session = sf.openSession();
            //userpass query
            Criteria crit = session.createCriteria(SystemUser.class);
            Criterion cond1 = Restrictions.eq("username", username);
            Criterion cond2 = Restrictions.eq("password", password);
            crit.add(Restrictions.and(cond1, cond2));
            if((SystemUser) crit.uniqueResult() != null){
                //redirect positive and delete account
                delete(read(((SystemUser) crit.uniqueResult()).getId()));
                RequestDispatcher rdp = request.getRequestDispatcher("/login.jsp");
                rdp.forward(request, response);
            }else{
                //redirect negative 
                errorMsg = "Username or password invalid";
                request.setAttribute("errorMsg", errorMsg);
                RequestDispatcher rdp = request.getRequestDispatcher("/fail.jsp");
                rdp.forward(request, response);
            };
        }
    }
    private static void delete(SystemUser su){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        session.delete(su);
        session.getTransaction().commit();
        session.close();
    }
    private static SystemUser read(int id){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        SystemUser su = (SystemUser) session.get(SystemUser.class, id);
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
