package tr.com.app.scheduler.util;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * The type Time helper.
 */
public final class TimeHelper {

    private TimeHelper() {
    }

    /**
     * Time format string.
     *
     * @param time the time
     * @return the string
     */
    public static String timeFormat(String time) {
        return time.replace(":", "");
    }

    /**
     * Get minute between start time and end time int.
     *
     * @param start the start
     * @param end   the end
     * @return the int
     */
    public static int getMinuteBetweenStartTimeAndEndTime(String start, String end){
        return (int) ChronoUnit.MINUTES.between(LocalTime.parse(start), LocalTime.parse(end));
    }

}
