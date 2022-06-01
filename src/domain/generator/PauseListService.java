package domain.generator;

import data.InfoProd;
import data.Pause;
import data.WorkHour;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PauseListService {

    public List<Pause> pauseListGenerator(WorkHour workHour, InfoProd infoProd) {
        System.out.println("");
        System.out.println("*** Pause List Generator ***");
        System.out.println("");
        System.out.println("Génération de la pause liste en cour");
        System.out.println("");


        List<Calendar> pauseList = new ArrayList<>();
        pauseList.add(workHour.getCalHourShortPauseStart());
        pauseList.add(workHour.getCalHourShortPauseEnd());
        pauseList.add(workHour.getCalHourLongPauseStart());
        pauseList.add(workHour.getCalHourLongPauseEnd());

        System.out.println("Heure debut de session : " +workHour.getCalHourSessionStart().getTime());
        Date dateSessionStart = workHour.getCalHourSessionStart().getTime();
        long durationWorkTotalMilliSec = infoProd.getDurationWorkTotalMilliSec();
        System.out.println("durationProd : " +durationWorkTotalMilliSec /60 /1000);
        Date dateSessionStartAddDurationProd = new Date();
        dateSessionStartAddDurationProd.setTime(dateSessionStart.getTime() + durationWorkTotalMilliSec);
        System.out.println("dateSessionStartAddDurationProd : " +dateSessionStartAddDurationProd);
        Date datePause;
        List<Pause> pauseList2 = new ArrayList<>();

        int i = 0;
        while (i < pauseList.size()) {
            datePause = pauseList.get(i).getTime();
            System.out.println("Pause possible à : " +datePause);
            System.out.println("");
            if (datePause.after(dateSessionStart) && datePause.before(dateSessionStartAddDurationProd)) {
                Pause pause = new Pause();
                Calendar hourPauseStart = pauseList.get(i);
                Calendar hourPauseEnd = pauseList.get(i+1);
                long durationPauseMilliSec = pauseList.get(i+1).getTimeInMillis() - pauseList.get(i).getTimeInMillis();
                int durationPauseMn = (int) (durationPauseMilliSec / 60 / 1000);
                System.out.println("pause apres debut de session /" +dateSessionStart +"/ et pause avant la fin du travail à faire /" +dateSessionStartAddDurationProd +"/");
                System.out.println("");
                System.out.println("Ajout de pause");
                pause.setHourPauseStart(hourPauseStart);
                System.out.println("Heure pause début" +hourPauseStart.getTime());
                pause.setHourPauseEnd(hourPauseEnd);
                System.out.println("Heure pause fin" +hourPauseEnd.getTime());
                pause.setDurationPauseMilliSec(durationPauseMilliSec);
                System.out.println("Durée de pause milliSec : " +durationPauseMilliSec);
                pause.setDurationPauseMn(durationPauseMn);
                System.out.println("Durée de pause minutes : " +durationPauseMn);
                pauseList2.add(pause);
                System.out.println("");

            } else {
                System.out.println("Condition d'ajout de pause nn rempli, pas d'ajout de la pause : " +datePause);
            }
            i = i+2;
        }
        return pauseList2;
    }

    public void pauseListDisplay(List<Pause> pauseList) {
        System.out.println("");
        System.out.println("*** Pause List Display ***");
        System.out.println("");
        SimpleDateFormat myFormat = new SimpleDateFormat();
        if (pauseList.isEmpty()) {
            System.out.println("Pas de pause pr la session");
        } else {
            int i;
            for (i=0; i < pauseList.size(); i++) {
                System.out.println("Pause n°" +(i+1));
                String hourPauseStartDisplay = myFormat.format(pauseList.get(i).getHourPauseStart().getTime());
                System.out.println("Début : " +hourPauseStartDisplay);
                String hourPauseEndDisplay = myFormat.format(pauseList.get(i).getHourPauseEnd().getTime());
                System.out.println("Fin : " +hourPauseEndDisplay);
                System.out.println("Durée MilliSec : " +pauseList.get(i).getDurationPauseMilliSec());
                System.out.println("Durée minutes : " +pauseList.get(i).getDurationPauseMn());
                System.out.println("");
            }
        }
    }
}
