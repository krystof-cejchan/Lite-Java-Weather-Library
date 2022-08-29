package cz.krystofcejchan.lite_weather_lib.weather_objects.subparts.forecast;

import cz.krystofcejchan.lite_weather_lib.enums_exception.enums.DAY;
import cz.krystofcejchan.lite_weather_lib.enums_exception.enums.TIME;
import cz.krystofcejchan.lite_weather_lib.enums_exception.exceptions.NoDataFoundForThisDay;
import cz.krystofcejchan.lite_weather_lib.enums_exception.exceptions.NoDataFoundForThisDayAndTime;
import cz.krystofcejchan.lite_weather_lib.utilities.UtilityClass;
import cz.krystofcejchan.lite_weather_lib.weather_objects.MethodRefPrint;
import cz.krystofcejchan.lite_weather_lib.weather_objects.subparts.forecast.days.AfterTomorrow;
import cz.krystofcejchan.lite_weather_lib.weather_objects.subparts.forecast.days.Today;
import cz.krystofcejchan.lite_weather_lib.weather_objects.subparts.forecast.days.Tomorrow;
import cz.krystofcejchan.lite_weather_lib.weather_objects.subparts.forecast.days.hour.ForecastAtHour;
import cz.krystofcejchan.lite_weather_lib.weather_objects.subparts.forecast.days.hour.IForecastDayTimesAndDays;
import org.jetbrains.annotations.NotNull;

import javax.annotation.CheckForNull;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Weather forecast for today, tomorrow, the day after tomorrow <br>
 * Data can be separated into hours → 12am, 3am, 6am, 9am, 12pm, 3pm, 6pm, 9pm.<br>
 * see {@link TIME}, {@link DAY}<br>
 *
 * @author krystof-cejchan
 * @version 17
 */
public class WeatherForecast {

    private final String location;
    private final Today today;
    private final Tomorrow tomorrow;
    private final AfterTomorrow tomorrowAfter;

    private final DAY[] days;
    private final TIME[] times;


    public WeatherForecast(@NotNull String location, @NotNull TIME time, @NotNull DAY... days) throws IOException {
        this(location, new TIME[]{time}, days);
    }

    public WeatherForecast(@NotNull String location, @NotNull DAY day, @NotNull TIME... times) throws IOException {
        this(location, times, day);
    }

    public WeatherForecast(@NotNull String location, @NotNull DAY[] day, @NotNull TIME... times) throws IOException {
        this(location, times, day);
    }

    public WeatherForecast(@NotNull String location, @NotNull TIME[] times, @NotNull DAY... days) throws IOException {
        this.location = location;
        Today todayHelper = null;
        Tomorrow tomorrowHelper = null;
        AfterTomorrow tomorrowAfterHelper = null;

        //filtering the arraylists
        List<TIME> timeList = new ArrayList<>(Arrays.stream(times).collect(Collectors.toList())).stream().distinct().collect(Collectors.toList());
        List<DAY> dayList = new ArrayList<>(Arrays.stream(days).collect(Collectors.toList())).stream().distinct().collect(Collectors.toList());
        if (dayList.contains(DAY.ALL))
            dayList = Arrays.stream(DAY.values()).collect(Collectors.toList()).stream().filter(day -> !day.equals(DAY.ALL)).collect(Collectors.toList());
        if (timeList.contains(TIME.ALL))
            timeList = Arrays.stream(TIME.values()).collect(Collectors.toList()).stream().filter(time -> !time.equals(TIME.ALL)).collect(Collectors.toList());

        TIME[] timesBackToArray = timeList.toArray(new TIME[0]);
        this.times = timesBackToArray;
        this.days = dayList.toArray(new DAY[0]);
        for (DAY day : dayList) {
            switch (day) {
                case TODAY -> todayHelper = new Today(location, timesBackToArray);
                case TOMORROW -> tomorrowHelper = new Tomorrow(location, timesBackToArray);
                case AFTER_TOMORROW -> tomorrowAfterHelper = new AfterTomorrow(location, timesBackToArray);
                case ALL -> {
                    todayHelper = new Today(location, timesBackToArray);
                    tomorrowHelper = new Tomorrow(location, timesBackToArray);
                    tomorrowAfterHelper = new AfterTomorrow(location, timesBackToArray);
                }
            }
        }

        today = todayHelper;
        tomorrow = tomorrowHelper;
        tomorrowAfter = tomorrowAfterHelper;
    }

