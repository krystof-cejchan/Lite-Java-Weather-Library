package cz.krystofcejchan.lite_weather_api.weather_objects.subparts.forecast;

import cz.krystofcejchan.lite_weather_api.enums_exception.enums.DAY;
import cz.krystofcejchan.lite_weather_api.enums_exception.enums.TIME;
import cz.krystofcejchan.lite_weather_api.enums_exception.exceptions.NoDataFoundForThisDay;
import cz.krystofcejchan.lite_weather_api.weather_objects.WeatherObject;
import cz.krystofcejchan.lite_weather_api.weather_objects.subparts.forecast.days.Today;
import cz.krystofcejchan.lite_weather_api.weather_objects.subparts.forecast.days.Tomorrow;
import cz.krystofcejchan.lite_weather_api.weather_objects.subparts.forecast.days.TomorrowAfter;
import cz.krystofcejchan.lite_weather_api.weather_objects.subparts.forecast.days.hour.ForecastAtHour;
import cz.krystofcejchan.lite_weather_api.weather_objects.subparts.forecast.days.hour.IForecastDayTimesAndDays;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Weather forecast for today, tomorrow, the day after tomorrow <br>
 * Data can be separated into hours → 12am, 3am, 6am, 9am, 12pm, 3pm, 6pm, 9pm.<br>
 * see {@link TIME}, {@link DAY}<br>
 */
public class WeatherForecast extends WeatherObject<WeatherForecast> {
    private final Today today;
    private final Tomorrow tomorrow;
    private final TomorrowAfter tomorrowAfter;


    public WeatherForecast(String location, TIME[] times, DAY... days) throws IOException {
        super(location, times, days);

        Today todayHelper = null;
        Tomorrow tomorrowHelper = null;
        TomorrowAfter tomorrowAfterHelper = null;

        //filtering the arraylists
        List<TIME> timeList = new ArrayList<>(Arrays.stream(times).distinct().toList());
        List<DAY> dayList = new ArrayList<>(Arrays.stream(days).distinct().toList());
        if (dayList.contains(DAY.ALL))
            dayList = dayList.stream().filter(it -> it == DAY.ALL).toList();
        if (timeList.contains(TIME.ALL))
            timeList = timeList.stream().filter(it -> it == TIME.ALL).toList();

        JSONObject weather_forecast = super.getJson().getJSONArray("weather").getJSONObject(0);
        TIME[] timesBackToArray = timeList.toArray(new TIME[0]);
        for (DAY day : dayList) {
            switch (day) {
                case TODAY -> todayHelper = new Today(location, timesBackToArray, days);
                case TOMORROW -> tomorrowHelper = new Tomorrow(location, timesBackToArray, days);
                case AFTERTOMORROW -> tomorrowAfterHelper = new TomorrowAfter(location, timesBackToArray, days);
                case ALL -> {
                    todayHelper = new Today(location, timesBackToArray, days);
                    tomorrowHelper = new Tomorrow(location, timesBackToArray, days);
                    tomorrowAfterHelper = new TomorrowAfter(location, timesBackToArray, days);
                }
            }
        }

        today = todayHelper;
        tomorrow = tomorrowHelper;
        tomorrowAfter = tomorrowAfterHelper;


    }

    public ForecastAtHour getForecastFor(DAY day, TIME time) {
        return IForecastDayTimesAndDays.getMatchingObjectFrom(day, time);
    }

    public Today getToday() {
        if (today == null)
            throw new NoDataFoundForThisDay("Today was not included in the constructor");

        return today;
    }

    public Tomorrow getTomorrow() {
        if (tomorrow == null)
            throw new NoDataFoundForThisDay("Tomorrow was not included in the constructor");

        return tomorrow;
    }

    public TomorrowAfter getTomorrowAfter() {
        if (tomorrowAfter == null)
            throw new NoDataFoundForThisDay("The day after tomorrow was not included in the constructor");

        return tomorrowAfter;
    }

    @Override
    public @NotNull WeatherForecast getObject() throws IOException {
        return new WeatherForecast(super.getLocation(), super.getTimes(), super.getDays());
    }

    @Override
    public String toString() {
        return "WeatherForecast{" +
                "today=" + today.toString() +
                ", tomorrow=" + tomorrow.toString() +
                ", tomorrowAfter=" + tomorrowAfter.toString() +
                '}';
    }
}
