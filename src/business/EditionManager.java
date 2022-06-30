package business;

import persistance.EditionDAO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class EditionManager {

    private EditionDAO editionDAO;

    private ArrayList<Edition> editionArrayList = new ArrayList<>();

    public ArrayList<Edition> getEditionArrayList() {
        return editionArrayList;
    }

    public EditionManager(EditionDAO editionDAO) {
        this.editionDAO = editionDAO;
    }

    public boolean checkYear(int editionYears) {
        boolean validYear = false;
        if(editionYears < 2022){
            System.out.println("\nL'any actual no és val·lid!\n");
        }else{
            validYear = true;
        }
        return validYear;
    }

    public boolean checkNewYear (int newEditionYears) {
        for(int i = 0; i < editionArrayList.size(); i++){
            if(editionArrayList.get(i).getYear() == newEditionYears){
                return false;
            }
        }
        return true;
    }

    public boolean checkNumberPlayers(int initialNumberPlayers) {
        boolean validNumberPlayers = false;
        if(initialNumberPlayers < 1 || initialNumberPlayers > 5){
            System.out.println("\nEnter a valid number of players!");
        }else{
            validNumberPlayers = true;
        }
        return validNumberPlayers;
    }

    public boolean checkNumberTrials(int numberTrials) {
        boolean validNumberTrials = false;
        if(numberTrials < 3 || numberTrials > 12){
            System.out.println("\nEnter a valid number of trials!");
        }else{
            validNumberTrials = true;
        }
        return  validNumberTrials;
    }

    public String createMessagePickEditionTrials (int i, int numTrials) {
        String message = "Pick a trial (" + i + "/" + numTrials + "): ";
        
        return message;
    }

    public void writeEditions(String optionFaction) throws IOException {
        if (optionFaction.equals("I")){
            if (!checkFile(editionDAO.getPathEditionCSV())){
                File editionFileCSV = new File(editionDAO.getPathEditionCSV());
                editionFileCSV.createNewFile();
                editionDAO.editionsWriteCSV(editionArrayList);
            }else{
                editionDAO.editionsWriteCSV(editionArrayList);
            }
        }else{
            if (!checkFile(editionDAO.getPathEditionJSON())){
                File editionFileJSON = new File(editionDAO.getPathEditionJSON());
                editionFileJSON.createNewFile();
                editionDAO.editionsWriteJson(editionArrayList);
            }else{
                editionDAO.editionsWriteJson(editionArrayList);
            }
        }
    }

    public void addEditionToArrayList(Edition edition) {
        editionArrayList.add(edition);
    }

    public boolean checkFile(String filePathString){
        boolean fileFound = false;
        File f = new File(filePathString);
        if(f.exists() && !f.isDirectory()) {
            fileFound = true;
        }
        return fileFound;
    }

    public void readEditionsJSON() throws IOException {
        boolean fileFound;
        fileFound = checkFile(editionDAO.getPathEditionJSON());

        if (!fileFound) {
            File editionFileJSON = new File(editionDAO.getPathEditionJSON());
            editionFileJSON.createNewFile();
            System.out.println("creando Editions JSON");
        }else{
            BufferedReader readerDT = new BufferedReader(new FileReader(editionDAO.getPathEditionJSON()));
            if (readerDT.readLine() != null) {
                editionArrayList = editionDAO.readJSONEditions();
            }
        }
    }

    public int getSizeArrayEditions() {
        return  editionArrayList.size();
    }

    public void duplicateEdition(int year, int numPlayers, int option, String optionFaction) throws IOException {

        Edition edition = new Edition(year, numPlayers,editionArrayList.get(option-1).getNumTrials() ,editionArrayList.get(option-1).getStringArrayList());

        editionArrayList.add(edition);
        editionArrayList.get(editionArrayList.size()-1).setYear(year);
        editionArrayList.get(editionArrayList.size()-1).setNumberPlayers(numPlayers);

        if(Objects.equals(optionFaction, "I")){
            editionDAO.editionsWriteCSV(editionArrayList);
        }else {
            editionDAO.editionsWriteJson(editionArrayList);
        }
    }

    public boolean deleteEdition(int option, int confirmationYear, String optionFaction) throws IOException {
        if (editionArrayList.get(option-1).getYear() == confirmationYear){
            editionArrayList.remove(option-1);

            if(Objects.equals(optionFaction, "I")){
                editionDAO.editionsWriteCSV(editionArrayList);
            }else {
                editionDAO.editionsWriteJson(editionArrayList);
            }
            return true;
        }else{
            return false;
        }
    }

    public void readEditionsCSV() throws IOException {
        boolean fileFound;

        fileFound = checkFile(editionDAO.getPathEditionCSV());

        if (!fileFound) {
            File editionFileCSV = new File(editionDAO.getPathEditionCSV());
            editionFileCSV.createNewFile();
        }else{
            BufferedReader readerDT = new BufferedReader(new FileReader(editionDAO.getPathEditionCSV()));
            if (readerDT.readLine() != null) {
                editionDAO.editionReadCSV(editionArrayList);
            }
        }
    }

    public boolean checkActualEdition() {

        boolean editionFound = false;
        int actualYear = 2022;

        for(int i = 0; i < editionArrayList.size(); i++){
            if(editionArrayList.get(i).getYear() == actualYear){
                editionFound = true;
            }
        }
        return editionFound;
    }

    public int getNumPlayers(){

        int numPlayers = 0;
        int actualYear = 2022;


        for(int i = 0; i < editionArrayList.size(); i++){
            if(editionArrayList.get(i).getYear() == actualYear){
                return editionArrayList.get(i).getNumPlayers();
            }
        }

        return numPlayers;

    }

    public ArrayList<String> getTrialsName() {

        ArrayList<String> stringArrayList = new ArrayList<>();
        int actualYear = 2022;

        for (int i = 0; i < editionArrayList.size(); i++) {
            if (editionArrayList.get(i).getYear() == actualYear) {
                return editionArrayList.get(i).getStringArrayList();
            }
        }

        return null;
    }
}
