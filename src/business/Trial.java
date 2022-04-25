package business;

import presentation.View;

public class Trial {
    String trialName;
    public Trial() {
        addTrialName();
    }

    public Trial (String trialName) {
        this.trialName = trialName;
    }

    public void addTrialName () {
        trialName = askForString("Enter the trial's name: ");
    }

    public String askForString (String s) {
        System.out.print(s);
        return scanner.next();
    }
}
