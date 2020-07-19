import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Scanner;

public class Weather {
    public static void main(String [] args) {

        final String API_key = "364cecae259c437fb96105745203006";
        String URL = "http://api.weatherapi.com/v1/current.json?key=364cecae259c437fb96105745203006&q";

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Your City:");
        String city = sc.next();

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(URL + "=" + city).build();

        try {
            Response response = client.newCall(request).execute();
            String data = response.body().string();

            JSONObject jsonObject = new JSONObject(data);
            JSONObject location = jsonObject.getJSONObject("location");
            JSONObject current = jsonObject.getJSONObject("current");
            JSONObject condition = current.getJSONObject("condition");

            String name = location.getString("name");
            String state = location.getString("region");
            String country = location.getString("country");

            double temp_c = current.getDouble("temp_c");
            double humidity = current.getDouble("humidity");
            double precip_in = current.getDouble("precip_in");
            double cloud = current.getDouble("cloud");
            double feelslike_c = current.getDouble("feelslike_c");

            String condition_t = condition.getString("text");

            System.out.println(name + "  " + country + "  " + state);
            System.out.println("Temp. in C: " + temp_c);
            System.out.println("Humidity: " + humidity);
            System.out.println("Cloud: " + cloud);
            System.out.println("Precipitation: " + precip_in + "in.");
            System.out.println();
            System.out.println("Feels like: " + feelslike_c + " C");
            System.out.println(condition_t);

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}
