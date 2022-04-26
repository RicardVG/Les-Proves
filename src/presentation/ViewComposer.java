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

}
