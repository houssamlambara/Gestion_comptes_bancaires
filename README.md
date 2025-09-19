🏦 Application Console – Gestion de Comptes Bancaires

Une banque souhaite automatiser la gestion de ses comptes via un système informatisé.
Cette application console en Java 8 permet de gérer efficacement les comptes bancaires et leurs opérations (versements, retraits, virements).

🗂️ Structure de l’application

Couche Présentation (UI/Menu) : Interface console interactive avec menus.

Couche Métier : Gestion de la logique des comptes et opérations.

Couche Utilitaire : Fonctions d’aide, validations, etc.
(Vous pouvez ajouter d’autres couches si nécessaire, comme une couche “DAO” pour la persistance future.)

🧩 Modèle Objet
Classe abstraite Compte

Attributs :

code

solde

listeOperations

Méthodes abstraites :

retirer()

calculerInteret()

afficherDetails()

Règle des codes compte : format CPT-XXXXX (X = chiffre).

Classe CompteCourant (hérite de Compte)

Attribut supplémentaire : decouvert

Calcul d’intérêt : retourne 0 (pas d’intérêt).

Règle de retrait : solde final ≥ -découvert.

Classe CompteEpargne (hérite de Compte)

Attribut supplémentaire : tauxInteret

Calcul d’intérêt : selon le taux.

Règle de retrait : possible uniquement si solde ≥ montant à retirer.

Classe abstraite Operation

Attributs :

numero (UUID)

date

montant

Classe Versement (hérite d’Operation)

Attribut supplémentaire : source (ex : “Virement externe”, “Salaire”).

Classe Retrait (hérite d’Operation)

Attribut supplémentaire : destination (ex : “Distributeur ATM”, “Chèque”).

⚙️ Fonctionnalités principales

✅ Créer un compte (courant ou épargne).

✅ Effectuer un versement.

✅ Effectuer un retrait.

✅ Effectuer un virement entre comptes (en réutilisant les méthodes existantes).

✅ Consulter le solde d’un compte.

✅ Consulter la liste des opérations effectuées sur un compte.

🖥️ Interface utilisateur

Menu interactif dans la console.

Navigation simple : choix numérotés.

Validation des saisies (montants positifs, format du code compte…).

🛠️ Spécifications techniques

Langage : Java 8.

Structures de données : ArrayList / HashMap pour le stockage et la recherche rapide.

Dates : API Java Time pour gérer les dates d’opération.

Gestion des exceptions : try/catch et messages clairs pour l’utilisateur.

Persistance des données : en mémoire (jusqu’à la fermeture de l’application).

📂 Structure du projet

src/
├─ Compte/
│   ├─ Compte.java
│   ├─ CompteCourant.java
│   └─ CompteEpargne.java
│
├─ Operation/
│   ├─ Operation.java
│   ├─ Versement.java
│   └─ Retrait.java
│
│
└─ Main.java

Capture D'ecrant

![Capture d’écran 2025-09-19 204016.png](../Capture%20d%E2%80%99%C3%A9cran%202025-09-19%20204016.png)
![Capture d’écran 2025-09-19 204036.png](../Capture%20d%E2%80%99%C3%A9cran%202025-09-19%20204036.png)
![Capture d’écran 2025-09-19 204300.png](../Capture%20d%E2%80%99%C3%A9cran%202025-09-19%20204300.png)
![Capture d’écran 2025-09-19 204327.png](../Capture%20d%E2%80%99%C3%A9cran%202025-09-19%20204327.png)