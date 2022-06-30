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

    /**
     * Aquesta funció mostra el menu del compositor
     * @return retorna la opció escollida per l'usuari en referencia al menú mostrat
     */
    public int menuComposer() {
        int optionComposer;
        System.out.println();
        System.out.println("Entering management mode...");
        showOptions();
        optionComposer = view.askForOption("Enter an option: ");

        return optionComposer;
    }

    /**
     * Aquest procediment mostra el menú general
     */
    public void showOptions() {
        System.out.println("\n\t1) Manage Trials");
        System.out.println("\t2) Manage Editions");
        System.out.println("\n\t3) Exit\n");
    }

    /**
     * Aquest procediment mostra el menú de les trials
     */
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

    /**
     * Aquest procediment mostra per pantalla un missatge de confirmació dient que la trial s'ha creat amb éxit
     */
    public void trialSuccessfull() {
        System.out.println("\nThe trial was created successfully!");
    }

    /**
     * Aquest procediment mostra per pantalla que no hi ha cap trial
     */
    public void showNoTrials() {
        System.out.println("\nNo trials available!");
    }

    /**
     * Aquest procediment pregunta a l'usuari si vol ecollir una trial per obtenir més detalls o prefereix tornar enrere
     */
    public void showListMenuTrials() {
        System.out.println("\nHere are the current trials, do you want to see more details or go back?\n");
    }

    /**
     * Aquesta funció mostra per pantalla totes les trials creades fins a l'actualitat
     * @param arrayListTrials arrayList que s'ha passat per poder mostrar el seu contingut per pantalla
     * @return retorna un int resultat de demanar a l'usuari que esculli una opció
     */
    public int showAllTrials(ArrayList<Trial> arrayListTrials) {
        int x = 1;
        for (int i = 0; i < arrayListTrials.size(); i++) {
            System.out.println(x + ")" + arrayListTrials.get(i).getName());
            x++;
        }
        System.out.println(x + ") Back");

        return askForOption("Enter an option: ");

    }

    /**
     * Aquesta funció mostra per pantalla un missatge que se li ha passat i retorna un int
     * @param message
     * @return retorna un int
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
     * Aquest procediment mostra per pantalla tota la informació detallada d'un PaperPublication que s'ha demanat
     * @param paperPublicationArrayList
     * @param optionListTrial
     */
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

    /**
     * Aquest procediment mostra per pantalla tota la informació detallada d'un MasterStudies que s'ha demanat
     * @param masterStudiesArrayList
     * @param optionListTrial
     */
    public void showSpecificInfoMasterStudies(ArrayList<MasterStudies> masterStudiesArrayList, int optionListTrial) {
        System.out.println();
        System.out.println("Trial: " + masterStudiesArrayList.get(optionListTrial - 1).getName() + " (Master studies)");
        System.out.println("Master: " + masterStudiesArrayList.get(optionListTrial - 1).getMasterName());
        System.out.println("ECTS: " + masterStudiesArrayList.get(optionListTrial - 1).getMasterECTSNumber() + " with a "
                + masterStudiesArrayList.get(optionListTrial - 1).getCreditProbability() + "% chance to pass each one");
    }

    /**
     * Aquest procediment mostra per pantalla tota la informació detallada d'un BudgetRequest que s'ha demanat
     * @param budgetRequestArrayList
     * @param optionListTrial
     */
    public void showSpecificInfoBudgetRequest(ArrayList<BudgetRequest> budgetRequestArrayList, int optionListTrial) {
        System.out.println();
        System.out.println("Trial: " + budgetRequestArrayList.get(optionListTrial - 1).getName() + " (Budget request)");
        System.out.println("Entity: " + budgetRequestArrayList.get(optionListTrial - 1).getEntityName());
        System.out.println("Budget: " + budgetRequestArrayList.get(optionListTrial - 1).getBudgetAmount() + "€");
    }

    /**
     * Aquest procediment mostra per pantalla tota la informació detallada d'un DoctoralThesis que s'ha demanat
     * @param doctoralThesisArrayList
     * @param optionListTrial
     */
    public void showSpecificInfoDoctoralThesis(ArrayList<DoctoralThesis> doctoralThesisArrayList, int optionListTrial) {
        System.out.println();
        System.out.println(
                "Trial: " + doctoralThesisArrayList.get(optionListTrial - 1).getName() + " (Doctoral thesis defense)");
        System.out.println("Field: " + doctoralThesisArrayList.get(optionListTrial - 1).getThesisField());
        System.out.println("Difficulty: " + doctoralThesisArrayList.get(optionListTrial - 1).getDefenseDifficulty());
    }

    /**
     * Aquest procediment pregunta a l'usuari quina es la trial que desitja eliminar
     */
    public void showListMenuDeleteTrials() {
        System.out.println("\nWhich trial do you want to delete?\n");
    }

    /**
     * Aquest procediment mostra per pantalla totes les trials en el menú de Edition Manager
     * @param arrayListTrials
     */
    public void showMenuTrials(ArrayList<Trial> arrayListTrials) {
        System.out.println("\n\t--- Trials ---");
        for (int i = 0; i < arrayListTrials.size(); i++) {
            System.out.println("\t" + (i + 1) + ") " + arrayListTrials.get(i).getName());
        }
    }

    /**
     * Aquesta funció mostra per pantalla totes les edicions que hi han
     * @param editionArrayList
     * @return retorna la opció a seguir despres del menú
     */
    public int showEditionsMenu(ArrayList<Edition> editionArrayList) {
        int i;
        for (i = 0; i < editionArrayList.size(); i++) {
            System.out.println("\t" + (i + 1) + ") " + "The Trials " + editionArrayList.get(i).getYear());
        }

        System.out.println("\n\t" + (i + 1) + ") " + "Back\n");

        return askForOption("Enter an option: ");
    }

    /**
     * Aquest procediment mostra per pantalla que l'opció introduida no és correcte
     */
    public void incorrectOptionTrialDelete() {
        System.out.println("\nThe input isn't correct. Please enter a valid number of trial!\n");
    }

    /**
     * Aquest procediment mostra per pantalla que no hi ha cap edició disponible encara
     */
    public void showNoEditions() {
        System.out.println("\nThere are no editions available!\n");
    }

    /**
     * Aquest procediment mostra per pantalla que el nombre de trials no és correcte
     */
    public void showInputIncorrectTrials() {
        System.out.println("\nThe input isn't correct. Please enter a valid number of trial!\n");
    }

    /**
     * Aquest procediment mostra per pantalla que l'opció introduida no és correcte
     */
    public void showInputIncorrectEditions() {
        System.out.println("\nThe input isn't correct. Please enter a valid number of edition!\n");
    }

    /**
     * Mostra per pantalla la informació específica d'una edició (a excepció de les trials, aixó en el procediment seguent)
     * @param year
     * @param numPlayers
     */
    public void showSpecificInformationEdition(int year, int numPlayers) {

        System.out.println("\n");
        System.out.println("Year: " + year);
        System.out.println("Players: " + numPlayers);
        System.out.println("Trials:\n");
    }

    /**
     * Mostra per pantalla totes les trials de l'edició
     * @param object
     * @param type
     * @param x
     */
    public void showSpecificInformationEditionExtended(String object, String type, int x) {

        System.out.println("\t" + (x + 1) + "- " + object + " ( " + type + " )");

    }

    /**
     * Mostra per pantalla que hi ha una trial que s'intenta esborrar, forma part d'una edició
     */
    public void showTrialDeleteError() {
        System.out.println(
                "\nThe trial already exists in a edition. Please, first remove the edition that contains the trial and then remove the trial\n");
    }

    /**
     * Aquesta funció mostra per pantalla el menú de trials
     * @return
     */
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

    /**
     * Aquesta funció mostra per pantalla el menú de les edicions
     * @return
     */
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

    /**
     * Aquest procediment mostra per pantalla que la edició s'ha pogut clonar
     */
    public void showEditionsDuplicateSuccessfully() {
        System.out.println("\nThe edition was cloned sucessfully!");
    }

    /**
     * Aquest procediment mostra per pantalla que la edició s'ha pogut esborrar
     */
    public void showEditionsDeleteSuccessfully() {
        System.out.println("\nThe edition was sucessfully deleted.");
    }
}
