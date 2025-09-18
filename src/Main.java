import Compte.Compte;
import Compte.CompteCourant;
import Compte.CompteEpargne;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Compte> comptes = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choix, compteType;

        do {
            System.out.println("\n=== Menu Banque ===");
            System.out.println("1. Creer un compte");
            System.out.println("2. Effectuer un Versement ");
            System.out.println("3. Effectuer un Retrait ");
            System.out.println("4. Afficher tous les comptes");
            System.out.println("5. Quitter");
            System.out.print("Votre choix : ");

            choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    do {
                        System.out.println("1. Compte Courant ");
                        System.out.println("2. Compte Epargne ");
                        System.out.println("3. Retour ");
                        System.out.println("Votre choix : ");

                        compteType = scanner.nextInt();

                        switch (compteType) {
                            case 1:
                                System.out.print("Rentrez votre solde : ");
                                double soldeCourant = scanner.nextDouble();
                                System.out.print("Rentrez votre decouvert : ");
                                double decouvert = scanner.nextDouble();
                                CompteCourant compteCourant = new CompteCourant(soldeCourant, decouvert);
                                comptes.add(compteCourant);
                                System.out.println("Compte courant créé !");
                                break;
                            case 2:
                                System.out.print("Rentrez votre solde : ");
                                double soldeEpargne = scanner.nextDouble();
                                System.out.print("Rentrez le taux d'interet : ");
                                double tauxInteret = scanner.nextDouble();
                                CompteEpargne compteEpargne = new CompteEpargne(soldeEpargne, tauxInteret);
                                comptes.add(compteEpargne);
                                System.out.println("Compte épargne créé !");
                                break;
                            case 3:
                                System.out.println("Retour au menu principal...");
                                break;
                            default:
                                System.out.println("Choix invalide !");
                        }
                    } while (compteType != 3);
                    break;

                case 2: // Effectuer un versement
                    if (comptes.isEmpty()) {
                        System.out.println("Aucun compte disponible !");
                        break;
                    }

                    System.out.println("Choissisez le compte pour le versement :");
                    for (int i = 0; i < comptes.size(); i++) {
                        System.out.println((i + 1) + ". " + comptes.get(i).getCode());
                    }
                    int compteIndex = scanner.nextInt() - 1;
                    if (compteIndex < 0 || compteIndex >= comptes.size()) {
                        System.out.println("Compte Invalide !");
                        break;
                    }

                    Compte compte = comptes.get(compteIndex);
                    System.out.print("Montant de Versement : ");
                    double montant = scanner.nextDouble();
                    System.out.print("Source de Versement : ");
                    scanner.nextLine(); // consommer le retour à la ligne
                    String source = scanner.nextLine();

                    if (compte instanceof CompteCourant) {
                        ((CompteCourant) compte).verser(montant, source);
                    } else if (compte instanceof CompteEpargne) {
                        ((CompteEpargne) compte).verser(montant, source);
                    }
                    break;

                case 3: // Effectuer un retrait
                    if (comptes.isEmpty()) {
                        System.out.println("Aucun compte disponible !");
                        break;
                    }

                    System.out.println("=== Choisissez le compte pour le retrait ===");
                    for (int i = 0; i < comptes.size(); i++) {
                        System.out.println((i + 1) + ". " + comptes.get(i).getCode() +
                                " - Solde: " + comptes.get(i).getSolde());
                    }

                    int compteIndexRetrait = scanner.nextInt() - 1;
                    if (compteIndexRetrait < 0 || compteIndexRetrait >= comptes.size()) {
                        System.out.println("Compte invalide !");
                        break;
                    }

                    Compte compteSelectionne = comptes.get(compteIndexRetrait);
                    System.out.print("Entrez le montant à retirer : ");
                    double montantRetrait = scanner.nextDouble();
                    compteSelectionne.retirer(montantRetrait);
                    System.out.println("Nouveau solde : " + compteSelectionne.getSolde());
                    break;


                case 4:
                    System.out.println("\n=== Liste des comptes ===");
                    if (comptes.isEmpty()) {
                        System.out.println("Aucun compte créé !");
                    } else {
                        for (Compte c : comptes) {
                            c.afficherDetails();
                            System.out.println("-------------------------");
                        }
                    }
                    break;
                case 5:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide !");
            }
        } while (choix != 5);

        scanner.close();
    }
}
