package business;

import presentation.View;

public class PaperPublication extends Trial{

    private Journal journal;
    private View view;
    private int acceptanceProbability;
    private int revisionProbability;
    private int rejectionProbability;

    public PaperPublication (View view) {
        super();
        this.view = view;
        addData();
    }


    //ARRAYLIST DE STRING IGUALAR A CADA VARIABLE DE LES CLASSES PAPERPUBLICATION,MASTER STUDIES, ETC...
    //COM FERHO JA QUE ACTUALMENT HO IGUALEM A UNA FUNCIO QUE DEMANA LA INFO

    public void addData() {

        Journal journal = new Journal(askForString("Enter the journal's name: "),
                askForString("Enter the journal's quartile: "));
        this.journal = journal;
        acceptanceProbability = view.askForOption("Enter the acceptance probability: ");
        revisionProbability = view.askForOption("Enter the revision probability: ");
        rejectionProbability = view.askForOption("Enter the rejection probability: ");
    }

    public String getJournalName() {
        return journal.journalName;
    }

    public String getJournalQuartile() {
        return journal.journalQuartile;
    }

    public int getAcceptanceProbability() {
        return acceptanceProbability;
    }

    public int getRejectionProbability() {
        return rejectionProbability;
    }

    public int getRevisionProbability() {
        return revisionProbability;
    }

    private class Journal {

        public Journal(String journalName, String journalQuartile) {
            this.journalName = journalName;
            this.journalQuartile = journalQuartile;
        }

        String journalName;
        String journalQuartile;
    }
}
