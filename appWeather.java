package a4;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
public class appWeather {
        public static Map<String, Object> toMap(String str){
            Map<String, Object> map = new Gson().fromJson(
                    str, new TypeToken<HashMap<String, Object>>() {}.getType()
            );

            return map;
        }
        public static void main(String[] args){
            String API_KEY = "9fa34357a095730d85afa72286f2fba9";
            String LOCATION = "almaty";
            String urlString = "https://api.openweathermap.org/data/2.5/weather?q=" + LOCATION + "&appid=" + API_KEY;
            try {
                StringBuilder result = new StringBuilder();
                URL url = new URL(urlString);
                URLConnection conn = url.openConnection();
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
                rd.close();
                System.out.println(result);
                Map<String, Object> respMap = toMap(result.toString());
                Map<String, Object> mainMap = toMap(respMap.get("main").toString());
                Map<String, Object> windMap = toMap(respMap.get("wind").toString());

                System.out.println("current temperature: " + mainMap.get("temp"));
                System.out.println("current humidity: " + mainMap.get("humidity"));
                System.out.println("wind speed: " + windMap.get("speed"));
                System.out.println("wind angle: " + windMap.get("deg"));

            } catch (IOException e){
                System.out.println(e.getMessage());

            }
        }

}
