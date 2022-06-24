package persistance;

import business.*;
import com.google.gson.*;

import javax.print.Doc;
import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class TrialDAO {

    
    private ArrayList <PaperPublication> paperPublicationArrayList;
    private ArrayList <MasterStudies> masterStudiesArrayList;
    private ArrayList <BudgetRequest> budgetRequestArrayList;
    private ArrayList <DoctoralThesis> doctoralThesisArrayList;


    private final String pathPaperPublicationJSON="jsonFiles/paperPublication.json";
    private final String pathMasterStudiesJSON="jsonFiles/masterStudies.json";
    private final String pathBudgetRequestJSON="jsonFiles/budgetRequest.json";
    private final String pathDoctoralThesisJSON="jsonFiles/doctoralThesis.json";
    private final String pathPaperPublicationCSV="csvFiles/paperPublication.csv";
    private final String pathMasterStudiesCSV="csvFiles/masterStudies.csv";
    private final String pathBudgetRequestCSV="csvFiles/budgetRequest.csv";
    private final String pathDoctoralThesisCSV="csvFiles/doctoralThesis.csv";
    
    public TrialDAO (ArrayList <PaperPublication> paperPublicationArrayList, ArrayList <MasterStudies> masterStudiesArrayList, ArrayList <BudgetRequest> budgetRequestArrayList, ArrayList <DoctoralThesis> doctoralThesisArrayList) {
        this.paperPublicationArrayList = paperPublicationArrayList;
        this.masterStudiesArrayList = masterStudiesArrayList;
        this.budgetRequestArrayList = budgetRequestArrayList;
        this.doctoralThesisArrayList = doctoralThesisArrayList;
    }


    public TrialDAO() {

    }



    public ArrayList<PaperPublication> readjsonTrialPP(String path) {
        try {


            String json = Files.readString(Paths.get(path));
            JsonElement element = JsonParser.parseString(json);
            JsonObject object = element.getAsJsonObject();

           System.out.println("object= "+object);


        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return paperPublicationArrayList;
    }

    public ArrayList<MasterStudies> readjsonTrialMS (String path) {
        try {
            String json = Files.readString(Paths.get(path));
            JsonElement element = JsonParser.parseString(json);
            JsonObject object = element.getAsJsonObject();
          /*      if (element instanceof JsonObject) {
                    JsonObject  jobject = element.getAsJsonObject();
                } else if (element instanceof JsonArray) {
                    JsonArray  jarray =  element.getAsJsonArray();
                }
    
           */
            System.out.println("object= "+object);
               
                /*
                for (int i = 0; i < object.getAsJsonArray("rappers").size(); i++) {
                    rappers.add(gson.fromJson(object.getAsJsonArray("rappers").get(i).getAsJsonObject(), Rapper.class));
                */

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return masterStudiesArrayList;
    }


    public ArrayList<BudgetRequest> readjsonTrialBR (String path) {
            try {
                String json = Files.readString(Paths.get(path));
                JsonElement element = JsonParser.parseString(json);
                JsonObject object = element.getAsJsonObject();
          /*      if (element instanceof JsonObject) {
                    JsonObject  jobject = element.getAsJsonObject();
                } else if (element instanceof JsonArray) {
                    JsonArray  jarray =  element.getAsJsonArray();
                }
    
           */
               System.out.println("object= "+object);
               
                /*
                for (int i = 0; i < object.getAsJsonArray("rappers").size(); i++) {
                    rappers.add(gson.fromJson(object.getAsJsonArray("rappers").get(i).getAsJsonObject(), Rapper.class));
                */
    
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return budgetRequestArrayList;
        }

    public ArrayList<DoctoralThesis> readjsonTrialDT (String path) {
        try {
            String json = Files.readString(Paths.get(path));
            JsonElement element = JsonParser.parseString(json);
            JsonObject object = element.getAsJsonObject();
          /*      if (element instanceof JsonObject) {
                    JsonObject  jobject = element.getAsJsonObject();
                } else if (element instanceof JsonArray) {
                    JsonArray  jarray =  element.getAsJsonArray();
                }
    
           */
            System.out.println("object= "+object);
               
                /*
                for (int i = 0; i < object.getAsJsonArray("rappers").size(); i++) {
                    rappers.add(gson.fromJson(object.getAsJsonArray("rappers").get(i).getAsJsonObject(), Rapper.class));
                */

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return doctoralThesisArrayList;
    }


    public void editionsReadJson(){

    }




    private ArrayList<PaperPublication> readCSVTrialPP(String pathPaperPublicationCSV) {
        return paperPublicationArrayList;
    }

    public void writePaperPublication(ArrayList<PaperPublication> paperPublicationArrayList) throws IOException {
        FileWriter fileWriter = new FileWriter(pathPaperPublicationJSON);
        Gson gBuilder = new GsonBuilder().setPrettyPrinting().create();
        try {
            gBuilder.toJson(paperPublicationArrayList, fileWriter);
            fileWriter.flush();
            fileWriter.close();
        }catch (JsonIOException ex){
            ex.printStackTrace();
        }

    }

    public void writeMasterStudies(ArrayList<MasterStudies> masterStudiesArrayList) throws IOException {
        FileWriter fileWriter = new FileWriter(pathMasterStudiesJSON);
        Gson gBuilder = new GsonBuilder().setPrettyPrinting().create();
        try {
            gBuilder.toJson(masterStudiesArrayList,fileWriter);
            fileWriter.flush();
            fileWriter.close();
        }catch(FileNotFoundException fileNotFoundException){
            fileNotFoundException.printStackTrace();
        }
    }

    public void writeDoctoralThesis(ArrayList<DoctoralThesis> doctoralThesisArrayList) throws IOException {
        FileWriter fileWriter = new FileWriter(pathDoctoralThesisJSON);
        Gson gBuilder = new GsonBuilder().setPrettyPrinting().create();
        try {
            gBuilder.toJson(doctoralThesisArrayList,fileWriter);
            fileWriter.flush();
            fileWriter.close();
        }catch(FileNotFoundException fileNotFoundException){
            fileNotFoundException.printStackTrace();
        }
    }


    public void writeBudgetRequest(ArrayList<BudgetRequest> budgetRequestArrayList) throws IOException {
        FileWriter fileWriter = new FileWriter(pathBudgetRequestJSON);
        Gson gBuilder = new GsonBuilder().setPrettyPrinting().create();
        try {

            gBuilder.toJson(budgetRequestArrayList,fileWriter);
            fileWriter.flush();
            fileWriter.close();
        }catch(FileNotFoundException fileNotFoundException){
            fileNotFoundException.printStackTrace();
        }
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


}
