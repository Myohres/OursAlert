package domain.service;

import data.InfoProd;

import java.util.Scanner;

public class InfoProdService {

    public InfoProd infoProdCollector() {
        InfoProd infoProd = new InfoProd();
        System.out.println("");
        System.out.println("***** Entrées informations *****");
        System.out.println("");

        System.out.println("Veuillez saisir le montant total de produits");
        Scanner sc = new Scanner(System.in);
        double nbProdTotal = sc.nextInt();
        System.out.println("Montant total de produits : " + nbProdTotal);

        System.out.println("Veuillez saisir le nombre de produits pour un lot");
        sc = new Scanner(System.in);
        double nbProdByLot = sc.nextInt();
        System.out.println("Montant de produits par lot : " + nbProdByLot);

        System.out.println("Veuillez saisir le temps alloué en minutes pour la production par lot");
        sc = new Scanner(System.in);
        double durationByLotMn = sc.nextInt();
        System.out.println("Le temps alloué en minutes pour la production par lot est de : " + durationByLotMn + " mn");

        infoProd.setNbProdTotal(nbProdTotal);
        infoProd.setNbProdByLot(nbProdByLot);
        infoProd.setDurationByLotMn(durationByLotMn);
        return infoProd;
    }

    public InfoProd calculateAndConversion(final InfoProd infoProd) {
        double durationByLot = infoProd.getDurationByLotMn();
        double nbProdTotal = infoProd.getNbProdTotal();
        double nbProdByLot = infoProd.getNbProdByLot();

        double durationWorkTotalMn = (durationByLot * nbProdTotal) / nbProdByLot;
        long durationWorkTotalMilliSec = (long) durationWorkTotalMn * 60 * 1000;
        double nbCheckTotal = nbProdTotal / nbProdByLot;
        double durationByLotMn = durationWorkTotalMn / nbCheckTotal;
        long durationByLotMilliSec = (long) durationByLotMn * 60 * 1000;

        System.out.println("");
        System.out.println("***** Calculs et conversions *****");
        System.out.println("");
        System.out.println("Calculs en cours....");

        infoProd.setDurationWorkTotalMn(durationWorkTotalMn);
        infoProd.setDurationWorkTotalMilliSec(durationWorkTotalMilliSec);
        infoProd.setNbCheckTotal(nbCheckTotal);
        infoProd.setDurationByLotMn(durationByLotMn);
        infoProd.setDurationByLotMilliSec(durationByLotMilliSec);
        return infoProd;
    }

    public void displayInfosProd(final InfoProd infoProd) {
        double durationWorkTotalMn = infoProd.getDurationWorkTotalMn();
        double nbCheckTotal = infoProd.getNbCheckTotal();
        double durationByLotMn = infoProd.getDurationByLotMn();

        System.out.println("");
        System.out.println("***** Affichage infos Prod *****");
        System.out.println("");
        System.out.println("Le temps de travail total est de : " + durationWorkTotalMn + " mn  /durationWorkTotalMn" );
        System.out.println("Il y a " + nbCheckTotal + " pointage(s) à faire  /NbCheckTotal");
        System.out.println("Il y a un pointage toutes les " + durationByLotMn + " mn / durationByLotMn");
        System.out.println("");
        System.out.println("NbProdTotal : " +infoProd.getNbProdTotal());
        System.out.println("NbProdByLot : " +infoProd.getNbProdByLot());
        System.out.println("");
        System.out.println("DurationByLotMn : " +infoProd.getDurationByLotMn());
        System.out.println("DurationByLotMilliSec: " +infoProd.getDurationByLotMilliSec());
        System.out.println("");
        System.out.println("DurationWorkTotalMn : " +infoProd.getDurationWorkTotalMn());
        System.out.println("DurationWorkTotalMilliSec : " +infoProd.getDurationWorkTotalMilliSec());
        System.out.println("");
        System.out.println("NbCheckTotal : " +infoProd.getNbCheckTotal());
        System.out.println("");
    }
}
