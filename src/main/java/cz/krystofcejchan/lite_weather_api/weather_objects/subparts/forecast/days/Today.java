package cz.krystofcejchan.lite_weather_api.weather_objects.subparts.forecast.days;

import cz.krystofcejchan.lite_weather_api.UtilityClass;
import cz.krystofcejchan.lite_weather_api.enums_exception.enums.DAY;
import cz.krystofcejchan.lite_weather_api.enums_exception.enums.TIME;
import cz.krystofcejchan.lite_weather_api.enums_exception.exceptions.NoDataFoundForThisDayAndTime;
import cz.krystofcejchan.lite_weather_api.weather_objects.subparts.forecast.days.hour.ForecastAtHour;
import cz.krystofcejchan.lite_weather_api.weather_objects.subparts.forecast.days.hour.IForecastDayTimesAndDays;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Today implements IForecastDayTimesAndDays {

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

    /**
     * {@link TIME}s <br>
     * times provided to the constructor<br> <b>duplicate items will be eventually removed -there will be unique TIMEs in the array</b>
     */
    private final TIME[] times;

    public Today(String location, TIME... times) throws IOException {
        if (Arrays.asList(times).contains(TIME.ALL)) {
            times = Arrays.stream(TIME.values()).filter(time -> !time.equals(TIME.ALL)).toList().toArray(new TIME[0]);
        }
        this.times = times;
        JSONObject jsonObject = UtilityClass.getJson(location);
        JSONObject daily = jsonObject.getJSONArray("weather").getJSONObject(0).getJSONArray("astronomy")
                .getJSONObject(0);
        moonIllumination = daily.getInt("moon_illumination");
        moonPhase = daily.getString("moon_phase");
        moonRise = UtilityClass.stringToLocalTime(new StringBuilder(daily.getString("moonrise")));
        moonSet = UtilityClass.stringToLocalTime(new StringBuilder(daily.getString("moonset")));
        sunRise = UtilityClass.stringToLocalTime(new StringBuilder(daily.getString("sunrise")));
        sunSet = UtilityClass.stringToLocalTime(new StringBuilder(daily.getString("sunset")));
        daily = jsonObject.getJSONArray("weather").getJSONObject(0);
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

        for (TIME t : times)
            IForecastDayTimesAndDays.super.addHour(new ForecastAtHour(jsonObject, getDay(), t));

        System.gc();
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
    public ForecastAtHour getForecastByTime(TIME time) throws NoDataFoundForThisDayAndTime {
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
        return "---Today---" +
                "\ntimes=" + Arrays.toString(times) +
                "\noonIllumination=" + moonIllumination +
                "\nmoonPhase='" + moonPhase + '\'' +
                "\nmoonRise=" + moonRise +
                "\nmoonSet=" + moonSet +
                "\nsunSet=" + sunSet +
                "\nsunRise=" + sunRise +
                "\naverageTemperatureC=" + averageTemperatureC +
                "\naverageTemperatureF=" + averageTemperatureF +
                "\ndate=" + date +
                "\nmaxTemperatureC=" + maxTemperatureC +
                "\nmaxTemperatureF=" + maxTemperatureF +
                "\nminTemperatureC=" + minTemperatureC +
                "\nminTemperatureF=" + minTemperatureF +
                "\nsunHour=" + sunHour +
                "\ntotalSnowCM=" + totalSnowCM +
                "\ntotalSnowInches=" + totalSnowInches +
                "\nuvIndex=" + uvIndex +
                "\nforecastHourlyList=" + forecastHourlyList;
    }
}
