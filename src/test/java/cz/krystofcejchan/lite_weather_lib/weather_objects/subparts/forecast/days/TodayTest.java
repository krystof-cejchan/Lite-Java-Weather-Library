package cz.krystofcejchan.lite_weather_lib.weather_objects.subparts.forecast.days;

import cz.krystofcejchan.lite_weather_lib.enums_exception.enums.TIME;
import org.junit.jupiter.api.Test;

class TodayTest {

    @Test
    void getlocationTest() {
        System.out.println(new Today("Náchod", TIME.AM_9).getLocation());
    }

}