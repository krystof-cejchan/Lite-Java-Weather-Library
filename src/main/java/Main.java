public class Main {
    public static void main(String[] args) {
        WeatherForecasterFormat w = new WeatherAsJSON("Náchod");
        System.out.println(w.getOutput());
    }
}
