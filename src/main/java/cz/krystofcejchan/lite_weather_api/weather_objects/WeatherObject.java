package cz.krystofcejchan.lite_weather_api.weather_objects;

import cz.krystofcejchan.lite_weather_api.WeatherForeCast;
import cz.krystofcejchan.lite_weather_api.enums.DAY;
import cz.krystofcejchan.lite_weather_api.enums.TIME;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public abstract class WeatherObject<T> extends WeatherForeCast {
    private final DAY[] days;
    private final TIME[] times;
    private final String location;
    private final String jsonUrl;
    private final JSONObject json;


   /* private final CurrentCondition currentCondition;
    private final NearestArea nearestArea;
    private final Request request;
    private final WeatherForecast weather;*/


    public WeatherObject(String location, TIME[] times, DAY... days) throws IOException {
        super(location);
        this.jsonUrl = "https://wttr.in/" + location + "?format=j1";
        this.json = new JSONObject(IOUtils.toString(new URL(jsonUrl), StandardCharsets.UTF_8));
        this.location = super.getLocation();
        this.days = days;
        this.times = times;
      /*  currentCondition = new CurrentCondition(location, times, days);
        nearestArea = new NearestArea(location, times, days);
        request = new Request(location, times, days);
        weather = new WeatherForecast(location, times, days);*/
    }

    public DAY[] getDays() {
        return days;
    }

    public TIME[] getTimes() {
        return times;
    }

    @Override
    public String getLocation() {
        return location;
    }

    /* public CurrentCondition getCurrentCondition() {
         return currentCondition;
     }

     public NearestArea getNearestArea() {
         return nearestArea;
     }

     public Request getRequest() {
         return request;
     }

     public WeatherForecast getWeather() {
         return weather;
     }
 */
    public String getJsonUrl() {
        return jsonUrl;
    }

    public JSONObject getJson() {
        return json;
    }

    @NotNull
    public abstract T getObject() throws IOException;
}
