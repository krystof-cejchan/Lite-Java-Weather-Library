package cz.krystofcejchan.lite_weather_lib.weather_objects.subparts.nearest_area.details;

import cz.krystofcejchan.lite_weather_lib.weather_objects.MethodRefPrint;

/**
 * record <br>
 * more info for {@link cz.krystofcejchan.lite_weather_lib.weather_objects.subparts.nearest_area.NearestArea}
 *
 * @author krystof-cejchan
 * @version 17
 */
public record AreaInfo(String name, String latitude, String longitude,
                       String population, String region) {
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
