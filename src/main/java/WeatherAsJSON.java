import unseenlogic.webpage_reader.WebPageReader;

public class WeatherAsJSON extends WeatherForeCast implements WeatherForecasterFormat {

    private String JsonAsText = "";

    public WeatherAsJSON(String location) {
        super(location);
        this.JsonAsText = WebPageReader.getTextFromWebpage("https://wttr.in/" + location + "?format=j1");

    }

    public String getJsonAsText() {
        return JsonAsText;
    }

    @Override
    public String getOutput() {
        return getJsonAsText();
    }

    @Override
    public String getLocation() {
        return super.getLocation();
    }
}
