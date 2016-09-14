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
 * @author Max
 */
@WebServlet(urlPatterns = {"/loginServlet"})
public class loginServlet extends HttpServlet {

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
        doPost(request, response);
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String errorMsg = "";
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        //userpass query
        Criteria crit = session.createCriteria(SystemUser.class);
        Criterion cond1 = Restrictions.eq("username", username);
        Criterion cond2 = Restrictions.eq("password", password);
        crit.add(Restrictions.and(cond1, cond2));
        if((SystemUser) crit.uniqueResult() != null){
            //redirect positive
            RequestDispatcher rdp = request.getRequestDispatcher("/success.jsp");
             rdp.forward(request, response);
        }else{
            //redirect negative
            errorMsg = "Invalid username or password";
            request.setAttribute("errorMsg", errorMsg);
            RequestDispatcher rdp = request.getRequestDispatcher("/fail.jsp");
            rdp.forward(request, response);
        };
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
