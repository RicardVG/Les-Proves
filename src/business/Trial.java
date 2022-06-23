package business;

import java.util.Scanner;

public class Trial {

   // public Scanner scanner;
    protected String trialName;
/*
    public Trial() {
        //scanner = new Scanner(System.in);
        addTrialName();
    }
*/

    public Trial (String trialName) {
        this.trialName = trialName;
    }

/*
    public void addTrialName () {
        trialName = askForString("Enter the trial's name: ");
    }

    public String askForString (String s) {
        System.out.print(s);
        return scanner.next();
    }
*/

    public String getName() {
        return trialName;
    }
}
