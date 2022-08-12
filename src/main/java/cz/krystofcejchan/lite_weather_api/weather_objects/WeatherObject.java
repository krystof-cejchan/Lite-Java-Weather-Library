package cz.krystofcejchan.lite_weather_api.weather_objects;

import cz.krystofcejchan.lite_weather_api.UtilityClass;
import cz.krystofcejchan.lite_weather_api.WeatherForeCast;
import cz.krystofcejchan.lite_weather_api.enums_exception.enums.DAY;
import cz.krystofcejchan.lite_weather_api.enums_exception.enums.TIME;
import cz.krystofcejchan.lite_weather_api.weather_objects.subparts.current_weather.CurrentCondition;
import cz.krystofcejchan.lite_weather_api.weather_objects.subparts.forecast.WeatherForecast;
import cz.krystofcejchan.lite_weather_api.weather_objects.subparts.nearest_area.NearestArea;
import cz.krystofcejchan.lite_weather_api.weather_objects.subparts.request.Request;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;

public final class WeatherObject extends WeatherForeCast {
    private final DAY[] days;
    private final TIME[] times;
    private final String location;
    private final JSONObject json;

    private final CurrentCondition currentCondition;
    private final NearestArea nearestArea;
    private final Request request;
    private final WeatherForecast weather;


    public WeatherObject(String location, TIME[] times, DAY... days) throws IOException {
        super(location);
        this.json = UtilityClass.getJson(location);
        this.location = super.getLocation();
        this.days = days;
        this.times = times;

        this.currentCondition = new CurrentCondition(location);
        this.nearestArea = new NearestArea(location);
        this.request = new Request(location);
        this.weather = new WeatherForecast(location, times, days);
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

    public JSONObject getJson() {
        return json;
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

    public WeatherForecast getWeather() {
        return weather;
    }
}
