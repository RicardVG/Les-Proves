package presentation;


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

}
