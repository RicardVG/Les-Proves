package presentation;


import business.*;
import persistance.TrialDAO;

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

    public Controller(ViewComposer viewComposer, ViewConductor viewConductor, View view, TrialManager trialManager, EditionManager editionManager) {
        this.viewComposer = viewComposer;
        this.viewConductor = viewConductor;
        this.view = view;
        this.trialManager = trialManager;
        this.editionManager = editionManager;
    }

    public void run() throws IOException {

        String optionFaction = "null";
        char optionRole;
        int optionComposer, optionConductor;
        char optionTrial, optionEdition;

        while (!optionFaction.equals("I") && !optionFaction.equals("II")){
            view.pickFaction();
            optionFaction = view.askForString("Pick a faction: ");
            pickedFaction(optionFaction);
        }

        view.showGeneralMenu();
        optionRole = view.askForChar("Enter a role: ");
        while (optionRole != 'A' && optionRole != 'B'){
            optionRole = view.askForChar("Invalid option. Enter A or B: ");
        }

        switch (optionRole) {
            case 'A':
                optionComposer = viewComposer.menuComposer();
                while (optionComposer < 1 || optionComposer > 3) {
                    optionComposer = view.askForOption("That's not a valid input , you have to enter between 1 and 3: ");
                }
                while (optionComposer != 3){
                    if (optionComposer == 1){
                        optionTrial = viewComposer.menuTrialManager();
                        optionTrialManager(optionTrial, optionFaction);
                        viewComposer.showOptions();
                        optionComposer = view.askForOption("Enter an option: ");
                    }
                    if (optionComposer == 2){
                        optionEdition = viewComposer.menuEditionsManager();
                        optionEditionManager(optionEdition, optionFaction);
                        viewComposer.showOptions();
                        optionComposer = view.askForOption("Enter an option: ");
                    }
                }
                break;
            case 'B':
              //  optionConductor = viewConductor.menuConductor();
                break;
            default:
                System.out.println("Enter a valid option!! (A/B)");

        }

    }

    private int optionEditionManager(char optionEdition, String optionFaction) throws IOException {

        int editionYears = 0;
        int initialNumberPlayers = 0;
        int numberTrials = 0;
        String messagePickTrial;
        ArrayList<String> trialArrayList = new ArrayList<>();

        switch (optionEdition){
            case 'a':

                int flag = 0;


                if (trialManager.getAllArrayLists().size() > 0) {
                    do {
                        editionYears = view.askForOption("Enter the edition's year: ");
                    }while(!(editionManager.checkYear(editionYears)));
                    do {
                        initialNumberPlayers = view.askForOption("Enter the initial number of players: ");
                    }while(!(editionManager.checkNumberPlayers(initialNumberPlayers)));
                    do {
                        numberTrials = view.askForOption("Enter the number of trials: ");
                    }while(!(editionManager.checkNumberTrials(numberTrials)));

                    view.showMenuTrials(trialManager.getAllArrayLists());
                    for (int i = 1; i <= numberTrials && flag == 0; i++) {
                        if(numberTrials <= trialManager.getAllArrayLists().size()) {
                            messagePickTrial = editionManager.createMessagePickEditionTrials(i, numberTrials);
                            int numTrial = view.askForOption(messagePickTrial);
                            numTrial = numTrial - 1;
                            for (int x = 0; x < trialManager.getAllArrayLists().size(); x++) {
                                if (numTrial == x) {
                                    trialArrayList.add(trialManager.getAllArrayLists().get(x).getName());
                                }
                            }
                        }else{
                            System.out.println("\nThere isn't enough trials to pick!");
                            flag = 1;
                        }

                    }
                    Edition edition = new Edition(editionYears,initialNumberPlayers,numberTrials, trialArrayList);
                    editionManager.addEditionToArrayList(edition);
                    if (flag != 1){
                        editionManager.writeEditions(optionFaction);
                        System.out.println("\nThe edition was created successfully!\n");
                    }
                }else{
                    System.out.println("\nThere are no trials created!\n");
                }
                break;
            case 'b':
                int optionEditionList = 0;
                view.showEditionsMenu(editionManager.getEditionArrayList());
                optionEditionList = view.askForOption("Enter an option: ");
                break;
            case 'c':
                break;
            case 'd':
                break;
            case 'e':
                return EXIT;
        }

        return EXIT;
    }



    private int optionTrialManager(char optionTrial, String optionFaction) throws IOException {

        int optionTrialTypes = 0;

        switch (optionTrial){
            case 'a':
                view.showMenuTrialTypes();
                optionTrialTypes = view.askForOption("Enter the trial's type: ");
                getDataTrials(optionTrialTypes, optionFaction);
                break;
            case 'b':
                listTrials();
                break;
            case 'c':
                deleteTrial();
                break;
            case 'd':
                return EXIT;
        }


        return EXIT;
    }

    private void deleteTrial() throws IOException {
        int optionListTrial;

        if (trialManager.getSizeArrayTrials()  > 0) {
            do {
                view.showListMenuDeleteTrials();
                optionListTrial = view.showAllTrials(trialManager.getAllArrayLists());
                if (optionListTrial > (trialManager.getSizeArrayTrials() + 1) || optionListTrial <= 0){
                    view.incorrectOptionTrialDelete();
                }else{
                    if (optionListTrial != trialManager.getSizeArrayTrials() +1){
                        String confirmation = view.askForString("Enter the trial’s name for confirmation: ");
                        trialManager.removeTrial(confirmation);
                    }


                }
            }while(optionListTrial <= trialManager.getSizeArrayTrials());// && optionListTrial != 5 && optionListTrial != 0); //Abans en el lloc del 5 teniem un 0
        }else{
            view.showNoTrials();
        }
    }



    private PaperPublication createPaperPublication() {

        String trialName = view.askForString("Enter the trial's name: ");
        String journalName = view.askForString("Enter the journal's name: ");
        String journalQuartile = view.askForString("Enter the journal's quartile: ");
        int acceptanceProbability = view.askForOption("Enter the acceptance probability: ");
        int revisionProbability = view.askForOption("Enter the revision probability: ");
        int rejectionProbability = view.askForOption("Enter the rejection probability: ");
        return new PaperPublication(trialName,journalName,journalQuartile,acceptanceProbability,revisionProbability,rejectionProbability);
    }

    private BudgetRequest createBudgetRequest() {
        String trialName = view.askForString("Enter the trial's name: ");
        String entityName = view.askForString("Enter the entity's name: ");
        int budgetAmount = view.askForOption("Enter the budget amount: ");
        return new BudgetRequest(trialName,entityName,budgetAmount);
    }

    private DoctoralThesis createDoctoralThesis() {
        String trialName = view.askForString("Enter the trial's name: ");
        String thesisField = view.askForString("Enter the thesis field of study: ");
        int defenseDifficulty = view.askForOption("Enter the defense difficulty: ");
        return new DoctoralThesis(trialName, thesisField, defenseDifficulty);
    }

    private MasterStudies createMasterStudies() {
        String trialName = view.askForString("Enter the trial's name: ");
        String masterName = view.askForString("Enter the master's name: ");
        int masterECTSNumber = view.askForOption("Enter the master's ECTS number: ");
        int creditProbability = view.askForOption("Enter the credit pass probability: ");
        return new MasterStudies(trialName, masterName, masterECTSNumber, creditProbability);
    }



    private void listTrials() {
        int optionListTrial;

        if (trialManager.getSizeArrayTrials()  > 0) {
            do {
                view.showListMenuTrials();
                optionListTrial = view.showAllTrials(trialManager.getAllArrayLists());
                if (optionListTrial > trialManager.getSizeArrayTrials() + 1 || optionListTrial <= 0){
                    view.showNoTrials();
                }else{
                    if (optionListTrial <= trialManager.getPaperPublicationArrayList().size()) { //La opció es troba dins del tamany de paperPublicationArrayList
                        view.showSpecificInfoPaperPublication(trialManager.getPaperPublicationArrayList(), optionListTrial);
                    } else if (optionListTrial <= trialManager.getSizePPMS()) { //La opció es troba dins del tamany de masterStudiesArrayList
                        //int opcioMS = (optionListTrial - trialManager.getMasterStudiesArrayList().size()) + 1;
                        int opcioMS = optionListTrial-trialManager.getPaperPublicationArrayList().size();
                        //optionListTrial = trialManager.getSizePPMS() - optionListTrial;

                        view.showSpecificInfoMasterStudies(trialManager.getMasterStudiesArrayList(), opcioMS);//optionListTrial);
                    } else if (optionListTrial <= trialManager.getSizePPMSBR()) { //La opció es troba dins del tamany de budgetRequestArrayList
                        //optionListTrial = trialManager.getSizePPMSBR() - optionListTrial;
                        int opcioBR = optionListTrial-trialManager.getSizePPMS();

                        view.showSpecificInfoBudgetRequest(trialManager.getBudgetRequestArrayList(), opcioBR);
                    } else if (optionListTrial <= trialManager.getSizeArrayTrials()) { //La opció es troba dins del tamany de doctoralThesisArrayList
                        //optionListTrial = trialManager.getSizeArrayTrials() - optionListTrial;
                        int opcioDT = optionListTrial-trialManager.getSizePPMSBR();
                        view.showSpecificInfoDoctoralThesis (trialManager.getDoctoralThesisArrayList(), opcioDT);
                    }

                    //SpecificInfoTrial(optionListTrial, infoAllTrials, paperPublicationArrayList, masterStudiesArrayList, budgetRequestArrayList, doctoralThesisArrayList);
                }
            }while(optionListTrial <= trialManager.getSizeArrayTrials());// && optionListTrial != 5 && optionListTrial != 0); //Abans en el lloc del 5 teniem un 0
        }else{
            view.showNoTrials();
        }
    }
