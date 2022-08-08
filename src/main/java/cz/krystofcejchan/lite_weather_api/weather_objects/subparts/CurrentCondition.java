package cz.krystofcejchan.lite_weather_api.weather_objects.subparts;

import cz.krystofcejchan.lite_weather_api.enums.DAY;
import cz.krystofcejchan.lite_weather_api.enums.TIME;
import cz.krystofcejchan.lite_weather_api.weather_objects.WeatherObject;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;

import static cz.krystofcejchan.lite_weather_api.enums.TIME.ALL;

/**
 * Current weather / the latest weather data
 */
public final class CurrentCondition extends WeatherObject<CurrentCondition> {

    private final int feelsLikeC;
    private final int feelsLikeF;
    private final int cloudCover;
    private final int humidity;
    /**
     * Data and time in the observed area
     */
    private final LocalDateTime localObsDateTime;
    /**
     * observation time
     */
    private final LocalTime observationTime;
    /**
     * precipitation
     */
    private final double precipInches, precipMM;
    private final int pressure;
    private final int pressureInches;
    private final int temp_C, temp_F;
    private final int uvIndex;
    private final int visibility;
    private final int visibilityMiles;
    private final int weatherCode;
    /**
     * Sunny, cloudy, snowy etc.
     */
    private final String weatherDescription;
    /**
     * North, south, southwest etc.
     */
    private final String windDir16Point;
    private final int winDirDegree;
    private final int windSpeedKmph;
    private final int windSpeedMiles;


//    public static WeatherForecasterFormat<CurrentCondition> getDataObject(String location) throws IOException {
//        return new CurrentCondition(location);
//    }

    /**
     * Constructor required to create this object of this class
     *
     * @param location {@link String} location / area you want to get the forecast for
     * @throws IOException if data are unavailable
     */
    public CurrentCondition(String location) throws IOException {
        super(location, new TIME[]{ALL}, DAY.ALL);

        //main json object for current condition
        JSONObject current_condition = super.getJson().getJSONArray("current_condition").getJSONObject(0);


        //calculating date and time
        StringBuilder locObsDateT = new StringBuilder(current_condition.getString("localObsDateTime").trim().toUpperCase(Locale.ROOT));
        int[] year_month_day = new int[3];
        int[] hour_minutes = new int[2];
        for (int i = 0; i < year_month_day.length; i++) {
            year_month_day[i] = Integer.parseInt((locObsDateT.substring(0, locObsDateT.indexOf(year_month_day.length - 1 == i ? " " : "-"))));
            locObsDateT.replace(0, locObsDateT.indexOf(year_month_day.length - 1 == i ? " " : "-") + 1, "");
        }

        boolean pm = locObsDateT.toString().contains("PM");
        for (int i = 0; i < hour_minutes.length; i++) {
            hour_minutes[i] = Integer.parseInt((locObsDateT.substring(0, locObsDateT.indexOf(hour_minutes.length - 1 == i ? " " : ":"))));
            locObsDateT.replace(0, locObsDateT.indexOf(hour_minutes.length - 1 == i ? " " : ":") + 1, "");
        }
        if (pm && hour_minutes[0] != 12)
            hour_minutes[0] += 12;

        localObsDateTime = LocalDateTime.of(
                year_month_day[0],
                year_month_day[1],
                year_month_day[2],
                hour_minutes[0],
                hour_minutes[1]);


        StringBuilder obsT = new StringBuilder(current_condition.getString("observation_time")
                .trim()
                .toUpperCase(Locale.ROOT));
        pm = obsT.toString().contains("PM");

        for (int i = 0; i < hour_minutes.length; i++) {
            hour_minutes[i] = Integer.parseInt((obsT.substring(0, obsT.indexOf(hour_minutes.length - 1 == i ? " " : ":"))));
            obsT.replace(0, obsT.indexOf(hour_minutes.length - 1 == i ? " " : ":") + 1, "");
        }
        if (pm && hour_minutes[0] != 12)
            hour_minutes[0] += 12;
        // -----  ------------ ----------- -------------

        //class fields
        feelsLikeC = current_condition.getInt("FeelsLikeC");
        feelsLikeF = current_condition.getInt("FeelsLikeF");
        cloudCover = current_condition.getInt("cloudcover");
        humidity = current_condition.getInt("humidity");
        observationTime = LocalTime.of(hour_minutes[0], hour_minutes[1]);
        precipInches = current_condition.getDouble("precipInches");
        precipMM = current_condition.getDouble("precipMM");
        pressure = current_condition.getInt("pressure");
        pressureInches = current_condition.getInt("pressureInches");
        temp_C = current_condition.getInt("temp_C");
        temp_F = current_condition.getInt("temp_F");
        uvIndex = current_condition.getInt("uvIndex");
        visibility = current_condition.getInt("visibility");
        visibilityMiles = current_condition.getInt("visibilityMiles");
        weatherCode = current_condition.getInt("weatherCode");
        weatherDescription = current_condition.getJSONArray("weatherDesc").getJSONObject(0).getString("value");
        windDir16Point = current_condition.getString("winddir16Point");
        winDirDegree = current_condition.getInt("winddirDegree");
        windSpeedKmph = current_condition.getInt("windspeedKmph");
        windSpeedMiles = current_condition.getInt("windspeedMiles");
    }

