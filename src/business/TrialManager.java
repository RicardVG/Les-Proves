package business;

import persistance.TrialDAO;
import java.io.IOException;
import java.util.ArrayList;

public class TrialManager {

    private final TrialDAO trialDAO;
    private PaperPublication paperPublication;
    private MasterStudies masterStudies;
    private DoctoralThesis doctoralThesis;
    private BudgetRequest budgetRequest;

    public TrialManager(TrialDAO trialDAO) {
        this.trialDAO = trialDAO;
    }

    public void writeJSONTrial() throws IOException {
        trialDAO.trialsWriteJson();
    }

    public void writeCSVTrial() throws IOException {
        trialDAO.trialsWriteCSV(paperPublication,masterStudies,doctoralThesis,budgetRequest);
    }

    public void createSpecificTrial(int optionTrialTypes) {
        switch (optionTrialTypes) {

            case 1:
                System.out.println();
                paperPublication = new PaperPublication();
                trialDAO.getArraylistTrials().add(paperPublication);
                System.out.println("");
                System.out.println("The trial was created successfully!");
                break;
            case 2:
                System.out.println();
                masterStudies = new MasterStudies();
                trialDAO.getArraylistTrials().add(masterStudies);
                System.out.println();
                System.out.println("The trial was created successfully!");
                break;
            case 3:
                System.out.println("");
                doctoralThesis = new DoctoralThesis();
                trialDAO.getArraylistTrials().add(doctoralThesis);
                System.out.println("");
                System.out.println("The trial was created successfully!");
                break;
            case 4:
                System.out.println("");
                budgetRequest = new BudgetRequest();
                trialDAO.getArraylistTrials().add(budgetRequest);
                System.out.println("");
                System.out.println("The trial was created successfully!");
                break;
        }
    }

    public void updateJsonTrial(int optionTrialTypes) {
        
    }

    public void updateCSVTrial(int optionTrialTypes) {
    }

    public void updateJsonEdition(int optionTrialTypes) {
    }

    public void updateCSVEdition(int optionTrialTypes) {
    }

}
