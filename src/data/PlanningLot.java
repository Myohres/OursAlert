package data;

import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;
import java.util.List;

@Getter
@Setter
public class PlanningLot {

    private Calendar hourStart;
    private Calendar hourEnd;
    private List<Calendar> hourPointageList;
}
