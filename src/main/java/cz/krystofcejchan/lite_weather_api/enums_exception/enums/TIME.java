package cz.krystofcejchan.lite_weather_api.enums_exception.enums;

public enum TIME {
    AM_3, AM_6, AM_9, AM_12, PM_3, PM_6, PM_9, PM_12, ALL;

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
}
