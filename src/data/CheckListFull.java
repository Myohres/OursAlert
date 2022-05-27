package data;

import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;
import java.util.List;

@Getter
@Setter
public class CheckListFull {

    private Calendar hourStart;
    private Calendar hourEnd;
    private Calendar hourPauseOneStart;
    private Calendar hourPauseOneEnd;
    private Calendar hourPauseTwoStart;
    private Calendar hourPauseTwoEnd;
    private List<Calendar> checkList;

}
