/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import HibernateUtil.HibernateUtil;
import static com.mycompany.mavenproject1.Main.userExists;
import com.mycompany.mavenproject1.SystemUser;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Max
 */
@WebServlet(urlPatterns = {"/createAccount"})
public class createAccount extends HttpServlet {

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
            List allusers = list();
            
            String username = request.getParameter("new_username");
            String password = request.getParameter("new_password");
            SystemUser su = new SystemUser(allusers.size(),username, password);
            SessionFactory sf = HibernateUtil.getSessionFactory();
            Session session = sf.openSession();
        
            session.beginTransaction();
        
            if(userExists(su.getUsername()) == null){
                int id = (int) session.save(su);
                su.setId(id);
                session.getTransaction().commit();
                session.close();
                RequestDispatcher rdp = request.getRequestDispatcher("/success.jsp");
                rdp.forward(request, response);
            } else{
                //something about user already exists
                RequestDispatcher rdp = request.getRequestDispatcher("/fail.jsp");
                rdp.forward(request, response);
                System.out.println("Username already exists!" );
            }
        }
    }

    private static List list(){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        List userList = session.createQuery("FROM SystemUser").list();
        session.close();
        return userList;
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
