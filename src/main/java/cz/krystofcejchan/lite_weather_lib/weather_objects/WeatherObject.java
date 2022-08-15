package cz.krystofcejchan.lite_weather_lib.weather_objects;

import cz.krystofcejchan.lite_weather_lib.UtilityClass;
import cz.krystofcejchan.lite_weather_lib.enums_exception.enums.DAY;
import cz.krystofcejchan.lite_weather_lib.enums_exception.enums.TIME;
import cz.krystofcejchan.lite_weather_lib.enums_exception.exceptions.NotFoundLocation;
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
 *
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

    public WeatherObject(String location, TIME[] times, DAY... days) throws IOException, NotFoundLocation {
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

    /**
     * @return provided days in constructor
     */
    public DAY[] getDays() {
        return days;
    }

    /**
     * @return provided TIMEs in constructor
     */
    public TIME[] getTimes() {
        return times;
    }

    /**
     * @return json as {@link JSONObject}
     */
    public JSONObject getJson() {
        return json;
    }

    /**
     * @return JSONObject as {@link String}
     */
    public String getJsonAsText() {
        return jsonAsText;
    }

    /**
     * @return CurrentCondition object
     */
    public CurrentCondition getCurrentCondition() {
        return currentCondition;
    }

    /**
     * @return Nearest Area object
     */
    public NearestArea getNearestArea() {
        return nearestArea;
    }

    /**
     * @return Request object
     */
    public Request getRequest() {
        return request;
    }

    /**
     * @return WeatherForecast object
     */
    public WeatherForecast getWeatherForecast() {
        return weatherForecast;
    }

    /**
     * @return location from constructor
     */
    public String getLocation() {
        return location;
    }

    /**
     * prints current object.toString to the console
     */
    public void print() {
        MethodRefPrint<WeatherObject> a = new MethodRefPrint<>(this);
        a.print();
    }

    @Override
    public String toString() {
        return "---WeatherObject---" +
                "\ndays=" + Arrays.toString(days) +
                "\ntimes=" + Arrays.toString(times) +
                "\nlocation='" + location + '\'' +
                "\njson=" + json +
                "\njsonAsText='" + jsonAsText + '\'' +
                "\ncurrentCondition=" + currentCondition.toString() +
                "\nnearestArea=" + nearestArea.toString() +
                "\nrequest=" + request.toString() +
                "\nweatherForecast=" + weatherForecast.toString();
    }
}
