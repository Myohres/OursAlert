package data;

import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;


@Getter
@Setter
public class WorkHour {

    private Calendar calendarInitial;

    private Calendar calHourWorkStart;
    private Calendar calHourWorkEnd;
    private Calendar calHourShortPauseStart;
    private Calendar calHourShortPauseEnd;
    private Calendar calHourLongPauseStart;
    private Calendar calHourLongPauseEnd;

}
