package cz.krystofcejchan.lite_weather_lib.weather_objects.subparts.nearest_area;

import cz.krystofcejchan.lite_weather_lib.UtilityClass;
import cz.krystofcejchan.lite_weather_lib.enums_exception.exceptions.CouldNotFindLocation;
import cz.krystofcejchan.lite_weather_lib.weather_objects.MethodRefPrint;
import cz.krystofcejchan.lite_weather_lib.weather_objects.subparts.nearest_area.details.AreaInfo;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

/**
 * Information about the area where the weather measurement was performed
 *
 * @author krystof-cejchan
 * @version 17
 */
public final class NearestArea {
    private final String country;
    /**
     * object containing more info for {@link NearestArea}
     */
    private final AreaInfo areaInfo;

    public NearestArea(@NotNull String location) throws CouldNotFindLocation {
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

    /**
     * @return returns country if possible
     */
    public String getCountry() {
        return country;
    }

    /**
     * @return AreaInfo class containing more data
     */
    public AreaInfo getAreaInfo() {
        return areaInfo;
    }

    /**
     * prints current object.toString to the console
     */
    public void print() {
        new MethodRefPrint<>(this).print();
    }

    @Override
    public String toString() {
        return "--NearestArea--" +
                "\ncountry='" + country +
                "\nareaInfo=" + areaInfo.toString();
    }
}
