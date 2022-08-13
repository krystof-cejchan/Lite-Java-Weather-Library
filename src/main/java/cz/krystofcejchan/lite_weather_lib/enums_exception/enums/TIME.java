package cz.krystofcejchan.lite_weather_lib.enums_exception.enums;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * TIMES for weather forecast
 * <br>
 * AM_3, AM_6, AM_9, AM_12, PM_3, PM_6, PM_9, PM_12, ALL;
 */
public enum TIME {
    AM_3, AM_6, AM_9, AM_12, PM_3, PM_6, PM_9, PM_12, ALL;

    /**
     * case PM_12 -> 0;
     * case AM_3 -> 1;
     * case AM_6 -> 2;
     * case AM_9 -> 3;
     * case AM_12 -> 4;
     * case PM_3 -> 5;
     * case PM_6 -> 6;
     * case PM_9 -> 7;
     * case ALL -> -1;
     *
     * @param time {@link TIME}
     * @return its index
     */
    public static int getIndex(TIME time) {
        return switch (time) {
            case PM_12 -> 0;
            case AM_3 -> 1;
            case AM_6 -> 2;
            case AM_9 -> 3;
            case AM_12 -> 4;
            case PM_3 -> 5;
            case PM_6 -> 6;
            case PM_9 -> 7;
            case ALL -> -1;
        };
    }

    public static java.util.List<TIME> getAllEnumsExcept(TIME time) {
        return new ArrayList<>(Arrays.stream(values()).filter(it -> !it.equals(time)).toList());
    }
}
