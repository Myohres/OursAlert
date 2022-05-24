package domain;

import constant.AmPm;
import constant.hour.Friday;
import constant.hour.Week;
import constant.hour.Weekend;
import data.Horaire;

import java.util.Calendar;

public class DayCalculator {

   private final Horaire horaire = new Horaire();
   private final Calendar calendar = Calendar.getInstance();

   public void displayHoraire() {
       System.out.println("Affichage horaire");
       System.out.println("Jour n° " + horaire.getDayWorkNumber());
       if (horaire.getAmPm() == 0) {
           System.out.println("Horaires du matin");
       } else {
           System.out.println("Horaires de l'après midi");
       }
       System.out.println("Heure début poste : " + horaire.getHourWorkStart());
       System.out.println("Heure fin poste : " + horaire.getHourWorkEnd());
   }

    public Horaire horaireCalculator() {
        int numberDay = calendar.get(Calendar.DAY_OF_WEEK);
        switch (numberDay) {
            case 1, 2, 3, 4 -> {
                matinOuApresMidiSemaine(calendar);
                horaire.setDayWorkNumber(numberDay);
                return horaire;
            }
            case 5 -> {
                matinOuApresMidiVendredi(calendar);
                horaire.setDayWorkNumber(numberDay);
                return horaire;
            }
            case 6, 7 -> {
                matinOuApresMidiWeekend(calendar);
                horaire.setDayWorkNumber(numberDay);
                return horaire;
            }
            default -> throw new IllegalStateException(
                    "Invalid day : " + numberDay);
        }
    }

    public void matinOuApresMidiSemaine(final Calendar calendar) {
        int dayHour = calendar.get(Calendar.HOUR);
        if (dayHour <= Week.WORK_HOUR_AM_END) {
            horaire.setAmPm(AmPm.AM);
            horaire.setHourWorkStart(Week.WORK_HOUR_AM_START);
            horaire.setHourWorkEnd(Week.WORK_HOUR_AM_END);

        } else {
            horaire.setAmPm(AmPm.PM);
            horaire.setHourWorkStart(Week.WORK_HOUR_PM_START);
            horaire.setHourWorkEnd(Week.WORK_HOUR_PM_END);
        }
    }

    public void matinOuApresMidiVendredi(final Calendar calendar) {
        int dayHour = calendar.get(Calendar.HOUR);
        if (dayHour <= Friday.WORK_HOUR_AM_END) {
            horaire.setAmPm(AmPm.AM);
            horaire.setHourWorkStart(Friday.WORK_HOUR_AM_START);
            horaire.setHourWorkEnd(Friday.WORK_HOUR_AM_END);

        } else {
            horaire.setAmPm(AmPm.PM);
            horaire.setHourWorkStart(Friday.WORK_HOUR_PM_START);
            horaire.setHourWorkEnd(Friday.WORK_HOUR_PM_END);
        }
    }

    public void matinOuApresMidiWeekend(final Calendar calendar) {
        int dayHour = calendar.get(Calendar.HOUR);
        if (dayHour <= Weekend.WORK_HOUR_AM_END) {
            horaire.setAmPm(AmPm.AM);
            horaire.setHourWorkStart(Weekend.WORK_HOUR_AM_START);
            horaire.setHourWorkEnd(Weekend.WORK_HOUR_AM_END);

        } else {
            horaire.setAmPm(AmPm.PM);
            horaire.setHourWorkStart(Weekend.WORK_HOUR_PM_START);
            horaire.setHourWorkEnd(Weekend.WORK_HOUR_PM_END);
        }
    }
}
