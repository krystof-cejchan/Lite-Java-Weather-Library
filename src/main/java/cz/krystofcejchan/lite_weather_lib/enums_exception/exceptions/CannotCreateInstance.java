package cz.krystofcejchan.lite_weather_lib.enums_exception.exceptions;

/**
 * user is forbidden to create an instance of a class; used in {@link cz.krystofcejchan.lite_weather_lib.UtilityClass}
 */
public class CannotCreateInstance extends RuntimeException {
    public CannotCreateInstance(String msg) {
        super(msg);
    }
}
