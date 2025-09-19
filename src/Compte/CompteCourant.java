package Compte;

import Operation.Retrait;
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
        try{
            if (montant > 0 && solde + decouvert >= montant) {
                solde -= montant;
                Retrait retrait = new Retrait(montant, "Retrait");
                listeOperations.add(retrait);
                System.out.println("Retrait de " + montant + " effectué. Nouveau solde : " + solde + "DH");
            } else {
                System.out.println("Fonds insuffisants !");
            }
        } catch (Exception e) {
            System.out.println("⚠️ Erreur lors du retrait " );
        }
    }

    @Override

    public double calculerInteret() {
        return 0;
    }

//    public void creerCompteCourant(CompteCourant compteCourant){
//        compteListe.put(this.code,compteCourant);
//        System.out.println(compteListe.get(this.code).toString());
//    }

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
