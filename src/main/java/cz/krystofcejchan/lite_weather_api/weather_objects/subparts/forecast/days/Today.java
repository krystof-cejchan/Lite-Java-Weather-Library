package cz.krystofcejchan.lite_weather_api.weather_objects.subparts.forecast.days;

import cz.krystofcejchan.lite_weather_api.UtilityClass;
import cz.krystofcejchan.lite_weather_api.enums_exception.enums.DAY;
import cz.krystofcejchan.lite_weather_api.enums_exception.enums.TIME;
import cz.krystofcejchan.lite_weather_api.weather_objects.WeatherObject;
import cz.krystofcejchan.lite_weather_api.weather_objects.subparts.forecast.WeatherForecast;
import cz.krystofcejchan.lite_weather_api.weather_objects.subparts.forecast.days.hour.ForecastAtHour;
import cz.krystofcejchan.lite_weather_api.weather_objects.subparts.forecast.days.hour.IForecastDayTimesAndDays;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Today extends WeatherForecast implements IForecastDayTimesAndDays {

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
    final private List<ForecastAtHour> forecastHourlyList = new ArrayList<>();
    private final TIME[] times;

    public Today(String location, TIME[] times, DAY... days) throws IOException {
        super(location, times, days);
        this.times = times;
        JSONObject daily = new WeatherObject<Today>(location, times, days) {
            @Override
            public @NotNull Today getObject() throws IOException {
                return new Today(getLocation(), getTimes(), getDays());
            }
        }.getJson().getJSONArray("weather").getJSONObject(0).getJSONArray("astronomy")
                .getJSONObject(0);
        moonIllumination = daily.getInt("moon_illumination");
        moonPhase = daily.getString("moon_phase");
        moonRise = UtilityClass.stringToLocalTime(new StringBuilder(daily.getString("moonrise")));
        moonSet = UtilityClass.stringToLocalTime(new StringBuilder(daily.getString("moonset")));
        sunRise = UtilityClass.stringToLocalTime(new StringBuilder(daily.getString("sunrise")));
        sunSet = UtilityClass.stringToLocalTime(new StringBuilder(daily.getString("sunset")));
        daily = super.getJson().getJSONArray("weather").getJSONObject(0);
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
            IForecastDayTimesAndDays.super.addHour(new ForecastAtHour(super.getJson(), getDay(), t),
                    UtilityClass.listOfAllDaysAndItsTimes);
        }
    }


    @Override
    public DAY getDay() {
        return DAY.TODAY;
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

    public int getaverageTemperatureF() {
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

    public List<ForecastAtHour> getForecastHourlyList() {
        return forecastHourlyList;
    }


    @Override
    public String toString() {
        return "Today{" +
                "moonIllumination=" + moonIllumination +
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
                ", forecastHourlyList=" + forecastHourlyList +
                ", times=" + Arrays.toString(times) +
                '}';
    }
}
