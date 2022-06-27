package persistance;

import business.*;
import com.google.gson.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.print.Doc;
import java.io.*;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
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


    public void editionsReadJson(){

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

    public ArrayList<String> readCSVTrialPP() {


        ArrayList<String> paperPublicationArrayList = new ArrayList<>();
        String line = "";  
        String splitBy = ",";  
        try {
            Scanner scanner = new Scanner(new File(pathPaperPublicationCSV));
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                String[] paperPublication = line.split(splitBy);
            
           //     paperPublicationArrayList.add(paperPublication[0],paperPublication[1],paperPublication[2],paperPublication[3],paperPublication[4],paperPublication[5]);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return paperPublicationArrayList;
    }

    public ArrayList<String> readCSVTrialMS() {
        ArrayList<String> arrayListStringTrial =  new ArrayList<>();

        Scanner s = new Scanner(pathMasterStudiesCSV);
        String readerLines;

        while (s.hasNextLine()) {
            readerLines = s.nextLine();
            if (!readerLines.equals("")) {
                arrayListStringTrial.add(readerLines);
            }
        }

        return arrayListStringTrial;
    }

    public ArrayList<String> readCSVTrialBR() {
        ArrayList<String> arrayListStringTrial =  new ArrayList<>();

        Scanner s = new Scanner(pathBudgetRequestCSV);
        String readerLines;

        while (s.hasNextLine()) {
            readerLines = s.nextLine();
            if (!readerLines.equals("")) {
                arrayListStringTrial.add(readerLines);
            }
        }

        return arrayListStringTrial;
    }

    public ArrayList<String> readCSVTrialDT() {
        ArrayList<String> arrayListStringTrial =  new ArrayList<>();

        Scanner s = new Scanner(pathDoctoralThesisCSV);
        String readerLines;

        while (s.hasNextLine()) {
            readerLines = s.nextLine();
            if (!readerLines.equals("")) {
                arrayListStringTrial.add(readerLines);
            }
        }

        return arrayListStringTrial;
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
        }
    }
}
