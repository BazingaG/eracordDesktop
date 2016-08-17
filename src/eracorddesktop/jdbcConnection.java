/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eracorddesktop;

import java.sql.*;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.json.JsonValue;
import java.text.SimpleDateFormat;

/**
 *
 * @author lenovo
 */
public class jdbcConnection {

    void createStudents(int id, String f_name, String l_name, String m_name) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/eracord_development", "root", "root");
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
            con.close();

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
    
    void get_atendance_records(Date date, int student_id) {
    }

    public static void main(String args[]) {
    }
}
