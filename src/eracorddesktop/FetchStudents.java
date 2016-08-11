/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eracorddesktop;

import javax.json.JsonObject;
import javax.json.JsonArray;

public class FetchStudents {

    jdbcConnection jdbcCon = new jdbcConnection();

    String fetch(String auth_token) {
        String date = "";
        try {
            JsonObject jsonObj = new httpConnection().doGet("/students/sync_organisation_students.json", auth_token);
            if (jsonObj.getBoolean("success")) {
                JsonArray getArray = jsonObj.getJsonArray("students");
                store_student(getArray);
                date = jdbcCon.last_fetch_date();

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return date;
    }

    void store_student(JsonArray studentJson) {
        for (int i = 0; i < studentJson.size(); i++) {
            JsonObject jsonObj = studentJson.getJsonObject(i);
            int id = jsonObj.getInt("id");
            String first_name = jsonObj.getString("first_name");
            String last_name = jsonObj.getString("last_name");
            String middle_name = jsonObj.getString("middle_name");

            jdbcCon.createStudents(id, first_name, last_name, middle_name);
        }
        // created new fetch record while fetching student 
        jdbcCon.create_last_fetch(1);
    }

}
