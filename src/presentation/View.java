package presentation;


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
}