    /**
     * Feels Like Index is a factored mixture of the Wind Chill Factor and the Heat Index.<br>
     * The combination of the heat index and the wind chill factor are denoted collectively by the single<br>
     * terms apparent temperature or relative outdoor temperature or simply Feels Like.
     * <br><br>
     * * <a href="https://www.wunderground.com/maps/temperature/feels-like"><i>source</i></a>
     *
     * @return feels-like temperature in Celsius
     */
    public int getFeelsLikeC() {
        return feelsLikeC;
    }

    /**
     * Feels Like Index is a factored mixture of the Wind Chill Factor and the Heat Index.<br>
     * The combination of the heat index and the wind chill factor are denoted collectively by the single<br>
     * terms apparent temperature or relative outdoor temperature or simply Feels Like.
     * <br><br>
     * * <a href="https://www.wunderground.com/maps/temperature/feels-like"><i>source</i></a>
     *
     * @return feels-like temperature in Fahrenheit
     */
    public int getFeelsLikeF() {
        return feelsLikeF;
    }

    /**
     * Cloud cover is an important component of understanding and predicting the weather.<br>
     * Not only does cloud cover impact sky conditions and inform precipitation predictions,<br>
     * it also helps regulate the temperature that occurs in a region. <br><br>
     * * <a href="https://education.nationalgeographic.org/resource/cloud-cover"><i>source</i></a>
     *
     * @return cloud coverage
     */
    public int getCloudCover() {
        return cloudCover;
    }

    /**
     * Humidity is the amount of water vapor in the air. If there is a lot of water vapor in the air,<br>
     * the humidity will be high. The higher the humidity, the wetter it feels outside. <br><br>
     * * <a href="https://education.nationalgeographic.org/resource/humidity"><i>source</i></a>
     *
     * @return cloud coverage
     */
    public int getHumidity() {
        return humidity;
    }

    public LocalDateTime getLocalObsDateTime() {
        return localObsDateTime;
    }

    public LocalTime getObservationTime() {
        return observationTime;
    }

    public double getPrecipInches() {
        return precipInches;
    }

    public double getPrecipMM() {
        return precipMM;
    }

    public int getPressure() {
        return pressure;
    }

    public int getPressureInches() {
        return pressureInches;
    }

    public int getTemp_C() {
        return temp_C;
    }

    public int getTemp_F() {
        return temp_F;
    }

    public int getUvIndex() {
        return uvIndex;
    }

    public int getVisibility() {
        return visibility;
    }

    public int getVisibilityMiles() {
        return visibilityMiles;
    }

    public int getWeatherCode() {
        return weatherCode;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public String getWindDir16Point() {
        return windDir16Point;
    }

    public int getWinDirDegree() {
        return winDirDegree;
    }

    public int getWindSpeedKmph() {
        return windSpeedKmph;
    }

    public int getWindSpeedMiles() {
        return windSpeedMiles;
    }

    @Override
    public String toString() {
        return "--CurrentCondition--" +
                "\nfeelsLikeC=" + feelsLikeC +
                "\nfeelsLikeF=" + feelsLikeF +
                "\ncloudCover=" + cloudCover +
                "\nhumidity=" + humidity +
                "\nlocalObsDateTime=" + localObsDateTime +
                "\nobservationTime=" + observationTime +
                "\nprecipInches=" + precipInches +
                "\nprecipMM=" + precipMM +
                "\npressure=" + pressure +
                "\npressureInches=" + pressureInches +
                "\ntemp_C=" + temp_C +
                "\ntemp_F=" + temp_F +
                "\nuvIndex=" + uvIndex +
                "\nvisibility=" + visibility +
                "\nvisibilityMiles=" + visibilityMiles +
                "\nweatherCode=" + weatherCode +
                "\nweatherDescription='" + weatherDescription + '\'' +
                "\nwindDir16Point='" + windDir16Point + '\'' +
                "\nwinDirDegree=" + winDirDegree +
                "\nwindSpeedKmph=" + windSpeedKmph +
                "\nwindSpeedMiles=" + windSpeedMiles +
                "\n---";
    }

    @Override
    public @NotNull CurrentCondition getObject() throws IOException {
        return new CurrentCondition(super.getLocation());
    }
}
