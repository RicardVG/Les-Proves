package business;

import persistance.TrialDAO;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

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
                trialDAO.readCSVTrialPP(paperPublicationArrayList);
            }
        }

        fileFound = checkFile(trialDAO.getPathMasterStudiesCSV());

        if (!fileFound) {
            File masterStudiesFile = new File(trialDAO.getPathMasterStudiesCSV());
            masterStudiesFile.createNewFile();
        }else{
            BufferedReader readerDT = new BufferedReader(new FileReader(trialDAO.getPathMasterStudiesCSV()));
            if (readerDT.readLine() != null) {
                trialDAO.readCSVTrialMS(masterStudiesArrayList);
            }
        }

        fileFound = checkFile(trialDAO.getPathBudgetRequestCSV());

        if (!fileFound) {
            File budgetRequestFile = new File(trialDAO.getPathBudgetRequestCSV());
            budgetRequestFile.createNewFile();
        }else{
            BufferedReader readerDT = new BufferedReader(new FileReader(trialDAO.getPathBudgetRequestCSV()));
            if (readerDT.readLine() != null) {
                trialDAO.readCSVTrialBR(budgetRequestArrayList);
            }
        }

        fileFound = checkFile(trialDAO.getPathDoctoralThesisCSV());

        if (!fileFound) {
            File doctoralThesisFile = new File(trialDAO.getPathDoctoralThesisCSV());
            doctoralThesisFile.createNewFile();
        }else{
            BufferedReader readerDT = new BufferedReader(new FileReader(trialDAO.getPathDoctoralThesisCSV()));
            if (readerDT.readLine() != null) {
                trialDAO.readCSVTrialDT(doctoralThesisArrayList);
            }
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
            if (!checkFile(trialDAO.getPathMasterStudiesCSV())){
                File masterStudiesFileCSV = new File(trialDAO.getPathMasterStudiesCSV());
                masterStudiesFileCSV.createNewFile();
            }else{
                trialDAO.writeMasterStudiesCSV(masterStudiesArrayList);
            }
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
            if (!checkFile(trialDAO.getPathDoctoralThesisCSV())){
                File doctoralThesisFileCSV = new File(trialDAO.getPathDoctoralThesisCSV());
                doctoralThesisFileCSV.createNewFile();
            }else{
                trialDAO.writeDoctoralThesisCSV(doctoralThesisArrayList);
            }
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
            if (!checkFile(trialDAO.getPathBudgetRequestCSV())){
                File budgetRequestFileCSV = new File(trialDAO.getPathBudgetRequestCSV());
                budgetRequestFileCSV.createNewFile();
            }else{
                trialDAO.writeBudgetRequestCSV(budgetRequestArrayList);
            }
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


    public void removeTrial(String name, String option) throws IOException {
        int flag = 0;

        for (int i = 0; i < paperPublicationArrayList.size() ; i++){
            if (paperPublicationArrayList.get(i).getName().equals(name)){
                paperPublicationArrayList.remove(i);
                if (Objects.equals(option, "I")){
                    trialDAO.writePaperPublicationCSV(paperPublicationArrayList);
                }else{
                    trialDAO.writePaperPublicationJSON(paperPublicationArrayList);
                }
                flag = 1;
                System.out.println("\nThe trial was successfully deleted.\n");
            }
        }
        for (int i = 0; i < masterStudiesArrayList.size() && flag == 0; i++){
            if (masterStudiesArrayList.get(i).getName().equals(name)){
                masterStudiesArrayList.remove(i);
                if (Objects.equals(option, "I")){
                    trialDAO.writeMasterStudiesCSV(masterStudiesArrayList);
                }else{
                    trialDAO.writeMasterStudiesJSON(masterStudiesArrayList);
                }
                flag = 1;
                System.out.println("\nThe trial was successfully deleted.\n");
            }
        }
        for (int i = 0; i < budgetRequestArrayList.size() && flag == 0; i++){
            if (budgetRequestArrayList.get(i).getName().equals(name)){
                budgetRequestArrayList.remove(i);
                if (Objects.equals(option, "I")){
                    trialDAO.writeBudgetRequestCSV(budgetRequestArrayList);
                }else{
                    trialDAO.writeBudgetRequestJSON(budgetRequestArrayList);
                }
                flag = 1;
                System.out.println("\nThe trial was successfully deleted.\n");
            }
        }
        for (int i = 0; i < doctoralThesisArrayList.size() && flag == 0; i++){
            if (doctoralThesisArrayList.get(i).getName().equals(name)){
                doctoralThesisArrayList.remove(i);
                if (Objects.equals(option, "I")){
                    trialDAO.writeDoctoralThesisCSV(doctoralThesisArrayList);
                }else{
                    trialDAO.writeDoctoralThesisJSON(doctoralThesisArrayList);
                }
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

    public boolean checkNameTrial(String trialNameConfirmation) {
        for (int i = 0; i < getAllArrayLists().size() ; i++){
            if(trialNameConfirmation.equals(getAllArrayLists().get(i).getName())){
                return false;
            }
        }

        return true;
    }

    public boolean checkQuartileTrial(String journalQuartile) {
        if(journalQuartile.equals("Q1") || journalQuartile.equals("Q2") || journalQuartile.equals("Q3") || journalQuartile.equals("Q4")){
            return true;
        }else{
         return false;
        }
    }

    public boolean checkProbability(int probability) {
        if(probability < 0 || probability > 100){
            return false;
        }else{
            return true;
        }
    }

    public boolean checkRevisionProbability(int acceptanceProbability, int revisionProbability) {
        if((acceptanceProbability + revisionProbability) > 100) {
            return false;
        }else{
            return true;
        }
    }

    public boolean checkRejectionProbability(int acceptanceProbability, int revisionProbability, int rejectionProbability) {
        if ((acceptanceProbability + revisionProbability + rejectionProbability) != 100) {
        return false;
        } else {
        return true;
        }
    }
}
