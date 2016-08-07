/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eracorddesktop;
import java.util.Enumeration;
import java.util.Hashtable;
/**
 *
 * @author lenovo
 */
public class HashToString {
    
     String getString(Hashtable hashTable) {
        String hashString = "";
        Enumeration keys;
        String name;
        keys = hashTable.keys();
        while(keys.hasMoreElements()) {
            name = (String) keys.nextElement();
            hashString = hashString + "\"" + name + "\":\"" + hashTable.get(name)+ "\"";
            if(keys.hasMoreElements()){
                hashString = hashString + ",";
            }
        }
        return "{" + hashString + "}";
    }
    
}
