package domain;

import constant.hour.Friday;
import constant.hour.Week;
import constant.hour.Weekend;
import data.WorkHour;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;


import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HourTimeCalculator {

    private final Calendar calendarInitial = Calendar.getInstance();

    public WorkHour run() {
        WorkHour workHour = workHourGenerator();
        workHourDisplay(workHour);
        return workHour;
    }

    public void workHourDisplay(final WorkHour workHour) {
        SimpleDateFormat dayNameFormat = new SimpleDateFormat("EEEE");
        String dayNameDisplay = dayNameFormat.format(workHour.getCalendarInitial().getTime());

        String morningOrAfternoonDisplay;
        if (calendarInitial.get(Calendar.AM_PM) == Calendar.AM) {
            morningOrAfternoonDisplay = "du matin";
        } else {
            morningOrAfternoonDisplay = "de l'après midi";
        }

        SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");
        String hourWorkStartDisplay = hourFormat.format(workHour.getCalHourWorkStart().getTime());
        String hourShortPauseStartDisplay = hourFormat.format(workHour.getCalHourShortPauseStart().getTime());
        String hourShortPauseEndDisplay = hourFormat.format(workHour.getCalHourShortPauseEnd().getTime());
        String hourLongPauseStartDisplay = hourFormat.format(workHour.getCalHourLongPauseStart().getTime());
        String hourLongPauseEndDisplay = hourFormat.format(workHour.getCalHourLongPauseEnd().getTime());
        String hourWorkEndDisplay = hourFormat.format(workHour.getCalHourWorkEnd().getTime());

        System.out.println("");
        System.out.println("*** WorkHourDisplay ***");
        System.out.println();
        System.out.println("Jour : " + dayNameDisplay);
        System.out.println("Horaires " + morningOrAfternoonDisplay);
        System.out.println("");
        System.out.println("Heure de début travail : " + hourWorkStartDisplay);
        System.out.println("");
        System.out.println("Heure de début de pause courte : " + hourShortPauseStartDisplay);
        System.out.println("heure fin de pause courte : " + hourShortPauseEndDisplay);
        System.out.println("");
        System.out.println("heure de début de pause longue : " + hourLongPauseStartDisplay);
        System.out.println("Heure de fin de pause longue : " + hourLongPauseEndDisplay);
        System.out.println("");
        System.out.println("heure de fin de travail : " + hourWorkEndDisplay);
    }



    public WorkHour workHourGenerator() {
        System.out.println("");
        System.out.println("*** WorkHourGenerator ***");
        System.out.println();
        System.out.println("Detection du jour en cour...");
        System.out.println("Affectation des horaires en cour...");
        int numberDay = calendarInitial.get(Calendar.DAY_OF_WEEK);
        WorkHour workHour;
        switch (numberDay) {
            case  2, 3, 4, 5 -> {
                workHour = sendHourWeek(calendarInitial);
            }
            case 6 -> {
                workHour = sendHourFriday(calendarInitial);
            }
            case 7, 1 -> {
                workHour = sendHourWeekend(calendarInitial);
            }
            default -> throw new IllegalStateException(
                    "Invalid day : " + numberDay);
        }
        workHour.setCalendarInitial(calendarInitial);
        return workHour;
    }

    public WorkHour sendHourWeek(final Calendar calendar) {
        int dayHour = calendar.get(Calendar.HOUR_OF_DAY);
        Calendar calHourWorkStart = Calendar.getInstance();
        Calendar calHourWorkEnd = Calendar.getInstance();
        Calendar calHourShortPauseStart = Calendar.getInstance();
        Calendar calHourShortPauseEnd = Calendar.getInstance();
        Calendar calHourLongPauseStart = Calendar.getInstance();
        Calendar calHourLongPauseEnd = Calendar.getInstance();
        WorkHour workHour = new WorkHour();
        if (dayHour <= Week.WORK_HOUR_AM_END) {
            calHourWorkStart.set(Calendar.HOUR, Week.WORK_HOUR_AM_START);
            calHourWorkStart.set(Calendar.MINUTE, Week.WORK_MIN_AM_START);
            calHourWorkEnd.set(Calendar.HOUR, Week.WORK_HOUR_AM_END);
            calHourWorkEnd.set(Calendar.MINUTE, Week.WORK_MIN_AM_END);
            calHourShortPauseStart.set(Calendar.HOUR, Week.SHORT_PAUSE_HOUR_AM_START);
            calHourShortPauseStart.set(Calendar.MINUTE, Week.SHORT_PAUSE_MIN_AM_START);
            calHourShortPauseEnd.set(Calendar.HOUR, Week.SHORT_PAUSE_HOUR_AM_END);
            calHourShortPauseEnd.set(Calendar.MINUTE, Week.SHORT_PAUSE_MIN_AM_END);
            calHourLongPauseStart.set(Calendar.HOUR, Week.LONG_PAUSE_HOUR_AM_START);
            calHourLongPauseStart.set(Calendar.MINUTE, Week.LONG_PAUSE_MIN_AM_START);
            calHourLongPauseEnd.set(Calendar.HOUR, Week.LONG_PAUSE_HOUR_AM_END);
            calHourLongPauseEnd.set(Calendar.MINUTE, Week.LONG_PAUSE_MIN_AM_END);
        } else {
            calHourWorkStart.set(Calendar.HOUR, Week.WORK_HOUR_PM_START);
            calHourWorkStart.set(Calendar.MINUTE, Week.WORK_MIN_PM_START);
            calHourWorkEnd.set(Calendar.HOUR, Week.WORK_HOUR_PM_END);
            calHourWorkEnd.set(Calendar.MINUTE, Week.WORK_MIN_PM_END);
            calHourShortPauseStart.set(Calendar.HOUR, Week.SHORT_PAUSE_HOUR_PM_START);
            calHourShortPauseStart.set(Calendar.MINUTE, Week.SHORT_PAUSE_MIN_PM_START);
            calHourShortPauseEnd.set(Calendar.HOUR, Week.SHORT_PAUSE_HOUR_PM_END);
            calHourShortPauseEnd.set(Calendar.MINUTE, Week.SHORT_PAUSE_MIN_PM_END);
            calHourLongPauseStart.set(Calendar.HOUR, Week.LONG_PAUSE_HOUR_PM_START);
            calHourLongPauseStart.set(Calendar.MINUTE, Week.LONG_PAUSE_MIN_PM_START);
            calHourLongPauseEnd.set(Calendar.HOUR, Week.LONG_PAUSE_HOUR_PM_END);
            calHourLongPauseEnd.set(Calendar.MINUTE, Week.LONG_PAUSE_MIN_PM_END);
        }
        workHour.setCalHourWorkStart(calHourWorkStart);
        workHour.setCalHourWorkEnd(calHourWorkEnd);
        workHour.setCalHourShortPauseStart(calHourShortPauseStart);
        workHour.setCalHourShortPauseEnd(calHourShortPauseEnd);
        workHour.setCalHourLongPauseStart(calHourLongPauseStart);
        workHour.setCalHourLongPauseEnd(calHourLongPauseEnd);
        return workHour;
    }

    public WorkHour sendHourFriday(final Calendar calendar) {
        int dayHour = calendar.get(Calendar.HOUR_OF_DAY);
        Calendar calHourWorkStart = Calendar.getInstance();
        Calendar calHourWorkEnd = Calendar.getInstance();
        Calendar calHourShortPauseStart = Calendar.getInstance();
        Calendar calHourShortPauseEnd = Calendar.getInstance();
        Calendar calHourLongPauseStart = Calendar.getInstance();
        Calendar calHourLongPauseEnd = Calendar.getInstance();
        WorkHour workHour = new WorkHour();
        if (dayHour <= Friday.WORK_HOUR_AM_END) {
            calHourWorkStart.set(Calendar.HOUR, Friday.WORK_HOUR_AM_START);
            calHourWorkStart.set(Calendar.MINUTE, Friday.WORK_MIN_AM_START);
            calHourWorkEnd.set(Calendar.HOUR, Friday.WORK_HOUR_AM_END);
            calHourWorkEnd.set(Calendar.MINUTE, Friday.WORK_MIN_AM_END);
            calHourShortPauseStart.set(Calendar.HOUR, Friday.SHORT_PAUSE_HOUR_AM_START);
            calHourShortPauseStart.set(Calendar.MINUTE, Friday.SHORT_PAUSE_MIN_AM_START);
            calHourShortPauseEnd.set(Calendar.HOUR, Friday.SHORT_PAUSE_HOUR_AM_END);
            calHourShortPauseEnd.set(Calendar.MINUTE, Friday.SHORT_PAUSE_MIN_AM_END);
            calHourLongPauseStart.set(Calendar.HOUR, Friday.LONG_PAUSE_HOUR_AM_START);
            calHourLongPauseStart.set(Calendar.MINUTE, Friday.LONG_PAUSE_MIN_AM_START);
            calHourLongPauseEnd.set(Calendar.HOUR, Friday.LONG_PAUSE_HOUR_AM_END);
            calHourLongPauseEnd.set(Calendar.MINUTE, Friday.LONG_PAUSE_MIN_AM_END);

        } else {
            calHourWorkStart.set(Calendar.HOUR, Friday.WORK_HOUR_PM_START);
            calHourWorkStart.set(Calendar.MINUTE, Friday.WORK_MIN_PM_START);
            calHourWorkEnd.set(Calendar.HOUR, Friday.WORK_HOUR_PM_END);
            calHourWorkEnd.set(Calendar.MINUTE, Friday.WORK_MIN_PM_END);
            calHourShortPauseStart.set(Calendar.HOUR, Friday.SHORT_PAUSE_HOUR_PM_START);
            calHourShortPauseStart.set(Calendar.MINUTE, Friday.SHORT_PAUSE_MIN_PM_START);
            calHourShortPauseEnd.set(Calendar.HOUR, Friday.SHORT_PAUSE_HOUR_PM_END);
            calHourShortPauseEnd.set(Calendar.MINUTE, Friday.SHORT_PAUSE_MIN_PM_END);
            calHourLongPauseStart.set(Calendar.HOUR, Friday.LONG_PAUSE_HOUR_PM_START);
            calHourLongPauseStart.set(Calendar.MINUTE, Friday.LONG_PAUSE_MIN_PM_START);
            calHourLongPauseEnd.set(Calendar.HOUR, Friday.LONG_PAUSE_HOUR_PM_END);
            calHourLongPauseEnd.set(Calendar.MINUTE, Friday.LONG_PAUSE_MIN_PM_END);
        }
        workHour.setCalHourWorkStart(calHourWorkStart);
        workHour.setCalHourWorkEnd(calHourWorkEnd);
        workHour.setCalHourShortPauseStart(calHourShortPauseStart);
        workHour.setCalHourShortPauseEnd(calHourShortPauseEnd);
        workHour.setCalHourLongPauseStart(calHourLongPauseStart);
        workHour.setCalHourLongPauseEnd(calHourLongPauseEnd);
        return workHour;
    }

    public WorkHour sendHourWeekend(final Calendar calendar) {
        int dayHour = calendar.get(Calendar.HOUR_OF_DAY);
        Calendar calHourWorkStart = Calendar.getInstance();
        Calendar calHourWorkEnd = Calendar.getInstance();
        Calendar calHourShortPauseStart = Calendar.getInstance();
        Calendar calHourShortPauseEnd = Calendar.getInstance();
        Calendar calHourLongPauseStart = Calendar.getInstance();
        Calendar calHourLongPauseEnd = Calendar.getInstance();
        WorkHour workHour = new WorkHour();
        if (dayHour <= Weekend.WORK_HOUR_AM_END) {
            calHourWorkStart.set(Calendar.HOUR, Weekend.WORK_HOUR_AM_START);
            calHourWorkStart.set(Calendar.MINUTE, Weekend.WORK_MIN_AM_START);
            calHourWorkEnd.set(Calendar.HOUR, Weekend.WORK_HOUR_AM_END);
            calHourWorkEnd.set(Calendar.MINUTE, Weekend.WORK_MIN_AM_END);
            calHourShortPauseStart.set(Calendar.HOUR, Weekend.SHORT_PAUSE_HOUR_AM_START);
            calHourShortPauseStart.set(Calendar.MINUTE, Weekend.SHORT_PAUSE_MIN_AM_START);
            calHourShortPauseEnd.set(Calendar.HOUR, Weekend.SHORT_PAUSE_HOUR_AM_END);
            calHourShortPauseEnd.set(Calendar.MINUTE, Weekend.SHORT_PAUSE_MIN_AM_END);
            calHourLongPauseStart.set(Calendar.HOUR, Weekend.LONG_PAUSE_HOUR_AM_START);
            calHourLongPauseStart.set(Calendar.MINUTE, Weekend.LONG_PAUSE_MIN_AM_START);
            calHourLongPauseEnd.set(Calendar.HOUR, Weekend.LONG_PAUSE_HOUR_AM_END);
            calHourLongPauseEnd.set(Calendar.MINUTE, Weekend.LONG_PAUSE_MIN_AM_END);
        } else {
            calHourWorkStart.set(Calendar.HOUR, Weekend.WORK_HOUR_PM_START);
            calHourWorkStart.set(Calendar.MINUTE, Weekend.WORK_MIN_PM_START);
            calHourWorkEnd.set(Calendar.HOUR, Weekend.WORK_HOUR_PM_END);
            calHourWorkEnd.set(Calendar.MINUTE, Weekend.WORK_MIN_PM_END);
            calHourShortPauseStart.set(Calendar.HOUR, Weekend.SHORT_PAUSE_HOUR_PM_START);
            calHourShortPauseStart.set(Calendar.MINUTE, Weekend.SHORT_PAUSE_MIN_PM_START);
            calHourShortPauseEnd.set(Calendar.HOUR, Weekend.SHORT_PAUSE_HOUR_PM_END);
            calHourShortPauseEnd.set(Calendar.MINUTE, Weekend.SHORT_PAUSE_MIN_PM_END);
            calHourLongPauseStart.set(Calendar.HOUR, Weekend.LONG_PAUSE_HOUR_PM_START);
            calHourLongPauseStart.set(Calendar.MINUTE, Weekend.LONG_PAUSE_MIN_PM_START);
            calHourLongPauseEnd.set(Calendar.HOUR, Weekend.LONG_PAUSE_HOUR_PM_END);
            calHourLongPauseEnd.set(Calendar.MINUTE, Weekend.LONG_PAUSE_MIN_PM_END);
        }
        workHour.setCalHourWorkStart(calHourWorkStart);
        workHour.setCalHourWorkEnd(calHourWorkEnd);
        workHour.setCalHourShortPauseStart(calHourShortPauseStart);
        workHour.setCalHourShortPauseEnd(calHourShortPauseEnd);
        workHour.setCalHourLongPauseStart(calHourLongPauseStart);
        workHour.setCalHourLongPauseEnd(calHourLongPauseEnd);
        return workHour;
    }


}
