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

    /**
     * Aquesta funció llegeix el fitxer Paper Publication de tipus JSON que conté totes les proves
     * @return paperPublicationArrayList retorna un arrayList amb tota la informació de les trials paperPublication
     * @throws IOException
     */
    public ArrayList<PaperPublication> readjsonTrialPP() throws IOException {

        ArrayList <PaperPublication> paperPublicationArrayList = new ArrayList<>();
        String json = Files.readString(Paths.get(pathPaperPublicationJSON));
        JsonElement element = JsonParser.parseString(json);
        JsonArray jsonArr = element.getAsJsonArray();
        Gson googleJson = new Gson();
        Collections.addAll(paperPublicationArrayList, googleJson.fromJson(new FileReader(pathPaperPublicationJSON), PaperPublication[].class));
        return paperPublicationArrayList;
    }

    /**
     * Aquesta funció llegeix el fitxer Master Studies de tipus JSON que conté totes les proves
     * @return masterStudiesArrayList retorna un arrayList amb tota la informació de les trials masterStudies
     * @throws IOException
     */
    public ArrayList<MasterStudies> readjsonTrialMS () throws IOException {
        ArrayList <MasterStudies> masterStudiesArrayList = new ArrayList<>();
        String json = Files.readString(Paths.get(pathMasterStudiesJSON));
        JsonElement element = JsonParser.parseString(json);
        JsonArray jsonArr = element.getAsJsonArray();
        Gson googleJson = new Gson();
        Collections.addAll(masterStudiesArrayList, googleJson.fromJson(new FileReader(pathMasterStudiesJSON), MasterStudies[].class));
        return masterStudiesArrayList;
    }

    /**
     * Aquesta funció llegeix el fitxer Budget Request de tipus JSON que conté totes les proves
     * @return budgetRequestArrayList retorna un arrayList amb tota la informació de les trials budgetRequest
     * @throws IOException
     */
    public ArrayList<BudgetRequest> readjsonTrialBR () throws IOException {
        ArrayList <BudgetRequest> budgetRequestArrayList = new ArrayList<>();
        String json = Files.readString(Paths.get(pathBudgetRequestJSON));
        JsonElement element = JsonParser.parseString(json);
        JsonArray jsonArr = element.getAsJsonArray();
        Gson googleJson = new Gson();
        Collections.addAll(budgetRequestArrayList, googleJson.fromJson(new FileReader(pathBudgetRequestJSON), BudgetRequest[].class));
        return budgetRequestArrayList;
        }

    /**
     * Aquesta funció llegeix el fitxer Doctoral Thesis de tipus JSON que conté totes les proves
     * @return doctoralThesisArrayList retorna un arrayList amb tota la informació de les trials doctoralThesis
     * @throws IOException
     */
    public ArrayList<DoctoralThesis> readjsonTrialDT () throws IOException {
        ArrayList <DoctoralThesis> doctoralThesisArrayList = new ArrayList<>();
        String json = Files.readString(Paths.get(pathPaperPublicationJSON));
        JsonElement element = JsonParser.parseString(json);
        JsonArray jsonArr = element.getAsJsonArray();
        Gson googleJson = new Gson();
        Collections.addAll(doctoralThesisArrayList, googleJson.fromJson(new FileReader(pathDoctoralThesisJSON), DoctoralThesis[].class));
        return doctoralThesisArrayList;
    }

    /**
     * Aquest procediment actualitza el fitxer de tipus JSON de Paper Publication
     * @param paperPublicationArrayList arrayList que conté tota la informació actualitzada de les trials de tipus paperPublication
     * @throws IOException
     */
    public void writePaperPublicationJSON (ArrayList<PaperPublication> paperPublicationArrayList) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter fileWriter = new FileWriter(pathPaperPublicationJSON);
        
        fileWriter.write(gson.toJson(paperPublicationArrayList));
        fileWriter.close();

    }

    /**
     * Aquest procediment actualitza el fitxer de tipus JSON de Master Studies
     * @param masterStudiesArrayList arrayList que conté tota la informació actualitzada de les trials de tipus masterStudies
     * @throws IOException
     */
    public void writeMasterStudiesJSON(ArrayList<MasterStudies> masterStudiesArrayList) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter fileWriter = new FileWriter(pathMasterStudiesJSON);

        fileWriter.write(gson.toJson(masterStudiesArrayList));
        fileWriter.close();
    }

    /**
     * Aquest procediment actualitza el fitxer de tipus JSON de Doctoral Thesis
     * @param doctoralThesisArrayList arrayList que conté tota la informació actualitzada de les trials de tipus doctoralThesis
     * @throws IOException
     */
    public void writeDoctoralThesisJSON(ArrayList<DoctoralThesis> doctoralThesisArrayList) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter fileWriter = new FileWriter(pathDoctoralThesisJSON);
        
        fileWriter.write(gson.toJson(doctoralThesisArrayList));
        fileWriter.close();
    }


    /**
     * Aquest procediment  escriu al fitxer en format JSON de Budget Request la informació actualitzada de les trials
     * @param budgetRequestArrayList arrayList on conté tota la informació actualitzada de les trials de tipus budgetRequest
     * @throws IOException
     */
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

    /**
     * Aquest procediment obre i llegeix el fitxer CSV que conté totes les Paper Publication
     * @param paperPublicationArrayList Se li passa un arrayList de PaperPublication per que el procediment l'ompleni amb les trials
     */
    public void readCSVTrialPP(ArrayList<PaperPublication> paperPublicationArrayList) {
        String line = "";  
        String splitBy = ",";  
        try {
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

    /**
     * Aquest procediment es dedica a obrir el fitxer en format CSV de Master Studies que conté totes les proves
     * @param masterStudies Se li passa un arrayList buit de MasterStudies per que s'ompleni amb les trials
     */
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

    /**
     * La funció d'aquest procediment es el de llegir totes les proves de BurgetRequest del fitxer en format CSV
     * @param budgetRequest Es passa l'arrayList buit de BudgetRequest per que el procediment l'ompleni
     */
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

    /**
     * Aquest procediment obre i llegeix el fitxer en format CSV de Doctoral Thesis per tal d'obtenir el contingut del fitxer
     * @param doctoralThesis Es passa un arrayList de tipus DoctoralThesis buit per que s'ompleni
     */
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

    /**
     * Aquest procediment s'ocupa d'escriure en el fitxer de format CSV de Paper Publication les noves proves que s'han afegit
     * @param paperPublicationArrayList Es passa l'arrayList de PaperPublication amb la informació actualitzada per poder escriure el fitxer CSV
     */
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

    /**
     * Aquest procediment s'ocupa d'escriure en el fitxer de format CSV de Master Studies per actualitzar el seu contingut
     * @param masterStudiesArrayList arrayList de MasterStudies amb tota la informació actualitzada per ser escrita en el fitxer
     */
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

    /**
     * Aquest procediment s'encarrega d'actualitzar la informació al fitxer CSV de Doctoral Thesis
     * @param doctoralThesisArrayList arrayList de tipus DoctoralThesis que conté tota la informació actualitzada per el fitxer
     */
    public void writeDoctoralThesisCSV(ArrayList<DoctoralThesis> doctoralThesisArrayList) {
        try {
            FileWriter writer = new FileWriter(pathDoctoralThesisCSV);

            for (DoctoralThesis doctoralThesis : doctoralThesisArrayList) {
                writer.write(doctoralThesis.writeCSV());
            }
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Aquest procediment s'encarrega d'actualitzar la informació del fitxer CSV de Budget Request
     * @param budgetRequestArrayList
     */
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
