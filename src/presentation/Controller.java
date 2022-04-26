package presentation;


import business.Rwfiles;
import business.TrialManager;

import java.io.IOException;

public class Controller {

    private final ViewComposer viewComposer;
    private final ViewConductor viewConductor;
    private final View view;
    private final Rwfiles rwfiles;
    private final TrialManager trialManager;

    public Controller(ViewComposer viewComposer, ViewConductor viewConductor, View view, Rwfiles rwfiles, TrialManager trialManager) {
        this.viewComposer = viewComposer;
        this.viewConductor = viewConductor;
        this.view = view;
        this.rwfiles = rwfiles;
        this.trialManager = trialManager;
    }

    public void run() throws IOException {

        String optionFaction = "null";
        char optionRole;
        int optionComposer;

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
                        trialManager.menuTrialManager();
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

    private void pickedFaction(String optionFaction) throws IOException {

        switch (optionFaction) {
            case "I", "II" -> rwfiles.chooseFormat(optionFaction);
            default -> System.out.println("Enter a correct option!");
        }
    }
}
