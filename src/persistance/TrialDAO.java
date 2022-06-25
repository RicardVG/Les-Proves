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
import java.util.Scanner;

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



    public ArrayList<PaperPublication> readjsonTrialPP() {
/*
        Gson gson = new Gson();
        paperPublicationArrayList = gson.fromJson(new FileReader(pathPaperPublicationJSON), (Type) PaperPublication[].class);
  */    try {


            String json = Files.readString(Paths.get(pathPaperPublicationJSON));
            JsonElement element = JsonParser.parseString(json);
            JsonObject object = element.getAsJsonObject();

           System.out.println("object= "+object);


        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return paperPublicationArrayList;
    }

    public ArrayList<MasterStudies> readjsonTrialMS () {
        try {
            String json = Files.readString(Paths.get(pathMasterStudiesJSON));
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


    public ArrayList<BudgetRequest> readjsonTrialBR () {
            try {
                String json = Files.readString(Paths.get(pathBudgetRequestJSON));
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

    public ArrayList<DoctoralThesis> readjsonTrialDT () {
        try {
            String json = Files.readString(Paths.get(pathDoctoralThesisJSON));
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


    public void writePaperPublication (ArrayList<PaperPublication> paperPublicationArrayList) throws IOException {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        JSONObject general = new JSONObject();
        JSONObject object1 = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        int i;
        for (i = 0; i <paperPublicationArrayList.size(); i++){

            object1.put("name",paperPublicationArrayList.get(i).getName());
            object1.put("journalName",paperPublicationArrayList.get(i).getJournalName());
            object1.put("journalQuartile",paperPublicationArrayList.get(i).getJournalQuartile());
            object1.put("acceptanceProbability",paperPublicationArrayList.get(i).getAcceptanceProbability());
            object1.put("revisionProbability",paperPublicationArrayList.get(i).getRevisionProbability());
            object1.put("rejectionProbability",paperPublicationArrayList.get(i).getRejectionProbability());
        }

        jsonArray.addAll(Arrays.asList(object1.get(i)));
        general.put("paperPublication",jsonArray);
        FileWriter fileWriter = new FileWriter(pathPaperPublicationJSON);
        fileWriter.write(gson.toJson(general));
        fileWriter.close();

    }



/*
    public void writePaperPublication(ArrayList<PaperPublication> paperPublicationArrayList) throws IOException {
            Path p = Paths.get(pathPaperPublicationJSON);
            String json = Files.readString(p);
            JsonElement element, element2;
            JsonObject object = new JsonObject ();
            element = JsonParser.parseString(json);

            for (int i = 0; i < paperPublicationArrayList.size(); i++) {
                    object.addProperty("trialName", paperPublicationArrayList.get(i).getName());
            object.addProperty("journalName", paperPublicationArrayList.get(i).getJournalName());
                object.addProperty("journalQuartile", paperPublicationArrayList.get(i).getJournalQuartile());
                object.addProperty("acceptancPoeProbability", paperPublicationArrayList.get(i).getAcceptanceProbability());
                object.addProperty("revisionProbability", paperPublicationArrayList.get(i).getRevisionProbability());
                object.addProperty("rejectionProbability", paperPublicationArrayList.get(i).getRejectionProbability());

                FileWriter fileWriter = new FileWriter(pathPaperPublicationJSON);
                Gson gBuilder = new GsonBuilder().setPrettyPrinting().create();

                element2 = object.getAsJsonObject();
                element.getAsJsonObject().getAsJsonArray("PaperPublication").add(element2);

                fileWriter.write(gBuilder.toJson(element));
                fileWriter.close();
            }
        }
*/

        /*
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
*/

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


    public ArrayList<String> readCSVTrialPP() {

        ArrayList<String> arrayListStringTrial =  new ArrayList<>();

        Scanner s = new Scanner(pathPaperPublicationCSV);
        String readerLines = new String();

        while (s.hasNextLine()) {
            readerLines = s.nextLine();
            if (!readerLines.equals("")) {
                arrayListStringTrial.add(readerLines);
            }
        }

        return arrayListStringTrial;
    }
}
