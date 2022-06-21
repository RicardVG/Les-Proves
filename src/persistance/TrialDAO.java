package persistance;

import business.*;
import com.google.gson.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

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


            String json = Files.readString(Paths.get(path));
            JsonElement element = JsonParser.parseString(json);
            JsonObject object = element.getAsJsonObject();

            System.out.println("object= "+object);



            /*
            for (int i = 0; i < object.getAsJsonArray("rappers").size(); i++) {
                rappers.add(gson.fromJson(object.getAsJsonArray("rappers").get(i).getAsJsonObject(), Rapper.class));
            */

        } catch (IOException ex) {
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
            System.out.println("creando Paper Publication JSON");
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
            System.out.println("creando Masters Studies JSON");
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
            System.out.println("creando Budget Request JSON");
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
            System.out.println("creando Doctoral Thesis JSON");
        }else {
            BufferedReader readerDT = new BufferedReader(new FileReader(pathDoctoralThesisJSON));
            if (readerDT.readLine() != null) {
                arraylistTrials= readjsonTrial(pathDoctoralThesisJSON);
            }
        }
    }

    public void editionsReadJson(){

    }


    public void writeTrialPaperPublication(String optionFaction, ArrayList<PaperPublication> arraylistPaperPublication) throws IOException {

        FileWriter fileWriter = new FileWriter(pathPaperPublicationJSON, true);
        if (optionFaction.equals("I")){
        }else{
            if (!checkFile(pathPaperPublicationJSON)){
                File paperPublicationFile = new File(pathPaperPublicationJSON);
                paperPublicationFile.createNewFile();
            }else{
                Gson gBuilder = new GsonBuilder().setPrettyPrinting().create();
                try {
                    gBuilder.toJson(arraylistPaperPublication, fileWriter);
                    fileWriter.flush();
                    fileWriter.close();
                }catch (JsonIOException ex){
                    ex.printStackTrace();
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
