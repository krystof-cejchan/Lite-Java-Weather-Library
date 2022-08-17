package cz.krystofcejchan.lite_weather_lib.utilities;

import org.jetbrains.annotations.NotNull;

public class IsNumeric {
    public static boolean containsNumbers(@NotNull String text) {
        return text.isEmpty() || text.matches("\\d+");
    }
}
