package cz.krystofcejchan.lite_weather_lib.weather_objects;

import cz.krystofcejchan.lite_weather_lib.enums_exception.enums.DAY;
import cz.krystofcejchan.lite_weather_lib.enums_exception.enums.TIME;

import java.io.IOException;

public class TestOfAllTests {

    @org.junit.jupiter.api.Test
    void test() throws IOException {
        WeatherObject weatherObject = new WeatherObject("Mexico City", DAY.ALL, TIME.AM_6, TIME.AM_9);
        //  Objects.requireNonNull(weatherObject.getWeatherForecast().getAllSavedForecasts()).forEach(System.out::println);
        weatherObject.getWeatherForecast().getAllForecastForAllDayAndAllTime().forEach((k, v) -> System.out.println(k + "→" + v));
    }
}
