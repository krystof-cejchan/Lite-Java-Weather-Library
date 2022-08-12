package cz.krystofcejchan.lite_weather_api.weather_objects.subparts.nearest_area;

import cz.krystofcejchan.lite_weather_api.UtilityClass;
import cz.krystofcejchan.lite_weather_api.weather_objects.subparts.nearest_area.helpers.AreaInfo;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Information about the area where the weather measurement was performed
 */
public final class NearestArea {
    private final String country;
    private final AreaInfo areaInfo;

    public NearestArea(String location) throws IOException {
        JSONObject nearest_area = UtilityClass.getJson(location).getJSONArray("nearest_area").getJSONObject(0);
        String v = "value";
        country = nearest_area.getJSONArray("country").getJSONObject(0).getString(v);

        areaInfo = new AreaInfo(nearest_area.getJSONArray("areaName").getJSONObject(0).getString(v),
                nearest_area.getString("latitude"),
                nearest_area.getString("longitude"),
                nearest_area.getString("population"),
                nearest_area.getJSONArray("region").getJSONObject(0).getString(v)
        );


    }

    public String getCountry() {
        return country;
    }

    public AreaInfo getAreaInfo() {
        return areaInfo;
    }

    @Override
    public String toString() {
        return "NearestArea{" +
                "country='" + country + '\'' +
                ", areaInfo=" + areaInfo.toString() +
                '}';
    }
}
