package persistance;

import java.io.*;
import java.util.*;

import business.*;
import com.google.gson.*;
import com.opencsv.CSVWriter;

public class TrialDAO {


    private final String pathPaperPublicationJSON="jsonFiles/paperPublication.json";
    private final String pathMasterStudiesJSON="jsonFiles/masterStudies.json";
    private final String pathBudgetRequestJSON="jsonFiles/budgetRequest.json";
    private final String pathDoctoralThesisJSON="jsonFiles/doctoralThesis.json";
    private final String pathPaperPublicationCSV="csv/paperPublication.csv";
    private final String pathMasterStudiesCSV="csvFiles/masterStudies.csv";
    private final String pathBudgetRequestCSV="csvFiles/budgetRequest.csv";
    private final String pathDoctoralThesisCSV="csvFiles/doctoralThesis.csv";
    private ArrayList<Trial> arrayListPaperPublication;
    private ArrayList<Trial> arrayListMasterStudies;
    private ArrayList<Trial> arrayListBudgetRequest;
    private ArrayList<Trial> arrayListDoctoralThesis;
    private ArrayList<Trial> arraylistTrials;


    public ArrayList<Trial> getArraylistTrials() {
        return arraylistTrials;
    }

    public void writejsonTrial(ArrayList<Trial> arraylistTrials, String path) throws IOException {
        try (Writer writer = new FileWriter(path)) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(arraylistTrials,writer);
        }catch(FileNotFoundException fileNotFoundException){
            fileNotFoundException.printStackTrace();
        }
    }

    public ArrayList<Trial> readjsonTrial(ArrayList<Trial> arraylistTrials, String path) throws FileNotFoundException {
        Gson gson = new Gson();
        Collections.addAll(arraylistTrials, gson.fromJson(new FileReader(path), Trial[].class));
        return arraylistTrials;
    }

    public boolean checkFile(String filePathString){
        boolean fileFound = false;
        File f = new File(filePathString);
        if(f.exists() && !f.isDirectory()) {
            fileFound = true;
        }
        return fileFound;
    }


    public void trialsWriteJson() throws IOException {

        boolean fileFound;

        fileFound = checkFile(pathPaperPublicationJSON);

        if (!fileFound){
            File paperPublicationFile = new File(pathPaperPublicationJSON);
            paperPublicationFile.createNewFile();
        }else{
            BufferedReader readerPP = new BufferedReader(new FileReader(pathPaperPublicationJSON));
            if (readerPP.readLine() == null) {
                writejsonTrial(arrayListPaperPublication, pathPaperPublicationJSON);
            }else{
                arrayListPaperPublication = readjsonTrial(arrayListPaperPublication, pathPaperPublicationJSON);
                arraylistTrials.addAll(arrayListPaperPublication);
            }
        }

        fileFound = checkFile(pathMasterStudiesJSON);

        if (!fileFound){
            File masterStudiesFile = new File(pathMasterStudiesJSON);
            masterStudiesFile.createNewFile();
        }else{
            BufferedReader readerMS = new BufferedReader(new FileReader(pathMasterStudiesJSON));
            if (readerMS.readLine() == null) {
                writejsonTrial(arrayListMasterStudies, pathMasterStudiesJSON);
            }else{
                arrayListMasterStudies = readjsonTrial(arrayListMasterStudies, pathMasterStudiesJSON);
                arraylistTrials.addAll(arrayListMasterStudies);
            }
        }

        fileFound = checkFile(pathBudgetRequestJSON);

        if (!fileFound){
            File budgetRequestFile = new File(pathBudgetRequestJSON);
            budgetRequestFile.createNewFile();
        }else{
            BufferedReader readerBR = new BufferedReader(new FileReader(pathBudgetRequestJSON));
            if (readerBR.readLine() == null) {
                writejsonTrial(arrayListBudgetRequest, pathBudgetRequestJSON);
            }else{
                arrayListBudgetRequest = readjsonTrial(arrayListBudgetRequest, pathBudgetRequestJSON);
                arraylistTrials.addAll(arrayListBudgetRequest);
            }
        }

        fileFound = checkFile(pathDoctoralThesisJSON);

        if (!fileFound){
            File doctoralThesisFile = new File(pathDoctoralThesisJSON);
            doctoralThesisFile.createNewFile();
        }else{
            checkFile(pathDoctoralThesisJSON);
            BufferedReader readerDT = new BufferedReader(new FileReader(pathDoctoralThesisJSON));
            if (readerDT.readLine() == null) {
                writejsonTrial(arrayListDoctoralThesis, pathDoctoralThesisJSON);
            }else{
                arrayListDoctoralThesis = readjsonTrial(arrayListDoctoralThesis, pathDoctoralThesisJSON);
                arraylistTrials.addAll(arrayListDoctoralThesis);
            }
        }
    }
    public void trialsWriteCSV(PaperPublication paperPublication, MasterStudies masterStudies, DoctoralThesis doctoralThesis, BudgetRequest budgetRequest) throws IOException {

        boolean fileFound;

        fileFound = checkFile(pathPaperPublicationJSON);

        if (!fileFound){
      //      if(trialType == 1){
                FileWriter fileWriter = new FileWriter(pathPaperPublicationCSV, true);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(paperPublication);
                stringBuilder.append(masterStudies);
                stringBuilder.append(doctoralThesis);
                stringBuilder.append(budgetRequest);
                stringBuilder.append("\n");
                fileWriter.append(stringBuilder.toString());
                fileWriter.flush();
                fileWriter.close();

        //    }
            //amb els altres objectes tmb
            //Crec que s'haurà de reformular aquesta part ja que 1) no esta del tot be tenir 10 variables amb
            //els paths, lo millor seria fer una funcio amb tots. 2) com aconsegueixo aqui el tipus de trial
            //si encara l'usuari no ha entrat el numero???? ni idea :(
        }



    }
}