    /**
     * removes saved forecasts
     */
    public static void clearSavedForecasts() {
        IForecastDayTimesAndDays.clearSavedForecasts();
    }

    /**
     * removes forecast from param
     *
     * @param forecast to be removed
     */
    public static void removedSavedForecast(ForecastAtHour forecast) {
        IForecastDayTimesAndDays.removedSavedForecast(forecast);
    }

    /**
     * returns forecast for specific day and time
     *
     * @param day  {@link DAY} for which you want to know the forecast
     * @param time {@link TIME} for which you want to know the forecast
     * @return ForecastAtHour for provided DAY and TIME
     * @throws NoDataFoundForThisDayAndTime if you did not include day and time when creating constructor
     */
    public ForecastAtHour getForecastFor(@NotNull DAY day, @NotNull TIME time) throws NoDataFoundForThisDayAndTime {
        return IForecastDayTimesAndDays.getMatchingObjectFrom(day, time);
    }

    /**
     * get all possible forecasts for all days and times you provided in the constructor
     *
     * @return a map of {@link DAY} and map of {@link TIME} and {@link ForecastAtHour}
     */
    public Map<DAY, Map<TIME, ForecastAtHour>> getAllForecastForAllDayAndAllTime() {
        Map<DAY, Map<TIME, ForecastAtHour>> returnMap = new HashMap<>();
        for (DAY day : days) {
            switch (day) {
                case TODAY -> returnMap.put(day, getToday().getAllForecastsForToday());
                case TOMORROW -> returnMap.put(day, getTomorrow().getAllForecastsForToday());
                case AFTER_TOMORROW -> returnMap.put(day, getTomorrowAfter().getAllForecastsForToday());
            }
        }
        return returnMap;
    }

    /**
     * @return object containing all weather data for today
     * @throws NoDataFoundForThisDay if you did not include this day in constructor
     */
    public Today getToday() throws NoDataFoundForThisDay {
        if (today == null)
            throw new NoDataFoundForThisDay("Today was not included in the constructor");

        return today;
    }

    /**
     * @return object containing all weather data for tomorrow
     * @throws NoDataFoundForThisDay if you did not include this day in constructor
     */
    public Tomorrow getTomorrow() throws NoDataFoundForThisDay {
        if (tomorrow == null)
            throw new NoDataFoundForThisDay("Tomorrow was not included in the constructor");

        return tomorrow;
    }

    /**
     * @return object containing all weather data for the day after tomorrow
     * @throws NoDataFoundForThisDay if you did not include this day in constructor
     */
    public AfterTomorrow getTomorrowAfter() throws NoDataFoundForThisDay {
        if (tomorrowAfter == null)
            throw new NoDataFoundForThisDay("The day after tomorrow was not included in the constructor");

        return tomorrowAfter;
    }

    /**
     * @return DAYs provided in constructor
     */
    public DAY[] getDays() {
        return days;
    }

    /**
     * @return TIMEs provided in constructor
     */
    public TIME[] getTimes() {
        return times;
    }
    /**
     *
     * @return location provided in the constructor of this class or its parent class <br>
     * if you need to get more detailed information, use {@link cz.krystofcejchan.lite_weather_lib.weather_objects.subparts.nearest_area.NearestArea}
     */
    public String getLocation() {
        return location;
    }

    /**
     * @return all saved forecasts
     */
    public @CheckForNull
    List<ForecastAtHour> getAllSavedForecasts() {
        return new ArrayList<>(UtilityClass.Storage.getListOfAllDaysAndItsTimes());
    }

    /**
     * prints current object.toString to the console
     */
    public void print() {
        new MethodRefPrint<>(this).print();
    }

    /**
     * @return object to string, modified to prevent {@link NullPointerException} if class fields are null
     */
    @Override
    public String toString() {
        return "---  WeatherForecast  ---" +
                "\n--today=\n" + (today == null ? " null " : today.toString()) +
                "\n--tomorrow=\n" + (tomorrow == null ? " null " : tomorrow.toString()) +
                "\n--AfterTomorrow=\n" + (tomorrowAfter == null ? " null " : tomorrowAfter.toString());
    }
}
