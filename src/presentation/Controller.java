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
    private TrialDAO trialDAO;

    private ArrayList <PaperPublication> paperPublicationArrayList = new ArrayList();
    private ArrayList <DoctoralThesis> doctoralThesisArrayList = new ArrayList();
    private ArrayList <MasterStudies> masterStudiesArrayList = new ArrayList();
    private ArrayList <BudgetRequest> budgetRequestArrayList = new ArrayList();

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

    private int optionEditionManager(char optionEdition, String optionFaction) {

        int optionEditionTypes = 0;

        switch (optionEdition){
            case 'a':
               // view.showMenuTrialTypes();
               // optionTrialTypes = view.askForOption("Enter the trial's type: ");
               // getDataTrials(optionTrialTypes, optionFaction);
               // viewComposer.menuTrialManager();
                break;
            case 'b':
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
                listTrials(paperPublicationArrayList, masterStudiesArrayList, budgetRequestArrayList, doctoralThesisArrayList);
                break;
            case 'c':
            //    deleteTrial();
                break;
            case 'd':
                return EXIT;
        }


        return EXIT;
    }

    private void listTrials(ArrayList<PaperPublication> paperPublicationArrayList, ArrayList<MasterStudies> masterStudiesArrayList, ArrayList<BudgetRequest> budgetRequestArrayList, ArrayList<DoctoralThesis> doctoralThesisArrayList) {
        int optionListTrial;

        int sizeArrayTrials = paperPublicationArrayList.size() + masterStudiesArrayList.size() + budgetRequestArrayList.size() + doctoralThesisArrayList.size();
        int sizePPMS = paperPublicationArrayList.size() + masterStudiesArrayList.size();
        int sizePPMSBR = paperPublicationArrayList.size() + masterStudiesArrayList.size() + budgetRequestArrayList.size();

        if (sizeArrayTrials > 0) {
            do {
                view.showListMenuTrials();
                optionListTrial = view.showAllTrials(paperPublicationArrayList, masterStudiesArrayList, budgetRequestArrayList, doctoralThesisArrayList);//trialManager.getAllTrialNames(infoAllTrials));
                if (optionListTrial > sizeArrayTrials + 1 ){
                    view.showNoTrials();
                }else{
                    if (optionListTrial <= paperPublicationArrayList.size()) { //La opció es troba dins del tamany de paperPublicationArrayList
                        view.showSpecificInfoPaperPublication(paperPublicationArrayList, optionListTrial);
                    } else if (optionListTrial <= sizePPMS) { //La opció es troba dins del tamany de masterStudiesArrayList
                        optionListTrial = sizePPMS - optionListTrial;
                        view.showSpecificInfoMasterStudies(masterStudiesArrayList, optionListTrial);
                    } else if (optionListTrial <= sizePPMSBR) { //La opció es troba dins del tamany de budgetRequestArrayList
                        optionListTrial = sizePPMSBR - optionListTrial;
                        view.showSpecificInfoBudgetRequest(budgetRequestArrayList, optionListTrial);
                    } else if (optionListTrial <= sizeArrayTrials) { //La opció es troba dins del tamany de doctoralThesisArrayList
                        optionListTrial = sizeArrayTrials - optionListTrial;
                        view.showSpecificInfoDoctoralThesis (doctoralThesisArrayList, optionListTrial);
                    } else {                                         //La opció escollida no es troba en el rang de trials mostrat
                        view.showNoTrials();
                    }

                    //SpecificInfoTrial(optionListTrial, infoAllTrials, paperPublicationArrayList, masterStudiesArrayList, budgetRequestArrayList, doctoralThesisArrayList);
                }
            }while(optionListTrial <= sizeArrayTrials && optionListTrial != 5 && optionListTrial != 0); //Abans en el lloc del 5 teniem un 0
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
                createPaperPublication();
                view.trialSuccessfull();
                trialDAO.writeTrialPaperPublication(optionFaction, paperPublicationArrayList);
                view.putEnter();
            }
            case 2 -> {
                view.putEnter();
                createMasterStudies();
                view.trialSuccessfull();
                trialDAO.writeTrialMasterStudies(optionFaction, masterStudiesArrayList);
                view.putEnter();
            }
            case 3 -> {
                view.putEnter();
                createDoctoralThesis();
                view.trialSuccessfull();
                trialDAO.writeTrialDoctoralThesis(optionFaction, doctoralThesisArrayList);
                view.putEnter();
            }
            case 4 -> {
                view.putEnter();
                createBudgetRequest();
                trialDAO.writeTrialBudgetRequest(optionFaction, budgetRequestArrayList);
                view.trialSuccessfull();
                view.putEnter();
            }
        }
    }

    private void createBudgetRequest() {
        String trialName = view.askForString("Enter the trial's name: ");
        String entityName = view.askForString("Enter the entity's name: ");
        int budgetAmount = view.askForOption("Enter the budget amount: ");
        budgetRequestArrayList.add(new BudgetRequest(trialName,entityName,budgetAmount));
    }

    private void createDoctoralThesis() {
        String trialName = view.askForString("Enter the trial's name: ");
        String thesisField = view.askForString("Enter the thesis field of study: ");
        int defenseDifficulty = view.askForOption("Enter the defense difficulty: ");
        doctoralThesisArrayList.add(new DoctoralThesis(trialName, thesisField, defenseDifficulty));
    }

    private void createMasterStudies() {
        String trialName = view.askForString("Enter the trial's name: ");
        String masterName = view.askForString("Enter the master's name: ");
        int masterECTSNumber = view.askForOption("Enter the master's ECTS number: ");
        int creditProbability = view.askForOption("Enter the credit pass probability: ");
        masterStudiesArrayList.add(new MasterStudies(trialName, masterName, masterECTSNumber, creditProbability));
    }

    private void createPaperPublication() {

        String trialName = view.askForString("Enter the trial's name: ");
        String journalName = view.askForString("Enter the journal's name: ");
        String journalQuartile = view.askForString("Enter the journal's quartile: ");
        int acceptanceProbability = view.askForOption("Enter the acceptance probability: ");
        int revisionProbability = view.askForOption("Enter the revision probability: ");
        int rejectionProbability = view.askForOption("Enter the rejection probability: ");
        paperPublicationArrayList.add(new PaperPublication(trialName,journalName,journalQuartile,acceptanceProbability,revisionProbability,rejectionProbability));
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
