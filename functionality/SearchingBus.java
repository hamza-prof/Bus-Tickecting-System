/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package functionality;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class SearchingBus {

    private String pickUp;
    private String drop;
    private String time;
    private String category;
    private Connection conn;

    public SearchingBus(String pickUp, String drop, String time, String category) {
        this.pickUp = pickUp;
        this.drop = drop;
        this.time = time;
        this.category = category;
        conn = ProvideConnection.getConnection();
    }

    public SearchingBus() {
        conn = ProvideConnection.getConnection();
    }

    public String getPickUp() {
        return pickUp;
    }

    public void setPickUp(String pickUp) throws SQLException {

    }

    public String getDrop() {
        return drop;
    }

    public void setDrop(String drop) {
        this.drop = drop;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String[] getBusData() throws SQLException {

        String[] busData = new String[5];
        ResultSet rs;
        String query;
        //System.out.println(pickUp + drop + time + category);
        query = "SELECT Bus_number,Bus_type,Available_seats,pickup_point,Destination,Departure_time,Arrival_time,seat_price "
                + "FROM bus_info WHERE pickup_point=? AND Destination=? AND Bus_type=? AND day_time=?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, pickUp);
        pstmt.setString(2, drop);
        pstmt.setString(3, category);
        pstmt.setString(4, time);
        pstmt.execute();
        rs = pstmt.executeQuery();

        while (rs.next()) {
            if (rs.getString("Bus_number") == null) {
                System.out.println("Nothing");
                return null;
            } else {
                System.out.println("Yes we have bus");
            }
            busData[0] = rs.getString("Bus_number");
            busData[1] = rs.getString("Available_seats");
            busData[2] = rs.getString("Departure_time");
            busData[3] = rs.getString("Arrival_time");
            busData[4] = rs.getString("seat_price");
        }
//        for (String val : busData) {
//            System.out.println(val);
//        }
//        rs.close();
//        conn.close();
        return busData;
    }

    public String hours(String dtime, String atime) {
        String str1 = new String(), str2 = new String();
        char am_pm1, am_pm2;
        int diff_am_pm = 0;
        am_pm1 = dtime.charAt(dtime.length() - 2);
        am_pm2 = atime.charAt(dtime.length() - 2);
        if (am_pm1 != am_pm2) {
            diff_am_pm = 12;
        }

        for (int i = 0; i < dtime.length(); i++) {
            if (dtime.charAt(i) != ':') {
                str1 += dtime.charAt(i);
            } else if (dtime.charAt(i) == ':') {
                break;
            }
        }
        for (int i = 0; i < atime.length(); i++) {
            if (atime.charAt(i) != ':') {
                str2 += atime.charAt(i);
            } else if (atime.charAt(i) == ':') {
                break;
            }
        }
        return String.valueOf(Integer.parseInt(str1) - Integer.parseInt(str2) + diff_am_pm);
    }

    public HashMap<String, String[]> getSeat(String bus_id, String tdate) throws SQLException {
        HashMap<String, String[]> seats = new HashMap();
        String query = "SELECT s.seat_id,s.passenger_id, p.travel_date,p.gender FROM seat_info s LEFT JOIN passenger_info p USING (passenger_id) "
                + "WHERE s.Bus_Number=? AND p.travel_date=?";
        PreparedStatement st = conn.prepareStatement(query);
        st.setString(1, bus_id);
        st.setString(2, tdate);
        ResultSet rs = st.executeQuery();
//        if (!rs.next()) {
//            System.out.println("Good");
//            return null;
//        }
        int i = 0;
        while (rs.next()) {
            System.out.println("Good");
            System.out.println(rs.getString("Seat_id"));
            String[] str = {String.valueOf(rs.getInt("Passenger_id")), rs.getString("travel_date"), rs.getString("gender")};
            seats.put(rs.getString("Seat_id"), str);
            System.out.println(seats.get(rs.getString("Seat_id")));
            i++;
        }
        return seats;
    }

    public static void main(String[] args) throws SQLException {
        SearchingBus sb = new SearchingBus("Rawalpindi", "Khairpur", "Evening", "Economy");
        sb.getBusData();
        HashMap<String, String[]> seatMap = sb.getSeat("AG231", "2022-11-03");
        for (int i = 0; i < seatMap.size(); i++) {
            System.out.println(seatMap.get("S01"));

        }
    }

    public void inputPassengerData(String[] data) throws SQLException {
        String insert = "INSERT INTO Passenger_Info (Passenger_id, cnic, first_name, last_name, contact, "
                + "address, gender, age, Bus_number,travel_date) values (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement psmt = conn.prepareStatement(insert);
        psmt.setInt(1, Integer.parseInt(data[0]));
        psmt.setString(2, data[1]);
        psmt.setString(3, data[2]);
        psmt.setString(4, data[3]);
        psmt.setString(5, data[4]);
        psmt.setString(6, data[5]);
        psmt.setString(7, data[6]);
        psmt.setInt(8, Integer.parseInt(data[7]));
        psmt.setString(9, data[8]);
        psmt.setString(10, data[9]);
        psmt.executeUpdate();
        psmt.close();
    }

    public void inputTicketdata(String[] ticket_data) throws SQLException {
        String insert_ticket = "INSERT INTO ticket_info (ticket_id, board, drop_point, total_tickets, ticket_price,final_price,"
                + " bus_number, passenger_id, ticket_date) values (?,?,?,?,?,?,?,?,?)";
        PreparedStatement psmt = conn.prepareStatement(insert_ticket);
        psmt.setInt(1, Integer.parseInt(ticket_data[0]));
        psmt.setString(2, ticket_data[1]);
        psmt.setString(3, ticket_data[2]);
        psmt.setInt(4, Integer.parseInt(ticket_data[3]));
        psmt.setInt(5, Integer.parseInt(ticket_data[4]));
        psmt.setInt(6, Integer.parseInt(ticket_data[5]));
        psmt.setString(7, ticket_data[6]);
        psmt.setInt(8, Integer.parseInt(ticket_data[7]));
        psmt.setString(9, ticket_data[8]);
        psmt.executeUpdate();
        psmt.close();
    }

    public void inputSeatInfo(ArrayList<String> seat_info,String bus_number,int passenger_id) throws SQLException {
        String update_seatInfo = "UPDATE seat_info SET passenger_id=? WHERE seat_id=? AND bus_number=?";

        for (String seat_id : seat_info) {
            PreparedStatement psmt = conn.prepareStatement(update_seatInfo);
            psmt.setInt(1, passenger_id);
            psmt.setString(2, seat_id);
            psmt.setString(3, bus_number);
            psmt.executeUpdate();
            psmt.close();
        }
    }
}
