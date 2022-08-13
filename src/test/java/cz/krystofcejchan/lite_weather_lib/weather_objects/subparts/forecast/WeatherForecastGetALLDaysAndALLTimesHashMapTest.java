package cz.krystofcejchan.lite_weather_lib.weather_objects.subparts.forecast;

import cz.krystofcejchan.lite_weather_lib.enums_exception.enums.DAY;
import cz.krystofcejchan.lite_weather_lib.enums_exception.enums.TIME;
import cz.krystofcejchan.lite_weather_lib.weather_objects.subparts.forecast.days.Tomorrow;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class WeatherForecastGetALLDaysAndALLTimesHashMapTest {

    @Test
    void getAllForecastForAllDayAndAllTime() throws IOException {
        WeatherForecast w = new WeatherForecast("Prague", DAY.TOMORROW, TIME.ALL);
        System.out.println(w.getAllForecastForAllDayAndAllTime().get(DAY.TOMORROW).get(TIME.AM_6));
       // w.getAllForecastForAllDayAndAllTime().forEach((k, v) -> System.out.println(k + "→" + v));
    }
}