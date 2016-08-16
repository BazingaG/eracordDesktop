/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eracorddesktop;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author lenovo
 */
public class pushAttendance extends Thread {

    public void run() {
        while(true){
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
        
    }
    public static void main(String[] args) {
        pushAttendance pa = new pushAttendance();
        pa.setName("pushattendanceThread");
        pa.start();
    }
}
