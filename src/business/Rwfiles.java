package business;

import persistance.TrialDAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Rwfiles {

    private TrialDAO trialDAO;
    private final String pathPaperPublicationJSON="jsonFiles/paperPublication.json";
    private final String pathMasterStudiesJSON="jsonFiles/masterStudies.json";
    private final String pathBudgetRequestJSON="jsonFiles/budgetRequest.json";
    private final String pathDoctoralThesisJSON="jsonFiles/doctoralThesis.json";
    private ArrayList<Trial> arrayListPaperPublication;
    private ArrayList<Trial> arrayListMasterStudies;
    private ArrayList<Trial> arrayListBudgetRequest;
    private ArrayList<Trial> arrayListDoctoralThesis;
    public ArrayList<Trial> arraylistTrials;


    public void chooseFormat(String optionFaction) throws IOException {

        if (Objects.equals(optionFaction, "I")){
            trialsWriteCSV();
            editionsWriteCSV();
            System.out.println("Loading data from CSV Files...");
        }else{
            trialsWriteJson();
            editionsWriteJson();
            System.out.println("Loading data from JSON Files...");
        }
    }

    private void trialsWriteJson() throws IOException {

        boolean fileFound;

        fileFound = trialDAO.checkFile(pathPaperPublicationJSON);

        if (!fileFound){
            File paperPublicationFile = new File(pathPaperPublicationJSON);
            paperPublicationFile.createNewFile();
        }else{
            BufferedReader readerPP = new BufferedReader(new FileReader(pathPaperPublicationJSON));
            if (readerPP.readLine() == null) {
                trialDAO.writejsonTrial(arrayListPaperPublication, pathPaperPublicationJSON);
            }else{
                arrayListPaperPublication = trialDAO.readjsonTrial(arrayListPaperPublication, pathPaperPublicationJSON);
                arraylistTrials.addAll(arrayListPaperPublication);
            }
        }

        fileFound = trialDAO.checkFile(pathMasterStudiesJSON);

        if (!fileFound){
            File masterStudiesFile = new File(pathMasterStudiesJSON);
            masterStudiesFile.createNewFile();
        }else{
            BufferedReader readerMS = new BufferedReader(new FileReader(pathMasterStudiesJSON));
            if (readerMS.readLine() == null) {
                trialDAO.writejsonTrial(arrayListMasterStudies, pathMasterStudiesJSON);
            }else{
                arrayListMasterStudies = trialDAO.readjsonTrial(arrayListMasterStudies, pathMasterStudiesJSON);
                arraylistTrials.addAll(arrayListMasterStudies);
            }
        }

        fileFound = trialDAO.checkFile(pathBudgetRequestJSON);

        if (!fileFound){
            File budgetRequestFile = new File(pathBudgetRequestJSON);
            budgetRequestFile.createNewFile();
        }else{
            BufferedReader readerBR = new BufferedReader(new FileReader(pathBudgetRequestJSON));
            if (readerBR.readLine() == null) {
                trialDAO.writejsonTrial(arrayListBudgetRequest, pathBudgetRequestJSON);
            }else{
                arrayListBudgetRequest = trialDAO.readjsonTrial(arrayListBudgetRequest, pathBudgetRequestJSON);
                arraylistTrials.addAll(arrayListBudgetRequest);
            }
        }

        fileFound = trialDAO.checkFile(pathDoctoralThesisJSON);

        if (!fileFound){
            File doctoralThesisFile = new File(pathDoctoralThesisJSON);
            doctoralThesisFile.createNewFile();
        }else{
            trialDAO.checkFile(pathDoctoralThesisJSON);
            BufferedReader readerDT = new BufferedReader(new FileReader(pathDoctoralThesisJSON));
            if (readerDT.readLine() == null) {
                trialDAO.writejsonTrial(arrayListDoctoralThesis, pathDoctoralThesisJSON);
            }else{
                arrayListDoctoralThesis = trialDAO.readjsonTrial(arrayListDoctoralThesis, pathDoctoralThesisJSON);
                arraylistTrials.addAll(arrayListDoctoralThesis);
            }
        }










    }

    private void trialsWriteCSV() {
    }

    private void editionsWriteJson() {
    }

    private void editionsWriteCSV() {
    }


}
