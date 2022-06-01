package domain.generator;

import constant.hour.Friday;
import constant.hour.Week;
import constant.hour.Weekend;

import data.WorkHour;


import java.text.SimpleDateFormat;
import java.util.Calendar;

public class WorkHourService {

    public void run() {

    }

    public void workHourDisplay(final WorkHour workHour) {
        SimpleDateFormat dayNameFormat = new SimpleDateFormat("EEEE");
        String dayNameDisplay = dayNameFormat.format(workHour.getCalHourSessionStart().getTime());
        String morningOrAfternoonDisplay;
        if (workHour.getCalHourSessionStart().get(Calendar.AM_PM) == Calendar.AM) {
            morningOrAfternoonDisplay = "du matin";
        } else {
            morningOrAfternoonDisplay = "de l'après midi";
        }

        SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");
        String hourSessionStartDisplay = hourFormat.format(workHour.getCalHourSessionStart().getTime());
        String hourSessionEndDisplay = /*hourFormat.format(workHour.getCalHourSessionEnd().getTime());*/ "";
        String hourPlanningStartDisplay = hourFormat.format(workHour.getCalHourPlanningStart().getTime());
        String hourPlanningEndDisplay = hourFormat.format(workHour.getCalHourPlanningEnd().getTime());
        String hourShortPauseStartDisplay = hourFormat.format(workHour.getCalHourShortPauseStart().getTime());
        String hourShortPauseEndDisplay = hourFormat.format(workHour.getCalHourShortPauseEnd().getTime());
        String hourLongPauseStartDisplay = hourFormat.format(workHour.getCalHourLongPauseStart().getTime());
        String hourLongPauseEndDisplay = hourFormat.format(workHour.getCalHourLongPauseEnd().getTime());


        System.out.println("");
        System.out.println("*** WorkHourDisplay ***");
        System.out.println();
        System.out.println("Jour : " + dayNameDisplay);
        System.out.println("Horaires " + morningOrAfternoonDisplay);
        System.out.println("");
        System.out.println("Heure de début session : " + hourSessionStartDisplay);
        System.out.println("heure de fin de session : " + hourSessionEndDisplay);
        System.out.println("");
        System.out.println("Heure de début planning : " + hourPlanningStartDisplay);
        System.out.println("heure de fin de  planning : " + hourPlanningEndDisplay);
        System.out.println("");
        System.out.println("Heure de début de pause 1: " + hourShortPauseStartDisplay);
        System.out.println("heure fin de pause 1 : " + hourShortPauseEndDisplay);
        System.out.println("");
        System.out.println("heure de début de pause 2 : " + hourLongPauseStartDisplay);
        System.out.println("Heure de fin de pause 2 : " + hourLongPauseEndDisplay);
        System.out.println("");
    }


    public WorkHour workHourGenerator(Calendar calendarInitial) {
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
        workHour.setCalHourSessionStart(calendarInitial);
        return workHour;
    }

