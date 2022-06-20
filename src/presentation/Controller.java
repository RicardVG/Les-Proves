package presentation;


import business.*;
import persistance.TrialDAO;

import java.io.IOException;
import java.util.Objects;

public class Controller {

    private static final int EXIT = 0;
    private ViewComposer viewComposer;
    private ViewConductor viewConductor;
    private View view;
    private TrialManager trialManager;
    private EditionManager editionManager;
    private TrialDAO trialDAO;

    public Controller(ViewComposer viewComposer, ViewConductor viewConductor, View view, TrialManager trialManager, EditionManager editionManager, TrialDAO trialDAO) {
        this.viewComposer = viewComposer;
        this.viewConductor = viewConductor;
        this.view = view;
        this.trialManager = trialManager;
        this.editionManager = editionManager;
        this.trialDAO = trialDAO;
    }

    public void run() throws IOException {

        String optionFaction = "null";
        char optionRole;
        int optionComposer;
        char optionTrial;

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
                        viewComposer.showOptions();
                        optionComposer = view.askForOption("Enter an option: ");
                    }
                }
                break;
            case 'B':

                break;
            default:
                System.out.println("Enter a valid option!! (A/B)");

        }

    }


    public int optionTrialManager(char optionTrial, String optionFaction) throws IOException {

        int optionTrialTypes = 0;

        switch (optionTrial){
            case 'a':
                view.showMenuTrialTypes();
                optionTrialTypes = view.askForOption("Enter the trial's type: ");
                getDataTrials(optionTrialTypes, optionFaction);
                viewComposer.menuTrialManager();
                break;
                case 'b':
                    break;
                case 'c':
                    break;
                case 'd':
                    return EXIT;
        }

      
        return EXIT;
    }

    private void getDataTrials(int optionTrialTypes, String optionFaction) throws IOException {
        switch (optionTrialTypes) {
            case 1 -> {
                view.putEnter();
                PaperPublication dataPaperPublication = createPaperPublication();
                view.trialSuccessfull();
                trialDAO.writeTrialPaperPublication(optionFaction, dataPaperPublication);
                view.putEnter();
            }
            case 2 -> {
                view.putEnter();
                MasterStudies dataMasterStudies = createMasterStudies();
                view.trialSuccessfull();
                trialDAO.writeTrialMasterStudies(optionFaction, dataMasterStudies);
                view.putEnter();
            }
            case 3 -> {
                view.putEnter();
                DoctoralThesis dataDoctoralThesis = createDoctoralThesis();
                view.trialSuccessfull();
                trialDAO.writeTrialDoctoralThesis(optionFaction, dataDoctoralThesis);
                view.putEnter();
            }
            case 4 -> {
                view.putEnter();
                BudgetRequest dataBudgetRequest = createBudgetRequest();
                trialDAO.writeTrialBudgetRequest(optionFaction, dataBudgetRequest);
                view.trialSuccessfull();
                view.putEnter();
            }
        }
    }

    private BudgetRequest createBudgetRequest() {
        String trialName = view.askForString("Enter the trial's name: ");
        String entityName = view.askForString("Enter the entity's name: ");
        int budgetAmount = view.askForOption("Enter the budget amount: ");
        return trialManager.createBudgetRequest(trialName,entityName,budgetAmount);
    }

    private DoctoralThesis createDoctoralThesis() {
        String trialName = view.askForString("Enter the trial's name: ");
        String thesisField = view.askForString("Enter the thesis field of study: ");
        int defenseDifficulty = view.askForOption("Enter the defense difficulty: ");
        return trialManager.createDoctoralThesis(trialName, thesisField, defenseDifficulty);
    }

    private MasterStudies createMasterStudies() {
        String trialName = view.askForString("Enter the trial's name: ");
        String masterName = view.askForString("Enter the master's name: ");
        int masterECTSNumber = view.askForOption("Enter the master's ECTS number: ");
        int creditProbability = view.askForOption("Enter the credit pass probability: ");
        return trialManager.createMasterStudies(trialName, masterName, masterECTSNumber, creditProbability);
    }

    private PaperPublication createPaperPublication() {
        String trialName = view.askForString("Enter the trial's name: ");
        String journalName = view.askForString("Enter the journal's name: ");
        String journalQuartile = view.askForString("Enter the journal's quartile: ");
        int acceptanceProbability = view.askForOption("Enter the acceptance probability: ");
        int revisionProbability = view.askForOption("Enter the revision probability: ");
        int rejectionProbability = view.askForOption("Enter the rejection probability: ");
        return trialManager.generatePaperPublication(trialName, journalName, journalQuartile, acceptanceProbability, revisionProbability, rejectionProbability);
    }


    private void pickedFaction(String optionFaction) throws IOException {

        switch (optionFaction) {
            case "I", "II" ->  chooseFormat(optionFaction);
            default -> System.out.println("Enter a correct option!");
        }
    }

    private void chooseFormat(String optionFaction) throws IOException {

        if (Objects.equals(optionFaction, "I")){
         //   trialManager.writeCSVTrial();
       //     trialManager.readCSVTrial();
       //     editionManager.readCSVEditions();
         //   editionManager.writeCSV();
            System.out.println("Loading data from CSV Files...");
        }else{
         //   trialManager.writeJSONTrial();
        //    trialManager.readJsonTrial();
            trialDAO.trialsReadJson();

            //    trialManager.readJsonEditions();
         //   editionManager.writeJSON();
            System.out.println("Loading data from JSON Files...");
        }
    }
}
