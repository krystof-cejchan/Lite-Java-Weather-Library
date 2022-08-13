package cz.krystofcejchan.lite_weather_lib;

import cz.krystofcejchan.lite_weather_lib.weather_objects.subparts.current_weather.CurrentCondition;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        CurrentCondition currentCondition = new CurrentCondition("New York");
        System.out.println(currentCondition);

    }
}
