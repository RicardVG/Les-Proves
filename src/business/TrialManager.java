package business;

import presentation.View;

import java.util.ArrayList;

public class TrialManager {

    private View view;
    private final int EXIT = 0;

    public TrialManager() {
    }

    public int menuTrialManager() {
        char optionTrial;
        System.out.println("");
        System.out.println("\ta) Create Trial");
        System.out.println("\tb) List Trials");
        System.out.println("\tc) Delete Trial");
        System.out.println("");
        System.out.println("\td) Back");
        optionTrial = view.askForChar("Enter an option: ");

        while (optionTrial != 'a' && optionTrial != 'b' && optionTrial != 'c' && optionTrial != 'd') {
            optionTrial = view.askForChar("That's not a valid input , you have to enter a,b,c or d: ");
        }
        return optionTrialManager(optionTrial);
    }

    private int optionTrialManager(char optionTrial) {

        switch (optionTrial){
            case 'a':
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
}
