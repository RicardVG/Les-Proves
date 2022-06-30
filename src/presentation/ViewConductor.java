package presentation;

import java.util.Scanner;

public class ViewConductor {

    private Scanner scanner;

    public ViewConductor() {
        scanner = new Scanner(System.in);
    }

    /**
     * Mostra per pantalla que s'accedeix al mode de conductor
     */
    public void menuConductor() {
        System.out.println();
        System.out.println("Entering execution mode...");
    }

    /**
     * Mostra per pantalla que en aquest moment no hi ha cap edició guardada i atura l'execució del programa
     */
    public void showNoActualEdition() {
        System.out.println("\nNo edition is defined for the current year (2022).\n");
        System.out.println();
        System.out.println("\nShutting down...");
    }

    /**
     * Mostra per pantalla l'edició actual
     */
    public void showActualEdition() {
        System.out.println("\n--- The Trials 2022 ---\n");
    }

    /**
     * Mostra per pantalla per quin trial es troba l'execució i el nom d'aquest
     * @param state el número actual de trial en l'execució
     * @param name el nom del trial actual
     */
    public void showCompetition(int state, String name) {
        System.out.println("\nTrial " + "#" + state + " - " + name + "\n");
    }

    /**
     * Pregunta per pantalla a l'usuari si vol continuar amb l'execució després de cada prova, en cas negatiu, atura
     *  l'execució del programa
     * @return retorna la resposta de l'usuari
     */
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
