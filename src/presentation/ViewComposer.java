package presentation;

import business.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ViewComposer {

    private final Scanner scanner;
    private View view;

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

    public void showMenuTrialTypes() {
        System.out.println("");
        System.out.println("\t--- Trial Types ---");
        System.out.println("");
        System.out.println("\t1) Paper publication");
        System.out.println("\t2) Master studies");
        System.out.println("\t3) Doctoral thesis defense");
        System.out.println("\t4) Budget request");
        System.out.println("");

    }

    public void trialSuccessfull() {
        System.out.println("\nThe trial was created successfully!");
    }

    public void showNoTrials() {
        System.out.println("\nNo trials available!");
    }

    public void showListMenuTrials() {
        System.out.println("\nHere are the current trials, do you want to see more details or go back?\n");
    }

    public int showAllTrials(ArrayList<Trial> arrayListTrials) {
        int x = 1;
        for (int i = 0; i < arrayListTrials.size(); i++) {
            System.out.println(x + ")" + arrayListTrials.get(i).getName());
            x++;
        }
        System.out.println(x + ") Back");

        return askForOption("Enter an option: ");

    }

    public int askForOption(String message) {
        while (true) {
            try {
                System.out.print(message);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("That's not a valid input, try again!");
            } finally {
                scanner.nextLine();
            }
        }
    }

    public void showSpecificInfoPaperPublication(ArrayList<PaperPublication> paperPublicationArrayList,
            int optionListTrial) {
        System.out.println();
        System.out.println(
                "Trial: " + paperPublicationArrayList.get(optionListTrial - 1).getTrialName() + " (Paper Publication)");
        System.out.println("Journal: " + paperPublicationArrayList.get(optionListTrial - 1).getJournalName() + " ("
                + paperPublicationArrayList.get(optionListTrial - 1).getJournalQuartile() + ")");
        System.out.println("Chances: " + paperPublicationArrayList.get(optionListTrial - 1).getAcceptanceProbability()
                + "% acceptance, " + paperPublicationArrayList.get(optionListTrial - 1).getRevisionProbability()
                + "% revision, " + paperPublicationArrayList.get(optionListTrial - 1).getRejectionProbability()
                + "% rejection");
    }

    public void showSpecificInfoMasterStudies(ArrayList<MasterStudies> masterStudiesArrayList, int optionListTrial) {
        System.out.println();
        System.out.println("Trial: " + masterStudiesArrayList.get(optionListTrial - 1).getName() + " (Master studies)");
        System.out.println("Master: " + masterStudiesArrayList.get(optionListTrial - 1).getMasterName());
        System.out.println("ECTS: " + masterStudiesArrayList.get(optionListTrial - 1).getMasterECTSNumber() + " with a "
                + masterStudiesArrayList.get(optionListTrial - 1).getCreditProbability() + "% chance to pass each one");
    }

    public void showSpecificInfoBudgetRequest(ArrayList<BudgetRequest> budgetRequestArrayList, int optionListTrial) {
        System.out.println();
        System.out.println("Trial: " + budgetRequestArrayList.get(optionListTrial - 1).getName() + " (Budget request)");
        System.out.println("Entity: " + budgetRequestArrayList.get(optionListTrial - 1).getEntityName());
        System.out.println("Budget: " + budgetRequestArrayList.get(optionListTrial - 1).getBudgetAmount() + "€");
    }

    public void showSpecificInfoDoctoralThesis(ArrayList<DoctoralThesis> doctoralThesisArrayList, int optionListTrial) {
        System.out.println();
        System.out.println(
                "Trial: " + doctoralThesisArrayList.get(optionListTrial - 1).getName() + " (Doctoral thesis defense)");
        System.out.println("Field: " + doctoralThesisArrayList.get(optionListTrial - 1).getThesisField());
        System.out.println("Difficulty: " + doctoralThesisArrayList.get(optionListTrial - 1).getDefenseDifficulty());
    }

    public void showListMenuDeleteTrials() {
        System.out.println("\nWhich trial do you want to delete?\n");
    }

    public void showMenuTrials(ArrayList<Trial> arrayListTrials) {
        System.out.println("\n\t--- Trials ---");
        for (int i = 0; i < arrayListTrials.size(); i++) {
            System.out.println("\t" + (i + 1) + ") " + arrayListTrials.get(i).getName());
        }
    }

    public int showEditionsMenu(ArrayList<Edition> editionArrayList) {
        int i;
        for (i = 0; i < editionArrayList.size(); i++) {
            System.out.println("\t" + (i + 1) + ") " + "The Trials " + editionArrayList.get(i).getYear());
        }

        System.out.println("\n\t" + (i + 1) + ") " + "Back\n");

        return askForOption("Enter an option: ");
    }

    public void incorrectOptionTrialDelete() {
        System.out.println("\nThe input isn't correct. Please enter a valid number of trial!\n");
    }

    public void showNoEditions() {
        System.out.println("\nThere are no editions available!\n");
    }

    public void showInputIncorrectTrials() {
        System.out.println("\nThe input isn't correct. Please enter a valid number of trial!\n");
    }

    public void showInputIncorrectEditions() {
        System.out.println("\nThe input isn't correct. Please enter a valid number of edition!\n");
    }

    public void showSpecificInformationEdition(int year, int numPlayers) {

        System.out.println("\n");
        System.out.println("Year: " + year);
        System.out.println("Players: " + numPlayers);
        System.out.println("Trials:\n");
    }

    public void showSpecificInformationEditionExtended(String object, String type, int x) {

        System.out.println("\t" + (x + 1) + "- " + object + " ( " + type + " )");

    }

    public void showTrialDeleteError() {
        System.out.println(
                "\nThe trial already exists in a edition. Please, first remove the edition that contains the trial and then remove the trial\n");
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
        System.out.println("\td) Delete Edition");
        System.out.println("");
        System.out.println("\te) Back");
        optionEdition = view.askForChar("Enter an option: ");

        while (optionEdition != 'a' && optionEdition != 'b' && optionEdition != 'c' && optionEdition != 'd'
                && optionEdition != 'e') {
            optionEdition = view.askForChar("That's not a valid input , you have to enter a,b,c,d or e: ");
        }

        return optionEdition;
    }

    public void showEditionsDuplicateSuccessfully() {
        System.out.println("\nThe edition was cloned sucessfully!");
    }

    public void showEditionsDeleteSuccessfully() {
        System.out.println("\nThe edition was sucessfully deleted.");
    }
}
