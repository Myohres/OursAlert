package domain;

import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class InteractiveShell {

    public static void  initialisation() {
        CheckListLightGenerator checkListLightGenerator = new CheckListLightGenerator();
        System.out.println("");
        System.out.println("***** Initialisation *****");
        System.out.println("");
        Date date = new Date();
        Calendar dateToday = Calendar.getInstance();
        SimpleDateFormat myFormat = new SimpleDateFormat("HH:mm");
        String time = myFormat.format(dateToday.getTime());
        System.out.println("Nous sommes le : " + date);
        System.out.println("Debut de session : " + time);
        checkListLightGenerator.run();
        newSession();
    }

    public static void newSession() {
        CheckListLightGenerator checkListLightGenerator = new CheckListLightGenerator();
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
                    checkListLightGenerator.run();
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
