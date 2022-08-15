package cz.krystofcejchan.lite_weather_lib.weather_objects.subparts.forecast.days.hour;

import cz.krystofcejchan.lite_weather_lib.enums_exception.enums.DAY;
import cz.krystofcejchan.lite_weather_lib.enums_exception.enums.TIME;
import cz.krystofcejchan.lite_weather_lib.weather_objects.MethodRefPrint;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

/**
 * Forecast for specific day and hour/time
 *
 * @author krystof-cejchan
 * @version 17
 */
public final class ForecastAtHour {
    private final DAY day;
    private final TIME time;

    private final int dewPointC, dewPointF;
    private final int feelsLikeC, feelsLikeF;
    private final int heatIndexC, heatIndexF;
    private final int windChillC, windChillF;
    private final int windGustKmph, windGustMiles;
    private final int chanceOfFog, chanceOfFrost, chanceOfHighTemperature, chanceOfOvercast, chanceOfRain, chanceOfRemdry,
            chanceOfSnow, chanceOfSunshine, chanceOfThunder, chanceOfWindy;
    private final int cloudCover;
    private final int humidity;
    private final double precipInches, precipMM;
    private final int pressure, pressureInches;
    private final int temperatureC, temperatureF;
    private final int timeInHundreds;
    private final int uvIndex;
    private final int visibility, visibilityMiles;
    private final int weatherCode;
    private final String weatherDescription;
    private final String windDir16Point;
    private final int windDirDegree;
    private final int windSpeedKmph, windSpeedMiles;


    public ForecastAtHour(@NotNull JSONObject startOfJsonObject, @NotNull DAY day, @NotNull TIME time) {
        this.day = day;
        this.time = time;
        JSONObject certHour = startOfJsonObject
                .getJSONArray("weather")
                .getJSONObject(DAY.getIndex(day))
                .getJSONArray("hourly").
                getJSONObject(TIME.getIndex(time));

        this.dewPointC = certHour.getInt("DewPointC");
        this.dewPointF = certHour.getInt("DewPointF");
        this.feelsLikeC = certHour.getInt("FeelsLikeC");
        this.feelsLikeF = certHour.getInt("FeelsLikeF");
        this.heatIndexC = certHour.getInt("HeatIndexC");
        this.heatIndexF = certHour.getInt("HeatIndexF");
        this.windChillC = certHour.getInt("WindChillC");
        this.windChillF = certHour.getInt("WindChillF");
        this.windGustKmph = certHour.getInt("WindGustKmph");
        this.windGustMiles = certHour.getInt("WindGustMiles");
        this.chanceOfFog = certHour.getInt("chanceoffog");
        this.chanceOfFrost = certHour.getInt("chanceoffrost");
        this.chanceOfHighTemperature = certHour.getInt("chanceofhightemp");
        this.chanceOfOvercast = certHour.getInt("chanceofovercast");
        this.chanceOfRain = certHour.getInt("chanceofrain");
        this.chanceOfRemdry = certHour.getInt("chanceofremdry");
        this.chanceOfSnow = certHour.getInt("chanceofsnow");
        this.chanceOfSunshine = certHour.getInt("chanceofsunshine");
        this.chanceOfThunder = certHour.getInt("chanceofthunder");
        this.chanceOfWindy = certHour.getInt("chanceofwindy");
        this.cloudCover = certHour.getInt("cloudcover");
        this.humidity = certHour.getInt("humidity");
        this.precipInches = certHour.getDouble("precipInches");
        this.precipMM = certHour.getDouble("precipMM");
        this.pressure = certHour.getInt("pressure");
        this.pressureInches = certHour.getInt("pressureInches");
        this.temperatureC = certHour.getInt("tempC");
        this.temperatureF = certHour.getInt("tempF");
        this.timeInHundreds = certHour.getInt("time");
        this.uvIndex = certHour.getInt("uvIndex");
        this.visibility = certHour.getInt("visibility");
        this.visibilityMiles = certHour.getInt("visibilityMiles");
        this.weatherCode = certHour.getInt("weatherCode");
        this.weatherDescription = certHour.getJSONArray("weatherDesc").getJSONObject(0).getString("value");
        this.windDir16Point = certHour.getString("winddir16Point");
        this.windDirDegree = certHour.getInt("winddirDegree");
        this.windSpeedKmph = certHour.getInt("windspeedKmph");
        this.windSpeedMiles = certHour.getInt("windspeedMiles");


    }

    /**
     * @return The dew point is the temperature to which air must be cooled to become saturated with water vapor
     */
    public int getDewPointC() {
        return dewPointC;
    }

    /**
     * @return The dew point is the temperature to which air must be cooled to become saturated with water vapor
     */
    public int getDewPointF() {
        return dewPointF;
    }

    public int getFeelsLikeC() {
        return feelsLikeC;
    }

    public int getFeelsLikeF() {
        return feelsLikeF;
    }

    /**
     * @return The heat index, also known as the apparent temperature, is what the temperature feels like to the human body when relative humidity is combined with the air temperature
     */
    public int getHeatIndexC() {
        return heatIndexC;
    }

