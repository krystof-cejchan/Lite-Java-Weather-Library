package cz.krystofcejchan.lite_weather_api.enums_exception.enums;

public enum DAY {
    TODAY, TOMORROW, AFTERTOMORROW, ALL;

    public static int getIndex(DAY day) {
        return switch (day) {
            case TODAY -> 0;
            case TOMORROW -> 1;
            case AFTERTOMORROW -> 2;
            case ALL -> -1;
        };
    }
}
