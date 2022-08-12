package cz.krystofcejchan.lite_weather_api.weather_objects.subparts.forecast;

import cz.krystofcejchan.lite_weather_api.enums_exception.enums.DAY;
import cz.krystofcejchan.lite_weather_api.enums_exception.enums.TIME;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class WeatherForecastForALLTimesAndDaysTest {

    @Test
    void getForecastFor() throws IOException {
        WeatherForecast a =new WeatherForecast("Moscow", DAY.ALL, TIME.ALL);
        System.out.println(a.getTomorrowAfter().getMinTemperatureC());
    }
}