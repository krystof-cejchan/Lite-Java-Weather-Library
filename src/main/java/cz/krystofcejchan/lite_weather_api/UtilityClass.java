package cz.krystofcejchan.lite_weather_api;

import cz.krystofcejchan.lite_weather_api.enums_exception.exceptions.CannotCreateInstance;
import cz.krystofcejchan.lite_weather_api.weather_objects.subparts.forecast.days.hour.ForecastAtHour;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class UtilityClass {
    public static List<ForecastAtHour> listOfAllDaysAndItsTimes = new ArrayList<>();

    private UtilityClass() {
        throw new CannotCreateInstance("This class serves as a utility class according to the design pattern of Utility Class");
    }

    public static LocalTime stringToLocalTime(StringBuilder time) {
        int[] hour_minutes = new int[2];
        boolean pm = time.toString().contains("PM");
        for (int i = 0; i < hour_minutes.length; i++) {
            hour_minutes[i] = Integer.parseInt((time.substring(0, time.indexOf(hour_minutes.length - 1 == i ? " " : ":"))));
            time.replace(0, time.indexOf(hour_minutes.length - 1 == i ? " " : ":") + 1, "");
        }
        if (pm && hour_minutes[0] != 12)
            hour_minutes[0] += 12;

        return LocalTime.of(hour_minutes[0], hour_minutes[1]);
    }

    public static LocalDateTime stringToDateTime(StringBuilder date) {

        int[] year_month_day = new int[3];
        int[] hour_minutes = new int[2];
        for (int i = 0; i < year_month_day.length; i++) {
            year_month_day[i] = Integer.parseInt((date.substring(0, date.indexOf(year_month_day.length - 1 == i ? " " : "-"))));
            date.replace(0, date.indexOf(year_month_day.length - 1 == i ? " " : "-") + 1, "");
        }

        boolean pm = date.toString().contains("PM");
        for (int i = 0; i < hour_minutes.length; i++) {
            hour_minutes[i] = Integer.parseInt((date.substring(0, date.indexOf(hour_minutes.length - 1 == i ? " " : ":"))));
            date.replace(0, date.indexOf(hour_minutes.length - 1 == i ? " " : ":") + 1, "");
        }
        if (pm && hour_minutes[0] != 12)
            hour_minutes[0] += 12;

        return LocalDateTime.of(
                year_month_day[0],
                year_month_day[1],
                year_month_day[2],
                hour_minutes[0],
                hour_minutes[1]);
    }

    public static LocalDate stringToDate(StringBuilder date) {
        int[] year_month_day = new int[3];
        for (int i = 0; i < year_month_day.length; i++) {
            year_month_day[i] = Integer.parseInt((date.substring(0, year_month_day.length - 1 == i ? date.length() : date.indexOf("-"))));
            date.replace(0, year_month_day.length - 1 == i ? date.length() : date.indexOf("-") + 1, "");
        }
        return LocalDate.of(year_month_day[0], year_month_day[1], year_month_day[2]);
    }
}
