package persistance;

import business.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TrialDAO {
    private final String pathPaperPublicationJSON="jsonFiles/paperPublication.json";
    private final String pathMasterStudiesJSON="jsonFiles/masterStudies.json";
    private final String pathBudgetRequestJSON="jsonFiles/budgetRequest.json";
    private final String pathDoctoralThesisJSON="jsonFiles/doctoralThesis.json";
    private final String pathPaperPublicationCSV="csvFiles/paperPublication.csv";
    private final String pathMasterStudiesCSV="csvFiles/masterStudies.csv";
    private final String pathBudgetRequestCSV="csvFiles/budgetRequest.csv";
    private final String pathDoctoralThesisCSV="csvFiles/doctoralThesis.csv";

    private ArrayList<Trial> arraylistTrials = new ArrayList<>();


    public ArrayList<Trial> getArraylistTrials() {
        return arraylistTrials;
    }

    public ArrayList<Trial> readjsonTrial(String path) {
        try {
            Gson gson = new Gson();
            Trial[] trials = gson.fromJson(new FileReader(path), Trial[].class);
            arraylistTrials.addAll(List.of(trials));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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



    public void trialsReadJson() throws IOException {
        boolean fileFound;
        fileFound = checkFile(pathPaperPublicationJSON);

        if (!fileFound) {
            File paperPublicationFile = new File(pathPaperPublicationJSON);
            paperPublicationFile.createNewFile();
        }else{
            BufferedReader readerDT = new BufferedReader(new FileReader(pathPaperPublicationJSON));
            if (readerDT.readLine() != null) {
                arraylistTrials = readjsonTrial(pathPaperPublicationJSON);
            }
        }

        fileFound = checkFile(pathMasterStudiesJSON);
        if (!fileFound) {
            File paperPublicationFile = new File(pathMasterStudiesJSON);
            paperPublicationFile.createNewFile();
        }else {
            BufferedReader readerDT = new BufferedReader(new FileReader(pathMasterStudiesJSON));
            if (readerDT.readLine() != null) {
                arraylistTrials = readjsonTrial(pathMasterStudiesJSON);
            }
        }
        fileFound = checkFile(pathBudgetRequestJSON);

        if (!fileFound) {
            File paperPublicationFile = new File(pathBudgetRequestJSON);
            paperPublicationFile.createNewFile();
        }else {
            BufferedReader readerDT = new BufferedReader(new FileReader(pathBudgetRequestJSON));
            if (readerDT.readLine() != null) {
                arraylistTrials = readjsonTrial(pathBudgetRequestJSON);
            }
        }
        fileFound = checkFile(pathDoctoralThesisJSON);

        if (!fileFound) {
            File paperPublicationFile = new File(pathDoctoralThesisJSON);
            paperPublicationFile.createNewFile();
        }else {
            BufferedReader readerDT = new BufferedReader(new FileReader(pathDoctoralThesisJSON));
            if (readerDT.readLine() != null) {
                arraylistTrials= readjsonTrial(pathDoctoralThesisJSON);
            }
        }
    }

    public void editionsReadJson(){

    }


    public void writeTrialPaperPublication(String optionFaction, PaperPublication dataPaperPublication) throws IOException {
        if (optionFaction.equals("I")){
       /*     boolean fileFound;

            fileFound = checkFile(pathPaperPublicationJSON);

            if (!fileFound) {
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
            }

        */
        }else{
            if (!checkFile(pathPaperPublicationJSON)){
                File paperPublicationFile = new File(pathPaperPublicationJSON);
                paperPublicationFile.createNewFile();
            }else{
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String json = gson.toJson(dataPaperPublication);
                FileWriter fileWriter = new FileWriter(pathPaperPublicationJSON);
                byte[] bytes = json.getBytes();
                try (OutputStream outputStream = new FileOutputStream(pathPaperPublicationJSON)) {
                    outputStream.write(bytes);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    fileWriter.close();
                }

            }

        }
    }

    public void writeTrialMasterStudies(String optionFaction, MasterStudies dataMasterStudies) throws IOException {
        if (optionFaction.equals("I")){
            //write CSV
        }else{
            if (!checkFile(pathMasterStudiesJSON)) {
                File paperPublicationFile = new File(pathMasterStudiesJSON);
                paperPublicationFile.createNewFile();
            }else {
                try (Writer writer = new FileWriter(pathMasterStudiesJSON)) {
                    Gson gson = new GsonBuilder().create();
                    gson.toJson(dataMasterStudies,writer);
                }catch(FileNotFoundException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
            }
        }
    }

    public void writeTrialDoctoralThesis(String optionFaction, DoctoralThesis dataDoctoralThesis) throws IOException {
        if (optionFaction.equals("I")){
            //write CSV
        }else{
            if (!checkFile(pathDoctoralThesisJSON)){
                File budgetRequestFile = new File(pathDoctoralThesisJSON);
                budgetRequestFile.createNewFile();
            }else{
                try (Writer writer = new FileWriter(pathDoctoralThesisJSON)) {
                    Gson gson = new GsonBuilder().create();
                    gson.toJson(dataDoctoralThesis,writer);
                }catch(FileNotFoundException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }

            }
        }
    }

    public void writeTrialBudgetRequest(String optionFaction, BudgetRequest dataBudgetRequest) throws IOException {
        if (optionFaction.equals("I")){
            //write CSV
        }else{
            if (!checkFile(pathBudgetRequestJSON)){
                File budgetRequestFile = new File(pathBudgetRequestJSON);
                budgetRequestFile.createNewFile();
            }else{
                try (Writer writer = new FileWriter(pathBudgetRequestJSON)) {
                    Gson gson = new GsonBuilder().create();
                    gson.toJson(dataBudgetRequest,writer);
                }catch(FileNotFoundException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
            }
        }
    }
}
