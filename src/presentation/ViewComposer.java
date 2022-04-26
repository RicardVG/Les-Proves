package presentation;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ViewComposer {

    private final Scanner scanner;
    private View view;

    public ViewComposer() {
        this.scanner = new Scanner(System.in);
    }

    public int menuComposer() {
        int optionComposer;
        System.out.println();
        System.out.println("Entering management mode...");
        showOptions();
        optionComposer = view.askForOption("Enter an option: ");
        return optionComposer;
    }

    public void showOptions() {
        System.out.println("\n\t1) Manage Trials");
        System.out.println("\t2) Manage Editions");
        System.out.println("\n\t3) Exit");
    }

    public char menuTrialManager() {
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
        return optionTrial;
    }

}
