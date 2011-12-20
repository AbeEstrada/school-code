package com.banuane.models;
import com.banuane.config.DB;

import java.io.*;
import java.util.*;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Transactions {    
    public boolean save(Integer client, Integer type, Integer amount) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB.dburl, DB.dbuser, DB.dbpass);
            
            PreparedStatement pst = null;
            pst = con.prepareStatement("INSERT INTO `transactions` (`client`, `type`, `amount`, `date`) VALUES(?, ?, ?, NOW())", Statement.RETURN_GENERATED_KEYS);
            
            pst.setInt(1, client);
            pst.setInt(2, type);
            pst.setInt(3, amount);
            
            Integer transaction = 0;
            transaction = pst.executeUpdate(); 
            
            con.close();
            
            if (transaction > 0) {
                return true;
            } else {
                return false;
            }

        } catch (ClassNotFoundException e) {
            System.err.println(">>> Exception: "+e.getMessage());
            return false;

        } catch(SQLException e) {
            System.err.println(">>> SQLException: "+e.getMessage());
            return false;
        }
    }
    
    public Map get(Integer type) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB.dburl, DB.dbuser, DB.dbpass);
            
            PreparedStatement pst = null;
            pst = con.prepareStatement("SELECT * FROM `transaction_types` WHERE `id`="+type);
            
            ResultSet rs = null;
            rs = pst.executeQuery();
            
            Map transactions = new HashMap();
            if (rs != null) {
                while (rs.next()) {
                    transactions.put("type", rs.getInt(1));
                    transactions.put("name", rs.getString(2));
                    transactions.put("action", rs.getInt(3));
                }
            }
            
            return transactions;
                        
        } catch (ClassNotFoundException e) {
            System.err.println(">>> Exception: "+e.getMessage());
            return null;

        } catch(SQLException e) {
            System.err.println(">>> SQLException: "+e.getMessage());
            return null;
        }
    }
}