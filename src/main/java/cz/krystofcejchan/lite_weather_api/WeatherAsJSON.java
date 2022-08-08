package cz.krystofcejchan.lite_weather_api;

import cz.krystofcejchan.lite_weather_api.webpage_reader.WebPageReader;

import java.io.IOException;

public class WeatherAsJSON extends WeatherForeCast implements WeatherForecasterFormat {

    private String JsonAsText = "";

    public WeatherAsJSON(String location) {
        super(location);
        this.JsonAsText = WebPageReader.getTextFromWebpage("https://wttr.in/" + location + "?format=j1");
    }

    public String getJsonAsText() {
        return JsonAsText;
    }

    @Override
    public Object getOutput(String location){
        return getJsonAsText();
    }

    @Override
    public String getLocation() {
        return super.getLocation();
    }
}
