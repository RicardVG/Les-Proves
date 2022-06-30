package persistance;

import business.BudgetRequest;
import business.DoctoralThesis;
import business.MasterStudies;
import business.PaperPublication;
import com.google.gson.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class TrialDAO {
    private final String pathPaperPublicationJSON="jsonFiles/paperPublication.json";
    private final String pathMasterStudiesJSON="jsonFiles/masterStudies.json";
    private final String pathBudgetRequestJSON="jsonFiles/budgetRequest.json";
    private final String pathDoctoralThesisJSON="jsonFiles/doctoralThesis.json";
    private final String pathPaperPublicationCSV="csvFiles/paperPublication.csv";
    private final String pathMasterStudiesCSV="csvFiles/masterStudies.csv";
    private final String pathBudgetRequestCSV="csvFiles/budgetRequest.csv";
    private final String pathDoctoralThesisCSV="csvFiles/doctoralThesis.csv";

    public TrialDAO() {
    }

    public ArrayList<PaperPublication> readjsonTrialPP() throws IOException {

        ArrayList <PaperPublication> paperPublicationArrayList = new ArrayList<>();
        String json = Files.readString(Paths.get(pathPaperPublicationJSON));
        JsonElement element = JsonParser.parseString(json);
        JsonArray jsonArr = element.getAsJsonArray();
        Gson googleJson = new Gson();
        Collections.addAll(paperPublicationArrayList, googleJson.fromJson(new FileReader(pathPaperPublicationJSON), PaperPublication[].class));
        return paperPublicationArrayList;
    }

    public ArrayList<MasterStudies> readjsonTrialMS () throws IOException {
        ArrayList <MasterStudies> masterStudiesArrayList = new ArrayList<>();
        String json = Files.readString(Paths.get(pathMasterStudiesJSON));
        JsonElement element = JsonParser.parseString(json);
        JsonArray jsonArr = element.getAsJsonArray();
        Gson googleJson = new Gson();
        Collections.addAll(masterStudiesArrayList, googleJson.fromJson(new FileReader(pathMasterStudiesJSON), MasterStudies[].class));
        return masterStudiesArrayList;
    }


    public ArrayList<BudgetRequest> readjsonTrialBR () throws IOException {
        ArrayList <BudgetRequest> budgetRequestArrayList = new ArrayList<>();
        String json = Files.readString(Paths.get(pathBudgetRequestJSON));
        JsonElement element = JsonParser.parseString(json);
        JsonArray jsonArr = element.getAsJsonArray();
        Gson googleJson = new Gson();
        Collections.addAll(budgetRequestArrayList, googleJson.fromJson(new FileReader(pathBudgetRequestJSON), BudgetRequest[].class));
        return budgetRequestArrayList;
        }

    public ArrayList<DoctoralThesis> readjsonTrialDT () throws IOException {
        ArrayList <DoctoralThesis> doctoralThesisArrayList = new ArrayList<>();
        String json = Files.readString(Paths.get(pathPaperPublicationJSON));
        JsonElement element = JsonParser.parseString(json);
        JsonArray jsonArr = element.getAsJsonArray();
        Gson googleJson = new Gson();
        Collections.addAll(doctoralThesisArrayList, googleJson.fromJson(new FileReader(pathDoctoralThesisJSON), DoctoralThesis[].class));
        return doctoralThesisArrayList;
    }

    public void writePaperPublicationJSON (ArrayList<PaperPublication> paperPublicationArrayList) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter fileWriter = new FileWriter(pathPaperPublicationJSON);
        
        fileWriter.write(gson.toJson(paperPublicationArrayList));
        fileWriter.close();

    }

    public void writeMasterStudiesJSON(ArrayList<MasterStudies> masterStudiesArrayList) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter fileWriter = new FileWriter(pathMasterStudiesJSON);

        fileWriter.write(gson.toJson(masterStudiesArrayList));
        fileWriter.close();
    }

    public void writeDoctoralThesisJSON(ArrayList<DoctoralThesis> doctoralThesisArrayList) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter fileWriter = new FileWriter(pathDoctoralThesisJSON);
        
        fileWriter.write(gson.toJson(doctoralThesisArrayList));
        fileWriter.close();
    }


    public void writeBudgetRequestJSON(ArrayList<BudgetRequest> budgetRequestArrayList) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter fileWriter = new FileWriter(pathBudgetRequestJSON);
        
        fileWriter.write(gson.toJson(budgetRequestArrayList));
        fileWriter.close();
    }

    public String getPathPaperPublicationJSON() {
        return pathPaperPublicationJSON;
    }

    public String getPathMasterStudiesJSON() {
        return pathMasterStudiesJSON;
    }

    public String getPathBudgetRequestJSON() {
        return pathBudgetRequestJSON;
    }

    public String getPathDoctoralThesisJSON() {
        return pathDoctoralThesisJSON;
    }

    public String getPathPaperPublicationCSV() {
        return pathPaperPublicationCSV;
    }

    public String getPathMasterStudiesCSV() {
        return pathMasterStudiesCSV;
    }

    public String getPathBudgetRequestCSV() {
        return pathBudgetRequestCSV;
    }

    public String getPathDoctoralThesisCSV() {
        return pathDoctoralThesisCSV;
    }

    public void readCSVTrialPP(ArrayList<PaperPublication> paperPublicationArrayList) {
        String line = "";  
        String splitBy = ",";  
        try {
<<<<<<< HEAD
            Gson gson = new Gson();
            Trial[] trials = gson.fromJson(new FileReader(path), Trial[].class);
            arraylistTrials.addAll(List.of(trials));
        } catch (IOException ioException) {
            ioException.printStackTrace();
=======
            Scanner scanner = new Scanner(new File(pathPaperPublicationCSV));
            scanner.useDelimiter("\n");   //sets the delimiter pattern

            while (scanner.hasNext()) {
                line = scanner.next();
                String[] lineArray = line.split(splitBy);
                if(lineArray.length>1){
                    for (int i = 0; i < lineArray.length; i++) {
                    }
                    paperPublicationArrayList.add(new PaperPublication(lineArray[0], lineArray[1], lineArray[2], Integer.parseInt(lineArray[3]), Integer.parseInt(lineArray[4]), Integer.parseInt(lineArray[5])));
                }
            }

            scanner.close();  //closes the scanner
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void readCSVTrialMS(ArrayList<MasterStudies> masterStudies) {
        String line = "";  
        String splitBy = ",";  
        try {
            Scanner scanner = new Scanner(new File(pathMasterStudiesCSV));
            scanner.useDelimiter("\n");   //sets the delimiter pattern

            while (scanner.hasNext()) {
                line = scanner.next();
                String[] lineArray = line.split(splitBy);
                if(lineArray.length>1){
                    for (int i = 0; i < lineArray.length; i++) {
                    }
                    masterStudies.add(new MasterStudies(lineArray[0], lineArray[1], Integer.parseInt(lineArray[2]), Integer.parseInt(lineArray[3])));
                }
            }

            scanner.close();  //closes the scanner
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void readCSVTrialBR(ArrayList<BudgetRequest> budgetRequest) {
        String line = "";  
        String splitBy = ",";  
        try {
            Scanner scanner = new Scanner(new File(pathBudgetRequestCSV));
            scanner.useDelimiter("\n");   //sets the delimiter pattern

            while (scanner.hasNext()) {
                line = scanner.next();
                String[] lineArray = line.split(splitBy);
                if(lineArray.length>1){
                    for (int i = 0; i < lineArray.length; i++) {
                    }
                    budgetRequest.add(new BudgetRequest(lineArray[0], lineArray[1], Integer.parseInt(lineArray[2])));
                }
            }

            scanner.close();  //closes the scanner
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void readCSVTrialDT(ArrayList<DoctoralThesis> doctoralThesis) {
        String line = "";  
        String splitBy = ",";  
        try {
            Scanner scanner = new Scanner(new File(pathDoctoralThesisCSV));
            scanner.useDelimiter("\n");   //sets the delimiter pattern

            while (scanner.hasNext()) {
                line = scanner.next();
                String[] lineArray = line.split(splitBy);
                if(lineArray.length>1){
                    for (int i = 0; i < lineArray.length; i++) {
                    }
                    doctoralThesis.add(new DoctoralThesis(lineArray[0], lineArray[1], Integer.parseInt(lineArray[2])));
                }
            }

            scanner.close();  //closes the scanner
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void writePaperPublicationCSV(ArrayList<PaperPublication> paperPublicationArrayList) {
        try {
            FileWriter writer = new FileWriter(pathPaperPublicationCSV);

           for (PaperPublication paperPublication : paperPublicationArrayList) {
                writer.write(paperPublication.writeCSV());
            }

            writer.close();

        } catch (IOException ex) {
            ex.printStackTrace();
>>>>>>> test
        }
    }

    public void writeMasterStudiesCSV(ArrayList<MasterStudies> masterStudiesArrayList) {
        try {
            FileWriter writer = new FileWriter(pathMasterStudiesCSV);

            for (MasterStudies masterStudies : masterStudiesArrayList) {
                writer.write(masterStudies.writeCSV());
            }
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void writeDoctoralThesisCSV(ArrayList<DoctoralThesis> doctoralThesisArrayList) {
        try {
            FileWriter writer = new FileWriter(pathDoctoralThesisCSV);

<<<<<<< HEAD
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
                try {
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    gson.toJson(dataPaperPublication, new FileWriter(pathPaperPublicationJSON, true));

                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
            }

=======
            for (DoctoralThesis doctoralThesis : doctoralThesisArrayList) {
                writer.write(doctoralThesis.writeCSV());
            }
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
>>>>>>> test
        }
    }

    public void writeBudgetRequestCSV(ArrayList<BudgetRequest> budgetRequestArrayList) {
        try {
            FileWriter writer = new FileWriter(pathBudgetRequestCSV);

            for (BudgetRequest budgetRequest : budgetRequestArrayList) {
                writer.write(budgetRequest.writeCSV());
            }
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
