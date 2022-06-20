package business;

public class MasterStudies extends Trial {
    
    private int creditProbability;
    private String masterName;
    private int masterECTSNumber;
    
    public MasterStudies(String trialName,String masterName, int masterECTSNumber, int creditProbability) {
        super(trialName);
        this.masterName = masterName;
        this.masterECTSNumber = masterECTSNumber;
        this.creditProbability = creditProbability;
    }
    
}


