import Compte.Compte;
import Compte.CompteCourant;
import Compte.CompteEpargne;
import Operation.Operation;
import Operation.Versement;
import Operation.Retrait;

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
            System.out.println("4. Effectuer un Virement entre Compte");
            System.out.println("5. Consulter le Solde dun Compte");
            System.out.println("6. Consulter les Operations");
            System.out.println("7. Quitter");
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

                case 2: // Versement
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
                    scanner.nextLine();
                    String source = scanner.nextLine();

                    if (compte instanceof CompteCourant) {
                        ((CompteCourant) compte).verser(montant, source);
                    } else if (compte instanceof CompteEpargne) {
                        ((CompteEpargne) compte).verser(montant, source);
                    }
                    break;

                case 3: // retrait
                    if (comptes.isEmpty()) {
                        System.out.println("Aucun compte disponible !");
                        break;
                    }

                    System.out.println("=== Choisissez le compte pour le retrait ===");
                    for (int i = 0; i < comptes.size(); i++) {
                        System.out.println((i + 1) + ". " + comptes.get(i).getCode() +
                                " - Solde: " + comptes.get(i).getSolde() + "DH");
                    }

                    int compteIndexRetrait = scanner.nextInt() - 1;
                    if (compteIndexRetrait < 0 || compteIndexRetrait >= comptes.size()) {
                        System.out.println("Compte invalide !");
                        break;
                    }

                    Compte compteSelectionne = comptes.get(compteIndexRetrait);
                    System.out.print("Entrez le montant à retirer : ");
                    double montantRetrait = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Entrez la destination du retrait : ");
                    String destination = scanner.nextLine();
                    compteSelectionne.retirer(montantRetrait);
                    Retrait r = new Retrait(montantRetrait, destination);
                    compteSelectionne.listeOperations.add(r);
                    System.out.println("Nouveau solde : " + compteSelectionne.getSolde());
                    System.out.println("Retrait effectué depuis : " + r.getDestination());
                    break;

                case 4:
                    if (comptes.size() < 2) {
                        System.out.println("Il faut au moins deux comptes pour effectuer un virement !");
                        break;
                    }

                    System.out.println("Choisissez le compte SOURCE :");
                    for (int i = 0; i < comptes.size(); i++) {
                        System.out.println((i + 1) + ". " + comptes.get(i).getCode() +
                                " - Solde: " + comptes.get(i).getSolde());
                    }
                    int sourceIndex = scanner.nextInt() - 1;
                    if (sourceIndex < 0 || sourceIndex >= comptes.size()) {
                        System.out.println("Compte source invalide !");
                        break;
                    }
                    Compte compteSource = comptes.get(sourceIndex);

                    System.out.println("Choisissez le compte DESTINATION :");
                    for (int i = 0; i < comptes.size(); i++) {
                        if (i != sourceIndex) {
                            System.out.println((i + 1) + ". " + comptes.get(i).getCode());
                        }
                    }
                    int destIndex = scanner.nextInt() - 1;
                    if (destIndex < 0 || destIndex >= comptes.size() || destIndex == sourceIndex) {
                        System.out.println("Compte destination invalide !");
                        break;
                    }
                    Compte compteDest = comptes.get(destIndex);
                    System.out.print("Montant du virement : ");
                    double montantVirement = scanner.nextDouble();

                    compteSource.retirer(montantVirement);

                    compteDest.verser(montantVirement, "Virement du compte " + compteSource.getCode());
                    System.out.println("Virement effectué de " + compteSource.getCode() +
                            " vers " + compteDest.getCode() + " pour un montant de " + montantVirement);
                    break;

                case 5: // Consulter
                    if (comptes.isEmpty()) {
                        System.out.println("Aucun compte disponible !");
                        break;
                    }

                    System.out.println("=== Choisissez le compte à consulter ===");
                    for (int i = 0; i < comptes.size(); i++) {
                        System.out.println((i + 1) + ". " + comptes.get(i).getCode());
                    }

                    int compteIndexSolde = scanner.nextInt() - 1;
                    if (compteIndexSolde < 0 || compteIndexSolde >= comptes.size()) {
                        System.out.println("Compte invalide !");
                        break;
                    }

                    Compte compteSolde = comptes.get(compteIndexSolde);
                    System.out.println("Le solde du compte " + compteSolde.getCode() +
                            " est : " + compteSolde.getSolde() + " DH");
                    break;

                case 6: // Consulter les opérations
                    if (comptes.isEmpty()) {
                        System.out.println("Aucun compte disponible !");
                        break;
                    }

                    System.out.println("=== Choisissez le compte pour voir ses opérations ===");
                    for (int i = 0; i < comptes.size(); i++) {
                        System.out.println((i + 1) + ". " + comptes.get(i).getCode());
                    }

                    int compteIndexOp = scanner.nextInt() - 1;
                    if (compteIndexOp < 0 || compteIndexOp >= comptes.size()) {
                        System.out.println("Compte invalide !");
                        break;
                    }

                    Compte compteOp = comptes.get(compteIndexOp);

                    if (compteOp.listeOperations.isEmpty()) {
                        System.out.println("Aucune opération trouvée pour ce compte.");
                    } else {
                        System.out.println("=== Opérations du compte " + compteOp.getCode() + " ===");
                        for (Operation op : compteOp.listeOperations) {
                            if (op instanceof Versement) {
                                Versement v = (Versement) op;
                                System.out.println("Versement : " + v.getMontant() + " DH - Source : " + v.getSource());
                            } else if (op instanceof Retrait) {
                                Retrait o = (Retrait) op;
                                System.out.println("Retrait : " + o.getMontant() + " DH - Destination : " + o.getDestination());
                            } else {
                                System.out.println("Opération : " + op.getMontant() + " DH");
                            }
                        }
                    }
                    break;

                case 7:
                    System.out.println("Au revoir !");
                    break;

                default:
                    System.out.println("Choix invalide !");
            }
        } while (choix != 7);

        scanner.close();
    }
}
