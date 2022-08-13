package cz.krystofcejchan.lite_weather_lib.enums_exception.enums;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * DAYs for weather forecast
 * <br> TODAY, TOMORROW, AFTER_TOMORROW, ALL;
 */
public enum DAY {
    TODAY, TOMORROW, AFTER_TOMORROW, ALL;

    /**
     * case TODAY -> 0;
     * case TOMORROW -> 1;
     * case AFTER_TOMORROW -> 2;
     * case ALL -> -1;
     *
     * @param day {@link DAY}
     * @return DAY's index
     */
    public static int getIndex(DAY day) {
        return switch (day) {
            case TODAY -> 0;
            case TOMORROW -> 1;
            case AFTER_TOMORROW -> 2;
            case ALL -> -1;
        };
    }

    public static java.util.List<DAY> getAllDaysExcept(DAY day) {
        return new ArrayList<>(Arrays.stream(values()).filter(it -> !it.equals(ALL)).toList());
    }
}
