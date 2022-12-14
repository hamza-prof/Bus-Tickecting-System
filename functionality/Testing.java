/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package functionality;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import GUI.SearchBar;

/**
 *
 * @author Lenovo
 */
public class Testing {

    public static void main(String[] args) throws SQLException {
        SearchBar sb=new SearchBar();
        String pickUp="Rawalpindi";
        String drop="Khairpur";
        String time="Economy";
        String category="Evening";
        String[] busData = new String[5];
        Connection conn = ProvideConnection.getConnection();
        String query = "SELECT Bus_number,Bus_type,Available_seats,pickup_point,Destination,Departure_time,Arrival_time,seat_price "
                + "FROM bus_info WHERE pickup_point='"+pickUp +"' AND Destination='"+ drop+"' AND Bus_type='"+category+"' AND day_time='"+time +"';";
        PreparedStatement pstmt=conn.prepareStatement(query);
//        pstmt.setString(1, "Rawalpindi");
//        pstmt.setString(2, "Khairpur");
//        pstmt.setString(3, "Economy");
//        pstmt.setString(4, "Evening");
        pstmt.executeQuery();
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            if (rs.getString("Bus_number") == null) {
                return;
            }
            busData[0] = rs.getString("Bus_number");
            busData[1] = rs.getString("Available_seats");
            busData[2] = rs.getString("Departure_time");
            busData[3] = rs.getString("Arrival_time");
            busData[4] = rs.getString("seat_price");
              
        }
        for(String val:busData){
            System.out.println(val);
        }
    }

}
