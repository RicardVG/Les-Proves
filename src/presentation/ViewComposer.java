package presentation;

import business.BudgetRequest;
import business.DoctoralThesis;
import business.MasterStudies;
import business.PaperPublication;

import java.util.Scanner;

public class ViewComposer {

    private final Scanner scanner;
    private View view;
    private PaperPublication paperPublication;
    private MasterStudies masterStudies;
    private DoctoralThesis doctoralThesis;
    private BudgetRequest budgetRequest;

    public ViewComposer(View view) {
        this.view = view;
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
        System.out.println("\n\t3) Exit\n");
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

    public char menuEditionsManager() {
        char optionEdition;
        System.out.println("");
        System.out.println("\ta) Create Edition");
        System.out.println("\tb) List Editions");
        System.out.println("\tc) Duplicate Edition");
        System.out.println("\tc) Delete Edition");
        System.out.println("");
        System.out.println("\te) Back");
        optionEdition = view.askForChar("Enter an option: ");

        while (optionEdition != 'a' && optionEdition != 'b' && optionEdition != 'c' && optionEdition != 'd' && optionEdition != 'e') {
            optionEdition = view.askForChar("That's not a valid input , you have to enter a,b,c,d or e: ");
        }
        
        return optionEdition;
    }

    public void showEditionsDuplicateSuccessfully() {
        System.out.println("\nThe edition was cloned sucessfully!");
    }
}
