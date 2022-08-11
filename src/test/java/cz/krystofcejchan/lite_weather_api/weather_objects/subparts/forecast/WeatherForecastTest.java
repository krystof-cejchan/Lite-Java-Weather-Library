package cz.krystofcejchan.lite_weather_api.weather_objects.subparts.forecast;

import cz.krystofcejchan.lite_weather_api.enums_exception.enums.DAY;
import cz.krystofcejchan.lite_weather_api.enums_exception.enums.TIME;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class WeatherForecastTest {

    @Test
    void getForecastFor() throws IOException {
        WeatherForecast weatherForecast = new WeatherForecast("Náchod",
                new TIME[]{TIME.AM_3, TIME.AM_3, TIME.AM_9},
                DAY.TOMORROW, DAY.AFTER_TOMORROW, DAY.AFTER_TOMORROW,DAY.TODAY);
        System.out.println(weatherForecast.toString());
        System.out.println(weatherForecast.getForecastFor(DAY.TOMORROW,TIME.AM_3).getFeelsLikeC());
    }
}