    public WorkHour sendHourWeek(final Calendar calendar) {
        int dayHour = calendar.get(Calendar.HOUR_OF_DAY);
        Calendar calHourPlanningStart = Calendar.getInstance();
        Calendar calHourPlanningEnd = Calendar.getInstance();
        Calendar calHourShortPauseStart = Calendar.getInstance();
        Calendar calHourShortPauseEnd = Calendar.getInstance();
        Calendar calHourLongPauseStart = Calendar.getInstance();
        Calendar calHourLongPauseEnd = Calendar.getInstance();
        WorkHour workHour = new WorkHour();
        if (dayHour <= Week.WORK_HOUR_AM_END) {
            calHourPlanningStart.set(Calendar.HOUR_OF_DAY, Week.WORK_HOUR_AM_START);
            calHourPlanningStart.set(Calendar.MINUTE, Week.WORK_MIN_AM_START);
            calHourPlanningEnd.set(Calendar.HOUR_OF_DAY, Week.WORK_HOUR_AM_END);
            calHourPlanningEnd.set(Calendar.MINUTE, Week.WORK_MIN_AM_END);
            calHourShortPauseStart.set(Calendar.HOUR_OF_DAY, Week.SHORT_PAUSE_HOUR_AM_START);
            calHourShortPauseStart.set(Calendar.MINUTE, Week.SHORT_PAUSE_MIN_AM_START);
            calHourShortPauseEnd.set(Calendar.HOUR_OF_DAY, Week.SHORT_PAUSE_HOUR_AM_END);
            calHourShortPauseEnd.set(Calendar.MINUTE, Week.SHORT_PAUSE_MIN_AM_END);
            calHourLongPauseStart.set(Calendar.HOUR_OF_DAY, Week.LONG_PAUSE_HOUR_AM_START);
            calHourLongPauseStart.set(Calendar.MINUTE, Week.LONG_PAUSE_MIN_AM_START);
            calHourLongPauseEnd.set(Calendar.HOUR_OF_DAY, Week.LONG_PAUSE_HOUR_AM_END);
            calHourLongPauseEnd.set(Calendar.MINUTE, Week.LONG_PAUSE_MIN_AM_END);
        } else {
            calHourPlanningStart.set(Calendar.AM_PM, Calendar.PM);
            calHourPlanningStart.set(Calendar.HOUR_OF_DAY, Week.WORK_HOUR_PM_START);
            calHourPlanningStart.set(Calendar.MINUTE, Week.WORK_MIN_PM_START);
            calHourPlanningEnd.set(Calendar.HOUR_OF_DAY, Week.WORK_HOUR_PM_END);
            calHourPlanningEnd.set(Calendar.MINUTE, Week.WORK_MIN_PM_END);
            calHourShortPauseStart.set(Calendar.HOUR_OF_DAY, Week.SHORT_PAUSE_HOUR_PM_START);
            calHourShortPauseStart.set(Calendar.MINUTE, Week.SHORT_PAUSE_MIN_PM_START);
            calHourShortPauseEnd.set(Calendar.HOUR_OF_DAY, Week.SHORT_PAUSE_HOUR_PM_END);
            calHourShortPauseEnd.set(Calendar.MINUTE, Week.SHORT_PAUSE_MIN_PM_END);
            calHourLongPauseStart.set(Calendar.HOUR_OF_DAY, Week.LONG_PAUSE_HOUR_PM_START);
            calHourLongPauseStart.set(Calendar.MINUTE, Week.LONG_PAUSE_MIN_PM_START);
            calHourLongPauseEnd.set(Calendar.HOUR_OF_DAY, Week.LONG_PAUSE_HOUR_PM_END);
            calHourLongPauseEnd.set(Calendar.MINUTE, Week.LONG_PAUSE_MIN_PM_END);
        }
        workHour.setCalHourPlanningStart(calHourPlanningStart);
        workHour.setCalHourPlanningEnd(calHourPlanningEnd);
        workHour.setCalHourShortPauseStart(calHourShortPauseStart);
        workHour.setCalHourShortPauseEnd(calHourShortPauseEnd);
        workHour.setCalHourLongPauseStart(calHourLongPauseStart);
        workHour.setCalHourLongPauseEnd(calHourLongPauseEnd);
        return workHour;
    }

