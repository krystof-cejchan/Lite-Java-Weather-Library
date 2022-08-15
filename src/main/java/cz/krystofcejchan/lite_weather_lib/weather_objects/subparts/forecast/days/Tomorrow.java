package cz.krystofcejchan.lite_weather_lib.weather_objects.subparts.forecast.days;

import cz.krystofcejchan.lite_weather_lib.UtilityClass;
import cz.krystofcejchan.lite_weather_lib.enums_exception.enums.DAY;
import cz.krystofcejchan.lite_weather_lib.enums_exception.enums.TIME;
import cz.krystofcejchan.lite_weather_lib.enums_exception.exceptions.NoDataFoundForThisDayAndTime;
import cz.krystofcejchan.lite_weather_lib.enums_exception.exceptions.NotFoundLocation;
import cz.krystofcejchan.lite_weather_lib.weather_objects.MethodRefPrint;
import cz.krystofcejchan.lite_weather_lib.weather_objects.subparts.forecast.days.hour.ForecastAtHour;
import cz.krystofcejchan.lite_weather_lib.weather_objects.subparts.forecast.days.hour.IForecastDayTimesAndDays;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * {@link cz.krystofcejchan.lite_weather_lib.weather_objects.subparts.forecast.WeatherForecast} for tommorow
 *
 * @author krystof-cejchan
 * @version 17
 */
public final class Tomorrow implements IForecastDayTimesAndDays {
    /**
     * {@link TIME}s <br>
     * times provided to the constructor<br> <b>duplicate items will be eventually removed -there will be unique TIMEs in the array</b>
     */
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

    public Tomorrow(@NotNull String location, @NotNull TIME... times) throws NotFoundLocation {
        if (Arrays.asList(times).contains(TIME.ALL)) {
            times = Arrays.stream(TIME.values()).filter(time -> !time.equals(TIME.ALL)).toList().toArray(new TIME[0]);
        }
        this.times = times;
        JSONObject json = UtilityClass.getJson(location);
        JSONObject daily = json.getJSONArray("weather").getJSONObject(0).getJSONArray("astronomy").getJSONObject(0);
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
        totalSnowInches = BigDecimal.valueOf(totalSnowCM / 2.54).setScale(2, RoundingMode.UP).doubleValue();
        uvIndex = daily.getInt("uvIndex");

        for (TIME t : this.times)
            IForecastDayTimesAndDays.super.addHour(new ForecastAtHour(json, getDay(), t));

        System.gc();
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
    public ForecastAtHour getForecastByTime(@NotNull TIME time) throws NoDataFoundForThisDayAndTime {
        return IForecastDayTimesAndDays.getMatchingObjectFrom(getDay(), time);
    }

    /**
     * get all possible forecast for {@link TIME}s provided in constructor
     *
     * @return map of time and forecast
     */
    public Map<TIME, ForecastAtHour> getAllForecastsForToday() {
        Map<TIME, ForecastAtHour> map = new HashMap<>();
        for (TIME time : getTime()) {
            try {
                map.put(time, getForecastByTime(time));
            } catch (NoDataFoundForThisDayAndTime exception) {
                exception.printStackTrace();
            }
        }
        return map;
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

    /**
     * prints current object.toString to the console
     */
    public void print() {
        MethodRefPrint<Tomorrow> a = new MethodRefPrint<>(this);
        a.print();
    }

    @Contract(pure = true)
    @Override
    public @NotNull
    String toString() {
        return "---Tomorrow---" +
                "\ntimes=" + Arrays.toString(times) +
                "\nmoonIllumination=" + moonIllumination +
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
                "\nuvIndex=" + uvIndex;
    }
}
