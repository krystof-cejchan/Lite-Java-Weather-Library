package cz.krystofcejchan.lite_weather_lib.enums_exception.exceptions;

import cz.krystofcejchan.lite_weather_lib.utilities.UtilityClass;

/**
 * user is forbidden to create an instance of a class; used in {@link UtilityClass}
 */
public class CannotCreateInstance extends RuntimeException {
    public CannotCreateInstance(String msg) {
        super(msg);
    }
}
