package cz.krystofcejchan.lite_weather_lib.maven_status

import cz.krystofcejchan.lite_weather_lib.UtilityClass
import java.util.*

open class Up {
    companion object {
        protected val isRepo1_maven_orgUp: Boolean
            protected get() = Objects.requireNonNull(
                UtilityClass.WebPageReader
                    .getTextFromWebpage("https://repo1.maven.org/maven2/cz/")
            ).contains("krystofcejchan")
        protected val isMyRepoUp: Boolean
            protected get() = Objects.requireNonNull(
                UtilityClass.WebPageReader
                    .getTextFromWebpage("https://repo1.maven.org/maven2/cz/krystofcejchan/")
            ).contains("lite_weather_library")
    }
}