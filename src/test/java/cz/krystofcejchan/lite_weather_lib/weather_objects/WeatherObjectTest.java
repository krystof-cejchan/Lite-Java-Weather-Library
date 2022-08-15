package cz.krystofcejchan.lite_weather_lib.weather_objects;

import cz.krystofcejchan.lite_weather_lib.enums_exception.enums.DAY;
import cz.krystofcejchan.lite_weather_lib.enums_exception.enums.TIME;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class WeatherObjectTest {

    @Test
    void getAll() throws IOException {
        WeatherObject weatherObject = new WeatherObject("Mexico Cithnhhy", DAY.ALL, TIME.AM_6, TIME.AM_9);
        System.out.println(weatherObject.getWeatherForecast().getForecastFor(DAY.TOMORROW, TIME.AM_9));
        //System.out.println(weatherObject.getJsonAsText());
        System.out.println(weatherObject.getCurrentCondition().getVisibility());
    }
}