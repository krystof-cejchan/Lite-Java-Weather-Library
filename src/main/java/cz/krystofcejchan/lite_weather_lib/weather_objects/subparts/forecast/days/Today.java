package cz.krystofcejchan.lite_weather_lib.weather_objects.subparts.forecast.days;

import cz.krystofcejchan.lite_weather_lib.enums_exception.enums.DAY;
import cz.krystofcejchan.lite_weather_lib.enums_exception.enums.TIME;
import cz.krystofcejchan.lite_weather_lib.enums_exception.exceptions.CouldNotFindLocation;
import cz.krystofcejchan.lite_weather_lib.enums_exception.exceptions.NoDataFoundForThisDayAndTime;
import cz.krystofcejchan.lite_weather_lib.utilities.IsNumeric;
import cz.krystofcejchan.lite_weather_lib.utilities.UtilityClass;
import cz.krystofcejchan.lite_weather_lib.weather_objects.MethodRefPrint;
import cz.krystofcejchan.lite_weather_lib.weather_objects.subparts.forecast.days.hour.ForecastAtHour;
import cz.krystofcejchan.lite_weather_lib.weather_objects.subparts.forecast.days.hour.IForecastDayTimesAndDays;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

/**
 * {@link cz.krystofcejchan.lite_weather_lib.weather_objects.subparts.forecast.WeatherForecast} for today
 *
 * @author krystof-cejchan
 * @version 17
 */
public final class Today implements IForecastDayTimesAndDays {

    final private String location;
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

    public Today(@NotNull String location, @NotNull TIME... times) throws CouldNotFindLocation {
        this.location = location;
        if (Arrays.asList(times).contains(TIME.ALL)) {
            ArrayList<TIME> t = new ArrayList<>();
            for (TIME time : TIME.values()
            ) {
                if (!time.equals(TIME.ALL))
                    t.add(time);
            }
            times = t.toArray(new TIME[0]);
        }
        this.times = times;
        JSONObject jsonObject = UtilityClass.getJson(location);
        JSONObject daily = jsonObject.getJSONArray("weather").getJSONObject(0).getJSONArray("astronomy")
                .getJSONObject(0);
        moonIllumination = daily.getInt("moon_illumination");
        moonPhase = daily.getString("moon_phase");
        moonRise = IsNumeric.containsNumbers(daily.getString("moonrise")) ? UtilityClass.stringToLocalTime(
                new StringBuilder(daily.getString("moonrise"))) : null;
        moonSet = IsNumeric.containsNumbers(daily.getString("moonset")) ? UtilityClass.stringToLocalTime(
                new StringBuilder(daily.getString("moonset"))) : null;
        sunRise = IsNumeric.containsNumbers(daily.getString("sunrise")) ? UtilityClass.stringToLocalTime(
                new StringBuilder(daily.getString("sunrise"))) : null;
        sunSet = IsNumeric.containsNumbers(daily.getString("sunset")) ? UtilityClass.stringToLocalTime(
                new StringBuilder(daily.getString("sunset"))) : null;
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

    @NotNull
    @Override
    public ForecastAtHour getForecastByTime(@NotNull TIME time) throws NoDataFoundForThisDayAndTime {
        return IForecastDayTimesAndDays.getMatchingObjectFrom(getDay(), time);
    }

    /**
     * get all possible forecast for {@link TIME}s provided in constructor
     *
     * @return map of time and forecast
     */
    @NotNull
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

    /**
     * prints current object.toString to the console
     */
    public void print() {
        new MethodRefPrint<>(this).print();
    }
    /**
     *
     * @return location provided in the constructor of this class or its parent class <br>
     * if you need to get more detailed information, use {@link cz.krystofcejchan.lite_weather_lib.weather_objects.subparts.nearest_area.NearestArea}
     */
    public String getLocation() {
        return location;
    }

    public int getAverageTemperatureF() {
        return averageTemperatureF;
    }

    public TIME[] getTimes() {
        return times;
    }

    @Contract(pure = true)
    @Override
    public @NotNull
    String toString() {
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
