package cz.krystofcejchan.lite_weather_lib.enums_exception.enums;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DAYs for weather forecast
 * <br> TODAY, TOMORROW, AFTER_TOMORROW, ALL;
 */
public enum DAY {
    /**
     * today
     */
    TODAY,
    /**
     * tomorrow
     */
    TOMORROW,
    /**
     * the day after tomorrow
     */
    AFTER_TOMORROW,
    /**
     * all days
     */
    ALL;

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
        switch (day) {
            case TODAY:
                return 0;
            case TOMORROW:
                return 1;
            case AFTER_TOMORROW:
                return 2;
            case ALL:
                return -1;
        }
        return 0;
    }

    /**
     * returns all {@link DAY} values expect the one included in param
     *
     * @param day TIME to be excluded
     * @return {@link java.util.List} of all {@link DAY}s except param
     */
    public static List<DAY> getAllDaysExcept(@NotNull DAY day) {
        return Arrays.stream(values()).filter(it -> !it.equals(ALL)).collect(Collectors.toList());
    }
}
