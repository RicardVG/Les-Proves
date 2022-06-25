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
