package business;

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

}
