package presentation;


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
        return scanner.next();
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
                System.out.println();
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
        System.out.println("The trial was created successfully!");
    }

    public void showNoTrials() {
        System.out.println("\nNo trials available!");
    }

    public void showListMenuTrials() {
        System.out.println("\nHere are the current trials, do you want to see more details or go back?\n");
    }

    public int showAllTrials(ArrayList<String> allTrialNames) {
        int i;

        for(i = 0; i < allTrialNames.size(); i++){
            System.out.printf("\t%d) ", i+1);
            System.out.println(allTrialNames.get(i));
        }
        System.out.printf("\n\n\t%d) Back\n\n", i+1);

        return askForOption("Enter an option: ");
    }
}
