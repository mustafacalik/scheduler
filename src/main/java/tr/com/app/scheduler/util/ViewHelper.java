package tr.com.app.scheduler.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * The type View helper.
 */
public final class ViewHelper {

    private ViewHelper(){
    }

    /**
     * Create schedule style string.
     *
     * @param timeSet           the time set
     * @param currentRoomNumber the current room number
     * @return the string
     */
    public static String createScheduleStyle(Set<String> timeSet, int currentRoomNumber) {
        StringBuilder style = new StringBuilder();
        style.append("display:grid;grid-gap:1em;grid-template-rows:[tracks] auto ");
        for (String timeStr : timeSet) {
            style.append("[time-").append(timeStr).append("] 1fr ");
        }
        style.append(";");
        style.append("grid-template-columns:[times] 4em ");
        style.append("[track-1-start] 1fr ");
        for (int i = 1; i < currentRoomNumber; i++) {
            style.append("[track-").append(i).append("-end ");
            style.append("track-").append(i + 1).append("-start] 1fr ");
        }
        style.append("[track-").append(currentRoomNumber).append("-end] 1fr; ");
        return style.toString();
    }


    /**
     * Create room style list.
     *
     * @param currentRoomNumber the current room number
     * @return the list
     */
    public static List<String[]> createRoomStyle(int currentRoomNumber){
        List<String[]> roomStyleList = new ArrayList<>();
        for(int i = 1; i<= currentRoomNumber; i++){
            roomStyleList.add(new String[]{StringHelper.stringAppend("grid-column: track-" , i, "; grid-row: tracks;"), StringHelper.stringAppend("Room " , i) });
        }
        return roomStyleList;
    }


    /**
     * Create time style list.
     *
     * @param timeSet the time set
     * @return the list
     */
    public static List<String[]> createTimeStyle(Set<String> timeSet){
        List<String[]> timeStyleList = new ArrayList<>();
        for (String timeStr : timeSet) {
            timeStyleList.add(new String[]{StringHelper.stringAppend("grid-row: time-", timeStr, ";"), StringHelper.stringAppend(timeStr.substring(0, 2), ":", timeStr.substring(2, 4))});
        }
        return timeStyleList;
    }

}
