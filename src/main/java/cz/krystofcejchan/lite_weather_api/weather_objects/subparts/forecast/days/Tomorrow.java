package cz.krystofcejchan.lite_weather_api.weather_objects.subparts.forecast.days;

import cz.krystofcejchan.lite_weather_api.UtilityClass;
import cz.krystofcejchan.lite_weather_api.enums_exception.enums.DAY;
import cz.krystofcejchan.lite_weather_api.enums_exception.enums.TIME;
import cz.krystofcejchan.lite_weather_api.weather_objects.WeatherObject;
import cz.krystofcejchan.lite_weather_api.weather_objects.subparts.forecast.days.hour.ForecastAtHour;
import cz.krystofcejchan.lite_weather_api.weather_objects.subparts.forecast.days.hour.IForecastDayTimesAndDays;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

public class Tomorrow implements IForecastDayTimesAndDays {
    private final TIME[] times;

    final private int moonIllumination;
    final private String moonPhase;
    final private LocalTime moonRise;
    final private LocalTime moonSet;
    final private LocalTime sunSet, sunRise;
    final private int averageTemperatureC;
    final private int averageTemperatureF;
    final private LocalDate date;
    final private int maxTemperatureC, maxTemperatureF;
    final private int minTemperatureC, minTemperatureF;
    final private double sunHour;
    final private double totalSnowCM, totalSnowInches;
    final private int uvIndex;

    public Tomorrow(String location, TIME[] times, DAY... days) throws IOException {
        //  super(location, times, days);
        this.times = times;

        JSONObject json = new WeatherObject<Tomorrow>(location, times, days) {
            @Override
            public @NotNull Tomorrow getObject() throws IOException {
                return new Tomorrow(getLocation(), getTimes(), getDays());
            }
        }.getJson();
        JSONObject daily =json.getJSONArray("weather").getJSONObject(0).getJSONArray("astronomy").getJSONObject(0);
        moonIllumination = daily.getInt("moon_illumination");
        moonPhase = daily.getString("moon_phase");
        moonRise = UtilityClass.stringToLocalTime(new StringBuilder(daily.getString("moonrise")));
        moonSet = UtilityClass.stringToLocalTime(new StringBuilder(daily.getString("moonset")));
        sunRise = UtilityClass.stringToLocalTime(new StringBuilder(daily.getString("sunrise")));
        sunSet = UtilityClass.stringToLocalTime(new StringBuilder(daily.getString("sunset")));
        daily = json.getJSONArray("weather").getJSONObject(0);
        averageTemperatureC = daily.getInt("avgtempC");
        averageTemperatureF = daily.getInt("avgtempF");
        date = UtilityClass.stringToDate(new StringBuilder(daily.getString("date")));
        maxTemperatureC = daily.getInt("maxtempC");
        maxTemperatureF = daily.getInt("maxtempF");
        minTemperatureC = daily.getInt("mintempC");
        minTemperatureF = daily.getInt("mintempF");
        sunHour = daily.getDouble("sunHour");
        totalSnowCM = daily.getDouble("totalSnow_cm");
        totalSnowInches = totalSnowCM / 2.54;
        uvIndex = daily.getInt("uvIndex");

        for (TIME t : times) {
            IForecastDayTimesAndDays.super.addHour(new ForecastAtHour(json, getDay(), t),
                    UtilityClass.listOfAllDaysAndItsTimes);
        }
    }

    @Override
    public DAY getDay() {
        return DAY.TOMORROW;
    }

    @Override
    public TIME[] getTime() {
        return times;
    }

    @Override
    public ForecastAtHour getForecastByTime(TIME time) {
        return IForecastDayTimesAndDays.getMatchingObjectFrom(getDay(), time);
    }

    public int getMoonIllumination() {
        return moonIllumination;
    }

    public String getMoonPhase() {
        return moonPhase;
    }

    public LocalTime getMoonRise() {
        return moonRise;
    }

    public LocalTime getMoonSet() {
        return moonSet;
    }

    public LocalTime getSunSet() {
        return sunSet;
    }

    public LocalTime getSunRise() {
        return sunRise;
    }

    public int getAverageTemperatureC() {
        return averageTemperatureC;
    }

    public int getAverageTemperatureF() {
        return averageTemperatureF;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getMaxTemperatureC() {
        return maxTemperatureC;
    }

    public int getMaxTemperatureF() {
        return maxTemperatureF;
    }

    public int getMinTemperatureC() {
        return minTemperatureC;
    }

    public int getMinTemperatureF() {
        return minTemperatureF;
    }

    public double getSunHour() {
        return sunHour;
    }

    public double getTotalSnowCM() {
        return totalSnowCM;
    }

    public double getTotalSnowInches() {
        return totalSnowInches;
    }

    public int getUvIndex() {
        return uvIndex;
    }

    @Override
    public String toString() {
        return "Tomorrow{" +
                "times=" + Arrays.toString(times) +
                ", moonIllumination=" + moonIllumination +
                ", moonPhase='" + moonPhase + '\'' +
                ", moonRise=" + moonRise +
                ", moonSet=" + moonSet +
                ", sunSet=" + sunSet +
                ", sunRise=" + sunRise +
                ", averageTemperatureC=" + averageTemperatureC +
                ", averageTemperatureF=" + averageTemperatureF +
                ", date=" + date +
                ", maxTemperatureC=" + maxTemperatureC +
                ", maxTemperatureF=" + maxTemperatureF +
                ", minTemperatureC=" + minTemperatureC +
                ", minTemperatureF=" + minTemperatureF +
                ", sunHour=" + sunHour +
                ", totalSnowCM=" + totalSnowCM +
                ", totalSnowInches=" + totalSnowInches +
                ", uvIndex=" + uvIndex +
                '}';
    }
}
