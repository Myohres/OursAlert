package data;

import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;

@Getter
@Setter
public class Pause {

    Calendar hourPauseStart;
    Calendar hourPauseEnd;
    long durationPauseMilliSec;
    int durationPauseMn;
}
