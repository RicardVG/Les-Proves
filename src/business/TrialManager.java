package business;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import persistance.TrialDAO;

import java.io.*;
import java.util.ArrayList;
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
            System.out.println("creando Paper Publication JSON");
        }else{
            BufferedReader readerDT = new BufferedReader(new FileReader(trialDAO.getPathPaperPublicationJSON()));
            if (readerDT.readLine() != null) {
                paperPublicationArrayList = trialDAO.readjsonTrialPP(trialDAO.getPathPaperPublicationJSON());
            }
        }

        fileFound = checkFile(trialDAO.getPathMasterStudiesJSON());
        if (!fileFound) {
            File paperPublicationFile = new File(trialDAO.getPathMasterStudiesJSON());
            paperPublicationFile.createNewFile();
            System.out.println("creando Masters Studies JSON");
        }else {
            BufferedReader readerDT = new BufferedReader(new FileReader(trialDAO.getPathMasterStudiesJSON()));
            if (readerDT.readLine() != null) {
                masterStudiesArrayList = trialDAO.readjsonTrialMS(trialDAO.getPathMasterStudiesJSON());
            }
        }
        fileFound = checkFile(trialDAO.getPathBudgetRequestJSON());

        if (!fileFound) {
            File paperPublicationFile = new File(trialDAO.getPathBudgetRequestJSON());
            paperPublicationFile.createNewFile();
            System.out.println("creando Budget Request JSON");
        }else {
            BufferedReader readerDT = new BufferedReader(new FileReader(trialDAO.getPathBudgetRequestJSON()));
            if (readerDT.readLine() != null) {
                budgetRequestArrayList = trialDAO.readjsonTrialBR(trialDAO.getPathBudgetRequestJSON());
            }
        }
        fileFound = checkFile(trialDAO.getPathDoctoralThesisJSON());

        if (!fileFound) {
            File paperPublicationFile = new File(trialDAO.getPathDoctoralThesisJSON());
            paperPublicationFile.createNewFile();
            System.out.println("creando Doctoral Thesis JSON");
        }else {
            BufferedReader readerDT = new BufferedReader(new FileReader(trialDAO.getPathDoctoralThesisJSON()));
            if (readerDT.readLine() != null) {
                doctoralThesisArrayList = trialDAO.readjsonTrialDT(trialDAO.getPathDoctoralThesisJSON());
            }
        }
    }


    public void trialsReadCSV() throws IOException {

        boolean fileFound;
        fileFound = checkFile(trialDAO.getPathPaperPublicationCSV());

        if (!fileFound) {
            File paperPublicationFile = new File(trialDAO.getPathPaperPublicationCSV());
            paperPublicationFile.createNewFile();
            System.out.println("creando Paper Publication CSV");
        }else{
            BufferedReader readerDT = new BufferedReader(new FileReader(trialDAO.getPathPaperPublicationCSV()));
            if (readerDT.readLine() != null) {
        //        paperPublicationArrayList = readCSVTrialPP(pathPaperPublicationCSV);
            }
        }
    }

    public void writeTrialPaperPublication(String optionFaction) throws IOException {

        if (optionFaction.equals("I")){
            //CSV
        }else{

            if (!checkFile(trialDAO.getPathPaperPublicationJSON())){
                File paperPublicationFile = new File(trialDAO.getPathPaperPublicationJSON());
                paperPublicationFile.createNewFile();
            }else{
                trialDAO.writePaperPublication(paperPublicationArrayList);
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
                trialDAO.writeMasterStudies(masterStudiesArrayList);

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
                trialDAO.writeDoctoralThesis(doctoralThesisArrayList);
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
                trialDAO.writeBudgetRequest(budgetRequestArrayList);
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


    public void removeTrial(String name) {
        int flag = 0;

        for (int i = 0; i < paperPublicationArrayList.size() ; i++){
            if (paperPublicationArrayList.get(i).getName().equals(name)){
                paperPublicationArrayList.remove(i);
                flag = 1;
                System.out.println("\nThe trial was successfully deleted.\n");
            }
        }
        for (int i = 0; i < masterStudiesArrayList.size() && flag == 0; i++){
            if (masterStudiesArrayList.get(i).getName().equals(name)){
                masterStudiesArrayList.remove(i);
                flag = 1;
                System.out.println("\nThe trial was successfully deleted.\n");
            }
        }
        for (int i = 0; i < budgetRequestArrayList.size() && flag == 0; i++){
            if (budgetRequestArrayList.get(i).getName().equals(name)){
                budgetRequestArrayList.remove(i);
                flag = 1;
                System.out.println("\nThe trial was successfully deleted.\n");
            }
        }
        for (int i = 0; i < doctoralThesisArrayList.size() && flag == 0; i++){
            if (doctoralThesisArrayList.get(i).getName().equals(name)){
                doctoralThesisArrayList.remove(i);
                flag = 1;
                System.out.println("\nThe trial was successfully deleted.\n");
            }
        }

        if(flag == 0){
            System.out.println("\nThe input confirmation doesn't match with name of a Trial\n");
        }
    }
}
