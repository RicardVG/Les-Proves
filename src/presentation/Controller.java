package presentation;


import business.*;

import java.io.IOException;
import java.util.Objects;

public class Controller {

    private static final int EXIT = 0;
    private final ViewComposer viewComposer;
    private final ViewConductor viewConductor;
    private final View view;
    private final TrialManager trialManager;
    private final EditionManager editionManager;

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
                        optionTrialManager(optionTrial);
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


    public int optionTrialManager(char optionTrial) {

        int optionTrialTypes = 0;

        switch (optionTrial){
            case 'a':
                view.showMenuTrialTypes();
                optionTrialTypes = view.askForOption("Enter the trial's type: ");

                trialManager.createSpecificTrial(optionTrialTypes);

                viewComposer.menuTrialManager();

                break;
                case 'b':
                    break;
                case 'c':
                    break;
                case 'd':
                    return EXIT;
        }
        trialManager.updateJsonTrial(optionTrialTypes);
        trialManager.updateCSVTrial(optionTrialTypes);
        trialManager.updateJsonEdition(optionTrialTypes);
        trialManager.updateCSVEdition(optionTrialTypes);
        return EXIT;
    }


    private void pickedFaction(String optionFaction) throws IOException {

        switch (optionFaction) {
            case "I", "II" -> chooseFormat(optionFaction);
            default -> System.out.println("Enter a correct option!");
        }
    }

    private void chooseFormat(String optionFaction) throws IOException {

        if (Objects.equals(optionFaction, "I")){
            trialManager.writeCSVTrial();
            editionManager.writeCSV();
            System.out.println("Loading data from CSV Files...");
        }else{
            trialManager.writeJSONTrial();
            editionManager.writeJSON();
            System.out.println("Loading data from JSON Files...");
        }
    }
}
