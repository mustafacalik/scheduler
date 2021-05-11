package tr.com.app.scheduler.util;

public final class StringHelper {

    private StringHelper() {
    }

    public static String stringAppend(Object... arr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Object str : arr) {
            stringBuilder.append(str);
        }
        return stringBuilder.toString();
    }

}
