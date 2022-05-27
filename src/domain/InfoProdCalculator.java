package domain;

import data.CheckListFull;
import data.InfoProd;

import java.util.Scanner;

public class InfoProdCalculator {

    public InfoProd run() {
        InfoProd infoProd = infoProdCollector();
        infoProd = calculateAndConversion(infoProd);
        displayInfosProd(infoProd);
        return infoProd;
    }

    public InfoProd infoProdCollector() {
        InfoProd infoProd = new InfoProd();
        System.out.println("");
        System.out.println("***** Entrées informations *****");
        System.out.println("");

        System.out.println("Veuillez saisir le montant total de produits");
        Scanner sc = new Scanner(System.in);
        double quantityProductTotal = sc.nextInt();
        System.out.println("Montant total de produits : " + quantityProductTotal);

        System.out.println("Veuillez saisir le nombre de produits pour un lot");
        sc = new Scanner(System.in);
        double quantityByLot = sc.nextInt();
        System.out.println("Montant de produits par lot : " + quantityByLot);

        System.out.println("Veuillez saisir le temps alloué en minutes pour la production par lot");
        sc = new Scanner(System.in);
        double timeByLotMn = sc.nextInt();
        System.out.println("Le temps alloué en minutes pour la production par lot est de : " + timeByLotMn + " mn");

        infoProd.setNbProdTotal(quantityProductTotal);
        infoProd.setNbByLot(quantityByLot);
        infoProd.setTimeByLotMn(timeByLotMn);
        return infoProd;
    }

    public InfoProd calculateAndConversion(final InfoProd infoProd) {
        double tmpParLotMn = infoProd.getTimeByLotMn();
        double qteProductTotal = infoProd.getNbProdTotal();
        double qteParLot = infoProd.getNbByLot();

        double tmpTotalMinute = (tmpParLotMn * qteProductTotal) / qteParLot;
        long tmpTotalMilliSec = (long) tmpTotalMinute * 60 * 1000;
        double nbCheckTotal = qteProductTotal / qteParLot;
        double timeByCheckMn = tmpTotalMinute / nbCheckTotal;
        long timeByCheckMilliSec = (long) timeByCheckMn * 60 * 1000;

        System.out.println("");
        System.out.println("***** Calculs et conversions *****");
        System.out.println("");
        System.out.println("Calculs en cours....");

        infoProd.setTmpTotalMinute(tmpTotalMinute);
        infoProd.setTmpTotalMilliSec(tmpTotalMilliSec);
        infoProd.setNbCheckTotal(nbCheckTotal);
        infoProd.setTimeByCheckMn(timeByCheckMn);
        infoProd.setTimeByCheckMilliSec(timeByCheckMilliSec);
        return infoProd;
    }

    public void displayInfosProd(final InfoProd infoProd) {
        double tmpTotalMn = infoProd.getTmpTotalMinute();
        double nbPointage = infoProd.getNbCheckTotal();
        double tmpEntrePointageMin = infoProd.getTimeByCheckMn();

        System.out.println("");
        System.out.println("***** Affichage infos Prod *****");
        System.out.println("");
        System.out.println("Le temps de travail total est de : " + tmpTotalMn + " mn");
        System.out.println("Il y a " + nbPointage + " pointage(s) à faire");
        System.out.println("Il y a un pointage toutes les " + tmpEntrePointageMin + " mn");
    }
}
