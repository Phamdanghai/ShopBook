/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haipd.book;

import haipd.util.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author haiph
 */
public class BookDAO implements Serializable{
    public List<BookDTO> listBook() throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<BookDTO> list = new  ArrayList<>();
        try {
            con = DBHelper.makeConnection();
            if(con != null){
                String sql = "Select bookID, bookName, price, author "
                        + "From BookShop ";
                
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()){
                    String bookID = rs.getString("bookID");
                    String BookName = rs.getString("bookName");
                    int price = rs.getInt("price");
                    String author = rs.getString("author");
                    BookDTO dto = new BookDTO(bookID, BookName, price, author);
                    
                    list.add(dto);
                }
                return list;
            }
        }finally{
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                rs.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }
    
    
}
