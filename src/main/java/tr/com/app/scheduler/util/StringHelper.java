package tr.com.app.scheduler.util;

/**
 * The type String helper.
 */
public final class StringHelper {

    private StringHelper() {
    }

    /**
     * String append string.
     *
     * @param arr the arr
     * @return the string
     */
    public static String stringAppend(Object... arr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Object str : arr) {
            stringBuilder.append(str);
        }
        return stringBuilder.toString();
    }

}
