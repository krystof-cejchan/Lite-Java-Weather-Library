package cz.krystofcejchan.lite_weather_api.weather_objects.subparts;

import cz.krystofcejchan.lite_weather_api.enums.DAY;
import cz.krystofcejchan.lite_weather_api.enums.TIME;
import cz.krystofcejchan.lite_weather_api.weather_objects.WeatherObject;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;

/**
 * Weather forecast for today, tomorrow, the day after tomorrow <br>
 * Data can be separated into hours → 12am, 3am, 6am, 9am, 12pm, 3pm, 6pm, 9pm.<br>
 * see {@link TIME}, {@link DAY}<br>
 */
public final class WeatherForecast extends WeatherObject<WeatherForecast> {

    public WeatherForecast(String location, TIME[] times, DAY... days) throws IOException {
        super(location, times, days);
    }

    @Override
    public @NotNull WeatherForecast getObject() throws IOException {
        return new WeatherForecast(super.getLocation(), super.getTimes(), super.getDays());
    }

}
