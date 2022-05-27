package domain;

import data.CheckListFull;
import data.InfoProd;
import data.Pause;
import data.WorkHour;

import java.text.SimpleDateFormat;
import java.util.*;

public class CheckListLightGenerator {

    private HourTimeCalculator hourTimeCalculator = new HourTimeCalculator();
    private InfoProdCalculator infoProdCalculator = new InfoProdCalculator();
    private PauseListGenerator pauseListGenerator = new PauseListGenerator();
    private WorkTimeGenerator workTimeGenerator = new WorkTimeGenerator();
    private final WorkHour workHour = hourTimeCalculator.run();
    private final List<Calendar> pauseList = pauseListGenerator.pauseListGenerator(workHour);

    public void run() {
        workTimeGenerator.run(workHour);
        InfoProd infoProd = infoProdCalculator.run();
        CheckListFull checkList = checkListLightGenerator(infoProd);
        checkListLightDisplay(checkList);
    }


    public CheckListFull checkListLightGenerator(final InfoProd infoProd) {
        System.out.println("");
        System.out.println("***** Génération de liste *****");
        System.out.println("");
        System.out.println("Calcul du planning en cour....");
        CheckListFull checkListFull = new CheckListFull();
        checkListFull.setHourPauseOneStart(pauseList.get(0));
        checkListFull.setHourPauseOneEnd(pauseList.get(1));
        checkListFull.setHourPauseTwoStart(pauseList.get(2));
        checkListFull.setHourPauseTwoEnd(pauseList.get(3));
        final long timeByCheck = infoProd.getTimeByCheckMilliSec();
        List<Calendar> checkList = new ArrayList<>();
        Calendar hour = Calendar.getInstance();
        Pause nextPause = new Pause();
        Calendar hourStart = Calendar.getInstance();
        checkListFull.setHourStart(hourStart);
        System.out.println("Heure début " +hour.get(Calendar.HOUR) +":" +hour.get(Calendar.MINUTE));

        int nBCheckDone = 0;
        double nbCheckTotal = infoProd.getNbCheckTotal();
        while (nBCheckDone != nbCheckTotal) {
            long timeToCheck;
            if (pauseBeforeEnding(pauseList)) {
                nextPause = nextPause(pauseList, hour);
            }
            boolean pauseBeforeCheck = pauseBeforeCheck(timeByCheck, hour, nextPause );
            if ( pauseBeforeCheck) {
                System.out.println("hour                   " +hour.getTime());
                long timeAtPauseStart = nextPause.getHourPauseStart().getTimeInMillis();
                System.out.println("timeAtPauseStart       " +timeAtPauseStart);
                long timeWorkedAtPauseStart = timeAtPauseStart - hour.getTimeInMillis();
                System.out.println("timeWorkedAtPauseStart " + timeWorkedAtPauseStart);
                long timeAtPauseEnd = nextPause.getHourPauseEnd().getTimeInMillis();
                System.out.println("timeAtPauseEnd         " + timeAtPauseEnd);
                long timeStillWorkAfterPause = timeByCheck - timeWorkedAtPauseStart;
                System.out.println("timeStillWorkAfterPause " + timeStillWorkAfterPause );
                timeToCheck = timeAtPauseEnd + timeStillWorkAfterPause;
                System.out.println("timetoCheck pausebeforeCheck " +timeToCheck);

            } else {
                timeToCheck = hour.getTimeInMillis() + timeByCheck;
                System.out.println("timetoCheck             " +timeToCheck);
            }
            hour.setTimeInMillis(timeToCheck);
            System.out.println("hour                        " +hour.getTime());
            Calendar hourToCheck = Calendar.getInstance();
            System.out.println("hourToCheck init             " +hourToCheck.getTime());
            hourToCheck.setTimeInMillis(timeToCheck);
            System.out.println("hourTocheck with timeToCheck  " +hourToCheck.getTime());
            checkList.add(hourToCheck);
            nBCheckDone++;
            System.out.println("");
            System.out.println("cal hour         " +hour.get(Calendar.HOUR) +":" +hour.get(Calendar.MINUTE) +" pointage " +nBCheckDone);
            System.out.println("cal hourtocheck  " +hourToCheck.get(Calendar.HOUR) +":" +hourToCheck.get(Calendar.MINUTE) +" pointage " +nBCheckDone);
        }
        checkListFull.setHourEnd(hour);
        checkListFull.setCheckList(checkList);
        return checkListFull;
    }

    public Pause nextPause(List<Calendar> pauseList, Calendar calendar) {
        Pause nextPause = new Pause();
        Calendar calendarAtCheckBefore = Calendar.getInstance();
        calendarAtCheckBefore.setTimeInMillis(calendar.getTimeInMillis());
        if (calendarAtCheckBefore.getTime().before(pauseList.get(0).getTime())) {
            nextPause.setHourPauseStart(pauseList.get(0));
            nextPause.setHourPauseEnd(pauseList.get(1));
        } else if ( calendarAtCheckBefore.getTime().after(pauseList.get(0).getTime())) {
            nextPause.setHourPauseStart(pauseList.get(2));
            nextPause.setHourPauseEnd(pauseList.get(3));
        }
        return nextPause;
    }

    public boolean pauseBeforeEnding(List<Calendar> pauseList) {
        Calendar calendar = Calendar.getInstance();
        return !calendar.after(pauseList.get(2).getTime());
    }

    public boolean pauseBeforeCheck(long timeByCheck, Calendar hour, Pause nextPause) {
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
        String time = myFormat.format(checkListFull.getHourStart().getTime());
        System.out.println("Heure début : " + time);
        time = myFormat.format(checkListFull.getHourEnd().getTime());
        System.out.println("Heure fin : " + time);
        time = myFormat.format(checkListFull.getHourPauseOneStart().getTime());
        System.out.println("Heure début pause 1 : " + time);
        time = myFormat.format(checkListFull.getHourPauseOneEnd().getTime());
        System.out.println("Heure fin pause 1 : " + time);
        time = myFormat.format(checkListFull.getHourPauseTwoStart().getTime());
        System.out.println("Heure début pause 2 : " + time);
        time = myFormat.format(checkListFull.getHourPauseTwoEnd().getTime());
        System.out.println("Heure fin pause 2 : " + time);
        System.out.println("");
        System.out.println("Heures pointages : ");
        int i;
        for (i = 0; checkListSize > i; i++) {
            String time1 = myFormat.format(checkListFull.getCheckList().get(i).getTime());
            System.out.println("Pointage n°" + i + " : " + time1);
        }
    }

}
