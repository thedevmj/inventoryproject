
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Junaid Mansuri
 */
public class connectionprovider {
   
    public static Connection getCon(){
        
    try{
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection con=DriverManager.getConnection("jdbc:mysql://localhost/noteappdata","root","root");
    return con;
    }
    catch(Exception e){
    JOptionPane.showMessageDialog(null, e);
    return null;
    }
    }
    
    
}

