package cz.krystofcejchan.lite_weather_api.webpage_reader;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Pattern;

public class WebPageReader {
    /**
     * checks whether param is link or not
     *
     * @param link web URL
     * @return true if link is truly link, else false
     */
    public static boolean isLink(String link) {
        String urlRegex = "((http://|https://)?(www.)?(([a-zA-Z0-9-]){2,}\\.){1,4}([a-zA-Z]){2,6}(/([a-zA-Z-_/.0-9#:?=&;,]*)?)?)";
        return Pattern.compile(urlRegex).matcher(link).find();
    }

    /**
     * gets and returns text from webpage
     *
     * @param webUrl webpage url
     * @return text from webpage
     */
    public static String getTextFromWebpage(String webUrl) {
        try {
            if (!isLink(webUrl)) return null;
            Scanner sc = new Scanner(new URL(webUrl).openStream());
            StringBuilder sb = new StringBuilder();
            while (sc.hasNext()) {
                sb.append(sc.next());
            }
            sc.close();
            return sb.toString().replaceAll("<[^>]*>", "");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
