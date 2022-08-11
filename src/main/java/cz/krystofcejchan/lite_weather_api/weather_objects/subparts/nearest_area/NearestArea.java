package cz.krystofcejchan.lite_weather_api.weather_objects.subparts.nearest_area;

import cz.krystofcejchan.lite_weather_api.enums_exception.enums.DAY;
import cz.krystofcejchan.lite_weather_api.enums_exception.enums.TIME;
import cz.krystofcejchan.lite_weather_api.weather_objects.WeatherObject;
import cz.krystofcejchan.lite_weather_api.weather_objects.subparts.nearest_area.helpers.AreaInfo;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Information about the area where the weather measurement was performed
 */
public final class NearestArea extends WeatherObject<NearestArea> {
    private final String country;
    private final AreaInfo areaInfo;

    public NearestArea(String location) throws IOException {
        super(location, new TIME[]{TIME.ALL}, DAY.ALL);
        JSONObject nearest_area = super.getJson().getJSONArray("nearest_area").getJSONObject(0);
        String v = "value";
        country = nearest_area.getJSONArray("country").getJSONObject(0).getString(v);

        areaInfo = new AreaInfo(nearest_area.getJSONArray("areaName").getJSONObject(0).getString(v),
                nearest_area.getString("latitude"),
                nearest_area.getString("longitude"),
                nearest_area.getString("population"),
                nearest_area.getJSONArray("region").getJSONObject(0).getString(v)
        );


    }

    @Override
    public @NotNull NearestArea getObject() throws IOException {
        return new NearestArea(super.getLocation());
    }

    public String getCountry() {
        return country;
    }

    public AreaInfo getAreaInfo() {
        return areaInfo;
    }
}
