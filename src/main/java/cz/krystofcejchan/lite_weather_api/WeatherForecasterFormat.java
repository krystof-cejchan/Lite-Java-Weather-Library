package cz.krystofcejchan.lite_weather_api;

import java.io.IOException;

public interface WeatherForecasterFormat<T> {

    T getOutput(String location) throws IOException;



    String getLocation();

}
