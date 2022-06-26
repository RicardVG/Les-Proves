package business;

import java.util.ArrayList;

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

    public MasterStudies(ArrayList<String> stringArrayList) {
        super(stringArrayList.get(0));
        this.masterName = stringArrayList.get(1);
        this.masterECTSNumber= Integer.parseInt(stringArrayList.get(2));
        this.creditProbability = Integer.parseInt(stringArrayList.get(3));
    }

    public String getMasterName () {
        return masterName;
    }

    public int getCreditProbability () {
        return creditProbability;
    }

    public int getMasterECTSNumber () {
        return masterECTSNumber;
    }
    
}


