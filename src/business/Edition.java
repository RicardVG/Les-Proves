package business;

import java.util.ArrayList;

public class Edition {
    
    private int year;
    private int numPlayers;
    private int numTrials;
    private ArrayList<String> stringArrayList;

    public Edition (int year, int numPlayers, int numTrials, ArrayList<String> stringArrayList) {
        this.year = year;
        this.numPlayers = numPlayers;
        this.numTrials = numTrials;
        this.stringArrayList = stringArrayList;
    }

    public String writeCSV() {
        String aux = year + "," + numPlayers + "," + numTrials + ",";
        for (String s : stringArrayList) {
            aux += s + ",";
        }
        aux+= "\n";

        return aux;
        //return year + "," + numPlayers + "," + numTrials + "," + stringArrayList.toString()+"\n";
    }

    public int getYear() {
        return year;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public int getNumTrials() {
        return numTrials;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public ArrayList<String> getStringArrayList() {
        return stringArrayList;
    }


    public void setNumberPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }
}