    public WorkHour sendHourFriday(final Calendar calendar) {
        int dayHour = calendar.get(Calendar.HOUR_OF_DAY);
        Calendar calHourPlanningStart = Calendar.getInstance();
        Calendar calHourPlanningEnd = Calendar.getInstance();
        Calendar calHourShortPauseStart = Calendar.getInstance();
        Calendar calHourShortPauseEnd = Calendar.getInstance();
        Calendar calHourLongPauseStart = Calendar.getInstance();
        Calendar calHourLongPauseEnd = Calendar.getInstance();
        WorkHour workHour = new WorkHour();
        if (dayHour <= Friday.WORK_HOUR_AM_END) {
            calHourPlanningStart.set(Calendar.HOUR_OF_DAY, Friday.WORK_HOUR_AM_START);
            calHourPlanningStart.set(Calendar.MINUTE, Friday.WORK_MIN_AM_START);
            calHourPlanningEnd.set(Calendar.HOUR_OF_DAY, Friday.WORK_HOUR_AM_END);
            calHourPlanningEnd.set(Calendar.MINUTE, Friday.WORK_MIN_AM_END);
            calHourShortPauseStart.set(Calendar.HOUR_OF_DAY, Friday.SHORT_PAUSE_HOUR_AM_START);
            calHourShortPauseStart.set(Calendar.MINUTE, Friday.SHORT_PAUSE_MIN_AM_START);
            calHourShortPauseEnd.set(Calendar.HOUR_OF_DAY, Friday.SHORT_PAUSE_HOUR_AM_END);
            calHourShortPauseEnd.set(Calendar.MINUTE, Friday.SHORT_PAUSE_MIN_AM_END);
            calHourLongPauseStart.set(Calendar.HOUR_OF_DAY, Friday.LONG_PAUSE_HOUR_AM_START);
            calHourLongPauseStart.set(Calendar.MINUTE, Friday.LONG_PAUSE_MIN_AM_START);
            calHourLongPauseEnd.set(Calendar.HOUR_OF_DAY, Friday.LONG_PAUSE_HOUR_AM_END);
            calHourLongPauseEnd.set(Calendar.MINUTE, Friday.LONG_PAUSE_MIN_AM_END);
        } else {
            calHourPlanningStart.set(Calendar.HOUR_OF_DAY, Friday.WORK_HOUR_PM_START);
            calHourPlanningStart.set(Calendar.MINUTE, Friday.WORK_MIN_PM_START);
            calHourPlanningEnd.set(Calendar.HOUR_OF_DAY, Friday.WORK_HOUR_PM_END);
            calHourPlanningEnd.set(Calendar.MINUTE, Friday.WORK_MIN_PM_END);
            calHourShortPauseStart.set(Calendar.HOUR_OF_DAY, Friday.SHORT_PAUSE_HOUR_PM_START);
            calHourShortPauseStart.set(Calendar.MINUTE, Friday.SHORT_PAUSE_MIN_PM_START);
            calHourShortPauseEnd.set(Calendar.HOUR_OF_DAY, Friday.SHORT_PAUSE_HOUR_PM_END);
            calHourShortPauseEnd.set(Calendar.MINUTE, Friday.SHORT_PAUSE_MIN_PM_END);
            calHourLongPauseStart.set(Calendar.HOUR_OF_DAY, Friday.LONG_PAUSE_HOUR_PM_START);
            calHourLongPauseStart.set(Calendar.MINUTE, Friday.LONG_PAUSE_MIN_PM_START);
            calHourLongPauseEnd.set(Calendar.HOUR_OF_DAY, Friday.LONG_PAUSE_HOUR_PM_END);
            calHourLongPauseEnd.set(Calendar.MINUTE, Friday.LONG_PAUSE_MIN_PM_END);
        }
        workHour.setCalHourPlanningStart(calHourPlanningStart);
        workHour.setCalHourPlanningEnd(calHourPlanningEnd);
        workHour.setCalHourShortPauseStart(calHourShortPauseStart);
        workHour.setCalHourShortPauseEnd(calHourShortPauseEnd);
        workHour.setCalHourLongPauseStart(calHourLongPauseStart);
        workHour.setCalHourLongPauseEnd(calHourLongPauseEnd);
        return workHour;
    }

