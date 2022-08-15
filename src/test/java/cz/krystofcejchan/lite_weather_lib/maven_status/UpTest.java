package cz.krystofcejchan.lite_weather_lib.maven_status;

import org.junit.jupiter.api.Test;

class UpTest extends Up {
    @Test
    void runTests() {
        System.out.println(Up.Companion.isRepo1_maven_orgUp());
        System.out.println(Up.Companion.isMyRepoUp());
    }

}