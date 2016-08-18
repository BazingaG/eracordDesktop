/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eracorddesktop;
//package okhttp3.guide;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.json.*;

public class httpConnection {

    String serverURL;

    public httpConnection() throws Exception {
        serverURL = "http://192.168.0.100:3000";
    }

    public JsonObject doPost(String reqUrl, String formVal, String params) throws Exception {
        HttpURLConnection connection = null;
        URL url;
        JsonObject jsonobject = null;
        try {
            //Create connection
            url = new URL(serverURL + reqUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("custom-Header", "XYZ");
            connection.setRequestProperty("Content-Language", "en-US");
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            StringBuffer requestParams = new StringBuffer();
            requestParams.append(formVal);

            //Send request
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(requestParams.toString());
            wr.flush();
            wr.close();
            //Get Response    
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                response.append(line);
                System.out.println("eracord_desktop.eraConnection.<init>()");
                response.append('\r');
            }
            //convert json string to json object
            JsonReader jsonReader = Json.createReader(new StringReader(response.toString()));
            jsonobject = jsonReader.readObject();
            rd.close();
        } catch (Exception e) {
            System.out.println("Error!");
            e.printStackTrace();
            //return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return jsonobject;
    }

    public void pushAttendance(String token, String formVal) {
        HttpURLConnection connection = null;
        URL url;
        JsonObject jsonobject = null;
        try {
            url = new URL(serverURL +"/organisation/add_attendances");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", token);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Content-Language", "en-US");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            StringBuffer requestParams = new StringBuffer();
            requestParams.append(formVal);
            //Send request
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(requestParams.toString());
            wr.flush();
            wr.close();
            //Get Response    
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                response.append(line);
                System.out.println("eracord_desktop.eraConnection.<init>()");
                response.append('\r');
            }
            //convert json string to json object
            JsonReader jsonReader = Json.createReader(new StringReader(response.toString()));
            jsonobject = jsonReader.readObject();
            rd.close();
           
        } catch (Exception e) {
            System.err.println("pushAttendance Error..." + e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public JsonObject doGet(String reqUrl, String token) throws Exception {
        HttpURLConnection connection = null;
        URL url;
        JsonObject jsonobject = null;
        try {
            //Create connection
            url = new URL(serverURL + reqUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            //connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", token);

            //Get Response    
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                response.append(line);
                System.out.println("eracord_desktop.eraConnection.<init>()");
                response.append('\r');
            }
            //convert json string to json object
            JsonReader jsonReader = Json.createReader(new StringReader(response.toString()));
            jsonobject = jsonReader.readObject();
            rd.close();
        } catch (Exception e) {
            System.out.println("Error!");
            e.printStackTrace();
            //return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return jsonobject;
    }

    public static void main(String[] args) throws Exception {

    }

    JsonObject doPost(String studentssync_organisation_studentsjson, String auth_token) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
