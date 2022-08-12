package cz.krystofcejchan.lite_weather_api.enums_exception.exceptions;

/**
 * thrown if no data were found to the day and time <br>
 * thrown in {@link cz.krystofcejchan.lite_weather_api.weather_objects.subparts.forecast.days.hour.IForecastDayTimesAndDays} if no weather data found
 */

public class NoDataFoundForThisDayAndTime extends RuntimeException {
    public NoDataFoundForThisDayAndTime(String errorMsg) {
        super(errorMsg);
    }
}