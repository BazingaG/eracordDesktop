/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eracorddesktop;

import javax.json.JsonObject;
import javax.json.JsonArray;

/**
 *
 * @author lenovo
 */
public class FetchStudents {

    void fetch(String auth_token) {
        try {
            // System.out.println("{\"user\": "+new HashToString().getString(user)+"}");
            JsonObject jsonObj = new httpConnection().doGet("/students/sync_organisation_students.json", auth_token);
            if (jsonObj.getBoolean("success")) {
                JsonArray getArray = jsonObj.getJsonArray("students");

                store_student(getArray);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void store_student(JsonArray studentJson) {
        for (int i = 0; i < studentJson.size(); i++) {
            //JSONObject objects = studentJson.getJSONArray(i);
            //Iterate through the elements of the array i.
            //Get thier value.
            //Get the value for the first element and the value for the last element.
            JsonObject jsonObj = studentJson.getJsonObject(i);
            int id = jsonObj.getInt("id");
            String first_name = jsonObj.getString("first_name");
            String last_name = jsonObj.getString("last_name");
            String middle_name = jsonObj.getString("middle_name");
            
            new jdbcConnection().createStudents(id, first_name, last_name, middle_name);
            
        }
    }

}
