package cz.krystofcejchan.lite_weather_lib.weather_objects.subparts.request;

import cz.krystofcejchan.lite_weather_lib.UtilityClass;
import cz.krystofcejchan.lite_weather_lib.enums_exception.exceptions.CouldNotFindLocation;
import cz.krystofcejchan.lite_weather_lib.weather_objects.MethodRefPrint;
import org.json.JSONObject;

/**
 * request data
 *
 * @author krystof-cejchan
 * @version 17
 */
public final class Request {
    private final String query;
    private final String type;

    public Request(String location) throws CouldNotFindLocation {
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

    /**
     * prints current object.toString to the console
     */
    public void print() {
        new MethodRefPrint<>(this).print();
    }

    @Override
    public String toString() {
        return "Request{" +
                "query='" + query + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
