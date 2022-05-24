package domain;

import java.text.SimpleDateFormat;
import java.util.*;

public class PlanningGenerator {

    public void sessionGenerator() {

        /* initialisation */
        System.out.println("");
        System.out.println("***** Initialisation *****");
        System.out.println("");
        Date date = new Date();
        Calendar dateToday = Calendar.getInstance();
        int hour = dateToday.get(Calendar.HOUR);
        int minute = dateToday.get(Calendar.MINUTE);
        int seconde = dateToday.get(Calendar.SECOND);
        System.out.println("Bienvenue agent");
        System.out.println("Nous sommes le : " + date);
        System.out.println("Prise de poste effective à : " + hour + ":" + minute + ":" + seconde + " ");


        /* Input client */
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


        /* Calculs et conversions */
        System.out.println("");
        System.out.println("***** Calculs et conversions *****");
        System.out.println("");
        /* calcul temps total */
        double tmpTotalMn = (tmpParLotMn * qteProductTotale) / qteParLot;
        System.out.println("Le temps de travail total est de : " + tmpTotalMn + " mn");
        /*Convertion tps totale en milliseconde */
        long tmpTotalMilliSec = (long) tmpTotalMn * 60 * 1000;
        System.out.println("temps total en millisecondes : " + tmpTotalMilliSec);
        /* calcul nombre de pointage */
        double nbPointage = qteProductTotale / qteParLot;
        System.out.println("Il y a " + nbPointage + " pointage(s) à faire");
        /* calcul du temps entre chaque pointage */
        double tmpEntrePointageMin = tmpTotalMn / nbPointage;
        System.out.println("Il y a un pointage toutes les " + tmpEntrePointageMin + " mn");
        /* Conversion tps entre chaque pointage */
        long tmpEntrePointageMillisec = (long) tmpEntrePointageMin * 60 * 1000;
        System.out.println("temps entre chaque pointage en millisecondes : " + tmpEntrePointageMillisec);


        /* Générateur de list */
        System.out.println("");
        System.out.println("***** Génération de liste *****");
        System.out.println("");
        System.out.println("Calcul du planning en cour....");
        List<Calendar> listPointage = new ArrayList<>();
        Calendar startTimePointage = Calendar.getInstance();
        long timeSpendToWorkMilliSec = 0;
        while (timeSpendToWorkMilliSec <= tmpTotalMilliSec) {
            long timeWhenPointage = startTimePointage.getTimeInMillis() + timeSpendToWorkMilliSec;
            Calendar newTimePointage = Calendar.getInstance();
            newTimePointage.setTimeInMillis(timeWhenPointage);
            listPointage.add(newTimePointage);
            timeSpendToWorkMilliSec = timeSpendToWorkMilliSec + tmpEntrePointageMillisec;

        }


        /* affichage de la liste */
        SimpleDateFormat myFormat = new SimpleDateFormat("HH:mm");
        System.out.println("");
        System.out.println("***** Affichage de la liste *****");
        System.out.println("");
        String time = myFormat.format(listPointage.get(0).getTime());
        System.out.println("Heure début : "
                + time);
        System.out.println("Heure fin : "
                + listPointage.get(listPointage.size() - 1).get(Calendar.HOUR) + "h"
                + listPointage.get(listPointage.size() - 1).get(Calendar.MINUTE));
        System.out.println("");
        System.out.println("Heures pointages : ");
        int i;
        for (i = 1; listPointage.size() > i; i++) {
            String time1 = myFormat.format(listPointage.get(i).getTime());
            System.out.println(
                    "Pointage n°" + i + " : " + time1
                    );
        }

        System.out.println("");
        System.out.println("***** Fermeture programme *****");
    }
}
