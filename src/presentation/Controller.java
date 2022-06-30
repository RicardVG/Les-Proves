package presentation;

import business.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Controller {

    private static final int EXIT = 0;
    private ViewComposer viewComposer;
    private ViewConductor viewConductor;
    private View view;
    private TrialManager trialManager;
    private EditionManager editionManager;
    private Competition competition;

    public Controller(ViewComposer viewComposer, ViewConductor viewConductor, View view, TrialManager trialManager,
            EditionManager editionManager, Competition competition) {
        this.viewComposer = viewComposer;
        this.viewConductor = viewConductor;
        this.view = view;
        this.trialManager = trialManager;
        this.editionManager = editionManager;
        this.competition = competition;
        this.viewConductor = new ViewConductor();
    }

    /**
     * Procés principal del programa; es pregunta a l'usuari si es vol fer servir els fitxers JSON o CSv, si vol continuar
     *  com Conductor o Composer i segueix l'execució segons les opcions escollides, mostrant els menús pertinents
     * @throws IOException
     */
    public void run() throws IOException {
        String optionFaction = "null";
        char optionRole;
        int optionComposer;
        char optionTrial, optionEdition;

        while (!optionFaction.equals("I") && !optionFaction.equals("II")) {
            view.pickFaction();
            optionFaction = view.askForString("Pick a faction: ");
            pickedFaction(optionFaction);
        }

        view.showGeneralMenu();
        optionRole = view.askForChar("Enter a role: ");
        while (optionRole != 'A' && optionRole != 'B') {
            optionRole = view.askForChar("Invalid option. Enter A or B: ");
        }

        switch (optionRole) {
            case 'A':
                optionComposer = viewComposer.menuComposer();
                while (optionComposer < 1 || optionComposer > 3) {
                    optionComposer = view
                            .askForOption("That's not a valid input , you have to enter between 1 and 3: ");
                }
                while (optionComposer != 3) {
                    if (optionComposer == 1) {
                        optionTrial = viewComposer.menuTrialManager();
                        while (optionTrial != 'd') {
                            optionTrialManager(optionTrial, optionFaction);
                            optionTrial = viewComposer.menuTrialManager();
                        }
                        viewComposer.showOptions();
                        optionComposer = view.askForOption("Enter an option: ");
                    }
                    if (optionComposer == 2) {
                        optionEdition = viewComposer.menuEditionsManager();
                        while (optionEdition != 'e') {
                            optionEditionManager(optionEdition, optionFaction);
                            optionEdition = viewComposer.menuEditionsManager();
                        }
                        viewComposer.showOptions();
                        optionComposer = view.askForOption("Enter an option: ");
                    }
                }
                view.shutdown();
                break;
            case 'B':
                String player;
                viewConductor.menuConductor();
                if (editionManager.checkActualEdition()) {
                    viewConductor.showActualEdition();
                    if (competition.getState() == 0) {
                        competition.setTrialsName(editionManager.getTrialsName());
                        // competition.setState();
                        for (int i = 0; i < editionManager.getNumPlayers(); i++) {
                            player = view.askForString("Enter the player's name (" + (i + 1) + "/"
                                    + editionManager.getNumPlayers() + "): ");
                            competition.addPlayer(new Player(player));
                        }
                    }
                    playCompetition();
                } else {
                    viewConductor.showNoActualEdition();
                }

                break;
            default:
                System.out.println("Enter a valid option!! (A/B)");
        }

    }

    /**
     * Comença l'execució de la competició fins a la seva finalització
     */
    private void playCompetition() {

        do {
            viewConductor.showCompetition(competition.getState() + 1,
                    competition.getTrialsName(competition.getState()));
            competition.run(trialManager.getTypeObject(competition.getTrialsName(competition.getState())),
                    trialManager.getPaperPublicationArrayList(), trialManager.getMasterStudiesArrayList(),
                    trialManager.getBudgetRequestArrayList(), trialManager.getDoctoralThesisArrayList());
        } while (viewConductor.continueExecution() && competition.getState() < competition.getSizeTrials());
        competition.endCompetition();
    }

    /**
     * Aquesta funció rep la opció que ha d'executar i segons l'opció rebuda, s'encarrega de crear les edicions, llistar-les
     *  duplicar o esborrar una edició.
     * @param optionEdition opció rebuda que determina si crea, llista, duplica o esborra una edició
     * @param optionFaction opció que serveix per dir si la informació de les edicions es guarden en fitxers CSV o JSON
     * @return retorna l'opció que indica que surt de la funció
     * @throws IOException
     */
    private int optionEditionManager (char optionEdition, String optionFaction) throws IOException {

        int editionYears;
        int initialNumberPlayers;
        int numberTrials;
        boolean valid;
        String messagePickTrial;
        ArrayList<String> trialArrayList = new ArrayList<>();

        switch (optionEdition) {
            case 'a':
                int flag = 0;
                if (trialManager.getAllArrayLists().size() > 0) {
                    do {
                        editionYears = view.askForOption("Enter the edition's year: ");
                        valid = editionManager.checkNewYear(editionYears);
                        while (!valid) {
                            editionYears = view.askForOption(
                                    "\nThe edition's year already exists! Please, enter another edition's year: ");
                            valid = editionManager.checkNewYear(editionYears);
                        }
                    } while (!(editionManager.checkYear(editionYears)));
                    do {
                        initialNumberPlayers = view.askForOption("Enter the initial number of players: ");
                    } while (!(editionManager.checkNumberPlayers(initialNumberPlayers)));
                    do {
                        numberTrials = view.askForOption("Enter the number of trials: ");
                    } while (!(editionManager.checkNumberTrials(numberTrials)));

                    viewComposer.showMenuTrials(trialManager.getAllArrayLists());
                    for (int i = 1; i <= numberTrials && flag == 0; i++) {
                        if (numberTrials <= trialManager.getAllArrayLists().size()) {
                            messagePickTrial = editionManager.createMessagePickEditionTrials(i, numberTrials);
                            int numTrial = view.askForOption(messagePickTrial);
                            numTrial = numTrial - 1;
                            for (int x = 0; x < trialManager.getAllArrayLists().size(); x++) {
                                if (numTrial == x) {
                                    trialArrayList.add(trialManager.getAllArrayLists().get(x).getName());
                                }
                            }
                        } else {
                            System.out.println("\nThere isn't enough trials to pick!");
                            flag = 1;
                        }
                    }
                    Edition edition = new Edition(editionYears, initialNumberPlayers, numberTrials, trialArrayList);
                    editionManager.addEditionToArrayList(edition);
                    if (flag != 1) {
                        editionManager.writeEditions(optionFaction);
                        System.out.println("\nThe edition was created successfully!\n");
                    }
                } else {
                    System.out.println("\nThere are no trials created!\n");
                }
                break;
            case 'b':
                int optionEditionList;

                if (editionManager.getSizeArrayEditions() > 0) {
                    do {
                        System.out.println(
                                "\nHere are the current editions, do you want to see more details or go back?\n");
                        optionEditionList = viewComposer.showEditionsMenu(editionManager.getEditionArrayList());
                        if (optionEditionList > editionManager.getSizeArrayEditions() + 1 || optionEditionList <= 0) {
                            viewComposer.showInputIncorrectEditions();
                        } else {
                            if (optionEditionList == editionManager.getSizeArrayEditions() + 1) { // Aqui es prem el
                                                                                                  // botó de tornar
                                                                                                  // enrere
                                optionEditionList = editionManager.getSizeArrayEditions() + 1; // Aquesta seria per
                                                                                               // sortir del do-while
                            } else {
                                viewComposer.showSpecificInformationEdition(
                                        editionManager.getEditionArrayList().get(optionEditionList - 1).getYear(),
                                        editionManager.getEditionArrayList().get(optionEditionList - 1)
                                                .getNumPlayers());
                                for (int x = 0; x < editionManager.getEditionArrayList().get(optionEditionList - 1)
                                        .getStringArrayList().size(); x++) {
                                    viewComposer.showSpecificInformationEditionExtended(
                                            editionManager.getEditionArrayList().get(optionEditionList - 1)
                                                    .getStringArrayList().get(x),
                                            trialManager.getTypeObject(editionManager.getEditionArrayList()
                                                    .get(optionEditionList - 1).getStringArrayList().get(x)),
                                            x);
                                }
                            }
                        }
                    } while (optionEditionList <= editionManager.getSizeArrayEditions());
                } else {
                    viewComposer.showNoEditions();
                }
                break;
            case 'c':
                int newEditionYear;
                int newInitialNumberOfPlayers;

                if (editionManager.getSizeArrayEditions() > 0) {
                    do {
                        System.out.println("\nWhich edition do you want to clone?\n");
                        optionEditionList = viewComposer.showEditionsMenu(editionManager.getEditionArrayList());
                        if (optionEditionList > editionManager.getSizeArrayEditions() + 1 || optionEditionList <= 0) {
                            viewComposer.showInputIncorrectEditions();
                        } else {
                            if (optionEditionList == editionManager.getSizeArrayEditions() + 1) { // Sortir de la llista
                                optionEditionList = editionManager.getSizeArrayEditions() + 1; // Aquesta seria per
                                                                                               // sortir del do-while
                            } else {
                                newEditionYear = view.askForOption("\nEnter the new edition's year: ");
                                valid = editionManager.checkNewYear(newEditionYear);
                                while (!valid) {
                                    newEditionYear = view.askForOption(
                                            "\nThe edition's year already exists! Please, enter another edition's year: ");
                                    valid = editionManager.checkNewYear(newEditionYear);
                                }
                                newInitialNumberOfPlayers = view
                                        .askForOption("\nEnter the new edition's initial number of players: ");
                                editionManager.duplicateEdition(newEditionYear, newInitialNumberOfPlayers,
                                        optionEditionList, optionFaction);
                                viewComposer.showEditionsDuplicateSuccessfully();
                            }
                        }
                    } while (optionEditionList <= editionManager.getSizeArrayEditions());
                } else {
                    viewComposer.showNoEditions();
                }
                break;
            case 'd':
                int confirmationYear;

                if (editionManager.getSizeArrayEditions() > 0) {
                    do {
                        System.out.println("\nWhich edition do you want to delete?\n");
                        optionEditionList = viewComposer.showEditionsMenu(editionManager.getEditionArrayList());
                        if (optionEditionList > editionManager.getSizeArrayEditions() + 1 || optionEditionList <= 0) {
                            viewComposer.showInputIncorrectEditions();
                        } else {
                            if (optionEditionList == editionManager.getSizeArrayEditions() + 1) {
                                optionEditionList = editionManager.getSizeArrayEditions() + 1; // Aquesta seria per
                                                                                               // sortir del do-while
                            } else {
                                confirmationYear = view.askForOption("\nEnter the edition's year for confirmation: ");
                                if (editionManager.deleteEdition(optionEditionList, confirmationYear, optionFaction)) {
                                    viewComposer.showEditionsDeleteSuccessfully();
                                } else {
                                    viewComposer.showInputIncorrectEditions();
                                }
                            }
                        }
                    } while (optionEditionList <= editionManager.getSizeArrayEditions());
                } else {
                    viewComposer.showNoEditions();
                }
                break;
            case 'e':
                return EXIT;
        }
        return EXIT;
    }

    /**
     * Aquesta funció rep la opció que ha d'executar i segons l'opció rebuda, s'encarrega de crear les trials, llistar-les
     *  duplicar o esborrar una trial.
     * @param optionTrial opció rebuda que determina si crea, llista, duplica o esborra una trial
     * @param optionFaction opció que serveix per dir si la informació de les trials es guarden en fitxers CSV o JSON
     * @return retorna l'opció que indica que surt de la funció
     * @throws IOException
     */
    private int optionTrialManager(char optionTrial, String optionFaction) throws IOException {
        int optionTrialTypes;

        switch (optionTrial) {
            case 'a':
                viewComposer.showMenuTrialTypes();
                optionTrialTypes = view.askForOption("Enter the trial's type: ");
                getDataTrials(optionTrialTypes, optionFaction);
                break;
            case 'b':
                listTrials();
                break;
            case 'c':
                deleteTrial(optionFaction);
                break;
            case 'd':
                return EXIT;
        }
        return EXIT;
    }

    /**
     * Aquest procediment es dedica a esborrar una trial, per fer-ho crida a una funció per comprobar que aquesta trial
     *  no es trobi dins d'una de les edicions creades; a part demana confirmar l'acció tornant a escriure el nom de la
     *  trial a esborrar
     * @param optionFaction opció que serveix per dir si la informació de la trial s'esborra d'un fitxer CSV o JSON
     * @throws IOException
     */
    private void deleteTrial(String optionFaction) throws IOException {
        int optionListTrial, flag;

        if (trialManager.getSizeArrayTrials() > 0) {
            do {
                viewComposer.showListMenuDeleteTrials();
                optionListTrial = viewComposer.showAllTrials(trialManager.getAllArrayLists());
                if (optionListTrial > (trialManager.getSizeArrayTrials() + 1) || optionListTrial <= 0) {
                    viewComposer.incorrectOptionTrialDelete();
                } else {
                    if (optionListTrial != trialManager.getSizeArrayTrials() + 1) {
                        String confirmation = view.askForString("Enter the trial’s name for confirmation: ");
                        flag = 0;
                        for (int i = 0; i < editionManager.getEditionArrayList().size(); i++) {
                            for (int j = 0; j < editionManager.getEditionArrayList().get(i).getStringArrayList()
                                    .size(); j++) {
                                if (editionManager.getEditionArrayList().get(i).getStringArrayList().get(j)
                                        .equals(confirmation)) {
                                    flag = 1;
                                }
                            }
                        }
                        if (flag == 0) {
                            trialManager.removeTrial(confirmation, optionFaction);
                        } else {
                            viewComposer.showTrialDeleteError();
                        }
                    }
                }
            } while (optionListTrial <= trialManager.getSizeArrayTrials());// && optionListTrial != 5 && optionListTrial
                                                                           // != 0); //Abans en el lloc del 5 teniem un
                                                                           // 0
        } else {
            viewComposer.showNoTrials();
        }
    }

    /**
     * Aquesta funció demana la informació necessària per poder crear una trial de tipus PaperPublication fent les pertinents
     *  comprobacions de paràmetres.
     * @return retorna un objecte de tipus PaperPublication
     */
    private PaperPublication createPaperPublication() {
        String trialName = view.askForString("Enter the trial's name: ");

        while (!trialManager.checkNameTrial(trialName)) {
            trialName = view.askForString("The name of the trial already exists! Enter a valid trial's name: ");
        }

        String journalName = view.askForString("Enter the journal's name: ");
        String journalQuartile = view.askForString("Enter the journal's quartile: ");

        while (!trialManager.checkQuartileTrial(journalQuartile)) {
            journalQuartile = view
                    .askForString("The Quartile isn't correct! Please enter another journal's quartile: ");
        }

        int acceptanceProbability = view.askForOption("Enter the acceptance probability: ");

        while (!trialManager.checkProbability(acceptanceProbability)) {
            acceptanceProbability = view.askForOption(
                    "The acceptance probability is not correct! Please, enter another acceptance probability: ");
        }

        int revisionProbability = view.askForOption("Enter the revision probability: ");

        while (!trialManager.checkRevisionProbability(acceptanceProbability, revisionProbability)
                || !trialManager.checkProbability(revisionProbability)) {
            revisionProbability = view.askForOption(
                    "The revisionProbability probability is not correct! Please, enter another revision probability: ");
        }

        int rejectionProbability = view.askForOption("Enter the rejection probability: ");

        while (!trialManager.checkRejectionProbability(acceptanceProbability, revisionProbability, rejectionProbability)
                || !trialManager.checkProbability(rejectionProbability)) {
            rejectionProbability = view.askForOption(
                    "The rejectionProbability probability is not correct! Please, enter another rejection probability: ");
        }

        return new PaperPublication(trialName, journalName, journalQuartile, acceptanceProbability, revisionProbability,
                rejectionProbability);
    }

    /**
     * Aquesta funció demana la informació necessària per poder crear una trial de tipus BudgetRequest fent les pertinents
     *  comprobacions de paràmetres.
     * @return retorna un objecte de tipus BudgetRequest
     */
    private BudgetRequest createBudgetRequest() {
        String trialName = view.askForString("Enter the trial's name: ");

        while (!trialManager.checkNameTrial(trialName)) {
            trialName = view.askForString("The name of the trial already exists! Enter a valid trial's name: ");
        }

        String entityName = view.askForString("Enter the entity's name: ");
        int budgetAmount = view.askForOption("Enter the budget amount: ");

        while (budgetAmount < 1000 || budgetAmount > 2000000000) {
            budgetAmount = view.askForOption(
                    "You have introduced an incorrect amount for the budget; please reenter the budget with an appropiate amount [1000 - 2000000000]: ");
        }
        return new BudgetRequest(trialName, entityName, budgetAmount);
    }

    /**
     * Aquesta funció demana la informació necessària per poder crear una trial de tipus DoctoralThesis fent les pertinents
     *  comprobacions de paràmetres.
     * @return retorna un objecte de tipus DoctoralThesis
     */
    private DoctoralThesis createDoctoralThesis() {
        String trialName = view.askForString("Enter the trial's name: ");

        while (!trialManager.checkNameTrial(trialName)) {
            trialName = view.askForString("The name of the trial already exists! Enter a valid trial's name: ");
        }

        String thesisField = view.askForString("Enter the thesis field of study: ");
        int defenseDifficulty = view.askForOption("Enter the defense difficulty: ");

        while (defenseDifficulty < 1 || defenseDifficulty > 10) {
            defenseDifficulty = view.askForOption(
                    "You introduced an incorrect value for the difficulty; please enter a valid value [1 - 10]: ");
        }
        return new DoctoralThesis(trialName, thesisField, defenseDifficulty);
    }

    /**
     * Aquesta funció demana la informació necessària per poder crear una trial de tipus MasterStudies fent les pertinents
     *  comprobacions de paràmetres.
     * @return retorna un objecte de tipus MasterStudies
     */
    private MasterStudies createMasterStudies() {
        String trialName = view.askForString("Enter the trial's name: ");

        while (!trialManager.checkNameTrial(trialName)) {
            trialName = view.askForString("The name of the trial already exists! Enter a valid trial's name: ");
        }

        String masterName = view.askForString("Enter the master's name: ");
        int masterECTSNumber = view.askForOption("Enter the master's ECTS number: ");

        while (masterECTSNumber < 60 || masterECTSNumber > 120) {
            masterECTSNumber = view
                    .askForOption("You entered an incorrect amount of ECTS; please put a valid number [60 - 120]: ");
        }
        int creditProbability = view.askForOption("Enter the credit pass probability: ");

        while (creditProbability < 0 || creditProbability > 100) {
            creditProbability = view
                    .askForOption("You entered an incorrect probability; please put a valid number [0 - 100]: ");
        }

        return new MasterStudies(trialName, masterName, masterECTSNumber, creditProbability);
    }

    /**
     * Aquest procediment es dedica a llistar totes les trials creades fins el moment; si no hi ha cap, mostra un missatge
     *  d'error
     */
    private void listTrials() {
        int optionListTrial;

        if (trialManager.getSizeArrayTrials() > 0) {
            do {
                viewComposer.showListMenuTrials();
                optionListTrial = viewComposer.showAllTrials(trialManager.getAllArrayLists());
                if (optionListTrial > trialManager.getSizeArrayTrials() + 1 || optionListTrial <= 0) {
                    viewComposer.showInputIncorrectTrials();
                } else {
                    if (optionListTrial <= trialManager.getPaperPublicationArrayList().size()) { // La opció es troba
                                                                                                 // dins del tamany de
                                                                                                 // paperPublicationArrayList
                        viewComposer.showSpecificInfoPaperPublication(trialManager.getPaperPublicationArrayList(),
                                optionListTrial);
                    } else if (optionListTrial <= trialManager.getSizePPMS()) { // La opció es troba dins del tamany de
                                                                                // masterStudiesArrayList
                        // int opcioMS = (optionListTrial -
                        // trialManager.getMasterStudiesArrayList().size()) + 1;
                        int opcioMS = optionListTrial - trialManager.getPaperPublicationArrayList().size();
                        // optionListTrial = trialManager.getSizePPMS() - optionListTrial;

                        viewComposer.showSpecificInfoMasterStudies(trialManager.getMasterStudiesArrayList(), opcioMS);// optionListTrial);
                    } else if (optionListTrial <= trialManager.getSizePPMSBR()) { // La opció es troba dins del tamany
                                                                                  // de budgetRequestArrayList
                        // optionListTrial = trialManager.getSizePPMSBR() - optionListTrial;
                        int opcioBR = optionListTrial - trialManager.getSizePPMS();

                        viewComposer.showSpecificInfoBudgetRequest(trialManager.getBudgetRequestArrayList(), opcioBR);
                    } else if (optionListTrial <= trialManager.getSizeArrayTrials()) { // La opció es troba dins del
                                                                                       // tamany de
                                                                                       // doctoralThesisArrayList
                        // optionListTrial = trialManager.getSizeArrayTrials() - optionListTrial;
                        int opcioDT = optionListTrial - trialManager.getSizePPMSBR();
                        viewComposer.showSpecificInfoDoctoralThesis(trialManager.getDoctoralThesisArrayList(), opcioDT);
                    }

                    // SpecificInfoTrial(optionListTrial, infoAllTrials, paperPublicationArrayList,
                    // masterStudiesArrayList, budgetRequestArrayList, doctoralThesisArrayList);
                }
            } while (optionListTrial <= trialManager.getSizeArrayTrials());// && optionListTrial != 5 && optionListTrial
                                                                           // != 0); //Abans en el lloc del 5 teniem un
                                                                           // 0
        } else {
            viewComposer.showNoTrials();
        }
    }

    /**
     * Aquest procediment es dedica a crear una trial nova, guardar la nova trial en un arrayList del seu tipus i finalment
     *  guardar la trial en el fitxer corresponent
     * @param optionTrialTypes Tipus de trial que es desitja guardar
     * @param optionFaction Tipus de fitxer en el qual es vol guardar la trial
     * @throws IOException
     */
    private void getDataTrials(int optionTrialTypes, String optionFaction) throws IOException {
        switch (optionTrialTypes) {
            case 1 -> {
                view.putEnter();
                PaperPublication paperPublication = createPaperPublication();
                trialManager.addtoPaperPublicationArrayList(paperPublication);
                viewComposer.trialSuccessfull();
                trialManager.writeTrialPaperPublication(optionFaction);
                view.putEnter();
            }
            case 2 -> {
                view.putEnter();
                MasterStudies masterStudies = createMasterStudies();
                trialManager.addMasterStudiesArrayList(masterStudies);
                viewComposer.trialSuccessfull();
                trialManager.writeTrialMasterStudies(optionFaction);
                view.putEnter();
            }
            case 3 -> {
                view.putEnter();
                DoctoralThesis doctoralThesis = createDoctoralThesis();
                trialManager.addDoctoralThesisArrayList(doctoralThesis);
                viewComposer.trialSuccessfull();
                trialManager.writeTrialDoctoralThesis(optionFaction);
                view.putEnter();
            }
            case 4 -> {
                view.putEnter();
                BudgetRequest budgetRequest = createBudgetRequest();
                trialManager.addBudgetRequestArrayList(budgetRequest);
                viewComposer.trialSuccessfull();
                trialManager.writeTrialBudgetRequest(optionFaction);
                view.putEnter();
            }
        }
    }

    /**
     * Aquest procediment te la funció d'escollir el tipus de fitxer en el qual es vol seguir l'execució del programa
     *  per guardar les trials i edicions
     * @param optionFaction opció sobre el tipus de fitxer que es vol fer servir
     * @throws IOException
     */
    private void pickedFaction(String optionFaction) throws IOException {
        switch (optionFaction) {
            case "I", "II" -> chooseFormat(optionFaction);
            default -> System.out.println("Enter a correct option!");
        }
    }

    /**
     * Aquest procediment es dedica a llegir la informació dels fitxers de trials i edicions en el format escollit prèviament
     *  que s'ha passat com a paràmetre
     * @param optionFaction tipus de fitxer que es vol fer servir
     * @throws IOException
     */
    private void chooseFormat(String optionFaction) throws IOException {
        if (Objects.equals(optionFaction, "I")) {
            trialManager.trialsReadCSV();
            editionManager.readEditionsCSV();
            System.out.println("\nLoading data from CSV Files...\n");
        } else {
            trialManager.trialsReadJson();
            editionManager.readEditionsJSON();
            System.out.println("\nLoading data from JSON Files...\n");
        }
    }
}
