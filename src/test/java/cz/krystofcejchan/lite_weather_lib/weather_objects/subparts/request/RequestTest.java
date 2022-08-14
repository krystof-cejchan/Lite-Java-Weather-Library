package cz.krystofcejchan.lite_weather_lib.weather_objects.subparts.request;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class RequestTest {

    @Test
    void testToString() throws IOException {
        Request request = new Request("Oslo");
        System.out.println(request.toString());
    }
}