/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package functionality;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import GUI.*;

/**
 *
 * @author Lenovo
 */
public class ProvideConnection {

    public static Connection getConnection() {
        Connection con = null;
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ticket_booking", "root", "hamzaqureshi2937#");
        } catch (Exception e) {
            System.out.println(e);
            //JOptionPane.showMessageDialog(null,"System Error");
        }
        return con;
    }
//    public static PreparedStatement excutingQuery(Connection conn,String query) throws SQLException{
//        PreparedStatement pstmt=conn.prepareStatement(query);
//        return pstmt;
//    }

   public static void main(String [] args){
       if(ProvideConnection.getConnection()!=null){
           System.out.println("Connected");
       }
   }

}
