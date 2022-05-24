package domain;

import constant.AmPm;
import constant.hour.Friday;
import constant.hour.Week;
import constant.hour.Weekend;
import data.HourWork;

import java.util.Calendar;

public class DayCalculator {

   private final HourWork hourWork = new HourWork();
   private final Calendar calendar = Calendar.getInstance();

   public void hourWorkDisplay() {
       System.out.println("Affichage horaire");
       System.out.println("Jour n° " + hourWork.getDayWorkNumber());
       if (hourWork.getAmPm() == 0) {
           System.out.println("Horaires du matin");
       } else {
           System.out.println("Horaires de l'après midi");
       }
       System.out.println("Heure début poste : " + hourWork.getHourWorkStart());
       System.out.println("Heure fin poste : " + hourWork.getHourWorkEnd());
   }

    public void hourWorkGenerator() {
        int numberDay = calendar.get(Calendar.DAY_OF_WEEK);
        switch (numberDay) {
            case 1, 2, 3, 4 -> {
                morningOrAfternoonWeek(calendar);
                hourWork.setDayWorkNumber(numberDay);
            }
            case 5 -> {
                morningOrAfternoonFriday(calendar);
                hourWork.setDayWorkNumber(numberDay);
            }
            case 6, 7 -> {
                morningOrAfternoonWeekend(calendar);
                hourWork.setDayWorkNumber(numberDay);
            }
            default -> throw new IllegalStateException(
                    "Invalid day : " + numberDay);
        }
    }

    public void morningOrAfternoonWeek(final Calendar calendar) {
        int dayHour = calendar.get(Calendar.HOUR);
        if (dayHour <= Week.WORK_HOUR_AM_END) {
            hourWork.setAmPm(AmPm.AM);
            hourWork.setHourWorkStart(Week.WORK_HOUR_AM_START);
            hourWork.setHourWorkEnd(Week.WORK_HOUR_AM_END);

        } else {
            hourWork.setAmPm(AmPm.PM);
            hourWork.setHourWorkStart(Week.WORK_HOUR_PM_START);
            hourWork.setHourWorkEnd(Week.WORK_HOUR_PM_END);
        }
    }

    public void morningOrAfternoonFriday(final Calendar calendar) {
        int dayHour = calendar.get(Calendar.HOUR);
        if (dayHour <= Friday.WORK_HOUR_AM_END) {
            hourWork.setAmPm(AmPm.AM);
            hourWork.setHourWorkStart(Friday.WORK_HOUR_AM_START);
            hourWork.setHourWorkEnd(Friday.WORK_HOUR_AM_END);

        } else {
            hourWork.setAmPm(AmPm.PM);
            hourWork.setHourWorkStart(Friday.WORK_HOUR_PM_START);
            hourWork.setHourWorkEnd(Friday.WORK_HOUR_PM_END);
        }
    }

    public void morningOrAfternoonWeekend(final Calendar calendar) {
        int dayHour = calendar.get(Calendar.HOUR);
        if (dayHour <= Weekend.WORK_HOUR_AM_END) {
            hourWork.setAmPm(AmPm.AM);
            hourWork.setHourWorkStart(Weekend.WORK_HOUR_AM_START);
            hourWork.setHourWorkEnd(Weekend.WORK_HOUR_AM_END);

        } else {
            hourWork.setAmPm(AmPm.PM);
            hourWork.setHourWorkStart(Weekend.WORK_HOUR_PM_START);
            hourWork.setHourWorkEnd(Weekend.WORK_HOUR_PM_END);
        }
    }
}
