package domain;

import data.InfosProd;
import data.PlanningLot;

import java.text.SimpleDateFormat;
import java.util.*;

public class PlanningGenerator {

    public void planningGenerator() {
        Calendar calendar = initialisation();
        InfosProd infosProd = inputClient();
        infosProd = calculateAndConversion(infosProd);
        displayInfosProd(infosProd);
        PlanningLot planningLot = listGenerator(infosProd, calendar);
        displayList(planningLot);
        newSession();
    }

    public Calendar initialisation() {
        System.out.println("");
        System.out.println("***** Initialisation *****");
        System.out.println("");
        Date date = new Date();
        Calendar dateToday = Calendar.getInstance();
        SimpleDateFormat myFormat = new SimpleDateFormat("HH:mm");
        String time = myFormat.format(dateToday.getTime());
        System.out.println("Nous sommes le : " + date);
        System.out.println("Debut de session : " +time);
        return dateToday;
    }

    public InfosProd inputClient() {
        InfosProd infosProd = new InfosProd();
        System.out.println("");
        System.out.println("***** Entrées informations *****");
        System.out.println("");

        System.out.println("Veuillez saisir le montant total de produits");
        Scanner sc = new Scanner(System.in);
        double qteProductTotale = sc.nextInt();
        System.out.println("Montant total de produits : " + qteProductTotale);

        System.out.println("Veuillez saisir le nombre de produits pour un lot");
        sc = new Scanner(System.in);
        double qteParLot = sc.nextInt();
        System.out.println("Montant de produits par lot : " + qteParLot);

        System.out.println("Veuillez saisir le temps alloué en minutes pour la production par lot");
        sc = new Scanner(System.in);
        double tmpParLotMn = sc.nextInt();
        System.out.println("Le temps alloué en minutes pour la production par lot est de : " + tmpParLotMn + " mn");

        infosProd.setQteProductTotale(qteProductTotale);
        infosProd.setQteParLot(qteParLot);
        infosProd.setTmpParLotMn(tmpParLotMn);
        return infosProd;
    }

    public InfosProd calculateAndConversion(InfosProd infosProd) {
        double tmpParLotMn = infosProd.getTmpParLotMn();
        double qteProductTotale = infosProd.getQteProductTotale();
        double qteParLot = infosProd.getQteParLot();

        double tmpTotalMinute = (tmpParLotMn * qteProductTotale) / qteParLot;
        long tmpTotalMilliSec = (long) tmpTotalMinute * 60 * 1000;
        double nbPointage = qteProductTotale / qteParLot;
        double tmpEntrePointageMinute = tmpTotalMinute / nbPointage;
        long tmpEntrePointageMillisec = (long) tmpEntrePointageMinute * 60 * 1000;

        System.out.println("");
        System.out.println("***** Calculs et conversions *****");
        System.out.println("");
        System.out.println("Calculs en cours....");

        infosProd.setTmpTotalMinute(tmpTotalMinute);
        infosProd.setTmpTotalMilliSec(tmpTotalMilliSec);
        infosProd.setNbPointage(nbPointage);
        infosProd.setTmpEntrePointageMinute(tmpEntrePointageMinute);
        infosProd.setTmpEntrePointageMillisec(tmpEntrePointageMillisec);
        return infosProd;
    }

    public void displayInfosProd(InfosProd infosProd) {
        double tmpTotalMn = infosProd.getTmpTotalMinute();
        double nbPointage = infosProd.getNbPointage();
        double tmpEntrePointageMin = infosProd.getTmpEntrePointageMinute();

        System.out.println("");
        System.out.println("***** Affichage infos Prod *****");
        System.out.println("");
        System.out.println("Le temps de travail total est de : " + tmpTotalMn + " mn");
        System.out.println("Il y a " + nbPointage + " pointage(s) à faire");
        System.out.println("Il y a un pointage toutes les " + tmpEntrePointageMin + " mn");
    }

    public PlanningLot listGenerator(InfosProd infosProd, Calendar calendar) {
        long tmpTotalMilliSec = infosProd.getTmpTotalMilliSec();
        long tmpEntrePointageMillisec = infosProd.getTmpEntrePointageMillisec();
        PlanningLot planningLot = new PlanningLot();
        List<Calendar> listPointage = new ArrayList<>();

        System.out.println("");
        System.out.println("***** Génération de liste *****");
        System.out.println("");
        System.out.println("Calcul du planning en cour....");

        long timeSpendToWorkMilliSec = 0;
        while (timeSpendToWorkMilliSec <= tmpTotalMilliSec) {
            long timeWhenPointage = calendar.getTimeInMillis() + timeSpendToWorkMilliSec;
            Calendar newTimePointage = Calendar.getInstance();
            newTimePointage.setTimeInMillis(timeWhenPointage);
            listPointage.add(newTimePointage);
            timeSpendToWorkMilliSec = timeSpendToWorkMilliSec + tmpEntrePointageMillisec;
        }
        planningLot.setHourPointageList(listPointage);
        planningLot.setHourStart(listPointage.get(0));
        planningLot.setHourEnd(listPointage.get(listPointage.size() - 1));
        return planningLot;
    }

    public void displayList(PlanningLot planningLot) {
        SimpleDateFormat myFormat = new SimpleDateFormat("HH:mm");
        int planningLotSize = planningLot.getHourPointageList().size();
        System.out.println("");
        System.out.println("***** Affichage de la liste *****");
        System.out.println("");
        String time = myFormat.format(planningLot.getHourPointageList().get(0).getTime());
        System.out.println("Heure début : " + time);
        time = myFormat.format(planningLot.getHourPointageList().get(planningLotSize - 1).getTime());
        System.out.println("Heure fin : " + time);
        System.out.println("");
        System.out.println("Heures pointages : ");
        int i;
        for (i = 1; planningLotSize > i; i++) {
            String time1 = myFormat.format(planningLot.getHourPointageList().get(i).getTime());
            System.out.println(
                    "Pointage n°" + i + " : " + time1
            );
        }
    }

    public void newSession() {
        boolean continueApp = true;
        while(continueApp) {
            System.out.println("");
            System.out.println("***** Nouvelle session ? ******");
            System.out.println("");
            System.out.println("0: oui");
            System.out.println("1: non");
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            System.out.println("");
            if (choice == 0) {
                System.out.println("Nouvelle session");
                planningGenerator();
                continueApp = true;
            } else {
                System.out.println("***** Fermeture programme *****");
                continueApp = false;
            }
        }
    }
}
