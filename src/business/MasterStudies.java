package business;

import presentation.View;

public class MasterStudies extends Trial {

    private View view;
    private Master master;
    private int creditProbability;

    public MasterStudies(View view) {
        super();
        this.view = view;
        addData();
    }

    //Aquest seria per llegir inicialitzar a l'hora de llegir les dades per mostrar la llista per exemple
    public MasterStudies (String trialName, String name) {

        super();
    }


    public void addData () {
        Master master = new Master(askForString("Enter the master's name: "), view.askForOption("Enter the master's ECTS number: "));
        this.master = master;
        creditProbability = view.askForOption("Enter the credit pass probability: ");
    }

    public String getMasterName () {
        return master.name;
    }

    public int getMasterNumber () {
        return master.numberECTS;
    }

    public int getCreditProbability () {
        return creditProbability;
    }

    private class Master {

        public Master(String name, int numberECTS) {
            this.name = name;
            this.numberECTS = numberECTS;
        }

        String name;
        int numberECTS;

    }
}


