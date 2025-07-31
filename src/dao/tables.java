/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Junaid Mansuri
 */
public class tables {

    public static void main(String[] args) {

        Connection con = null;
        Statement st = null;

        try {
            con = connectionprovider.getCon();
            st = con.createStatement();
            // st.executeUpdate("create table appuser(appuser_pk int AUTO_INCREMENT primary key,userRole varchar(50),name varchar(200),mobileNumber varchar(50),email varchar(200),password varchar(50),address varchar(200),status varchar(50))");
            //st.executeUpdate("insert into appuser(userRole,name,mobileNumber,email,password,address,status) values('superadmin','junaid1','12345','superadmin@testmail.com','admin','India','Active')");
            // st.executeUpdate("create table category(category_pk int AUTO_INCREMENT primary key,name varchar(200))");
            //JOptionPane.showMessageDialog(null, "Table created sucessfully !");
            //   st.executeUpdate("create table product(product_pk int AUTO_INCREMENT primary key,name varchar(200),quantity int,price int,description varchar(200),category_fk int )");
           // st.executeUpdate("create table customer(customer_pk int AUTO_INCREMENT primary key,name varchar(200),mobilenumber varchar(50),email varchar(200))");
           /*st.executeUpdate("CREATE TABLE orderDetails (" +
                 "order_pk INT AUTO_INCREMENT PRIMARY KEY, " +
                 "orderid VARCHAR(200), " +
                 "customer_fk INT, " +
                 "orderdate VARCHAR(200), " +
                 "totalpaid INT)");
            */
            JOptionPane.showMessageDialog(null, "Table created sucessfully !");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        } finally {

            try {

                con.close();

            } catch (Exception e) {

            }
        }

    }
}
