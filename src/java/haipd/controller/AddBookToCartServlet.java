/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haipd.controller;

import haipd.cart.CartObject;
import haipd.util.MyApplicationConstant.AddBookToCartFeatures;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author haiph
 */
@WebServlet(name = "AddBookToCartServlet", urlPatterns = {"/AddBookToCartServlet"})
public class AddBookToCartServlet extends HttpServlet {
//    private final String SHOPPING_PAGE = "shopping.html";
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
        Properties siteMap = (Properties)context.getAttribute("SITEMAP");
        String url = siteMap.getProperty(AddBookToCartFeatures.SHOPPING_PAGE);
        try  {
            //1. cust goes to cart place
            HttpSession session = request.getSession(true);
            //2.cust takes his/her cart
            CartObject cart = (CartObject)session.getAttribute("CART");
            if(cart == null){
                cart = new CartObject();
            }//end if cart is not existed
            //3. take item
            String item = request.getParameter("txtNameBook");
            //4. Drop item to cart
            cart.addIteamToCart(item);
            //5. Update scope
            session.setAttribute("CART", cart);
            //6. Go to shopping
        }finally{
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
