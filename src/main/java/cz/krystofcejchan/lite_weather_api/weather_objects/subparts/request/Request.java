package cz.krystofcejchan.lite_weather_api.weather_objects.subparts.request;

import cz.krystofcejchan.lite_weather_api.enums_exception.enums.DAY;
import cz.krystofcejchan.lite_weather_api.enums_exception.enums.TIME;
import cz.krystofcejchan.lite_weather_api.weather_objects.WeatherObject;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;

/**
 * request data
 */
public final class Request extends WeatherObject<Request> {
    private final String query;
    private final String type;

    public Request(String location) throws IOException {
        super(location, new TIME[]{TIME.ALL}, DAY.ALL);
        JSONObject request = super.getJson().getJSONArray("request").getJSONObject(0);
        query = request.getString("query");
        type = request.getString("type");
    }

    public String getQuery() {
        return query;
    }

    public String getType() {
        return type;
    }

    @Override
    public @NotNull Request getObject() throws IOException {
        return new Request(super.getLocation());
    }


    @Override
    public String toString() {
        return "Request{" +
                "query='" + query + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
