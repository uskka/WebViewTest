    
package webview;

import java.io.FileNotFoundException;
import java.io.FileReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Array;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonRead {

    
    public static void JsonRead() { // file read
        
        JSONParser parser = new JSONParser();
        try
        {
            Object object = parser.parse(new FileReader("/Users/stefanmac/Documents/json.json"));
            
            //convert Object to JSONObject
            JSONObject jsonObject = (JSONObject)object;
            
            //Reading the String
//            String name = (String) jsonObject.get("Name");
//            Long age = (Long) jsonObject.get("Age");
            
            //Reading the array
            JSONArray countries = (JSONArray)jsonObject.get("1.0"); //array i nazwa
            
            
            //Printing all the values
          
            System.out.println("Array:");
            
            for(int a=0;a<countries.size();a++){
            
             System.out.println(countries.get(a)); //sciaganie jednego stringa z indexu 1
            }
           
           
            
//            for(Object country : countries)
//            {
//                System.out.println(country.toString());
//            }
        }
        catch(FileNotFoundException fe)
        {
            fe.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }
    
}
