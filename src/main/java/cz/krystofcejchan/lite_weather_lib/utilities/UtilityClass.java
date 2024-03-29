package cz.krystofcejchan.lite_weather_lib.utilities;

import com.google.common.net.UrlEscapers;
import cz.krystofcejchan.lite_weather_lib.enums_exception.exceptions.CannotCreateInstance;
import cz.krystofcejchan.lite_weather_lib.enums_exception.exceptions.CouldNotFindLocation;
import cz.krystofcejchan.lite_weather_lib.enums_exception.exceptions.WeatherDataNotAccessible;
import cz.krystofcejchan.lite_weather_lib.weather_objects.subparts.forecast.days.hour.ForecastAtHour;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Utility Class representing <b>design pattern</b> of the same name
 */
public class UtilityClass {

    private UtilityClass() {
        throw new CannotCreateInstance("This class serves as a utility class according to the design pattern of Utility Class");
    }

    /**
     * returns {@link LocalTime} from {@link StringBuilder}.toString; can be null if {@link StringBuilder} does not contain any numbers or is null
     *
     * @param time {@link StringBuilder} as text
     * @return LocalTime if possible, else null
     */
    @Nullable
    public static LocalTime stringToLocalTime(@NotNull StringBuilder time) {
        if (!IsNumeric.containsNumbers(time.toString())) return null;
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

    public static LocalDateTime stringToDateTime(@NotNull StringBuilder date) {

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

    public static LocalDate stringToDate(@NotNull StringBuilder date) {
        int[] year_month_day = new int[3];
        for (int i = 0; i < year_month_day.length; i++) {
            year_month_day[i] = Integer.parseInt((date.substring(0, year_month_day.length - 1 == i ? date.length() : date.indexOf("-"))));
            date.replace(0, year_month_day.length - 1 == i ? date.length() : date.indexOf("-") + 1, "");
        }
        return LocalDate.of(year_month_day[0], year_month_day[1], year_month_day[2]);
    }

    public static JSONObject getJson(@NotNull String location) throws CouldNotFindLocation {
        try {
            String jsonSource = IOUtils.toString(
                    new URL(UrlEscapers
                            .urlFragmentEscaper().escape("https://wttr.in/" + location + "?format=j1")),
                    StandardCharsets.UTF_8);
            return new JSONObject(jsonSource);
        } catch (IOException e) {
            throw new CouldNotFindLocation("It seems the location you entered could not be found");
        }

    }

    /**
     * Storing saved data
     */
    public static class Storage {

        /**
         * storing all forecasts
         */
        private static final List<ForecastAtHour> listOfAllDaysAndItsTimes = new ArrayList<>();

        @NotNull
        @Contract(" -> new")
        public static List<ForecastAtHour> getListOfAllDaysAndItsTimes() {
            return new ArrayList<>(listOfAllDaysAndItsTimes);
        }

        public static void addToListOfAllDaysAndItsTimes(@NotNull ForecastAtHour forecast) {
            if (listOfAllDaysAndItsTimes.stream().map(ForecastAtHour::getDay).collect(Collectors.toList()).contains(forecast.getDay())
                    && listOfAllDaysAndItsTimes.stream().map(ForecastAtHour::getTime).collect(Collectors.toList()).contains(forecast.getTime()))
                return;

            listOfAllDaysAndItsTimes.add(forecast);
        }

        public static void clearList() {
            listOfAllDaysAndItsTimes.clear();
        }

        public static void removeElement(@NotNull ForecastAtHour forecast) {
            listOfAllDaysAndItsTimes.remove(forecast);
        }
    }

    /**
     * class for reading webpages
     */
    public static class WebPageReader {
        /**
         * checks whether param is link or not
         *
         * @param link web URL
         * @return true if link is truly link, else false
         */
        public static boolean isLink(@NotNull String link) {
            String urlRegex = "((http://|https://)?(www.)?(([a-zA-Z0-9-]){2,}\\.){1,4}([a-zA-Z]){2,6}(/([a-zA-Z-_/.0-9#:?=&;,]*)?)?)";
            return Pattern.compile(urlRegex).matcher(link).find();
        }

        /**
         * gets and returns text from webpage
         *
         * @param webUrl webpage url
         * @return text from webpage
         */
        public static String getTextFromWebpage(@NotNull String webUrl) {
            try {
                if (!isLink(webUrl)) return null;
                webUrl = webUrl.replace(" ", "%20");
                return IOUtils.toString(URI.create(webUrl), StandardCharsets.UTF_8);
            } catch (IOException e) {
                throw new WeatherDataNotAccessible("Weather data could not be accessed; try again later");
            }
        }
    }

}
