package cz.krystofcejchan.lite_weather_api.weather_objects.subparts.forecast;

import cz.krystofcejchan.lite_weather_api.UtilityClass;
import cz.krystofcejchan.lite_weather_api.enums_exception.enums.DAY;
import cz.krystofcejchan.lite_weather_api.enums_exception.enums.TIME;
import cz.krystofcejchan.lite_weather_api.weather_objects.WeatherObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class WeatherForecastForALLTimesAndDaysTest {

    @Test
    void getForecastFor() throws IOException {
        WeatherForecast a = new WeatherForecast("Moscow", DAY.AFTER_TOMORROW, TIME.AM_3);
        System.out.println(a.toString());

        WeatherObject c = new WeatherObject("Dublin", DAY.ALL, TIME.ALL);
       System.out.println(c.getWeatherForecast().toString());
    }
}