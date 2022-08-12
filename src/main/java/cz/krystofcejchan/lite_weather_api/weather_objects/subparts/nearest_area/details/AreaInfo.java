package cz.krystofcejchan.lite_weather_api.weather_objects.subparts.nearest_area.details;

/**
 * record <br>
 * more info for {@link cz.krystofcejchan.lite_weather_api.weather_objects.subparts.nearest_area.NearestArea}
 */
public record AreaInfo(String name, String latitude, String longitude,
                       String population, String region) {
    @Override
    public String toString() {
        return "AreaInfo{" +
                "name='" + name + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", population='" + population + '\'' +
                ", region='" + region + '\'' +
                '}';
    }
}
