package Compte;
import Operation.Retrait;

public class CompteEpargne extends Compte {
    protected double tauxInteret = 0.05;

    public CompteEpargne(double solde, double tauxInteret){
        super(solde);
        this.tauxInteret = tauxInteret;
    }

    public double getTauxInteret() {
        return tauxInteret;
    }

    @Override
    public void retirer(double montant) {
        try{
            if (montant > 0 && solde >= montant) {
                solde -= montant;
                Retrait retrait = new Retrait(montant, "Retrait");
                listeOperations.add(retrait);
                System.out.println("Retrait de " + montant + " effectué. Nouveau solde : " + solde + "DH");
            } else {
                System.out.println("Fonds insuffisants !");
            }
        } catch (Exception e) {
            System.out.println("⚠️ Erreur lors du retrait : ");
        }
    }


    public void creerCompteEpargne(CompteEpargne compteEpargne){

    }

    @Override
    public double calculerInteret(){
        double taux = 0.05;
        return solde * taux;
    }

    @Override
    public void afficherDetails(){
        System.out.println("code :" +code);
        System.out.println("solde :" +solde);
        System.out.println("Taux d'intérêt :" +tauxInteret);
        System.out.println("nombre d'operation : " +listeOperations.size());
    }
}
