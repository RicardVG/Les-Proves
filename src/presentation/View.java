package presentation;

import java.util.InputMismatchException;
import java.util.Scanner;

public class View {

    private Scanner scanner;

    public View() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Mostra per pantalla el menú inicial per després l'usuari esculli si vol fer servir fitxers CSV o JSON
     */
    public void pickFaction() {
        System.out.println("The IEEE needs to know where your allegiance lies.");
        System.out.println();
        System.out.println("\tI) People's Front of Engineering (CSV)");
        System.out.println("\tII) Engineering People's Front (JSON)");
        System.out.println();
    }

    /**
     * Aquesta funció rep un String que ha de mostrar per pantalla just abans de guardar i enviar un String a la funció d'on ha sigut cridat
     * @param s String que s'ha de mostrar per pantalla just abans de demanar input a l'usuari
     * @return retorna un String que es farà servir per guardar el nom d'un trial o la descripció d'aquest (per posar un exemple)
     */
    public String askForString(String s) {
        System.out.print(s);
        return scanner.nextLine();
    }


    /**
     * Aquesta funció rep un String que ha de mostrar per pantalla just abans de guardar i enviar un char a la funció d'on ha sigut cridat
     * @param message String que s'ha de mostrar per pantalla just abans de demanar input a l'usuari
     * @return retorna un char que es farà servir per seleccionar una opció d'un menú
     */
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

    /**
     * Aquesta funció rep un String per mostrar per pantalla just abans de guardar i enviar un int a la funció d'on ha sigut cridat
     * @param message rep un String que s'ha de mostrar per pantalla just abans de demanar input a l'usuari
     * @return retorna un int que es farà servir per seleccionar una opció d'un menú
     */
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

    /**
     * Mostra per pantalla el logo del projecte
     */
    private void printTrials() {
        System.out.println("/__   \\ |__   ___  /__   \\_ __(_) __ _| |___");
        System.out.println("  / /\\/ '_ \\ / _ \\   / /\\/ '__| |/ _` | / __|");
        System.out.println(" / /  | | | |  __/  / /  | |  | | (_| | \\__ \\");
        System.out.println(" \\/   |_| |_|\\___|  \\/   |_|  |_|\\__,_|_|___/");
    }

    /**
     * Aquest procediment mostra per pantalla el menú en el que l'usuari pot escollir si es el compositor o el conductor de les edicions
     */
    public void showGeneralMenu() {
        printTrials();
        System.out.println();
        System.out.println("Welcome to The Trials. Who are you?");
        System.out.println();
        System.out.println("\tA) The Composer");
        System.out.println("\tB) This year's Conductor");
    }

    public void putEnter() {
        System.out.println();
    }

    /**
     * Mostra per pantalla un missatge per acomiadar-se de l'usuari just abans de finalitzar l'execució del programa
     */
    public void shutdown() {
        System.out.println("\tShutting down...");
    }

}