/*
    private void SpecificInfoTrial(int optionListTrial, ArrayList<Trial> infoAllTrials, ArrayList <PaperPublication> paperPublicationArrayList, ArrayList <MasterStudies> masterStudiesArrayList, ArrayList <BudgetRequest> budgetRequestArrayList, ArrayList <DoctoralThesis> doctoralThesisArrayList) {
        ArrayList <String> arryaListStringInfo = retornarArrayListString (infoAllTrials, optionListTrial);

        view.showSpecificInfoTrial(infoAllTrials.get(optionListTrial -1));
    }

    private ArrayList<String> retornarArrayListString(ArrayList<Trial> infoAllTrials, int optionListTrial) {
        return infoAllTrials.get(optionListTrial).getInfo();
    }


 */
    private void getDataTrials(int optionTrialTypes, String optionFaction) throws IOException {
        switch (optionTrialTypes) {
            case 1 -> {
                view.putEnter();
                PaperPublication paperPublication = createPaperPublication();
                trialManager.addtoPaperPublicationArrayList(paperPublication);
                view.trialSuccessfull();
                trialManager.writeTrialPaperPublication(optionFaction);
                view.putEnter();
            }
            case 2 -> {
                view.putEnter();
                MasterStudies masterStudies = createMasterStudies();
                trialManager.addMasterStudiesArrayList(masterStudies);
                view.trialSuccessfull();
                trialManager.writeTrialMasterStudies(optionFaction);
                view.putEnter();
            }
            case 3 -> {
                view.putEnter();
                DoctoralThesis doctoralThesis = createDoctoralThesis();
                trialManager.addDoctoralThesisArrayList(doctoralThesis);
                view.trialSuccessfull();
                trialManager.writeTrialDoctoralThesis(optionFaction);
                view.putEnter();
            }
            case 4 -> {
                view.putEnter();
                BudgetRequest budgetRequest = createBudgetRequest();
                trialManager.addBudgetRequestArrayList(budgetRequest);
                view.trialSuccessfull();
                trialManager.writeTrialBudgetRequest(optionFaction);
                view.putEnter();
            }
        }
    }



    private void pickedFaction(String optionFaction) throws IOException {

        switch (optionFaction) {
            case "I", "II" ->  chooseFormat(optionFaction);
            default -> System.out.println("Enter a correct option!");
        }
    }

    private void chooseFormat(String optionFaction) throws IOException {

        if (Objects.equals(optionFaction, "I")){

       //     editionManager.readCSVEditions();

            trialManager.trialsReadCSV();
            System.out.println("\nLoading data from CSV Files...\n");
        }else{

            trialManager.trialsReadJson();
            editionManager.readEditionsJSON();
            System.out.println("\nLoading data from JSON Files...\n");
        }
    }
}
