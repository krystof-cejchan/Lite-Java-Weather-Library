package cz.krystofcejchan.lite_weather_api.weather_objects.subparts.request;

import cz.krystofcejchan.lite_weather_api.UtilityClass;
import org.json.JSONObject;

import java.io.IOException;

/**
 * request data
 */
public final class Request {
    private final String query;
    private final String type;

    public Request(String location) throws IOException {
        JSONObject request = UtilityClass.getJson(location).getJSONArray("request").getJSONObject(0);
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
    public String toString() {
        return "Request{" +
                "query='" + query + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
