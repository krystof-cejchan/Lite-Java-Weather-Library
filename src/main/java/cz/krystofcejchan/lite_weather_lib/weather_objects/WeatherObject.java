package cz.krystofcejchan.lite_weather_lib.weather_objects;

import cz.krystofcejchan.lite_weather_lib.UtilityClass;
import cz.krystofcejchan.lite_weather_lib.enums_exception.enums.DAY;
import cz.krystofcejchan.lite_weather_lib.enums_exception.enums.TIME;
import cz.krystofcejchan.lite_weather_lib.weather_objects.subparts.current_weather.CurrentCondition;
import cz.krystofcejchan.lite_weather_lib.weather_objects.subparts.forecast.WeatherForecast;
import cz.krystofcejchan.lite_weather_lib.weather_objects.subparts.nearest_area.NearestArea;
import cz.krystofcejchan.lite_weather_lib.weather_objects.subparts.request.Request;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;

/**
 * Universal object from which you can get to more detailed objects {@link Request}, {@link NearestArea}, {@link WeatherForecast}, {@link CurrentCondition}, which lead
 * to another even more detailed objects or class fields
 * @author krystof-cejchan
 * @version 17
 */
public final class WeatherObject {
    private final DAY[] days;
    private final TIME[] times;
    private final String location;
    private final JSONObject json;
    private final String jsonAsText;
    private final CurrentCondition currentCondition;
    private final NearestArea nearestArea;
    private final Request request;
    private final WeatherForecast weatherForecast;

    public WeatherObject(String location, TIME time, DAY... days) throws IOException {
        this(location, new TIME[]{time}, days);
    }

    public WeatherObject(String location, DAY day, TIME... times) throws IOException {
        this(location, times, day);
    }

    public WeatherObject(String location, DAY[] day, TIME... times) throws IOException {
        this(location, times, day);
    }

    public WeatherObject(String location, TIME[] times, DAY... days) throws IOException {
        this.json = UtilityClass.getJson(location);
        this.jsonAsText = UtilityClass.WebPageReader.getTextFromWebpage("https://wttr.in/" + location + "?format=j1");
        this.location = location;
        this.days = days;
        this.times = times;

        this.currentCondition = new CurrentCondition(location);
        this.nearestArea = new NearestArea(location);
        this.request = new Request(location);
        this.weatherForecast = new WeatherForecast(location, times, days);
    }

    public DAY[] getDays() {
        return days;
    }

    public TIME[] getTimes() {
        return times;
    }

    public JSONObject getJson() {
        return json;
    }

    public String getJsonAsText() {
        return jsonAsText;
    }

    public CurrentCondition getCurrentCondition() {
        return currentCondition;
    }

    public NearestArea getNearestArea() {
        return nearestArea;
    }

    public Request getRequest() {
        return request;
    }

    public WeatherForecast getWeatherForecast() {
        return weatherForecast;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "---WeatherObject---" +
                "\ndays=" + Arrays.toString(days) +
                "\ntimes=" + Arrays.toString(times) +
                "\nlocation='" + location.toString() + '\'' +
                "\njson=" + json +
                "\njsonAsText='" + jsonAsText.toString() + '\'' +
                "\ncurrentCondition=" + currentCondition.toString() +
                "\nnearestArea=" + nearestArea.toString() +
                "\nrequest=" + request.toString() +
                "\nweatherForecast=" + weatherForecast.toString();
    }
}
