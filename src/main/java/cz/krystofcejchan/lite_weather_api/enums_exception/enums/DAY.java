package cz.krystofcejchan.lite_weather_api.enums_exception.enums;

/**
 * DAYs for weather forecast
 * <br> TODAY, TOMORROW, AFTER_TOMORROW, ALL;
 */
public enum DAY {
    TODAY, TOMORROW, AFTER_TOMORROW, ALL;

    public static int getIndex(DAY day) {
        return switch (day) {
            case TODAY -> 0;
            case TOMORROW -> 1;
            case AFTER_TOMORROW -> 2;
            case ALL -> -1;
        };
    }
}
