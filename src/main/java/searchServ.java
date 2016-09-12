/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import HibernateUtil.HibernateUtil;
import static com.mycompany.mavenproject1.Main.userExists;
import com.mycompany.mavenproject1.SystemUser;
import com.mycompany.mavenproject1.WishListItem;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

/**
 *
 * @author Max
 */
@WebServlet(urlPatterns = {"/searchServ"})
public class searchServ extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String replaceText = request.getParameter("game_search");
            String replaced = replaceSpace(replaceText);
            //co-optimus scrape
            Document doc = Jsoup.connect("http://api.co-optimus.com/games.php?search=true&name=".concat(replaced)).get();
            String htmlString = doc.toString();
            doc = Jsoup.parse(htmlString, "", Parser.xmlParser());
            boolean foundOptimus = false;
            boolean foundCheapShark = false;
            
            Element span = doc.select("games > game").first();
            Element node = span.nextElementSibling();
            String system = "PC";
            boolean sysFound = true;
            String title = "";
            String genre = "";
            int localp = 0;
            int onlinep = 0;
            String normalPrice = "";
            String salePrice = "";
            String savings = "";
            int metacriticScore = 0;
            
            if(span != null){
                System.out.println("exists!");
                foundOptimus = true;
                //check for multiple results!!!
                if(span.select("game > system").text().equals(system)){
                    sysFound = true;//case one its the first result
                    title = node.select("game > title").text();
                    genre = node.select("game > genre").text();
                    localp = Integer.parseInt(node.select("game > local").text());
                    onlinep = Integer.parseInt(node.select("game > online").text());
                }else{
                    while(!(span.select("game > system").text().equals(system))){
                        if(node != null){
                            if(node.select("game > system").text().equals(system)){
                                sysFound = true;
                                title = node.select("game > title").text();
                                genre = node.select("game > genre").text();
                                localp = Integer.parseInt(node.select("game > local").text());
                                onlinep = Integer.parseInt(node.select("game > online").text());
                                break;//case 2: its one of the following results
                            }else{
                                span = node;
                                node = span.nextElementSibling();
                            }   
                        }else{
                            System.out.println("Debug: no "+ system + " game found");
                            sysFound = false;
                            break; //case 3: no game found for that system 
                        }
                    }
                }
                
                System.out.println("debug: "+title+genre+localp+onlinep);
                //Cheapshark scrape 

                String urlString = ("http://www.cheapshark.com/api/1.0/deals?&desc=0&title=".concat(replaced).concat("&pageSize=10")).trim(); //just a string
                try {

                    String jsonS = readUrl(urlString);
                    JSONArray jsonA = new JSONArray(jsonS);
                    if(jsonA.length() != 0){
                        System.out.println("debug: inside for loop");
                        for(int i = 0; i<jsonA.length(); i++){
                            if((jsonA.getJSONObject(i).getString("title")).toLowerCase().equals(replaceText.toLowerCase())){
                                normalPrice = jsonA.getJSONObject(i).getString("normalPrice");
                                salePrice = jsonA.getJSONObject(i).getString("salePrice");
                                savings = jsonA.getJSONObject(i).getString("savings");
                                metacriticScore = Integer.parseInt(jsonA.getJSONObject(i).getString("metacriticScore"));
                                foundCheapShark = true;
                            }
                        }
                    }else{
                        //break exectution
                        System.out.println("no results found on cheapshark");
                        String resultMessage = "No results found on cheapshark";
                        request.setAttribute("resultMessage", resultMessage);
                        request.getRequestDispatcher("/success.jsp").forward(request, response);
                    }   
                    if(!foundCheapShark){
                        //break execution
                        System.out.println("no matches found on cheapshark");
                        String resultMessage = "No matches found on cheapshark!";
                        request.setAttribute("resultMessage", resultMessage);
                        request.getRequestDispatcher("/success.jsp").forward(request, response);
                    }
                    System.out.println(normalPrice);
                    System.out.println(salePrice);
                    System.out.println(savings);
                    System.out.println(metacriticScore);

                    } catch (JSONException e) {
                        e.printStackTrace();
                }
                
                WishListItem wli = new WishListItem( title, genre, localp, onlinep, normalPrice, salePrice, savings, metacriticScore);
                //request.setAttribute("wish", wli);
                //response.sendRedirect(request.getContextPath() + "/result.jsp");
                if(foundCheapShark){
                    //save search wli to database
                    SessionFactory sf = HibernateUtil.getSessionFactory();
                    Session session = sf.openSession();
                    session.beginTransaction();

                    if(gameExists(wli.getTitle()) == null){
                        int id = (int) session.save(wli);
                        wli.setId(id);
                        session.getTransaction().commit();
                        session.close();
                        //rdp.forward(request, response);
                        System.out.println("Game added to database!" );
                        String resultMessage = "Game added to database!";
                        request.setAttribute("resultMessage", resultMessage);
                        request.getRequestDispatcher("/success.jsp").forward(request, response);
                        //RequestDispatcher rdp = request.getRequestDispatcher("/success.jsp");
                        //rdp.forward(request, response);
                    } else{
                        //something about user already exists
                        //RequestDispatcher rdp = request.getRequestDispatcher("/fail.jsp");
                        //rdp.forward(request, response);
                        System.out.println("Game already exists in database!" );
                        String resultMessage = "Game already exists in database!";
                        request.setAttribute("resultMessage", resultMessage);
                        request.getRequestDispatcher("/success.jsp").forward(request, response);
                    }
                }
            } else{
                System.out.println("No game found on co-optimus");
                String resultMessage = "No game found on co-optimus";
                request.setAttribute("resultMessage", resultMessage);
                request.getRequestDispatcher("/success.jsp").forward(request, response);
            }
            //System.out.println("debug");
                    
        }
    }
    public static String replaceSpace(String str){
        StringBuffer strBuffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                strBuffer.append("%20");
            }else {
                strBuffer.append(str.charAt(i));
            }
        }        
        return strBuffer.toString();
    }
    public static WishListItem gameExists(String gameName){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Criteria crit = session.createCriteria(WishListItem.class);
        Criterion cond1 = Restrictions.eq("title", gameName);
        crit.add(Restrictions.and(cond1));
        return (WishListItem) crit.uniqueResult();
    }
    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read); 

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(searchServ.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(searchServ.class.getName()).log(Level.SEVERE, null, ex);
        }
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
