package com.bank.model;

import java.util.HashMap;

public class CompteCourant extends Compte {

    private double decouvert;
    private HashMap<String, CompteCourant> compteListe = new HashMap<>();

    public CompteCourant(double solde, double decouvert) {
        super(solde);
        this.decouvert = decouvert;
    }

    public double getDecouvert() {
        return decouvert;
    }

    @Override
    public void retirer(double montant) {
        if (solde - montant >= -decouvert) {
            solde -= montant;
            System.out.println("Retrait Effectue : " + montant);
        } else {
            System.out.println("Retrait impossible");
        }
    }

    @Override

    public double calculerInteret() {
        return 0;
    }

    public void creerCompteCourant(CompteCourant compteCourant){
        compteListe.put(this.code,compteCourant);
        System.out.println(compteListe.get(this.code).toString());

    }

    @Override
    public String toString() {
        return "CompteCourant{" +
                "decouvert=" + decouvert +
                ", code='" + this.code + '\'' +
                ", solde=" + solde +
                '}';
    }

    @Override

    public void afficherDetails() {

        System.out.println("Code :" + code);
        System.out.println("solde :" + solde);
        System.out.println("Découvert :" + decouvert);
        System.out.println("Nombre d'opérations : " + listeOperations.size());
    }
}
