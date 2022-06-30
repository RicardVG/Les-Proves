package business;

import java.util.ArrayList;

public class Edition {

    private int year;
    private int numPlayers;
    private int numTrials;
    private ArrayList<String> stringArrayList;

    public Edition(int year, int numPlayers, int numTrials, ArrayList<String> stringArrayList) {
        this.year = year;
        this.numPlayers = numPlayers;
        this.numTrials = numTrials;
        this.stringArrayList = stringArrayList;
    }

    /**
     * Aquesta funció retorna les dades de la Edition en el format correcte per poder-ho guardar en un fitxer CSV
     * @return retorna les dades de la edició en format CSV
     */
    public String writeCSV() {
        String aux = year + "," + numPlayers + "," + numTrials;
        for (String s : stringArrayList) {
            aux += "," + s;
        }
        aux += "\n";

        return aux;
    }

    /**
     * Aquesta funció retorna l'any de l'edició
     * @return year
     */
    public int getYear() {
        return year;
    }

    /**
     * Aquesta funció retorna el nombre de jugadors per a l'edició
     * @return numPlayers
     */
    public int getNumPlayers() {
        return numPlayers;
    }

    /**
     * Aquesta funció retorna el nombre de trials en la edició
     * @return numTrials
     */
    public int getNumTrials() {
        return numTrials;
    }

    /**
     * Aquest procediment guarda l'any de l'edició dins de l'edició
     * @param year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Aquesta funció retorna la edicio per poder guardar-ho en el CSV més endavant (aqui ja s'han guardat les dades de
     *  les edicions en el format per el CSV)
     * @return stringArrayList
     */
    public ArrayList<String> getStringArrayList() {
        return stringArrayList;
    }

    /**
     * Aquest procediment guarda el nombre de jugadors de l'edició dins de l'edició
     * @param numPlayers
     */
    public void setNumberPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }
}
