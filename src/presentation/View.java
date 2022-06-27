package presentation;


import business.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class View {

    private Scanner scanner;

    public View(){
        this.scanner = new Scanner(System.in);
    }

    public void pickFaction() {
        System.out.println("The IEEE needs to know where your allegiance lies.");
        System.out.println();
        System.out.println("\tI) People's Front of Engineering (CSV)");
        System.out.println("\tII) Engineering People's Front (JSON)");
        System.out.println();
    }

    public String askForString(String s) {
        System.out.print(s);
        return scanner.nextLine();
    }


    public char askForChar(String message) {
        while (true) {
            try {
                System.out.println();
                System.out.print(message);
                return scanner.next(".").charAt(0);
            } catch (InputMismatchException e) {
                System.out.println("That's not a valid input, try again!");
            } finally {
                scanner.nextLine();
            }
        }
    }

    public int askForOption (String message) {
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

    private void printTrials() {
        System.out.println("/__   \\ |__   ___  /__   \\_ __(_) __ _| |___");
        System.out.println("  / /\\/ '_ \\ / _ \\   / /\\/ '__| |/ _` | / __|");
        System.out.println(" / /  | | | |  __/  / /  | |  | | (_| | \\__ \\");
        System.out.println(" \\/   |_| |_|\\___|  \\/   |_|  |_|\\__,_|_|___/");
    }

    public void showGeneralMenu() {
        printTrials();
        System.out.println();
        System.out.println("Welcome to The Trials. Who are you?");
        System.out.println();
        System.out.println("\tA) The Composer");
        System.out.println("\tB) This year's Conductor");
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

    public void putEnter() {
        System.out.println();
    }
    
    public void trialSuccessfull(){
        System.out.println("\nThe trial was created successfully!");
    }

    public void showNoTrials() {
        System.out.println("\nNo trials available!");
    }

    public void showListMenuTrials() {
        System.out.println("\nHere are the current trials, do you want to see more details or go back?\n");
    }

    public int showAllTrials (ArrayList<Trial> arrayListTrials) {
        int x = 1;
        for (int i = 0; i < arrayListTrials.size(); i++) {
            System.out.println (x + ")" + arrayListTrials.get(i).getName());
            x++;
        }
        System.out.println (x + ") Back");

        return askForOption ("Enter an option: ");

    }

    public void showSpecificInfoPaperPublication(ArrayList<PaperPublication> paperPublicationArrayList, int optionListTrial) {
        System.out.println();
        System.out.println("Trial: " + paperPublicationArrayList.get(optionListTrial -1).getTrialName() + " (Paper Publication)");
        System.out.println("Journal: " + paperPublicationArrayList.get (optionListTrial -1).getJournalName() + " (" + paperPublicationArrayList.get(optionListTrial - 1).getJournalQuartile() + ")");
        System.out.println("Chances: " + paperPublicationArrayList.get (optionListTrial -1).getAcceptanceProbability() + "% acceptance, " + paperPublicationArrayList.get (optionListTrial -1).getRevisionProbability() + "% revision, " + paperPublicationArrayList.get (optionListTrial -1).getRejectionProbability() + "% rejection");
    }

    public void showSpecificInfoMasterStudies(ArrayList<MasterStudies> masterStudiesArrayList, int optionListTrial) {
        System.out.println();
        System.out.println("Trial: " + masterStudiesArrayList.get(optionListTrial -1).getName() + " (Master studies)");
        System.out.println("Master: " + masterStudiesArrayList.get (optionListTrial -1).getMasterName());
        System.out.println("ECTS: " + masterStudiesArrayList.get (optionListTrial -1).getMasterECTSNumber() + " with a " + masterStudiesArrayList.get (optionListTrial -1).getCreditProbability() + "% chance to pass each one");
    }

    public void showSpecificInfoBudgetRequest(ArrayList<BudgetRequest> budgetRequestArrayList, int optionListTrial) {
        System.out.println();
        System.out.println("Trial: " + budgetRequestArrayList.get(optionListTrial -1 ).getName() + " (Budget request)");
        System.out.println("Entity: " + budgetRequestArrayList.get (optionListTrial -1).getEntityName());
        System.out.println("Budget: " + budgetRequestArrayList.get(optionListTrial -1).getBudgetAmount() + "€");
    }

    public void showSpecificInfoDoctoralThesis(ArrayList<DoctoralThesis> doctoralThesisArrayList, int optionListTrial) {
        System.out.println();
        System.out.println("Trial: " + doctoralThesisArrayList.get(optionListTrial -1).getName() + " (Doctoral thesis defense)");
        System.out.println("Field: " + doctoralThesisArrayList.get (optionListTrial -1).getThesisField());
        System.out.println("Difficulty: " + doctoralThesisArrayList.get(optionListTrial -1).getDefenseDifficulty());
    }

    public void showListMenuDeleteTrials() {
        System.out.println("\nWhich trial do you want to delete?\n");
    }

    public void showMenuTrials(ArrayList<Trial> arrayListTrials) {
        System.out.println("\n\t--- Trials ---");
        for (int i = 0; i < arrayListTrials.size(); i++){
            System.out.println("\t" + (i+1) + ") " + arrayListTrials.get(i).getName());
        }
    }


    public int showEditionsMenu(ArrayList<Edition> editionArrayList) {
        int i;
        System.out.println("\nHere are the current editions, do you want to see more details or go back?\n");
        for (i = 0; i < editionArrayList.size(); i++){
            System.out.println("\t" + (i+1) + ") " + "The Trials " + editionArrayList.get(i).getYear());
        }

        System.out.println("\t" + (i+1) + ") " + "Back\n");

        return askForOption ("Enter an option: ");
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

    public void showSpecificInformationEdition(ArrayList<Edition> editionArrayList, int optionEditionList) {

            System.out.println("\n");
            System.out.println("Year: " + editionArrayList.get(optionEditionList-1).getYear());
            System.out.println("Players: " + editionArrayList.get(optionEditionList-1).getNumPlayers());
            System.out.println("Trials:\n");
            for(int x = 0 ; x < editionArrayList.get(optionEditionList-1).getStringArrayList().size() ; x ++){
                System.out.println("\t" + (x+1) + "- " + editionArrayList.get(optionEditionList-1).getStringArrayList().get(x) + " (X)");
            }

    }



/*
    public int showAllTrials(ArrayList<String> allTrialNames) {
        int i;

        for(i = 0; i < allTrialNames.size(); i++){
            System.out.printf("\t%d) ", i+1);
            System.out.println(allTrialNames.get(i));
        }
        System.out.printf("\n\n\t%d) Back\n\n", i+1);

        return askForOption("Enter an option: ");
    }
*/

    /*
    public void showSpecificInfoTrial(Trial trial) {
        System.out.println(t);
    }

     */
}
