package cz.krystofcejchan.lite_weather_api.weather_objects.subparts.forecast.days.hour;

import cz.krystofcejchan.lite_weather_api.UtilityClass;
import cz.krystofcejchan.lite_weather_api.enums_exception.enums.DAY;
import cz.krystofcejchan.lite_weather_api.enums_exception.enums.TIME;
import cz.krystofcejchan.lite_weather_api.enums_exception.exceptions.NoDataFoundForThisDayAndTime;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * inteface impletended in {@link cz.krystofcejchan.lite_weather_api.weather_objects.subparts.forecast.days.Today}
 * {@link cz.krystofcejchan.lite_weather_api.weather_objects.subparts.forecast.days.Tomorrow} and {@link cz.krystofcejchan.lite_weather_api.weather_objects.subparts.forecast.days.AfterTomorrow}
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
     */
    static @NotNull ForecastAtHour getMatchingObjectFrom(DAY day, TIME time) throws NoDataFoundForThisDayAndTime {
        return UtilityClass.Storage.getListOfAllDaysAndItsTimes().stream()
                .filter(f -> f.getDay().equals(day) && f.getTime().equals(time))
                .findFirst()
                .orElseThrow(() -> new NoDataFoundForThisDayAndTime("No data found for such day and time"));

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

    static void clearSavedForecasts() {
        UtilityClass.Storage.clearList();
    }

    static void removedSavedForecast(ForecastAtHour forecast) {
        UtilityClass.Storage.removeElement(forecast);
    }
}
