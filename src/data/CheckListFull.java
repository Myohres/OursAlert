package data;

import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;
import java.util.List;

@Getter
@Setter
public class CheckListFull {

    private Calendar hourSessionStart;
    private Calendar hourSessionEnd;
    private List<Pause> pauseList;
    private List<Calendar> checkList;

}
