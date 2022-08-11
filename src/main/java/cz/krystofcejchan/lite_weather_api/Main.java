package cz.krystofcejchan.lite_weather_api;

import cz.krystofcejchan.lite_weather_api.weather_objects.WeatherObject;
import cz.krystofcejchan.lite_weather_api.weather_objects.subparts.current_weather.CurrentCondition;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        CurrentCondition currentCondition = new CurrentCondition("New York");
        System.out.println(currentCondition.toString());
        WeatherObject<CurrentCondition> currentConditionWeatherObject = new CurrentCondition("New York");
        System.out.println(currentConditionWeatherObject.getObject().toString());
    }
}
