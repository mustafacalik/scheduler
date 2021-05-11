package tr.com.app.scheduler.util;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public final class TimeHelper {

    private TimeHelper() {
    }

    public static String timeFormat(String time) {
        return time.replace(":", "");
    }

    public static int getMinuteBetweenStartTimeAndEndTime(String start, String end){
        return (int) ChronoUnit.MINUTES.between(LocalTime.parse(start), LocalTime.parse(end));
    }

}
