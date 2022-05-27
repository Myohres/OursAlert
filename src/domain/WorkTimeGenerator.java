package domain;

import data.WorkHour;
import data.WorkTime;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class WorkTimeGenerator {

    public void run(WorkHour workHour) {
        WorkTime workTime = workTimeCalculator(workHour);
        workTimeDisplay(workTime);
    }

    public WorkTime workTimeCalculator(final WorkHour workHour) {
        System.out.println("");
        System.out.println("*** WortTimeCalculator ***");
        System.out.println("");
        System.out.println("Calcul des temps de travails en cour...");
        Calendar calHourWorkStart = workHour.getCalHourWorkStart();
        Calendar calHourShortPauseStart = workHour.getCalHourShortPauseStart();
        Calendar calHourShortPauseEnd = workHour.getCalHourShortPauseEnd();
        Calendar calHourLongPauseStart = workHour.getCalHourLongPauseStart();
        Calendar calHourWorkEnd = workHour.getCalHourWorkEnd();
        Calendar calHourLongPauseEnd = workHour.getCalHourLongPauseEnd();

        long timeWorkAtShortPause = (calHourShortPauseStart.getTimeInMillis() - calHourWorkStart.getTimeInMillis());
        timeWorkAtShortPause = timeWorkAtShortPause / 60 / 1000;
        long  timeWorkAtLongPause = calHourLongPauseStart.getTimeInMillis() - calHourShortPauseEnd.getTimeInMillis();
        timeWorkAtLongPause = timeWorkAtLongPause / 60 / 1000;
        timeWorkAtLongPause = timeWorkAtLongPause + timeWorkAtShortPause;
        long  timeWorkEnd = calHourWorkEnd.getTimeInMillis() - calHourLongPauseEnd.getTimeInMillis();
        timeWorkEnd = timeWorkEnd / 60 / 1000;
        timeWorkEnd = timeWorkEnd + timeWorkAtLongPause;

        WorkTime workTime = new WorkTime();
        workTime.setTimeWorkAtShortPause(timeWorkAtShortPause);
        workTime.setTimeWorkAtLongPause(timeWorkAtLongPause);
        workTime.setTimeWorkEnd(timeWorkEnd);
        return workTime;
    }

    public void workTimeDisplay(WorkTime workTime) {
        System.out.println("");
        System.out.println("*** WortTime display ***");
        System.out.println("");

        SimpleDateFormat hourFormat = new SimpleDateFormat("mm");
        String timeWorkAtShortPause = hourFormat.format(workTime.getTimeWorkAtShortPause());
        String timeWorkAtLongPause = hourFormat.format(workTime.getTimeWorkAtLongPause());
        String timeWorkEnd = hourFormat.format(workTime.getTimeWorkEnd());

        System.out.println("Temps de travail effectué au début de la première pause : " +timeWorkAtShortPause);
        System.out.println("Temps de travail effectué au début de la seconde pause : " +timeWorkAtLongPause);
        System.out.println("Temps de travail effectué à la fin de production : " +timeWorkEnd);

    }
}
