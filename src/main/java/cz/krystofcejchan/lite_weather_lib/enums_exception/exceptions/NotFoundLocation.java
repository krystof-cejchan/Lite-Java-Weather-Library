package cz.krystofcejchan.lite_weather_lib.enums_exception.exceptions;

/**
 * {@link NotFoundLocation} means that no weather data could be found for this location, or location name is invalid
 */
public class NotFoundLocation extends RuntimeException {
    public NotFoundLocation(String msg) {
        super(msg);
    }
}
