/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haipd.controller;

import haipd.login.LoginDAO;
import haipd.login.LoginDTO;
import haipd.util.MyApplicationConstant;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 5k nhe
 */
public class LoginServlet extends HttpServlet {

//    private final String INVALID_PAGE = "invalid.html";
//    private final String SEARCH_PAGE = "search.jsp";
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
        PrintWriter out = response.getWriter();

        ServletContext context = this.getServletContext();
        Properties siteMap = (Properties) context.getAttribute("SITEMAP");
        //String button = request.getParameter("btAction");
        String username = request.getParameter("txtusername");
        String password = request.getParameter("txtpassword");
        String url = siteMap.getProperty(MyApplicationConstant.LoginFeatures.INVALID_PAGE);
        try {
            //if (button.equals("login")) {
            //Call DAO
            LoginDAO dao = new LoginDAO();
            LoginDTO check = dao.checkLogin(username, password);

            if (check != null) {
                
                url = siteMap.getProperty(MyApplicationConstant.LoginFeatures.SEARCH_PAGE);
                HttpSession session = request.getSession();
                //imlement DAO method to get lastname from DB
                session.setAttribute("USERNAME", check);
                session.setAttribute("USER", username);
                
                Cookie cookie = new Cookie(username, password);
                cookie.setMaxAge(60 * 5);
                response.addCookie(cookie);
            }//end if result
            //}//end if login is clicked
        } catch (NamingException ex) {
            log("LoginSevrlet naming" + ex.getMessage());
        } catch (SQLException ex) {
            log("LoginSevrlet SQL" + ex.getMessage());
        } finally {
            //response.sendRedirect(url);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
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
