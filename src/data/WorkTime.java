package data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkTime {

    private long timeWorkAtShortPause;
    private long timeWorkAtLongPause;
    private long timeWorkEnd;
}
