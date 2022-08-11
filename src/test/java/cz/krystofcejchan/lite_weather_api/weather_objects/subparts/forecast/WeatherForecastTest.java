package cz.krystofcejchan.lite_weather_api.weather_objects.subparts.forecast;

import cz.krystofcejchan.lite_weather_api.enums_exception.enums.DAY;
import cz.krystofcejchan.lite_weather_api.enums_exception.enums.TIME;
import cz.krystofcejchan.lite_weather_api.weather_objects.subparts.forecast.days.Today;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class WeatherForecastTest {

    @Test
    void getForecastFor() throws IOException {
        WeatherForecast weatherForecast = new WeatherForecast("Náchod",new TIME[]{TIME.ALL}, DAY.TOMORROW);
             System.out.println(weatherForecast.getTomorrow().getForecastByTime(TIME.PM_3).getWindSpeedKmph());
    }
}