package cz.krystofcejchan.lite_weather_lib.weather_objects.subparts;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;

class CurrentConditionTest {
    @org.junit.jupiter.api.Test
    void getJsonTest() throws IOException {
        StringBuilder locObsDateT = new StringBuilder(" 2022-08-08 9:10 am ".trim().toUpperCase(Locale.ROOT));
        int[] year_month_day = new int[3];
        int[] hour_minutes = new int[2];
        for (int i = 0; i < year_month_day.length; i++) {
            year_month_day[i] = Integer.parseInt((locObsDateT.substring(0, locObsDateT.indexOf(year_month_day.length - 1 == i ? " " : "-"))));
            locObsDateT.replace(0, locObsDateT.indexOf(year_month_day.length - 1 == i ? " " : "-") + 1, "");
        }
        System.out.println(locObsDateT);
        boolean pm = locObsDateT.toString().contains("PM");
        for (int i = 0; i < hour_minutes.length; i++) {
            hour_minutes[i] = Integer.parseInt((locObsDateT.substring(0, locObsDateT.indexOf(hour_minutes.length - 1 == i ? " " : ":"))));
            locObsDateT.replace(0, locObsDateT.indexOf(hour_minutes.length - 1 == i ? " " : ":") + 1, "");
        }
        if (pm && hour_minutes[0] != 12)
            hour_minutes[0] += 12;

        System.out.println(
                LocalDateTime.of(year_month_day[0],
                        year_month_day[1],
                        year_month_day[2],
                        hour_minutes[0], hour_minutes[1]));

        StringBuilder obsT = new StringBuilder("9:00 AM".trim().toUpperCase(Locale.ROOT));
        pm = obsT.toString().contains("PM");
        for (int i = 0; i < hour_minutes.length; i++) {
            hour_minutes[i] = Integer.parseInt((obsT.substring(0, obsT.indexOf(hour_minutes.length - 1 == i ? " " : ":"))));
            obsT.replace(0, obsT.indexOf(hour_minutes.length - 1 == i ? " " : ":") + 1, "");
        }
        if (pm && hour_minutes[0] != 12)
            hour_minutes[0] += 12;

        System.out.println(LocalTime.of(hour_minutes[0], hour_minutes[1]));


    }

}