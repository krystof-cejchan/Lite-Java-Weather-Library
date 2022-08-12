package cz.krystofcejchan.lite_weather_api.examples;

import cz.krystofcejchan.lite_weather_api.enums_exception.enums.DAY;
import cz.krystofcejchan.lite_weather_api.enums_exception.enums.TIME;
import cz.krystofcejchan.lite_weather_api.weather_objects.subparts.forecast.WeatherForecast;

import java.io.IOException;

public class WeatherForecast_Example {
    public static void example01() throws IOException {
        WeatherForecast weatherForecast = new WeatherForecast("London", new TIME[]{TIME.AM_3, TIME.AM_6, TIME.AM_9}, DAY.TODAY);
        System.out.println(weatherForecast.getToday().getMaxTemperatureC());
        System.out.println(weatherForecast.getForecastFor(DAY.TODAY, TIME.AM_9).getChanceOfRain());
        System.out.println(weatherForecast.toString());
    }
    //OUTPUT:
//    35
//    0
//      ---  WeatherForecast  ---
//      --today=
//      ---Today---
//    times=[AM_3, AM_6, AM_9]
//    oonIllumination=100
//    moonPhase='Full Moon'
//    moonRise=21:18
//    moonSet=05:35
//    sunSet=20:29
//    sunRise=05:41
//    averageTemperatureC=26
//    averageTemperatureF=79
//    date=2022-08-12
//    maxTemperatureC=35
//    maxTemperatureF=94
//    minTemperatureC=20
//    minTemperatureF=67
//    sunHour=14.9
//    totalSnowCM=0.0
//    totalSnowInches=0.0
//    uvIndex=7
//    forecastHourlyList=[]
//    times=[AM_3, AM_6, AM_9]
//            --tomorrow=
//            null
//            --AfterTomorrow=
//            null


}
