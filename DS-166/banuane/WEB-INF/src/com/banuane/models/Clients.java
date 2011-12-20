package com.banuane.models;
import com.banuane.config.DB;

import java.io.*;
import java.util.*;
import java.text.*;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Clients {
    public Integer add(Map client) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB.dburl, DB.dbuser, DB.dbpass);
            
            PreparedStatement pst = null;
            pst = con.prepareStatement("INSERT INTO `clients` (`first_name`, `last_name`, `telephone`, `address`, `email`, `password`, `birthday`, `balance`, `created`) VALUES(?, ?, ?, ?, ?, ?, ?, ?, NOW())", Statement.RETURN_GENERATED_KEYS);
            
            /*Iterator entries = client.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry entry = (Map.Entry) entries.next();
                String key = (String)entry.getKey();
                String value = (String)entry.getValue();
                System.out.println("Key = " + key + ", Value = " + value);
            }*/
            
            //System.out.println("Client Name: "+client.get("first_name"));
            
            pst.setString(1, client.get("first_name").toString());
            pst.setString(2, client.get("last_name").toString());
            pst.setString(3, client.get("telephone").toString());
            pst.setString(4, client.get("address").toString());
            pst.setString(5, client.get("email").toString());
            pst.setString(6, client.get("password").toString());
            
            //Calendar _birthday = new GregorianCalendar(Integer.parseInt(_b_year), Integer.parseInt(_b_month), Integer.parseInt(_b_day));
            pst.setString(7, client.get("b_year")+"-"+client.get("b_month")+"-"+client.get("b_day"));
            
            pst.setInt(8, Integer.parseInt(client.get("balance").toString()));
            
            Integer newClient = pst.executeUpdate();
            
            Integer clientId = 0;
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()){
                clientId = rs.getInt(1);
            }
            
            con.close();
            
            return clientId;

        } catch (ClassNotFoundException e) {
            System.err.println(">>> Exception: "+e.getMessage());
            return 0;

        } catch(SQLException e) {
            System.err.println(">>> SQLException: "+e.getMessage());
            return 0;
        }
    }
    
    public boolean update(Integer account, Map data) {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB.dburl, DB.dbuser, DB.dbpass);
            con.setAutoCommit(false);
            
            PreparedStatement pst = null;
            
            Iterator entries = data.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry entry = (Map.Entry)entries.next();
                String key = (String)entry.getKey();
                String value = (String)entry.getValue();
                
                pst = con.prepareStatement("UPDATE `clients` SET `"+key+"`=? WHERE `id`="+account);
                try {
                    pst.setInt(1, Integer.parseInt(value));
                } catch(Exception e) {  
                     pst.setString(1, value);
                }
                pst.addBatch();
            }
            int counts[] = pst.executeBatch();
            con.commit();
            
            return true;
            
        } catch (BatchUpdateException e) {
            try {
                con.rollback();
                System.err.println(">>> BatchSQLException: "+e.getMessage());
            } catch (Exception e2) {
                System.err.println(">>> BatchException: "+e2.getMessage());
            }
            return false;
            
        } catch (ClassNotFoundException e) {
            System.err.println(">>> Exception: "+e.getMessage());
            return false;

        } catch(SQLException e) {
            System.err.println(">>> SQLException: "+e.getMessage());
            System.err.println(">>> SQLState: "+e.getSQLState());
            return false;
        }
    }
    
    public Map get(Integer account) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB.dburl, DB.dbuser, DB.dbpass);
            
            PreparedStatement pst = null;
            pst = con.prepareStatement("SELECT * FROM `clients` WHERE `id`="+account);
            
            ResultSet rs = null;
            rs = pst.executeQuery();
            
            Map client = new HashMap();
            if (rs != null) {
                while (rs.next()) {
                    client.put("account", rs.getInt(1));
                    client.put("first_name", rs.getString(2));
                    client.put("last_name", rs.getString(3));
                    client.put("balance", rs.getInt(9));
                }
            }
            
            return client;
                        
        } catch (ClassNotFoundException e) {
            System.err.println(">>> Exception: "+e.getMessage());
            return null;

        } catch(SQLException e) {
            System.err.println(">>> SQLException: "+e.getMessage());
            return null;
        }
    }
    
    public ArrayList transactions(Integer account) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB.dburl, DB.dbuser, DB.dbpass);
            
            PreparedStatement pst = null;
            pst = con.prepareStatement("SELECT types.name, types.action, t.amount, t.date FROM `transactions` AS `t`, `transaction_types` AS `types` WHERE t.type=types.id AND t.client="+account+" ORDER BY `date` DESC");
            
            ResultSet rs = null;
            rs = pst.executeQuery();
            
            ArrayList<Map> transactions = new ArrayList<Map>();
            if (rs != null) {
                while (rs.next()) {
                    Map t = new HashMap();
                    t.put("name", rs.getString(1));
                    t.put("action", rs.getString(2));
                    t.put("amount", rs.getString(3));
                    
                    java.util.Date date = rs.getDate(4);
                    SimpleDateFormat _date_format = new SimpleDateFormat("dd/MM/yyyy");
                    String _date = new StringBuilder(_date_format.format(date)).toString();
                    t.put("date", _date);
                    
                    java.util.Date time = rs.getTime(4);
                    SimpleDateFormat _time_format = new SimpleDateFormat("HH:mm:ss");
                    String _time = new StringBuilder(_time_format.format(time)).toString();
                    t.put("time", _time);
                    
                    transactions.add(t);
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
    
    
    public boolean login(Integer account, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB.dburl, DB.dbuser, DB.dbpass);
            
            PreparedStatement pst = null;
            pst = con.prepareStatement("SELECT * FROM `clients` WHERE `id`="+account+" AND `password`='"+password+"'");
            
            ResultSet rs = null;
            rs = pst.executeQuery();
            
            boolean isClient = false;
            if (rs != null) {
                while (rs.next()) {
                    if (rs.getInt(1) == account) {
                        isClient = true;
                    }
                }
            }
            
            return isClient;
                        
        } catch (ClassNotFoundException e) {
            System.err.println(">>> Exception: "+e.getMessage());
            return false;

        } catch(SQLException e) {
            System.err.println(">>> SQLException: "+e.getMessage());
            return false;
        }
    }
}