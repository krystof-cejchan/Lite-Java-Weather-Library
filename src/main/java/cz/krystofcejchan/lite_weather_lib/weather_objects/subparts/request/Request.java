package cz.krystofcejchan.lite_weather_lib.weather_objects.subparts.request;

import cz.krystofcejchan.lite_weather_lib.UtilityClass;
import cz.krystofcejchan.lite_weather_lib.enums_exception.exceptions.NotFoundLocation;
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

    public Request(String location) throws NotFoundLocation {
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
        MethodRefPrint<Request> a = new MethodRefPrint<>(this);
        a.print();
    }

    @Override
    public String toString() {
        return "Request{" +
                "query='" + query + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
