package business;

public class MasterStudies extends Trial {

    private int creditProbability;
    private String masterName;
    private int masterECTSNumber;

    public MasterStudies(String trialName, String masterName, int masterECTSNumber, int creditProbability) {
        super(trialName);
        this.masterName = masterName;
        this.masterECTSNumber = masterECTSNumber;
        this.creditProbability = creditProbability;
    }

    public String getMasterName() {
        return masterName;
    }

    public int getCreditProbability() {
        return creditProbability;
    }

    public int getMasterECTSNumber() {
        return masterECTSNumber;
    }

    public String getTrialName() {
        return super.trialName;
    }

    public String writeCSV() {
        return super.trialName + "," + masterName + "," + masterECTSNumber + "," + creditProbability + "\n";
    }
}
