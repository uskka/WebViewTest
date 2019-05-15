    


import java.io.FileNotFoundException;
import java.io.FileReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonHendler {
    
    public static int NuberOfSections(String book) throws FileNotFoundException, ParseException{
    JSONParser parser = new JSONParser();

    switch(book){
        case"Biology": 
    {
        try {
            Object object = parser.parse(new FileReader("/Users/stefanmac/Documents/json.json"));
            JSONObject jsonObject = (JSONObject)object;
            int size = jsonObject.size()/2;
            return size;
            
            
        } catch (IOException ex) {
            Logger.getLogger(JsonHendler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        break;        
        
    }
        
    return 0;
    }
    
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
    
        public static void Json() { // file read
        
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
