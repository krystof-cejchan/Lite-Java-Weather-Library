package cz.krystofcejchan.lite_weather_lib;

import com.google.common.net.UrlEscapers;
import cz.krystofcejchan.lite_weather_lib.enums_exception.exceptions.CannotCreateInstance;
import cz.krystofcejchan.lite_weather_lib.weather_objects.subparts.forecast.days.hour.ForecastAtHour;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Utility Class representing <b>design pattern</b> of the same name
 */
public class UtilityClass {

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

    public static JSONObject getJson(String location) throws IOException {
        return new JSONObject(IOUtils.toString(
                new URL(UrlEscapers
                        .urlFragmentEscaper().escape("https://wttr.in/" + location + "?format=j1")),
                StandardCharsets.UTF_8));
    }

    public static class Storage {

        /**
         * storing all forecasts
         */
        private static final List<ForecastAtHour> listOfAllDaysAndItsTimes = new ArrayList<>();

        public static List<ForecastAtHour> getListOfAllDaysAndItsTimes() {
            return listOfAllDaysAndItsTimes;
        }

        public static void addToListOfAllDaysAndItsTimes(ForecastAtHour forecast) {
            if (listOfAllDaysAndItsTimes.stream().map(ForecastAtHour::getDay).toList().contains(forecast.getDay())
                    && listOfAllDaysAndItsTimes.stream().map(ForecastAtHour::getTime).toList().contains(forecast.getTime()))
                return;

            listOfAllDaysAndItsTimes.add(forecast);
        }

        public static void clearList() {
            listOfAllDaysAndItsTimes.clear();
        }

        public static void removeElement(ForecastAtHour forecast) {
            listOfAllDaysAndItsTimes.remove(forecast);
        }
    }

    public static class WebPageReader {
        /**
         * checks whether param is link or not
         *
         * @param link web URL
         * @return true if link is truly link, else false
         */
        public static boolean isLink(String link) {
            String urlRegex = "((http://|https://)?(www.)?(([a-zA-Z0-9-]){2,}\\.){1,4}([a-zA-Z]){2,6}(/([a-zA-Z-_/.0-9#:?=&;,]*)?)?)";
            return Pattern.compile(urlRegex).matcher(link).find();
        }

        /**
         * gets and returns text from webpage
         *
         * @param webUrl webpage url
         * @return text from webpage
         */
        public static String getTextFromWebpage(String webUrl) {
            try {
                if (!WebPageReader.isLink(webUrl)) return null;
                Scanner sc = new Scanner(new URL(webUrl).openStream());
                StringBuilder sb = new StringBuilder();
                while (sc.hasNext()) {
                    sb.append(sc.next());
                }
                sc.close();
                return sb.toString().replaceAll("<[^>]*>", "");
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

}
