package cn.mrcsh.Excepiton;

public class WeatherException extends Exception{
    public WeatherException(String message) {
        super(message);
    }

    public WeatherException() {
        super();
    }

    public WeatherException(String message, Throwable cause) {
        super(message, cause);
    }

    public WeatherException(Throwable cause) {
        super(cause);
    }

    protected WeatherException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
