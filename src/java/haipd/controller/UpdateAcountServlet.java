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
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author haiph
 */
@WebServlet(name = "UpdateAcountServlet", urlPatterns = {"/UpdateAcountServlet"})
public class UpdateAcountServlet extends HttpServlet {
    private final String ERROR_PAGE = "error.html";
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
        
//        String searchValue = request.getParameter("lastSearchValue");
        String username = request.getParameter("txtUsername");

        String password = request.getParameter("txtPassword");
        String role = request.getParameter("chkRole");
        ServletContext context = this.getServletContext();
        Properties siteMap = (Properties) context.getAttribute("SITEMAP");
        boolean isAdmin = false;
        if (role != null) {
            isAdmin = true;
        }
        String searchValue = request.getParameter("lastSearchValue");
        String url = siteMap.getProperty(
                MyApplicationConstant.UpdateAccountFeatures.ERROR_PAGE);
        try {
            LoginDAO dao = new LoginDAO();
            boolean result = dao.updateAccount(username, password, isAdmin);
            if (result) {
                url = "processSearchLastname"
                        + "?btAction=Search"
                        + "&txtSearchValue=" + searchValue;
            }

        } catch (SQLException ex) {
            log("LoginSevrlet naming" + ex.getMessage());
        } catch (NamingException ex) {
            log("LoginSevrlet SQL" + ex.getMessage());
        } finally {
            response.sendRedirect(url);
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
