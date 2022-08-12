package cz.krystofcejchan.lite_weather_api.weather_objects.subparts.forecast;

import cz.krystofcejchan.lite_weather_api.enums_exception.enums.DAY;
import cz.krystofcejchan.lite_weather_api.enums_exception.enums.TIME;
import cz.krystofcejchan.lite_weather_api.weather_objects.WeatherObject;
import cz.krystofcejchan.lite_weather_api.weather_objects.subparts.current_weather.CurrentCondition;
import cz.krystofcejchan.lite_weather_api.weather_objects.subparts.forecast.days.Today;
import cz.krystofcejchan.lite_weather_api.weather_objects.subparts.nearest_area.NearestArea;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class WeatherForecastTest {

    @Test
    void getForecastFor() throws IOException {
       CurrentCondition c = new CurrentCondition("Náchod");
        System.out.println(c);

        WeatherObject w = new WeatherObject("Paris", new TIME[]{TIME.AM_9}, DAY.TODAY);
        System.out.println(w.getNearestArea().toString());

        System.out.println(new Today("Náchod", TIME.AM_9, TIME.AM_12));

       System.out.println(new NearestArea("Náchod").getAreaInfo().toString());
    }
}