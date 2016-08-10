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

    void createRecords(Hashtable hs, String table) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/eracord_development", "root", "root");

//here sonoo is database name, root is username and password  
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select first_name from students");

            while (rs.next()) {
                System.out.println(rs.getString(1));
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void getRecords(String rows, String table ) {
        
    }

    public static void main(String args[]) {
    }
}
