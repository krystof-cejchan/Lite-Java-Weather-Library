package cz.krystofcejchan.lite_weather_lib.enums_exception.enums;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * TIMES for weather forecast
 * <br>
 * AM_3, AM_6, AM_9, AM_12, PM_3, PM_6, PM_9, PM_12, ALL;
 */
public enum TIME {
    /**
     * 3:00 a.m.
     */
    AM_3,
    /**
     * 6:00 a.m.
     */
    AM_6,
    /**
     * 9:00 a.m.
     */
    AM_9,
    /**
     * noon
     */
    AM_12,
    /**
     * 3:00 p.m.
     */
    PM_3,
    /**
     * 6:00 p.m.
     */
    PM_6,
    /**
     * 9:00 p.m.
     */
    PM_9,
    /**
     * midnight
     */
    PM_12,
    /**
     * all times
     */
    ALL;

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

    /**
     * returns all {@link TIME} values expect the one included in param
     *
     * @param time TIME to be excluded
     * @return {@link java.util.List} of all {@link TIME}s except param
     */
    public static java.util.List<TIME> getAllEnumsExcept(@NotNull TIME time) {
        return new ArrayList<>(Arrays.stream(values()).filter(it -> !it.equals(time)).toList());
    }
}
