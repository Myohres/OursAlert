package data;

import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;
import java.util.List;


@Getter
@Setter
public class WorkHour {

    private Calendar calendarInitial;

    private Calendar calHourSessionStart;
    private Calendar calHourSessionEnd;

    private Calendar calHourPlanningStart;
    private Calendar calHourPlanningEnd;

    private Calendar calHourShortPauseStart;
    private Calendar calHourShortPauseEnd;

    private Calendar calHourLongPauseStart;
    private Calendar calHourLongPauseEnd;

}
