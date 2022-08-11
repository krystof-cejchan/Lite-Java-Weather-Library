package cz.krystofcejchan.lite_weather_api.weather_objects.subparts.nearest_area.helpers;

import java.io.IOException;

public final class AreaInfo {
    private final String name;
    private final String latitude;
    private final String longitude;
    private final String population;
    private final String region;

    public AreaInfo( String name, String latitude, String longitude, String population, String region) throws IOException {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.population = population;
        this.region = region;
    }

    public String getName() {
        return name;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getPopulation() {
        return population;
    }

    public String getRegion() {
        return region;
    }
}
