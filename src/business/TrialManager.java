package business;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import persistance.TrialDAO;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class TrialManager {

    private TrialDAO  trialDAO;
    private ArrayList <PaperPublication> paperPublicationArrayList = new ArrayList();
    private ArrayList <DoctoralThesis> doctoralThesisArrayList = new ArrayList();
    private ArrayList <MasterStudies> masterStudiesArrayList = new ArrayList();
    private ArrayList <BudgetRequest> budgetRequestArrayList = new ArrayList();
    private ArrayList<Trial> arraylistTrials = new ArrayList<>();


    public TrialManager(TrialDAO trialDAO) {
        this.trialDAO = trialDAO;
    }


    public void addtoPaperPublicationArrayList(PaperPublication paperPublication) {
        paperPublicationArrayList.add(paperPublication);
    }

    public void addDoctoralThesisArrayList(DoctoralThesis doctoralThesis) {
        doctoralThesisArrayList.add(doctoralThesis);
    }

    public void addMasterStudiesArrayList(MasterStudies masterStudies) {
        masterStudiesArrayList.add(masterStudies);
    }

    public void addBudgetRequestArrayList(BudgetRequest budgetRequest) {
        budgetRequestArrayList.add(budgetRequest);
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
        fileFound = checkFile(trialDAO.getPathPaperPublicationJSON());

        if (!fileFound) {
            File paperPublicationFile = new File(trialDAO.getPathPaperPublicationJSON());
            paperPublicationFile.createNewFile();
        }else{
            BufferedReader readerDT = new BufferedReader(new FileReader(trialDAO.getPathPaperPublicationJSON()));
            if (readerDT.readLine() != null) {
                paperPublicationArrayList = trialDAO.readjsonTrialPP();
            }
        }

        fileFound = checkFile(trialDAO.getPathMasterStudiesJSON());
        if (!fileFound) {
            File paperPublicationFile = new File(trialDAO.getPathMasterStudiesJSON());
            paperPublicationFile.createNewFile();
        }else {
            BufferedReader readerDT = new BufferedReader(new FileReader(trialDAO.getPathMasterStudiesJSON()));
            if (readerDT.readLine() != null) {
                masterStudiesArrayList = trialDAO.readjsonTrialMS();
            }
        }
        fileFound = checkFile(trialDAO.getPathBudgetRequestJSON());

        if (!fileFound) {
            File paperPublicationFile = new File(trialDAO.getPathBudgetRequestJSON());
            paperPublicationFile.createNewFile();
        }else {
            BufferedReader readerDT = new BufferedReader(new FileReader(trialDAO.getPathBudgetRequestJSON()));
            if (readerDT.readLine() != null) {
                budgetRequestArrayList = trialDAO.readjsonTrialBR();
            }
        }
        fileFound = checkFile(trialDAO.getPathDoctoralThesisJSON());

        if (!fileFound) {
            File paperPublicationFile = new File(trialDAO.getPathDoctoralThesisJSON());
            paperPublicationFile.createNewFile();
        }else {
            BufferedReader readerDT = new BufferedReader(new FileReader(trialDAO.getPathDoctoralThesisJSON()));
            if (readerDT.readLine() != null) {
                doctoralThesisArrayList = trialDAO.readjsonTrialDT();
            }
        }
    }


    public void trialsReadCSV() throws IOException {

        ArrayList<String> arrayListStringTrial;


        boolean fileFound;
        fileFound = checkFile(trialDAO.getPathPaperPublicationCSV());

        if (!fileFound) {
            File paperPublicationFile = new File(trialDAO.getPathPaperPublicationCSV());
            paperPublicationFile.createNewFile();
        }else{
            BufferedReader readerDT = new BufferedReader(new FileReader(trialDAO.getPathPaperPublicationCSV()));
            if (readerDT.readLine() != null) {
             //   arrayListStringTrial = trialDAO.readCSVTrialPP();
             //   System.out.println(arrayListStringTrial.size());
             //   converterPPArray(arrayListStringTrial);
            }
        }

        fileFound = checkFile(trialDAO.getPathMasterStudiesCSV());

        if (!fileFound) {
            File masterStudiesFile = new File(trialDAO.getPathMasterStudiesCSV());
            masterStudiesFile.createNewFile();
            System.out.println("creando Master Studies CSV");
        }else{
            BufferedReader readerDT = new BufferedReader(new FileReader(trialDAO.getPathMasterStudiesCSV()));
            if (readerDT.readLine() != null) {
                arrayListStringTrial = trialDAO.readCSVTrialMS();
       //         converterMSArray(arrayListStringTrial);
            }
        }

        fileFound = checkFile(trialDAO.getPathBudgetRequestCSV());

        if (!fileFound) {
            File budgetRequestFile = new File(trialDAO.getPathBudgetRequestCSV());
            budgetRequestFile.createNewFile();
            System.out.println("creando Budget Request CSV");
        }else{
            BufferedReader readerDT = new BufferedReader(new FileReader(trialDAO.getPathBudgetRequestCSV()));
            if (readerDT.readLine() != null) {
                arrayListStringTrial = trialDAO.readCSVTrialBR();
      //          converterBRArray(arrayListStringTrial);
            }
        }

        fileFound = checkFile(trialDAO.getPathDoctoralThesisCSV());

        if (!fileFound) {
            File doctoralThesisFile = new File(trialDAO.getPathDoctoralThesisCSV());
            doctoralThesisFile.createNewFile();
            System.out.println("creando DoctoralThesis CSV");
        }else{
            BufferedReader readerDT = new BufferedReader(new FileReader(trialDAO.getPathDoctoralThesisCSV()));
            if (readerDT.readLine() != null) {
                arrayListStringTrial = trialDAO.readCSVTrialDT();
     //           converterDTArray(arrayListStringTrial);
            }
        }

    }

    private void converterDTArray(ArrayList<String> arrayListStringTrial) {
        for (int i = 0; i < arrayListStringTrial.size(); i++) {
      //      DoctoralThesis drTemp = new DoctoralThesis(fromLine(arrayListStringTrial.get(i)));
    //        doctoralThesisArrayList.add(drTemp);
        }
    }

    private void converterBRArray(ArrayList<String> arrayListStringTrial) {
        for (int i = 0; i < arrayListStringTrial.size(); i++) {
      //      BudgetRequest brTemp = new BudgetRequest(fromLine(arrayListStringTrial.get(i)));
      //      budgetRequestArrayList.add(brTemp);
        }
    }

    private void converterMSArray(ArrayList<String> arrayListStringTrial) {
        for (int i = 0; i < arrayListStringTrial.size(); i++) {
     //       MasterStudies msTemp = new MasterStudies(fromLine(arrayListStringTrial.get(i)));
     //       masterStudiesArrayList.add(msTemp);
        }
    }

    private void converterPPArray(ArrayList<String> arrayListStringTrial) {
        for (int i = 0; i < arrayListStringTrial.size(); i++) {
            String[] paperPublication = arrayListStringTrial.get(i).split("\n");
        //    PaperPublication ppTemp = new PaperPublication(paperPublication);
        //    paperPublicationArrayList.add(ppTemp);
        }
    }

    public void writeTrialPaperPublication(String optionFaction) throws IOException {
        if (optionFaction.equals("I")){
            if (!checkFile(trialDAO.getPathPaperPublicationCSV())){
                File paperPublicationFileCSV = new File(trialDAO.getPathPaperPublicationCSV());
                paperPublicationFileCSV.createNewFile();
            }else{
                trialDAO.writePaperPublicationCSV(paperPublicationArrayList);
            }
        }else{
            if (!checkFile(trialDAO.getPathPaperPublicationJSON())){
                File paperPublicationFile = new File(trialDAO.getPathPaperPublicationJSON());
                paperPublicationFile.createNewFile();
            }else{
                trialDAO.writePaperPublicationJSON(paperPublicationArrayList);
            }
        }
    }

    public void writeTrialMasterStudies(String optionFaction) throws IOException {

        if (optionFaction.equals("I")){
            //write CSV
        }else{

            if (!checkFile(trialDAO.getPathMasterStudiesJSON())) {
                File paperPublicationFile = new File(trialDAO.getPathMasterStudiesJSON());
                paperPublicationFile.createNewFile();
            }else {
                trialDAO.writeMasterStudiesJSON(masterStudiesArrayList);
            }
        }
    }

    public void writeTrialDoctoralThesis(String optionFaction) throws IOException {
        if (optionFaction.equals("I")){
            //write CSV
        }else{
            if (!checkFile(trialDAO.getPathDoctoralThesisJSON())){
                File budgetRequestFile = new File(trialDAO.getPathDoctoralThesisJSON());
                budgetRequestFile.createNewFile();
            }else{
                trialDAO.writeDoctoralThesisJSON(doctoralThesisArrayList);
            }
        }
    }

    public void writeTrialBudgetRequest(String optionFaction) throws IOException {
        if (optionFaction.equals("I")){
            //CSV
        }else{
            if (!checkFile(trialDAO.getPathBudgetRequestJSON())){
                File budgetRequestFile = new File(trialDAO.getPathBudgetRequestJSON());
                budgetRequestFile.createNewFile();
            }else{
                trialDAO.writeBudgetRequestJSON(budgetRequestArrayList);
            }
        }
    }


    public int getSizeArrayTrials() {
        return paperPublicationArrayList.size() + masterStudiesArrayList.size() + budgetRequestArrayList.size() + doctoralThesisArrayList.size();
    }

    public ArrayList<Trial> getAllArrayLists() {
        arraylistTrials.removeAll(arraylistTrials);

        arraylistTrials.addAll(paperPublicationArrayList);
        arraylistTrials.addAll(masterStudiesArrayList);
        arraylistTrials.addAll(budgetRequestArrayList);
        arraylistTrials.addAll(doctoralThesisArrayList);

        return arraylistTrials;
    }

    public ArrayList<PaperPublication> getPaperPublicationArrayList() {
        return paperPublicationArrayList;
    }

    public ArrayList<DoctoralThesis> getDoctoralThesisArrayList() {
        return doctoralThesisArrayList;
    }

    public ArrayList<MasterStudies> getMasterStudiesArrayList() {
        return masterStudiesArrayList;
    }

    public ArrayList<BudgetRequest> getBudgetRequestArrayList() {
        return budgetRequestArrayList;
    }

    public int getSizePPMS() {
        return paperPublicationArrayList.size() + masterStudiesArrayList.size();
    }

    public int getSizePPMSBR() {
        return paperPublicationArrayList.size() + masterStudiesArrayList.size() + budgetRequestArrayList.size();
    }


    public void removeTrial(String name) throws IOException {
        int flag = 0;

        for (int i = 0; i < paperPublicationArrayList.size() ; i++){
            if (paperPublicationArrayList.get(i).getName().equals(name)){
                paperPublicationArrayList.remove(i);
                trialDAO.writePaperPublicationJSON(paperPublicationArrayList);
                flag = 1;
                System.out.println("\nThe trial was successfully deleted.\n");
            }
        }
        for (int i = 0; i < masterStudiesArrayList.size() && flag == 0; i++){
            if (masterStudiesArrayList.get(i).getName().equals(name)){
                masterStudiesArrayList.remove(i);
                trialDAO.writeMasterStudiesJSON(masterStudiesArrayList);
                flag = 1;
                System.out.println("\nThe trial was successfully deleted.\n");
            }
        }
        for (int i = 0; i < budgetRequestArrayList.size() && flag == 0; i++){
            if (budgetRequestArrayList.get(i).getName().equals(name)){
                budgetRequestArrayList.remove(i);
                trialDAO.writeBudgetRequestJSON(budgetRequestArrayList);
                flag = 1;
                System.out.println("\nThe trial was successfully deleted.\n");
            }
        }
        for (int i = 0; i < doctoralThesisArrayList.size() && flag == 0; i++){
            if (doctoralThesisArrayList.get(i).getName().equals(name)){
                doctoralThesisArrayList.remove(i);
                trialDAO.writeDoctoralThesisJSON(doctoralThesisArrayList);
                flag = 1;
                System.out.println("\nThe trial was successfully deleted.\n");
            }
        }

        if(flag == 0){
            System.out.println("\nThe input confirmation doesn't match with name of a Trial\n");
        }
    }

    public String getTypeObject(String object){

        for (int i = 0; i < paperPublicationArrayList.size(); i++){
            if (paperPublicationArrayList.get(i).getName().equals(object)){
                return "PaperPublication";
            }
        }
        for (int i = 0; i < masterStudiesArrayList.size(); i++){
            if (masterStudiesArrayList.get(i).getName().equals(object)){
                return "MasterStudies";
            }
        }
        for (int i = 0; i < budgetRequestArrayList.size(); i++){
            if (budgetRequestArrayList.get(i).getName().equals(object)){
                return "BudgetRequest";
            }
        }
        for (int i = 0; i < doctoralThesisArrayList.size(); i++){
            if (doctoralThesisArrayList.get(i).getName().equals(object)){
                return "DoctoralThesis";
            }
        }
        return null;
    }
}
