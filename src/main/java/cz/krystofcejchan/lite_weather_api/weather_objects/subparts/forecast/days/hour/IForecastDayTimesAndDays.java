package cz.krystofcejchan.lite_weather_api.weather_objects.subparts.forecast.days.hour;

import cz.krystofcejchan.lite_weather_api.UtilityClass;
import cz.krystofcejchan.lite_weather_api.enums_exception.enums.DAY;
import cz.krystofcejchan.lite_weather_api.enums_exception.enums.TIME;
import cz.krystofcejchan.lite_weather_api.enums_exception.exceptions.NoDataFoundForThisDay;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public interface IForecastDayTimesAndDays {

    public abstract DAY getDay();

    public abstract TIME[] getTime();

    ForecastAtHour getForecastByTime(TIME time);

    public default void addHour(ForecastAtHour forecast, @NotNull List<ForecastAtHour> forecastHourlyList) {
        if (forecastHourlyList.stream().noneMatch(it -> it.equals(forecast))) {
            forecastHourlyList.add(forecast);
            UtilityClass.listOfAllDaysAndItsTimes.add(forecast);
        }

        UtilityClass.listOfAllDaysAndItsTimes = UtilityClass.listOfAllDaysAndItsTimes.stream().distinct().collect(Collectors.toList());

    }

    public static ForecastAtHour getMatchingObjectFrom(DAY day, TIME time) {
        for (ForecastAtHour f : UtilityClass.listOfAllDaysAndItsTimes) {
            if (f.getTime().equals(time) && f.getDay().equals(day))
                return f;
        }
        throw new NoDataFoundForThisDay().addTime("No data found for such day and time");
    }
}
