package business;

import java.io.IOException;
import java.util.ArrayList;

public class TrialManager {
    
    private ArrayList<Trial> arrayListPaperPublication = new ArrayList<>();
    private ArrayList<Trial> arrayListMasterStudies = new ArrayList<>();
    private ArrayList<Trial> arrayListBudgetRequest = new ArrayList<>();
    private ArrayList<Trial> arrayListDoctoralThesis = new ArrayList<>();
    private ArrayList<Trial> arraylistTrials = new ArrayList<>();

    public TrialManager() {

    }
    
    public void writeJsonEdition(int optionTrialTypes) {
    }

    public void writeCSVEdition(int optionTrialTypes) {
    }

    /*public void readJsonEditions() {
        trialDAO.editionsReadJson();
    }


     */
    public PaperPublication generatePaperPublication(String trialName,String journalName, String journalQuartile, int acceptanceProbability, int revisionProbability, int rejectionProbability) {
        return new PaperPublication(trialName, journalName,journalQuartile,acceptanceProbability,revisionProbability,rejectionProbability);
    }

    public MasterStudies createMasterStudies(String trialName, String masterName, int masterECTSNumber, int creditProbability) {
        return new MasterStudies (trialName, masterName, masterECTSNumber, creditProbability);
    }

    public DoctoralThesis createDoctoralThesis(String trialName, String thesisField, int defenseDifficulty) {
        return new DoctoralThesis (trialName, thesisField, defenseDifficulty);
    }

    public BudgetRequest createBudgetRequest(String trialName, String entityName, int budgetAmount) {
        return new BudgetRequest(trialName,entityName,budgetAmount);
    }
}
