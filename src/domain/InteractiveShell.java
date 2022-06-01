package domain;

import data.CheckListFull;
import data.InfoProd;
import data.Pause;
import data.WorkHour;
import domain.generator.CheckListService;
import domain.generator.PauseListService;
import domain.generator.WorkHourService;
import domain.generator.InfoProdService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class InteractiveShell {

    private static final CheckListService CHECK_LIST_SERVICE = new CheckListService();
    private static final InfoProdService INFO_PROD_SERVICE = new InfoProdService();
    private static final PauseListService pauseListService = new PauseListService();
    private static final WorkHourService WORK_HOUR_SERVICE = new WorkHourService();


    public static void  initialisation() {
        System.out.println("");
        System.out.println("***** Initialisation *****");
        System.out.println("");
        Date date = new Date();
        Calendar dateToday = Calendar.getInstance();
        dateToday.set(Calendar.HOUR_OF_DAY, 7);
        SimpleDateFormat myFormat = new SimpleDateFormat("HH:mm");
        String time = myFormat.format(dateToday.getTime());
        System.out.println("Nous sommes le : " + date);
        System.out.println("Debut de session : " + time);

        WorkHour workHour = WORK_HOUR_SERVICE.workHourGenerator(dateToday);
        WORK_HOUR_SERVICE.workHourDisplay(workHour);

        InfoProd infoProd = INFO_PROD_SERVICE.infoProdCollector();
        infoProd = INFO_PROD_SERVICE.calculateAndConversion(infoProd);
        INFO_PROD_SERVICE.displayInfosProd(infoProd);

        List<Pause> pauseList = pauseListService.pauseListGenerator(workHour, infoProd);
        pauseListService.pauseListDisplay(pauseList);



        CheckListFull checkList = CHECK_LIST_SERVICE.checkListLightGenerator(infoProd, pauseList, workHour);
        CHECK_LIST_SERVICE.checkListLightDisplay(checkList);
        newSession();
    }

    public static void newSession() {
        boolean continueApp = true;
        while (continueApp) {
            System.out.println("");
            System.out.println("***** Nouvelle session ? ******");
            System.out.println("");
            System.out.println("0: oui");
            System.out.println("1: non");
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            System.out.println("");

            switch (choice) {
                case 0 -> {
                    System.out.println("Nouvelle session");
                    initialisation();
                    continueApp = true;
                }
                case 1 -> {
                    System.out.println("***** Fermeture programme *****");
                    continueApp = false;
                }
                default -> {
                    System.out.println("Entrée incorrect veuillez recommencer.");
                    continueApp = true;
                }
            }

        }
    }
}
