package cz.krystofcejchan.lite_weather_lib.weather_objects.subparts.current_weather;

import cz.krystofcejchan.lite_weather_lib.utilities.UtilityClass;
import cz.krystofcejchan.lite_weather_lib.enums_exception.exceptions.CouldNotFindLocation;
import cz.krystofcejchan.lite_weather_lib.weather_objects.MethodRefPrint;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;

/**
 * Current weather / the latest weather data
 *
 * @author krystof-cejchan
 * @version 17
 */
public final class CurrentCondition {

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

    /**
     * Constructor required to create this object of this class
     *
     * @param location {@link String} location / area you want to get the forecast for
     */
    public CurrentCondition(@NotNull String location) throws CouldNotFindLocation {

        //main json object for current condition
        JSONObject current_condition = UtilityClass.getJson(location).getJSONArray("current_condition").getJSONObject(0);


        //calculating date and time
        StringBuilder locObsDateT = new StringBuilder(current_condition.getString("localObsDateTime").trim().toUpperCase(Locale.ROOT));
        StringBuilder obsT = new StringBuilder(current_condition.getString("observation_time")
                .trim()
                .toUpperCase(Locale.ROOT));

        localObsDateTime = UtilityClass.stringToDateTime(locObsDateT);

        // -----  ------------ ----------- -------------

        //class fields
        feelsLikeC = current_condition.getInt("FeelsLikeC");
        feelsLikeF = current_condition.getInt("FeelsLikeF");
        cloudCover = current_condition.getInt("cloudcover");
        humidity = current_condition.getInt("humidity");
        observationTime = UtilityClass.stringToLocalTime(obsT);
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

    /**
     * @return Local date and time {@link LocalDateTime} at the provided location
     */
    public LocalDateTime getLocalObsDateTime() {
        return localObsDateTime;
    }

    /**
     * @return Local time {@link LocalTime} of the observation
     */
    public LocalTime getObservationTime() {
        return observationTime;
    }

    /**
     * Precipitation is any liquid or frozen water that forms in the atmosphere and falls to the Earth.<br>
     * It is one of the three main steps of the global water cycle.<br><br>
     * * <a href="https://education.nationalgeographic.org/resource/precipitation"><i>source</i></a>
     *
     * @return precipitation in inches
     */
    public double getPrecipInches() {
        return precipInches;
    }

    /**
     * Precipitation is any liquid or frozen water that forms in the atmosphere and falls to the Earth.<br>
     * It is one of the three main steps of the global water cycle.<br><br>
     * * <a href="https://education.nationalgeographic.org/resource/precipitation"><i>source</i></a>
     *
     * @return precipitation in millimeters
     */

    public double getPrecipMM() {
        return precipMM;
    }

    /**
     * The air around you has weight, and it presses against everything it touches. That pressure is called atmospheric pressure, or air pressure.
     * <br><br>
     * <a href="https://education.nationalgeographic.org/resource/atmospheric-pressure"><i>source</i></a>
     *
     * @return Atmospheric Pressure in Millibars
     */
    public int getPressure() {
        return pressure;
    }

    /**
     * The air around you has weight, and it presses against everything it touches. That pressure is called atmospheric pressure, or air pressure.
     * <br><br>
     * <a href="https://education.nationalgeographic.org/resource/atmospheric-pressure"><i>source</i></a>
     *
     * @return Atmospheric Pressure in inches
     */
    public int getPressureInches() {
        return pressureInches;
    }

    /**
     * @return current temperature in Celsius
     */

    public int getTemp_C() {
        return temp_C;
    }

    /**
     * @return current temperature in Fahrenheit
     */
    public int getTemp_F() {
        return temp_F;
    }

    /**
     * <a href="https://www.who.int/news-room/questions-and-answers/item/radiation-the-ultraviolet-(uv)-index"><i>for more info see this</i></a>
     *
     * @return UV index
     */
    public int getUvIndex() {
        return uvIndex;
    }

    /**
     * @return how far can you see in kilometers
     */
    public int getVisibility() {
        return visibility;
    }

    /**
     * @return how far can you see in miles
     */
    public int getVisibilityMiles() {
        return visibilityMiles;
    }

    /**
     * @return weather code
     */
    public int getWeatherCode() {
        return weatherCode;
    }

    /**
     * examples: <b>Cloudy, Sunny, Snowy, Heavy Rainstorm</b>...
     *
     * @return weather description
     */
    public String getWeatherDescription() {
        return weatherDescription;
    }

    /**
     * examples: <b>west, south, south-east</b>...
     *
     * @return direction of wind
     */
    public String getWindDir16Point() {
        return windDir16Point;
    }

    /**
     * @return wind direction degree
     */
    public int getWinDirDegree() {
        return winDirDegree;
    }

    /**
     * @return wind speed in kilometers per hour
     */
    public int getWindSpeedKmph() {
        return windSpeedKmph;
    }

    /**
     * @return wind speed in miles per hour
     */
    public int getWindSpeedMiles() {
        return windSpeedMiles;
    }

    /**
     * prints current object.toString to the console
     */
    public void print() {
        new MethodRefPrint<>(this).print();
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
}
