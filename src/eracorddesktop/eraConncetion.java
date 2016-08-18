/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eracorddesktop;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import javax.json.*;
/*
 * @author lenovo
 */
public class eraConncetion {
    AdminDesk a1 = null;
    public void eraConnection(String username, String password) throws Exception{
        String targetURL = "http://192.168.0.100:3000/users/sign_in";
        URL url;
        HttpURLConnection connection = null;  
        try {
            //Create connection
            url = new URL(targetURL);
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("custom-Header", "XYZ");
            //connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");  
            connection.setUseCaches (false);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            StringBuffer requestParams = new StringBuffer();
            requestParams.append("{\"user\": {\"email\": \"");
            requestParams.append(username);
            requestParams.append("\",\"password\": \"");
            requestParams.append(URLEncoder.encode(password, "UTF-8"));
            requestParams.append("\"}}");
            //Send request
            DataOutputStream wr = new DataOutputStream ( connection.getOutputStream ());
            wr.writeBytes(requestParams.toString());
            wr.flush ();
            wr.close ();
            //Get Response    
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer(); 
            while((line = rd.readLine()) != null) {
              response.append(line);
              System.out.println("eracord_desktop.eraConnection.<init>()");
              response.append('\r');
            }
            //convert json string to json object
            JsonReader jsonReader = Json.createReader(new StringReader(response.toString()));
            JsonObject jsonobject = jsonReader.readObject();
            String email = jsonobject.getString("email");
            String token = jsonobject.getString("token");
            a1 = new AdminDesk();
           // a1.setValue(email, token);
            a1.setVisible(true);
            rd.close();
          } catch (Exception e) {
            System.out.println("Error!");
            e.printStackTrace();
            //return null;   
          } finally {
            if(connection != null) {
              connection.disconnect(); 
            }
          }
    }
    public static void main(String[] args) throws Exception{
        
    }
    
}
