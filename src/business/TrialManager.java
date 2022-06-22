package business;

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

/*
    public ArrayList<String> getAllTrialNames (ArrayList<Trial> infoAllTrials) {
        ArrayList<String> nameTrial = new ArrayList<>();

        for(int i = 0; i < infoAllTrials.size(); i++) {
            nameTrial.add(infoAllTrials.get(i).getName());
        }
        return nameTrial;
    }
    
 */
}
