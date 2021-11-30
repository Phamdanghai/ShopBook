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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author haiph
 */
@WebServlet(name = "StarupServlet", urlPatterns = {"/StarupServlet"})
public class StarupServlet extends HttpServlet {
//    private final String LOGIN_PAGE = "login.html";
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

        ServletContext context = this.getServletContext();
        Properties siteMap = (Properties) context.getAttribute("SITEMAP");
        String url = siteMap.getProperty(
                MyApplicationConstant.SatarUpFeatures.LOGIN_PAGE);
        try {
            //1.get cookies from clien of specific domain
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                //2. get last cookies
                Cookie lastCookie = cookies[cookies.length - 1];
                //3. get username and password from last,cookies
                String username = lastCookie.getName();
                String password = lastCookie.getValue();
                //4. call DAO to check Login
                LoginDAO dao = new LoginDAO();
                LoginDTO result = dao.checkLogin(username, password);

                if (result != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("USERNAME", result);
                    url = siteMap.getProperty(
                            MyApplicationConstant.SatarUpFeatures.SEARCH_PAGE);
                }//end if check login is success - user is exised
            }//end if cookies existed
        } catch (SQLException ex) {
            log("LoginSevrlet naming" + ex.getMessage());
        } catch (NamingException ex) {
            log("LoginSevrlet SQL" + ex.getMessage());
        } finally {
//            response.sendRedirect(url);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
