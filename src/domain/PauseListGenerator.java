package domain;

import data.WorkHour;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PauseListGenerator {

    public List<Calendar> pauseListGenerator(WorkHour workHour) {
        List<Calendar> pauseList = new ArrayList<>();
        pauseList.add(workHour.getCalHourShortPauseStart());
        pauseList.add(workHour.getCalHourShortPauseEnd());
        pauseList.add(workHour.getCalHourLongPauseStart());
        pauseList.add(workHour.getCalHourLongPauseEnd());
        return pauseList;
    }
}
