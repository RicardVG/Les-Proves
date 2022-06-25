package business;

import persistance.EditionDAO;

import java.util.ArrayList;

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
            System.out.println("\nL'any actual no és val·lid!");
        }else{
            validYear = true;
        }
        return validYear;
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


    public void writeEditions(String optionFaction) {
        if (optionFaction.equals("I")){
            //CSV
        }else{
            //JSON
            editionDAO.editionsWriteJson(editionArrayList);
        }
    }
}
