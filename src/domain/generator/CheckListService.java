package domain.generator;

import data.CheckListFull;
import data.InfoProd;
import data.Pause;
import data.WorkHour;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CheckListService {

    public CheckListFull checkListLightGenerator(final InfoProd infoProd,
                                                 final List<Pause> pauseList,
                                                 final WorkHour workHour) {
        CheckListFull checkListFull = new CheckListFull();
        List<Calendar> checkList = new ArrayList<>();

        final long durationByLotMilliSec = infoProd.getDurationByLotMilliSec();
        Calendar hourRef = Calendar.getInstance();
        hourRef.setTimeInMillis(workHour.getCalHourSessionStart().getTimeInMillis());
        long hourToCheck;

        Calendar hourSessionStart = Calendar.getInstance();
        hourSessionStart.setTimeInMillis(workHour.getCalHourSessionStart().getTimeInMillis());
        checkListFull.setHourSessionStart(hourSessionStart);
        Calendar hourSessionEnd = hourSessionEndCalculator(pauseList, infoProd, hourSessionStart);
        checkListFull.setHourSessionEnd(hourSessionEnd);

        System.out.println("");
        System.out.println("***** Génération de liste *****");
        System.out.println("");
        SimpleDateFormat myFormat = new SimpleDateFormat("HH:mm");
        System.out.println("Durée par check " + durationByLotMilliSec / 60 / 1000 + "mn");
        String hourSessionStartDisplay = myFormat.format(hourSessionStart);
        System.out.println("Heure début session " + hourSessionStartDisplay);
        String hourSessionEndDisplay = myFormat.format(hourSessionEnd);
        System.out.println("Heure fin session " + hourSessionEndDisplay);
        System.out.println("");

        int nBCheckDone = 0;
        double nbCheckTotal = infoProd.getNbCheckTotal();
        while (nBCheckDone != nbCheckTotal) {
            if (pauseBeforeEndingSession(pauseList, hourRef, hourSessionEnd)) {

                Pause nextPause = nextPause(pauseList, hourRef);
                String nextPauseDisplay = myFormat.format(nextPause.getHourPauseStart().getTime());
                System.out.println("Next pause hourRef " + nextPauseDisplay);
                boolean pauseBeforeCheck = pauseBeforeCheck(durationByLotMilliSec, hourRef, nextPause);
                if (pauseBeforeCheck) {
                    long timeAtPauseStart = nextPause.getHourPauseStart().getTimeInMillis();
                    long durationWorkedAtPauseStart = timeAtPauseStart - hourRef.getTimeInMillis();
                    long hourAtPauseEnd = nextPause.getHourPauseEnd().getTimeInMillis();
                    long durationStillWorkAfterPause = durationByLotMilliSec - durationWorkedAtPauseStart;
                    hourToCheck = hourAtPauseEnd + durationStillWorkAfterPause;
                    System.out.println("hourRef                   " + hourRef.getTime());
                    System.out.println("timeAtPauseStart       " + nextPause.getHourPauseStart().getTime());
                    System.out.println("HourPauseEnd           " + nextPause.getHourPauseEnd().getTime());
                    System.out.println("durationWorkedAtPauseStart " + durationWorkedAtPauseStart / 60 / 1000);
                    String timeAtPauseEndDisplay = myFormat.format(hourAtPauseEnd);
                    System.out.println("hourAtPauseEnd         " + timeAtPauseEndDisplay);
                    System.out.println("durationStillWorkAfterPause " + durationStillWorkAfterPause / 60 / 1000);
                    String hourToCheckDisplay = myFormat.format(hourToCheck);
                    System.out.println("hourtocheck " + hourToCheckDisplay);
                } else {
                    hourToCheck = hourRef.getTimeInMillis() + durationByLotMilliSec;
                    String timeToCheckDisplay = myFormat.format(hourToCheck);
                    System.out.println("hourtocheck            " + timeToCheckDisplay);
                }
            } else {
                hourToCheck = hourRef.getTimeInMillis() + durationByLotMilliSec;
                String timeToCheckDisplay = myFormat.format(hourToCheck);
                System.out.println("hourtocheck            " + timeToCheckDisplay);
            }
            hourRef.setTimeInMillis(hourToCheck);
            Calendar hourNextCheck = Calendar.getInstance();
            hourNextCheck.setTimeInMillis(hourToCheck);

            System.out.println("hourRef      "  + hourRef.getTime());
            System.out.println("hourNextCheck init             " + hourNextCheck.getTime());
            System.out.println("hourTocheck with timeToCheck  " + hourNextCheck.getTime());

            checkList.add(hourNextCheck);
            nBCheckDone++;

            String calHourRefDisplay = myFormat.format(hourRef);
            System.out.println("cal hourRef         " + calHourRefDisplay + " pointage " + nBCheckDone);
            String calHourToCheckDisplay = myFormat.format(hourNextCheck);
            System.out.println("cal hourtocheck  " + calHourToCheckDisplay + " pointage " + nBCheckDone);
            System.out.println("");
        }
        checkListFull.setPauseList(pauseList);
        checkListFull.setHourSessionEnd(hourRef);
        checkListFull.setCheckList(checkList);
        return checkListFull;
    }

    public boolean pauseBeforeEndingSession(final List<Pause> pauseList,
                                            final Calendar hourRef,
                                            final Calendar hourSessionEnd) {
        List<Pause> pauseList2 = new ArrayList<>();
        if (!pauseList.isEmpty()) {
            int i;
            for (i = 0; i < pauseList.size(); i++) {
                if (pauseList.get(i).getHourPauseStart().getTime().after(hourRef.getTime())
                        && pauseList.get(i).getHourPauseStart().getTime().before(hourSessionEnd.getTime())) {
                    pauseList2.add(pauseList.get(i));
                }
            }
        }
        System.out.println("pauseList 2 size : " + pauseList2.size());
        return !pauseList2.isEmpty();
    }

    public Pause nextPause(final List<Pause> pauseList, final Calendar calendar) {
        Pause nextPause = new Pause();
        Calendar calendarAtCheckBefore = Calendar.getInstance();
        calendarAtCheckBefore.setTimeInMillis(calendar.getTimeInMillis());
        int i;
        for (i = 0; i < pauseList.size(); i++) {
            if (calendarAtCheckBefore.getTime().before(pauseList.get(i).getHourPauseStart().getTime())) {
                nextPause.setHourPauseStart(pauseList.get(i).getHourPauseStart());
                nextPause.setHourPauseEnd(pauseList.get(i).getHourPauseEnd());
                return nextPause;
            }
        }
        return nextPause;
    }

    public boolean pauseBeforeCheck(final long timeByCheck,
                                    final Calendar hour,
                                    final Pause nextPause) {
        long timeNextCheck = hour.getTimeInMillis() + timeByCheck;
        long timeNextPause = nextPause.getHourPauseStart().getTimeInMillis();
        return timeNextCheck > timeNextPause;
    }

    public void checkListLightDisplay(final CheckListFull checkListFull) {
        SimpleDateFormat myFormat = new SimpleDateFormat("HH:mm");
        int checkListSize = checkListFull.getCheckList().size();
        System.out.println("");
        System.out.println("***** Affichage de la liste *****");
        System.out.println("");
        String time = myFormat.format(checkListFull.getHourSessionStart().getTime());
        System.out.println("Heure début session : " + time);
        time = myFormat.format(checkListFull.getHourSessionEnd().getTime());
        System.out.println("Heure fin de session : " + time);
        int i;
        if (!checkListFull.getPauseList().isEmpty()) {
            for (i = 0; i < checkListFull.getPauseList().size(); i++) {
                time = myFormat.format(checkListFull.getPauseList().get(i).getHourPauseStart().getTime());
                System.out.println("Heure début de pause " + i + 1 + " : " + time);
                time = myFormat.format(checkListFull.getPauseList().get(i).getHourPauseEnd().getTime());
                System.out.println("Heure fin de pause " + i + 1 + " : " + time);
            }
        }
        System.out.println("");
        System.out.println("Heures pointages : ");
        int y;
        for (y = 0; y < checkListSize; y++) {
            String time1 = myFormat.format(checkListFull.getCheckList().get(y).getTime());
            System.out.println("Pointage n°" + y + " : " + time1);
        }
    }

    public Calendar hourSessionEndCalculator(final List<Pause> pauseList,
                                             final InfoProd infoProd,
                                             final Calendar hourSessionStart) {
        Calendar hourSessionEnd = Calendar.getInstance();
        long nbCheckTotal = (long) infoProd.getNbCheckTotal();
        long timeByCheck = infoProd.getDurationByLotMilliSec();
        long durationPauseTotalMilliSec = 0;
        System.out.println("durationPauseTotalMilliSec : " + durationPauseTotalMilliSec);
        int i;
        for (i = 0; i < pauseList.size(); i++) {
            long durationPauseMilliSec = pauseList.get(i).getDurationPauseMilliSec();
            System.out.println("durationPauseMilliSec : " + durationPauseMilliSec);
            durationPauseTotalMilliSec = durationPauseTotalMilliSec + durationPauseMilliSec;
            System.out.println("durationPauseTotalMilliSec : " + durationPauseTotalMilliSec);
        }
        hourSessionEnd.setTimeInMillis(hourSessionStart.getTimeInMillis() + (nbCheckTotal * timeByCheck) + durationPauseTotalMilliSec);
        return hourSessionEnd;
    }
}
