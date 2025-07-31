/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Junaid Mansuri
 */
public class connectionprovider {
    public static Connection getCon(){
    try{
        
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection con=DriverManager.getConnection("jdbc:mysql://localhost/inventory","root","root");
    return con;
    }
    catch(Exception e)
    {
   System.out.print(e);
    return null;
    }
   
    
        
}
}
