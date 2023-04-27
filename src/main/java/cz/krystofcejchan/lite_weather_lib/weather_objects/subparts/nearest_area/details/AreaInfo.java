package cz.krystofcejchan.lite_weather_lib.weather_objects.subparts.nearest_area.details;

import cz.krystofcejchan.lite_weather_lib.weather_objects.MethodRefPrint;

/**
 * record <br>
 * more info for {@link cz.krystofcejchan.lite_weather_lib.weather_objects.subparts.nearest_area.NearestArea}
 *
 * @author krystof-cejchan
 */
public class AreaInfo {
    final private String name, latitude, longitude, population, region;

    public AreaInfo(String name, String latitude, String longitude, String population, String region) {
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

    /**
     * prints current object.toString to the console
     */
    public void print() {
        new MethodRefPrint<>(this).print();
    }

    @Override
    public String toString() {
        return "\n--AreaInfo--" +
                "\nname=" + name +
                "\nlatitude=" + latitude +
                "\nlongitude=" + longitude +
                "\npopulation=" + population +
                "\nregion=" + region;
    }
}
