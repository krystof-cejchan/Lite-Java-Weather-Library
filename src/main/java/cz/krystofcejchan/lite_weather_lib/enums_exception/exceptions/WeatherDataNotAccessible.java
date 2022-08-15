package cz.krystofcejchan.lite_weather_lib.enums_exception.exceptions;

/**
 * weather data could not be accessed, check internet connection or make sure that location is correct {@link NotFoundLocation}
 */
public class WeatherDataNotAccessible extends RuntimeException {
    public WeatherDataNotAccessible(String msg) {
        super(msg);
    }
}
