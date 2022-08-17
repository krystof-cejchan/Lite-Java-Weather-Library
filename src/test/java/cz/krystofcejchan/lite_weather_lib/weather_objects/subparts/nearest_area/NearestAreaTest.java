package cz.krystofcejchan.lite_weather_lib.weather_objects.subparts.nearest_area;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class NearestAreaTest {

    @Test
    void NearestArea() throws IOException {
        NearestArea nearestArea = new NearestArea("Dallas");
        System.out.println(nearestArea.getCountry() + ", " + nearestArea.getAreaInfo().getRegion() + ", " + nearestArea.getAreaInfo().getName());
        System.out.println(nearestArea.toString());
    }
}