    public WorkHour sendHourWeekend(final Calendar calendar) {
        int dayHour = calendar.get(Calendar.HOUR_OF_DAY);
        Calendar calHourPlanningStart = Calendar.getInstance();
        Calendar calHourPlanningEnd = Calendar.getInstance();
        Calendar calHourShortPauseStart = Calendar.getInstance();
        Calendar calHourShortPauseEnd = Calendar.getInstance();
        Calendar calHourLongPauseStart = Calendar.getInstance();
        Calendar calHourLongPauseEnd = Calendar.getInstance();
        WorkHour workHour = new WorkHour();
        if (dayHour <= Weekend.WORK_HOUR_AM_END) {
            calHourPlanningStart.set(Calendar.HOUR_OF_DAY, Weekend.WORK_HOUR_AM_START);
            calHourPlanningStart.set(Calendar.MINUTE, Weekend.WORK_MIN_AM_START);
            calHourPlanningEnd.set(Calendar.HOUR_OF_DAY, Weekend.WORK_HOUR_AM_END);
            calHourPlanningEnd.set(Calendar.MINUTE, Weekend.WORK_MIN_AM_END);
            calHourShortPauseStart.set(Calendar.HOUR_OF_DAY, Weekend.SHORT_PAUSE_HOUR_AM_START);
            calHourShortPauseStart.set(Calendar.MINUTE, Weekend.SHORT_PAUSE_MIN_AM_START);
            calHourShortPauseEnd.set(Calendar.HOUR_OF_DAY, Weekend.SHORT_PAUSE_HOUR_AM_END);
            calHourShortPauseEnd.set(Calendar.MINUTE, Weekend.SHORT_PAUSE_MIN_AM_END);
            calHourLongPauseStart.set(Calendar.HOUR_OF_DAY, Weekend.LONG_PAUSE_HOUR_AM_START);
            calHourLongPauseStart.set(Calendar.MINUTE, Weekend.LONG_PAUSE_MIN_AM_START);
            calHourLongPauseEnd.set(Calendar.HOUR_OF_DAY, Weekend.LONG_PAUSE_HOUR_AM_END);
            calHourLongPauseEnd.set(Calendar.MINUTE, Weekend.LONG_PAUSE_MIN_AM_END);
        } else {
            calHourPlanningStart.set(Calendar.HOUR_OF_DAY, Weekend.WORK_HOUR_PM_START);
            calHourPlanningStart.set(Calendar.MINUTE, Weekend.WORK_MIN_PM_START);
            calHourPlanningEnd.set(Calendar.HOUR_OF_DAY, Weekend.WORK_HOUR_PM_END);
            calHourPlanningEnd.set(Calendar.MINUTE, Weekend.WORK_MIN_PM_END);
            calHourShortPauseStart.set(Calendar.HOUR_OF_DAY, Weekend.SHORT_PAUSE_HOUR_PM_START);
            calHourShortPauseStart.set(Calendar.MINUTE, Weekend.SHORT_PAUSE_MIN_PM_START);
            calHourShortPauseEnd.set(Calendar.HOUR_OF_DAY, Weekend.SHORT_PAUSE_HOUR_PM_END);
            calHourShortPauseEnd.set(Calendar.MINUTE, Weekend.SHORT_PAUSE_MIN_PM_END);
            calHourLongPauseStart.set(Calendar.HOUR_OF_DAY, Weekend.LONG_PAUSE_HOUR_PM_START);
            calHourLongPauseStart.set(Calendar.MINUTE, Weekend.LONG_PAUSE_MIN_PM_START);
            calHourLongPauseEnd.set(Calendar.HOUR_OF_DAY, Weekend.LONG_PAUSE_HOUR_PM_END);
            calHourLongPauseEnd.set(Calendar.MINUTE, Weekend.LONG_PAUSE_MIN_PM_END);
        }
        workHour.setCalHourPlanningStart(calHourPlanningStart);
        workHour.setCalHourPlanningEnd(calHourPlanningEnd);
        workHour.setCalHourShortPauseStart(calHourShortPauseStart);
        workHour.setCalHourShortPauseEnd(calHourShortPauseEnd);
        workHour.setCalHourLongPauseStart(calHourLongPauseStart);
        workHour.setCalHourLongPauseEnd(calHourLongPauseEnd);
        return workHour;
    }


}
