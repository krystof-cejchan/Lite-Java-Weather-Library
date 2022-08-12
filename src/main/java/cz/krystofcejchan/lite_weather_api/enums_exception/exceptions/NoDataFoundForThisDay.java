package cz.krystofcejchan.lite_weather_api.enums_exception.exceptions;

/**
 * thrown if no data were found to the day <br>
 * thrown in {@link cz.krystofcejchan.lite_weather_api.weather_objects.subparts.forecast.WeatherForecast} if user calls
 * getToday(), getTomorrow ... methods while not including these days in constructor
 */
public class NoDataFoundForThisDay extends RuntimeException {
    public NoDataFoundForThisDay(String errorMsg) {
        super(errorMsg);
    }

    public NoDataFoundForThisDay(String errorMsg, Throwable throwable) {
        super(errorMsg, throwable);
    }

    public NoDataFoundForThisDay() {
        super();
    }

    /**
     * method throwing {@link NoDataFoundForThisDayAndTime}
     *
     * @param msg message to be displayed
     * @return NoDataFoundForThisDay with time
     */
    public NoDataFoundForThisDayAndTime addTime(String msg) {
        return new NoDataFoundForThisDayAndTime(msg);
    }
}


