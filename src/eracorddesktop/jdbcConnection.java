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

    public void getRecords(String rows, String table) {

    }

    public static void main(String args[]) {
    }
}
