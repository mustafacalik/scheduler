package tr.com.app.scheduler.util;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Utils {

    private Utils() {
    }

    public static String timeFormat(String time) {
        return time.replace(":", "");
    }

    public static String stringAppend(Object... arr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Object str : arr) {
            stringBuilder.append(str);
        }
        return stringBuilder.toString();
    }

    public static int getMinuteBetweenStartTimeAndEndTime(String start, String end){
        return (int) ChronoUnit.MINUTES.between(LocalTime.parse(start), LocalTime.parse(end));
    }

}
