package com.BatiCouisine;

import com.BatiCouisine.controller.*;
import com.BatiCouisine.entities.*;
import com.BatiCouisine.repository.*;
import com.BatiCouisine.repository.implementation.*;
import com.BatiCouisine.service.*;
import com.BatiCouisine.service.Implementation.*;
import com.BatiCouisine.util.DBUtils;

import java.util.*;

import static com.BatiCouisine.util.DBUtils.validateAndGetDate;

public class Main {

    private static Projet projet = new Projet();
    private static MainDoeuvre mainDoeuvre = new MainDoeuvre();
    private static Materiau materiau = new Materiau();
    private static Devis devis = new Devis();
    private static Client client = new Client();

    private static List<MainDoeuvre> MainDoeuvreList = new ArrayList<>();;
    private static List<Materiau> MateriauList = new ArrayList<>();;

    private static double pourcentageMargeBeneficiaire = 0.0;
    private static double pourcentageTVA = 0.0;


    public static void main(String[] args) {
        ClientController clientController = initializeClientController();
        ProjectController projectController = initializeProjectController();
        MateriauController materiauController = initializeMateriauController();
        MainDOeuvreController mainDoeuvreController = initializeMainDOeuvreController();

        Scanner scanner = new Scanner(System.in);
        int choix;

        System.out.println("=== Bienvenue dans l'application de gestion des projets de rénovation de cuisines ===");

        do {
            mainMenu();
            System.out.println(">>>>>>   Veuillez choisir une option:   <<<<<<");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    handleNewProjectCreation(clientController, projectController, materiauController, mainDoeuvreController, scanner);
                    break;
                case 2:
                    System.out.println("Affichage des projets existants");
                    break;
                case 3:
                    System.out.println("Calcul du coût d'un projet");
                    break;
                case 4:
                    System.out.println("Merci d'avoir utilisé notre application");
                    break;
                default:
                    System.out.println("Veuillez choisir une option valide");
                    break;
            }
        } while (choix != 4);
    }

    private static void handleNewProjectCreation(ClientController clientController, ProjectController projectController,
                                                 MateriauController materiauController, MainDOeuvreController mainDoeuvreController,
                                                 Scanner scanner) {
        Optional<Client> OptionalClient = clientController.index();
        if (OptionalClient.isPresent() && OptionalClient.get().getId() != 0) {
            System.out.println("Souhaitez-vous continuer avec ce client ? (y/n) : ");
            if (scanner.nextLine().equalsIgnoreCase("y")) {
                projet  = projectController.store(OptionalClient.get().getId());
                if (projet.getId() != 0) {
                    handleMateriauCreation(materiauController, scanner, projet.getId());
                    handleMainDoeuvreCreation(mainDoeuvreController, scanner, projet.getId());
                    handleClaculCoutTotale(scanner, new MateriauServiceImp(new MateriauRepositoryImp()), new MainDoeuvreServiceImp(new MainDoeuvreRepositoryImp()));
                    System.out.println("--- Résultat du Calcul ---\n" +
                            "Nom du projet : " + projet.getNom() + "\n" +
                            "Client : " + OptionalClient.get().getNom() + "\n" +
                            "Adresse du chantier : " + OptionalClient.get().getAddress() + "\n" +
                            "Surface : " + projet.getSurfaceCouisine() + " m²\n" +
                            "--- Détail des Coûts ---");
                    System.out.println("1. Matériaux :");
                    MateriauList.forEach(materiau -> {
                        System.out.println("Nom : " + materiau.getNom()   + calculateCoutTotalMateriau(materiau) +" ( quantité : " + materiau.getQuantite() + " coutUnitaire : " + materiau.getCoutUnitaire() + " coutTransport : " + materiau.getCoutTransport() + " coefficientQualite : " + materiau.getCoefficientQualite()  +")");
                    });
                    System.out.println("Coût total des matériaux avant TVA : " + MateriauList.stream().mapToDouble(materiau -> calculateCoutTotalMateriau(materiau)).sum());
                    System.out.println("Coût total des matériaux avec TVA (" + pourcentageTVA + "%) : " + MateriauList.stream().mapToDouble(materiau -> materiau.getTauxTVA()).sum());

                    System.out.println("2. Main d'œuvre :");

                    MainDoeuvreList.forEach(mainDoeuvre -> {
                        System.out.println("Nom : " + mainDoeuvre.getNom() + calculateCoutTotalMainDoeuvre(mainDoeuvre) + " ( tauxHoraire : " + mainDoeuvre.getTauxHoraire() + " heurTravail : " + mainDoeuvre.getHeurTravail() + " productiviteOuvrier : " + mainDoeuvre.getProductiviteOuvrier() + ")");
                    });
                    System.out.println("Coût total de la main d'œuvre avant TVA : " + MainDoeuvreList.stream().mapToDouble(mainDoeuvre -> calculateCoutTotalMainDoeuvre(mainDoeuvre)).sum());
                    System.out.println("Coût total de la main d'œuvre avec TVA (" + pourcentageTVA + "%) : " + MainDoeuvreList.stream().mapToDouble(mainDoeuvre -> mainDoeuvre.getTauxTVA()).sum());

                    double totalCoutMateriau = MateriauList.stream().mapToDouble(materiau -> calculateCoutTotalMateriau(materiau)).sum();
                    double totalCoutMainDoeuvre = MainDoeuvreList.stream().mapToDouble(mainDoeuvre -> calculateCoutTotalMainDoeuvre(mainDoeuvre)).sum();
                    double totalCoutAvantMarge = totalCoutMateriau + totalCoutMainDoeuvre;
                    double margeBeneficiaire = totalCoutAvantMarge * pourcentageMargeBeneficiaire;
                    double totalCoutApresMarge = totalCoutAvantMarge + margeBeneficiaire;

                    System.out.println("3. Coût total avant marge : " + totalCoutAvantMarge);
                    System.out.println("4. Marge bénéficiaire : " + margeBeneficiaire);
                    projet.setCoutTotal(totalCoutAvantMarge);
                    projet.setMargeBeneficiaire(margeBeneficiaire);

                    System.out.println("5. Coût total après marge : " + totalCoutApresMarge);

                    System.out.println("--- Enregistrement du Devis ---");
                    System.out.println("Entrez la date d'émission du devis (format : jj/mm/aaaa) : ");
                    Date dateEmission =  DBUtils.validateAndGetDate(scanner.nextLine());
                    System.out.println("Entrez la date de validité du devis (format : jj/mm/aaaa) : ");
                    Date dateValidite =  DBUtils.validateAndGetDate(scanner.nextLine());

                    System.out.println("Souhaitez-vous enregistrer le devis ? (y/n) : ");
                    String choixEnregistrementDevis = scanner.nextLine();
                    Devis devis = new Devis();
                    devis.setDateMission(dateEmission);
                    devis.setDateValidite(dateValidite);
                    devis.setMontantEstime(totalCoutApresMarge);
                    if (choixEnregistrementDevis.equalsIgnoreCase("y")) {
                        devis.setAccepted(true);
                        projet.setEtat(EtatProject.EN_COURS);
                        System.out.println("Devis enregistré avec succès !");
                    }else{
                        projet.setEtat(EtatProject.ANNULER);
                        devis.setAccepted(false);
                    }
                    projectController.update(projet.getId(), projet);
                    DevisService devisService = new DevisServiceImp(new DevisRepositoryImp());
                    devisService.store(devis, projet.getId());
                } else {
                    System.out.println("Vous avez choisi de ne pas continuer avec ce client");
                }
            }
        }
    }

    private static void handleMateriauCreation(MateriauController materiauController, Scanner scanner, int idProjet) {
        System.out.println("--- Ajout des matériaux ---");
        String choixContinuedCreation;
        do {
            materiau = materiauController.store(idProjet);
            if (materiau.getId() != 0) {
                System.out.println("Matériau ajouté avec succès !");
                System.out.println("Voulez-vous ajouter un autre matériau ? (y/n) : ");
                MateriauList.add(materiau);
                choixContinuedCreation = scanner.nextLine().toLowerCase();
            } else {
                choixContinuedCreation = "n";
            }
        } while (choixContinuedCreation.equals("y"));
    }

    private static void handleMainDoeuvreCreation(MainDOeuvreController mainDoeuvreController, Scanner scanner, int idProjet) {
        System.out.println("--- Ajout de la main d'œuvre ---");
        String choixContinuedCreation;
        do {
            mainDoeuvre = mainDoeuvreController.store(idProjet);
            if (mainDoeuvre.getId() != 0) {
                System.out.println("Main d'œuvre ajoutée avec succès !");
                MainDoeuvreList.add(mainDoeuvre);
                System.out.println("Voulez-vous ajouter une autre main d'œuvre ? (y/n) : ");
                choixContinuedCreation = scanner.nextLine().toLowerCase();
            } else {
                choixContinuedCreation = "n";
            }
        } while (choixContinuedCreation.equals("y"));
    }

    private static void handleClaculCoutTotale(Scanner scanner, MateriauService materiauService, MainDoeuvreService mainDoeuvreService) {
        System.out.println("--- Calcul du coût total ---\n");

        System.out.println("Souhaitez-vous appliquer une TVA au projet ? (y/n) : ");
        String choixApplicateTVA = scanner.nextLine();

        if (choixApplicateTVA.equalsIgnoreCase("y")) {
            System.out.println("Entrez le pourcentage de TVA (%) : ");
            pourcentageTVA = scanner.nextDouble();
            scanner.nextLine();
        }
        System.out.println("Souhaitez-vous appliquerune marge bénéficiaire au projet ? (y/n) : y ");
        String choixApplicateMargeBeneficiaire = scanner.nextLine();

        if (choixApplicateMargeBeneficiaire.equalsIgnoreCase("y")) {
            System.out.println("Entrez le pourcentage de marge bénéficiaire (%) : ");
            pourcentageMargeBeneficiaire = scanner.nextDouble();
            scanner.nextLine();
        }

        System.out.println("Calcul du coût en cours...\n");

        MateriauList.forEach(materiau -> {
            double CoutTotalMateriau = calculateCoutTotalMateriau(materiau);
            double TauxTVA = 0.0;
            if(pourcentageTVA != 0.0) {
                TauxTVA = CoutTotalMateriau + CoutTotalMateriau * pourcentageTVA / 100;
                materiau.setTauxTVA(TauxTVA);
                materiauService.update(materiau.getId(), materiau);
            }

        });
        double coutTotalMateriaux = MateriauList.stream()
                .mapToDouble(materiau -> materiau.getTauxTVA())
                .sum();

        MainDoeuvreList.forEach(mainDoeuvre -> {
            double coutTotalMainDoeuvre = calculateCoutTotalMainDoeuvre(mainDoeuvre);
            double TauxTVA = 0.0;
            if(pourcentageTVA != 0.0) {
                TauxTVA = coutTotalMainDoeuvre + coutTotalMainDoeuvre * pourcentageTVA / 100;
                mainDoeuvre.setTauxTVA(TauxTVA);
                mainDoeuvreService.update(mainDoeuvre.getId(), mainDoeuvre);
            }
            mainDoeuvre.setTauxTVA(coutTotalMainDoeuvre);
        });

    }

    public static void mainMenu() {
        System.out.println("==================================== Menu Principal =================================\n" +
                "1. Créer un nouveau projet :\n" +
                "2. Afficher les projets existants :\n" +
                "3. Calculer le coût d'un projet :\n" +
                "4. Quitter :\n" +
                "=====================================================================================\n");
    }

    private static ClientController initializeClientController() {
        ClientRepository clientRepository = new ClientRepositoryImp();
        ClientService clientService = new ClientServiceImp(clientRepository);
        return new ClientController(clientService);
    }

    private static ProjectController initializeProjectController() {
        ProjectRepositoryImp projectRepository = new ProjectRepositoryImp();
        ProjectService projectService = new ProjectServiceImp(projectRepository);
        return new ProjectController(projectService);
    }

    private static MateriauController initializeMateriauController() {
        MateriauRepository materiauRepository = new MateriauRepositoryImp();
        MateriauService materiauService = new MateriauServiceImp(materiauRepository);
        return new MateriauController(materiauService);
    }

    private static MainDOeuvreController initializeMainDOeuvreController() {
        MainDoeuvreRepository mainDoeuvreRepository = new MainDoeuvreRepositoryImp();
        MainDoeuvreService mainDoeuvreService = new MainDoeuvreServiceImp(mainDoeuvreRepository);
        return new MainDOeuvreController(mainDoeuvreService);
    }

    private static double calculateCoutTotalMateriau(Materiau materiau) {
        double coutTotalMateriau = materiau.getCoutUnitaire() * materiau.getQuantite() * materiau.getCoefficientQualite() + materiau.getCoutTransport() ;
        return coutTotalMateriau;
    }

    private static double calculateCoutTotalMainDoeuvre(MainDoeuvre mainDoeuvre) {
        double coutTotalMainDoeuvre = mainDoeuvre.getTauxHoraire() * mainDoeuvre.getHeurTravail() * mainDoeuvre.getProductiviteOuvrier();
        return coutTotalMainDoeuvre;
    }
}
