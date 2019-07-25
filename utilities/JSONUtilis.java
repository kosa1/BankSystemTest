package utilities;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class JSONUtilis {

    public static Map getCustomerJsonMap(String fileName) {
        JSONParser jsonParser = new JSONParser();

        try {
            // loading full JSON file into JSONObject
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(fileName));
            Log.info("Reading data from JSON file");
            // System.out.println("Full: "+ jsonObject);
            // creating JSONArray for customer attribute
            JSONArray array = (JSONArray) jsonObject.get("customer");


            // List that store Customers
            List<HashMap> list = new ArrayList<HashMap>();

            //Map that store particular customer

            // printing customer data
            for (Object a: array) {
                JSONObject object = (JSONObject) a;
                String name = (String) object.get("name");
                String gender = (String) object.get("gender");
                String date = (String) object.get("date");
                String address = (String) object.get("address");
                String city = (String) object.get("city");
                String state = (String) object.get("state");
                String pin = (String) object.get("pin");
                String number = (String) object.get("number");
                String email = (String) object.get("email");
                String password = (String) object.get("password");
                Map<String,String> map = new HashMap<String, String>();
                map.put("name",name);
                map.put("gender",gender);
                map.put("date",date);
                map.put("address",address);
                map.put("city",city);
                map.put("state",state);
                map.put("pin",pin);
                map.put("number",number);
                map.put("email",email);
                map.put("password",password);
                list.add((HashMap) map);


//                System.out.println("Customer:\n ");
//                System.out.println(name);
//                System.out.println(gender);
//                System.out.println(date);
//                System.out.println(address);
//                System.out.println(city);
//                System.out.println(state);
//                System.out.println(pin);
//                System.out.println(number);
//                System.out.println(email);
//                System.out.println(password);

            }
            // returning Map Object
            return list.get(0);

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
