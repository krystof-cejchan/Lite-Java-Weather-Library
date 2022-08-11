package cz.krystofcejchan.lite_weather_api;

import cz.krystofcejchan.lite_weather_api.webpage_reader.WebPageReader;

public class WeatherAsJSON extends WeatherForeCast implements WeatherForecasterFormat<String> {

    private String JsonAsText = "";

    public WeatherAsJSON(String location) {
        super(location);
        this.JsonAsText = WebPageReader.getTextFromWebpage("https://wttr.in/" + location + "?format=j1");
    }

    public String getJsonAsText() {
        return JsonAsText;
    }

    @Override
    public String getOutput(String location) {
        return getJsonAsText();
    }

    @Override
    public String getLocation() {
        return super.getLocation();
    }
}
