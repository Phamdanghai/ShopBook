/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haipd.login;

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
public class LoginDAO implements Serializable {

    public LoginDTO checkLogin(String username, String password)
            throws SQLException, /*ClassNotFoundException*/ NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        LoginDTO dto = null;
        try {
            //1. make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. query
                //2.1 create sql string
                String sql = "Select lastname "
                        + "From Login "
                        + "Where username  = ? And password = ?";
                //2.2 create statement object and set parameters
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //2.3 execute query
                rs = stm.executeQuery();
                //2.4 process data
                if (rs.next()) {
                    String fullname = rs.getString("lastname");
                    dto = new LoginDTO(username, password, fullname);
                }

            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return dto;
    }

    private List<LoginDTO> accountList;

    public List<LoginDTO> getAccountList() {
        return accountList;
    }

    public void saerchLastName(String searchValue)
            throws SQLException, /*ClassNotFoundException*/ NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. query
                //2.1 create sql string
                String sql = "Select username, password, lastname, isAdmin "
                        + "From Login "
                        + "Where lastname Like ?";
                //2.2 create statement object and set parameters
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //2.3 execute query
                rs = stm.executeQuery();
                //2.4 process data
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullName = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");

                    LoginDTO dto = new LoginDTO(username, password, fullName, role);
                    if (this.accountList == null) {
                        this.accountList = new ArrayList<>();
                    }//end if account list is not allocated
                    this.accountList.add(dto);
                }//end while
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean deleteAccount(String username)
            throws SQLException, /*ClassNotFoundException*/ NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            //1. make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. query
                //2.1 create sql string
                String sql = "Delete From Login "
                        + "Where username = ?";
                //2.2 create statement object and set parameters
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                //2.3 execute query
                int affectedRows = stm.executeUpdate();
                //2.4 process data
                if (affectedRows > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean updateAccount(String username, String password, boolean isRole)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            //1. Macke connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Query
                //2.1 create sql string
                String sql = "Update Login "
                        + "Set password = ?, isAdmin = ? "
                        + "Where username = ?";
                //2.2 create stament oject and set parameters
                stm = con.prepareStatement(sql);
                
                stm.setString(1,password);
                stm.setBoolean(2,isRole);
                stm.setString(3, username);
                
                //2.3 excute query
                int affectedRows = stm.executeUpdate();
                //2.4 process data 
                if (affectedRows > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;

    }
    
    public boolean createAccount(String username, String password,
            String fullname, boolean role) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            //1. Macke connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Query
                //2.1 create sql string
                String sql = "Insert Into Login("
                        + "username, password, lastname, isAdmin) "
                        + "Values(?, ?, ?, ?)";
                //2.2 create stament oject and set parameters
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                stm.setString(3, fullname);
                stm.setBoolean(4, role);
                //2.3 excute query
                int affectedRows = stm.executeUpdate();
                //2.4 process data 
                if (affectedRows > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
