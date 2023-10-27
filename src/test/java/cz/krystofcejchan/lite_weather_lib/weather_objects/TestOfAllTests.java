package cz.krystofcejchan.lite_weather_lib.weather_objects;

import cz.krystofcejchan.lite_weather_lib.enums_exception.enums.DAY;
import cz.krystofcejchan.lite_weather_lib.enums_exception.enums.TIME;
import cz.krystofcejchan.lite_weather_lib.weather_objects.subparts.current_weather.CurrentCondition;
import cz.krystofcejchan.lite_weather_lib.weather_objects.subparts.request.Request;

import java.io.IOException;

public class TestOfAllTests {

    @org.junit.jupiter.api.Test
    void test() throws IOException {
        WeatherObject weatherObject = new WeatherObject("Mexico City", DAY.ALL, TIME.ALL);
        weatherObject.getWeatherForecast().getForecastFor(DAY.TODAY,TIME.AM_6).print();
        CurrentCondition currentCondition = new CurrentCondition("Prague");
        currentCondition.print();
        Request request = new Request("Moscow");
        request.print();
    }
}
