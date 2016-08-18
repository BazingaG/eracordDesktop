/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eracorddesktop;

import java.sql.*;
import java.text.SimpleDateFormat;
import javax.json.Json;
import javax.json.JsonArrayBuilder;

/**
 *
 * @author lenovo
 */
public class jdbcConnection {

    void createStudents(int id, String f_name, String l_name, String m_name) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/eracord_development", "root", "root")) {
                Statement stmt = con.createStatement();
                int count = 0;
                String check_query = "select id from students where id = " + id;
                ResultSet rs = stmt.executeQuery(check_query);
                while (rs.next()) {
                    //count = Integer.parseInt(rs.getString("count(*)"));
                    count++;
                }
                if (count == 0) {
                    String query = "insert into students (id,first_name, last_name, middle_name) values(" + id + ",'" + f_name + "','" + l_name + "','" + m_name + "' );";
                    System.out.println(query);
                    stmt.executeUpdate(query);
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void create_last_fetch(int organisation_id) {
        //create new record after server students fetch
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/eracord_development", "root", "root");
            Statement stmt = con.createStatement();
            String query = "insert into fetch_data (organisation_id) values(" + organisation_id + ");";
            stmt.executeUpdate(query);

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    String last_fetch_date() {
        //get last fetch record timestamp
        String date = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/eracord_development", "root", "root");
            Statement stmt = con.createStatement();
            String query = "select * from fetch_data ORDER BY datetime DESC LIMIT 1";
            ResultSet rs = stmt.executeQuery(query);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy' 'HH:mm:ss");
            while (rs.next()) {
                date = simpleDateFormat.format(rs.getTimestamp("datetime")).toString();
            }
        } catch (Exception e) {
            System.err.println(e);
        }

        return date;
    }

    void get_attendances() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/eracord_development", "root", "root");
            Statement stmt = con.createStatement();
            String query = "select * from attendances";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("student_id");
                System.out.println(id);
            }
            con.close();
            Thread.sleep(100000);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    JsonArrayBuilder get_atendance_records(String sdate, int student_id) {
        String query = "";
        JsonArrayBuilder jr = Json.createArrayBuilder();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/eracord_development", "root", "root");
            Statement stmt = con.createStatement();
            if (("").equals(sdate)) {
                //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy' 'HH:mm:ss");
                query = "select * from attendances";
            } else {
                query = "select * from attendances";
                //query = "select * from attendances where date >= "+ sdate;
            }
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Date date = rs.getDate("date");
                int id = rs.getInt("student_id");
                if (date != null) {
                    jr.add(Json.createObjectBuilder()
                            .add("date", date.toString())
                            .add("student_id", "" + id));
                }
                System.out.println("ID: " + id + " DATE: " + date);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return jr;
    }

    public static void main(String args[]) {
    }
}
