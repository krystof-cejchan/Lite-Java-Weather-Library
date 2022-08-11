package cz.krystofcejchan.lite_weather_api.enums_exception.exceptions;

public class NoDataFoundForThisDay extends RuntimeException {
    public NoDataFoundForThisDay(String errorMsg) {
        super(errorMsg);
    }

    public NoDataFoundForThisDay(String errorMsg, Throwable throwable) {
        super(errorMsg, throwable);
    }

    public NoDataFoundForThisDay() {
        super();
    }

    public NoDataFoundForThisDayAndTime addTime(String msg) {
        return new NoDataFoundForThisDayAndTime(msg);
    }
}

class NoDataFoundForThisDayAndTime extends RuntimeException {
    public NoDataFoundForThisDayAndTime(String errorMsg) {
        super(errorMsg);
    }
}
