package persistance;

import business.*;
import com.google.gson.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
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

            /*
            String json = Files.readString(Paths.get(path));
            JsonElement element = JsonParser.parseString(json);
            JsonObject object = element.getAsJsonObject();

            System.out.println("object= "+object);

             */

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


    public void writeTrialPaperPublication(String optionFaction, PaperPublication dataPaperPublication) throws IOException {

        FileWriter fileWriter = new FileWriter(pathPaperPublicationJSON, true);
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
                try {
                    /*
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    String json = gson.toJson(dataPaperPublication);
                    byte[] bytes = json.getBytes();

                    try (OutputStream outputStream = new FileOutputStream(pathPaperPublicationJSON)) {
                        outputStream.write(bytes);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        fileWriter.close();
                    }
                    */
                    String json = Files.readString(Paths.get(pathPaperPublicationJSON));

                    System.out.println("json= "+ json);
                    JsonElement element2;
                    JsonObject object = new JsonObject();
                    JsonElement element = JsonParser.parseString(json);

                    object.addProperty("trialName", dataPaperPublication.getTrialName());
                    object.addProperty("journalName", dataPaperPublication.getJournalName());
                    object.addProperty("journalQuartile", dataPaperPublication.getJournalQuartile());
                    object.addProperty("acceptanceProbability", dataPaperPublication.getAcceptanceProbability());
                    object.addProperty("revisionProbability", dataPaperPublication.getRevisionProbability());
                    object.addProperty("rejectionProbability", dataPaperPublication.getRejectionProbability());

                    element2 = object.getAsJsonObject();
                    element.getAsJsonObject().getAsJsonArray("paperPublication").add(element2);

                    Gson gson = new GsonBuilder().setPrettyPrinting().create();

                    fileWriter.write(gson.toJson(element));
                    fileWriter.close();

                } catch (IOException e) {
                    e.printStackTrace();
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
