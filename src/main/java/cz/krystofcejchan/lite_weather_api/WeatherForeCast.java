package cz.krystofcejchan.lite_weather_api;

public class WeatherForeCast {

    private final String location;

    public WeatherForeCast(String location) {
        this.location = location;
    }

    /**
     * @return String value of user's city input
     */
    public String getLocation() {
        return location;
    }

}
