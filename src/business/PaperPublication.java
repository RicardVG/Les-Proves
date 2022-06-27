package business;

import java.util.ArrayList;
import java.util.Arrays;

public class PaperPublication extends Trial{

    private String journalName;
    private String journalQuartile;
    private int acceptanceProbability;
    private int revisionProbability;
    private int rejectionProbability;


    public PaperPublication(String trialName,String journalName, String journalQuartile, int acceptanceProbability, int revisionProbability, int rejectionProbability) {
        super(trialName);
        this.journalName = journalName;
        this.journalQuartile = journalQuartile;
        this.acceptanceProbability = acceptanceProbability;
        this.revisionProbability = revisionProbability;
        this.rejectionProbability = rejectionProbability;

    }


    public PaperPublication(ArrayList<String> stringArrayList) {
        super(stringArrayList.get(0));
        this.journalName = stringArrayList.get(1);
        this.journalQuartile = stringArrayList.get(2);
        this.acceptanceProbability = Integer.parseInt(stringArrayList.get(3));
        this.revisionProbability = Integer.parseInt(stringArrayList.get(4));
        this.rejectionProbability = Integer.parseInt(stringArrayList.get(5));
    }

    public String writeCSV() {
        return super.trialName + "," + journalName + "," + journalQuartile + "," + acceptanceProbability + "," + revisionProbability + "," + rejectionProbability + "\n";
    }





    public String getTrialName () {
        return super.trialName;
    }
    
    public String getJournalName() {
        return this.journalName;
    }
    
    public String getJournalQuartile() {
        return this.journalQuartile;
    }

    public int getAcceptanceProbability() {
        return acceptanceProbability;
    }

    public int getRevisionProbability() {
        return revisionProbability;
    }

    public int getRejectionProbability() {
        return rejectionProbability;
    }
}
