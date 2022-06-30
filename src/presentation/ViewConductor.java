package presentation;

import java.util.Scanner;

public class ViewConductor {

    private Scanner scanner;

    public ViewConductor() {
        scanner = new Scanner(System.in);
    }

    public void menuConductor() {
        System.out.println();
        System.out.println("Entering execution mode...");
    }

    public void showNoActualEdition() {
        System.out.println("\nNo edition is defined for the current year (2022).\n");
        System.out.println();
        System.out.println("\nShutting down...");
    }

    public void showActualEdition() {
        System.out.println("\n--- The Trials 2022 ---\n");
    }

    public void showCompetition(int state, String name) {
        System.out.println("\nTrial " + "#" + state + " - " + name + "\n");
    }

    public boolean continueExecution() {

        System.out.print("\nContinue the execution? [yes/no]: ");
        String answer = scanner.nextLine();

        if (answer.equals("no")) {
            System.out.println("\nSaving & Shutting down...");
            System.exit(0);
        }

        // sc.close();
        return answer.equals("yes") || answer.equals("y");
    }

}
