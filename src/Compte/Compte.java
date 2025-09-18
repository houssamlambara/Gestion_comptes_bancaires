    package Compte;
    import Operation.Operation;
    import Operation.Versement;

    import java.util.ArrayList;
//    import java.util.HashMap;

    public abstract class Compte {
        public static int chiffre = 11111;
        protected String code;
        protected double solde;
        public ArrayList<Operation> listeOperations = new ArrayList<>();

        public Compte(Double solde){
            this.solde = solde;
            codeGenerateur();
        }

        public String getCode(){
            return code;
        }

        public void setCode(String code){
            this.code = code;
        }

        public double getSolde(){
            return solde;
        }

        public void setSolde(double solde){
            this.solde = solde;
        }

        public void codeGenerateur(){
            code = "CPT-" + chiffre++;
        }

        public void verser(double montant, String source) {
            if (montant > 0) {
                solde += montant;
                Versement v = new Versement(montant, source);
                listeOperations.add(v);
                System.out.println("Versement de " + montant + " effectué depuis " + source + ". Nouveau solde : " + solde + "DH");
            } else {
                System.out.println("Montant invalide !");
            }
        }

        public abstract void retirer(double montant);
        public abstract double calculerInteret();
        public abstract void afficherDetails();
    }