    /**
     * @return The heat index, also known as the apparent temperature, is what the temperature feels like to the human body when relative humidity is combined with the air temperature
     */
    public int getHeatIndexF() {
        return heatIndexF;
    }

    /**
     * @return Wind chill or windchill is the lowering of body temperature due to the passing-flow of lower-temperature air.
     */
    public int getWindChillC() {
        return windChillC;
    }

    /**
     * @return Wind chill or windchill is the lowering of body temperature due to the passing-flow of lower-temperature air.
     */
    public int getWindChillF() {
        return windChillF;
    }

    /**
     * @return A gust or wind gust is a brief increase in the speed of the wind, usually less than 20 seconds.
     */
    public int getWindGustKmph() {
        return windGustKmph;
    }

    /**
     * @return A gust or wind gust is a brief increase in the speed of the wind, usually less than 20 seconds.
     */
    public int getWindGustMiles() {
        return windGustMiles;
    }

    public int getChanceOfFog() {
        return chanceOfFog;
    }

    public int getChanceOfFrost() {
        return chanceOfFrost;
    }

    public int getChanceOfHighTemperature() {
        return chanceOfHighTemperature;
    }

    public int getChanceOfOvercast() {
        return chanceOfOvercast;
    }

    public int getChanceOfRain() {
        return chanceOfRain;
    }

    public int getChanceOfRemdry() {
        return chanceOfRemdry;
    }

    public int getChanceOfSnow() {
        return chanceOfSnow;
    }

    public int getChanceOfSunshine() {
        return chanceOfSunshine;
    }

    public int getChanceOfThunder() {
        return chanceOfThunder;
    }

    public int getChanceOfWindy() {
        return chanceOfWindy;
    }

    public int getCloudCover() {
        return cloudCover;
    }

    public int getHumidity() {
        return humidity;
    }

    /**
     * @return Precipitation
     */
    public double getPrecipInches() {
        return precipInches;
    }

    /**
     * @return Precipitation
     */
    public double getPrecipMM() {
        return precipMM;
    }

    public int getPressure() {
        return pressure;
    }

    public int getPressureInches() {
        return pressureInches;
    }

    public int getTemperatureC() {
        return temperatureC;
    }

    public int getTemperatureF() {
        return temperatureF;
    }

    public int getTimeInHundreds() {
        return timeInHundreds;
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

    public int getWindDirDegree() {
        return windDirDegree;
    }

    public int getWindSpeedKmph() {
        return windSpeedKmph;
    }

    public int getWindSpeedMiles() {
        return windSpeedMiles;
    }

    public DAY getDay() {
        return day;
    }

    public TIME getTime() {
        return time;
    }

    /**
     * prints current object.toString to the console
     */
    public void print() {
        MethodRefPrint<ForecastAtHour> a = new MethodRefPrint<>(this);
        a.print();
    }

    @Override
    public String toString() {
        return "---ForecastAtHour---" +
                "\nday=" + day +
                "\ntime=" + time +
                "\ndewPointC=" + dewPointC +
                "\ndewPointF=" + dewPointF +
                "\nfeelsLikeC=" + feelsLikeC +
                "\nfeelsLikeF=" + feelsLikeF +
                "\nheatIndexC=" + heatIndexC +
                "\nheatIndexF=" + heatIndexF +
                "\nwindChillC=" + windChillC +
                "\nwindChillF=" + windChillF +
                "\nwindGustKmph=" + windGustKmph +
                "\nwindGustMiles=" + windGustMiles +
                "\nchanceOfFog=" + chanceOfFog +
                "\nchanceOfFrost=" + chanceOfFrost +
                "\nchanceOfHighTemperature=" + chanceOfHighTemperature +
                "\nchanceOfOvercast=" + chanceOfOvercast +
                "\nchanceOfRain=" + chanceOfRain +
                "\nchanceOfRemdry=" + chanceOfRemdry +
                "\nchanceOfSnow=" + chanceOfSnow +
                "\nchanceOfSunshine=" + chanceOfSunshine +
                "\nchanceOfThunder=" + chanceOfThunder +
                "\nchanceOfWindy=" + chanceOfWindy +
                "\ncloudCover=" + cloudCover +
                "\nhumidity=" + humidity +
                "\nprecipInches=" + precipInches +
                "\nprecipMM=" + precipMM +
                "\npressure=" + pressure +
                "\npressureInches=" + pressureInches +
                "\ntemperatureC=" + temperatureC +
                "\ntemperatureF=" + temperatureF +
                "\ntimeInHundreds=" + timeInHundreds +
                "\nuvIndex=" + uvIndex +
                "\nvisibility=" + visibility +
                "\nvisibilityMiles=" + visibilityMiles +
                "\nweatherCode=" + weatherCode +
                "\nweatherDescription='" + weatherDescription + '\'' +
                "\nwindDir16Point='" + windDir16Point + '\'' +
                "\nwindDirDegree=" + windDirDegree +
                "\nwindSpeedKmph=" + windSpeedKmph +
                "\nwindSpeedMiles=" + windSpeedMiles;
    }
}
