package cz.krystofcejchan.lite_weather_lib.weather_objects.subparts.forecast;

import cz.krystofcejchan.lite_weather_lib.enums_exception.enums.DAY;
import cz.krystofcejchan.lite_weather_lib.enums_exception.enums.TIME;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class WeatherForecastForALLTimesAndDaysTest {

    @Test
    void getForecastFor() throws IOException {
        //create a WeatherForecast object for City of Denver, for all days and times
        WeatherForecast weatherForecast = new WeatherForecast("Denver", DAY.ALL, TIME.ALL);
        //search for forecast for the day after tomorrow at 6 am
        String toString = weatherForecast.getForecastFor(DAY.AFTER_TOMORROW, TIME.AM_6).toString();
        //get average temperature for tomorrow in Celsius
        int averageTemperatureCForTomorrow = weatherForecast.getTomorrow().getAverageTemperatureC();
        //get temperature for today at 3 pm in Fahrenheit
        int temperatureFTodayAt3pm = weatherForecast.getForecastFor(DAY.TODAY, TIME.PM_3).getTemperatureF();

        System.out.println(toString + "\n\n" + "average temperature for tomorrow: " +
                averageTemperatureCForTomorrow + "\n" + "temperature for today at 3 pm: " + temperatureFTodayAt3pm);
    }
}