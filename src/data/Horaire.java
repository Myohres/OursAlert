package data;

import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;

@Getter
@Setter
public class Horaire {

    private int dayWorkNumber;
    private int amPm;
    private int hourWorkStart;
    private int hourWorkEnd;
}
