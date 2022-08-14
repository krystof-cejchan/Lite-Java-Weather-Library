package cz.krystofcejchan.lite_weather_lib.weather_objects.subparts.forecast.days.hour;

import cz.krystofcejchan.lite_weather_lib.UtilityClass;
import cz.krystofcejchan.lite_weather_lib.enums_exception.enums.DAY;
import cz.krystofcejchan.lite_weather_lib.enums_exception.enums.TIME;
import cz.krystofcejchan.lite_weather_lib.enums_exception.exceptions.CannotSearchForAll;
import cz.krystofcejchan.lite_weather_lib.enums_exception.exceptions.NoDataFoundForThisDayAndTime;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * inteface impletended in {@link cz.krystofcejchan.lite_weather_lib.weather_objects.subparts.forecast.days.Today}
 * {@link cz.krystofcejchan.lite_weather_lib.weather_objects.subparts.forecast.days.Tomorrow} and {@link cz.krystofcejchan.lite_weather_lib.weather_objects.subparts.forecast.days.AfterTomorrow}
 *
 * @author krystof-cejchan
 * @version 17
 */
public interface IForecastDayTimesAndDays {
    /**
     * returns forecast for specific day and hour
     *
     * @param day  for which you need the forecast
     * @param time for which you need the forecast
     * @return Forecast data for provided {@link DAY} and {@link TIME}; if not found {@link NoDataFoundForThisDayAndTime} exception is thrown
     * @throws NoDataFoundForThisDayAndTime if no forecast was included for this {@link TIME} and {@link DAY};
     *                                      you need to use TIME.ALL with DAY.ALL if you want to prevent this
     *                                      or include {@link TIME} and {@link DAY} you want to know the forecast for in the constructor when creating forecast object or its subclasses
     * @throws CannotSearchForAll           {@link DAY} or {@link TIME} is set to ALL; you can get single object of {@link ForecastAtHour} only for certain DAY and TIME
     */
    static @NotNull ForecastAtHour getMatchingObjectFrom(DAY day, TIME time) throws NoDataFoundForThisDayAndTime, CannotSearchForAll {
        if (day.equals(DAY.ALL) || time.equals(TIME.ALL))
            throw new CannotSearchForAll("In order to get Forecast for certain hour, you need to pass any DAY or TIME except ALL");

        return UtilityClass.Storage.getListOfAllDaysAndItsTimes().stream().filter(f -> f.getDay().equals(day) && f.getTime().equals(time)).findFirst().orElseThrow(() -> new NoDataFoundForThisDayAndTime("No data found for such day and time"));

    }

    static void clearSavedForecasts() {
        UtilityClass.Storage.clearList();
    }

    static void removedSavedForecast(ForecastAtHour forecast) {
        UtilityClass.Storage.removeElement(forecast);
    }

    /**
     * @return day which implementing class represents
     */
    DAY getDay();

    /**
     * @return time provided by user when calling this class or superclass
     */
    TIME[] getTime();

    /**
     * implemented by Today, Tomorrow and AfterTomorrow, therefor no day is needed
     *
     * @param time for weather data
     * @return ForecastAtHour object containing all data for specific day and time
     */
    ForecastAtHour getForecastByTime(TIME time) throws NoDataFoundForThisDayAndTime;

    /**
     * add Forecast object
     *
     * @param forecast {@link ForecastAtHour} object containing weather data for specific day and time(hour)
     */
    default void addHour(ForecastAtHour forecast) {
        List<ForecastAtHour> forecastHourlyList = UtilityClass.Storage.getListOfAllDaysAndItsTimes();
        if (forecastHourlyList.stream().noneMatch(it -> it.equals(forecast))) {
            forecastHourlyList.add(forecast);
            UtilityClass.Storage.addToListOfAllDaysAndItsTimes(forecast);
        }
    }
}
