package cz.krystofcejchan.lite_weather_lib.weather_objects;

import cz.krystofcejchan.lite_weather_lib.enums_exception.enums.DAY;
import cz.krystofcejchan.lite_weather_lib.enums_exception.enums.TIME;

import java.io.IOException;

public class TestOfAllTests {

    @org.junit.jupiter.api.Test
    void test() throws IOException {
        WeatherObject weatherObject = new WeatherObject("Mexico City", DAY.ALL, TIME.ALL);
        //  Objects.requireNonNull(weatherObject.getWeatherForecast().getAllSavedForecasts()).forEach(System.out::println);
        weatherObject.getWeatherForecast().getForecastFor(DAY.TODAY,TIME.AM_6).print();
    }
}
