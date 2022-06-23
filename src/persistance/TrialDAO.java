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
    private final String pathPaperPublicationJSON="jsonFiles/paperPublication.json";
    private final String pathMasterStudiesJSON="jsonFiles/masterStudies.json";
    private final String pathBudgetRequestJSON="jsonFiles/budgetRequest.json";
    private final String pathDoctoralThesisJSON="jsonFiles/doctoralThesis.json";
    private final String pathPaperPublicationCSV="csvFiles/paperPublication.csv";
    private final String pathMasterStudiesCSV="csvFiles/masterStudies.csv";
    private final String pathBudgetRequestCSV="csvFiles/budgetRequest.csv";
    private final String pathDoctoralThesisCSV="csvFiles/doctoralThesis.csv";
    
    private ArrayList <PaperPublication> paperPublicationArrayList;
    private ArrayList <MasterStudies> masterStudiesArrayList;
    private ArrayList <BudgetRequest> budgetRequestArrayList;
    private ArrayList <DoctoralThesis> doctoralThesisArrayList;
    
    public TrialDAO (ArrayList <PaperPublication> paperPublicationArrayList, ArrayList <MasterStudies> masterStudiesArrayList, ArrayList <BudgetRequest> budgetRequestArrayList, ArrayList <DoctoralThesis> doctoralThesisArrayList) {
        this.paperPublicationArrayList = paperPublicationArrayList;
        this.masterStudiesArrayList = masterStudiesArrayList;
        this.budgetRequestArrayList = budgetRequestArrayList;
        this.doctoralThesisArrayList = doctoralThesisArrayList;
    }

    private ArrayList<Trial> arraylistTrials = new ArrayList<>();


    public ArrayList<Trial> getArraylistTrials() {
        return arraylistTrials;
    }

    public ArrayList<PaperPublication> readjsonTrialPP(String path) {
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

    public boolean checkFile(String filePathString){
        boolean fileFound = false;
        File f = new File(filePathString);
        if(f.exists() && !f.isDirectory()) {
            fileFound = true;
        }
        return fileFound;
    }



    public void trialsReadJson(ArrayList<PaperPublication> paperPublicationArrayList, ArrayList<MasterStudies> masterStudiesArrayList, ArrayList<BudgetRequest> budgetRequestArrayList, ArrayList<DoctoralThesis> doctoralThesisArrayList) throws IOException {
        boolean fileFound;
        fileFound = checkFile(pathPaperPublicationJSON);

        if (!fileFound) {
            File paperPublicationFile = new File(pathPaperPublicationJSON);
            paperPublicationFile.createNewFile();
            System.out.println("creando Paper Publication JSON");
        }else{
            BufferedReader readerDT = new BufferedReader(new FileReader(pathPaperPublicationJSON));
            if (readerDT.readLine() != null) {
                paperPublicationArrayList = readjsonTrialPP(pathPaperPublicationJSON);
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
                masterStudiesArrayList = readjsonTrialMS(pathMasterStudiesJSON);
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
                budgetRequestArrayList = readjsonTrialBR(pathBudgetRequestJSON);
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
                doctoralThesisArrayList = readjsonTrialDT(pathDoctoralThesisJSON);
            }
        }
    }

    public void editionsReadJson(){

    }


    public void writeTrialPaperPublication(String optionFaction, ArrayList<PaperPublication> arraylistPaperPublication) throws IOException {

        if (optionFaction.equals("I")){
            //CSV
        }else{

            FileWriter fileWriter = new FileWriter(pathPaperPublicationJSON);

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

    public void writeTrialMasterStudies(String optionFaction, ArrayList<MasterStudies> arrayListMasterStudies) throws IOException {

        if (optionFaction.equals("I")){
            //write CSV
        }else{

            FileWriter fileWriter = new FileWriter(pathMasterStudiesJSON);

            if (!checkFile(pathMasterStudiesJSON)) {
                File paperPublicationFile = new File(pathMasterStudiesJSON);
                paperPublicationFile.createNewFile();
            }else {
                Gson gBuilder = new GsonBuilder().setPrettyPrinting().create();
                try {
                    gBuilder.toJson(arrayListMasterStudies,fileWriter);
                    fileWriter.flush();
                    fileWriter.close();
                }catch(FileNotFoundException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
            }
        }
    }

    public void writeTrialDoctoralThesis(String optionFaction, ArrayList<DoctoralThesis> arrayListDoctoralThesis) throws IOException {

        if (optionFaction.equals("I")){
            //write CSV
        }else{

            FileWriter fileWriter = new FileWriter(pathDoctoralThesisJSON);

            if (!checkFile(pathDoctoralThesisJSON)){
                File budgetRequestFile = new File(pathDoctoralThesisJSON);
                budgetRequestFile.createNewFile();
            }else{
                Gson gBuilder = new GsonBuilder().setPrettyPrinting().create();
                try {
                    gBuilder.toJson(arrayListDoctoralThesis,fileWriter);
                    fileWriter.flush();
                    fileWriter.close();
                }catch(FileNotFoundException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }

            }
        }
    }

    public void writeTrialBudgetRequest(String optionFaction, ArrayList<BudgetRequest> arrayListBudgetRequest) throws IOException {

        if (optionFaction.equals("I")){
            //write CSV
        }else{

            FileWriter fileWriter = new FileWriter(pathBudgetRequestJSON);

            if (!checkFile(pathBudgetRequestJSON)){
                File budgetRequestFile = new File(pathBudgetRequestJSON);
                budgetRequestFile.createNewFile();
            }else{
                Gson gBuilder = new GsonBuilder().setPrettyPrinting().create();
                try {

                    gBuilder.toJson(arrayListBudgetRequest,fileWriter);
                    fileWriter.flush();
                    fileWriter.close();
                }catch(FileNotFoundException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
            }
        }
    }
    
}
