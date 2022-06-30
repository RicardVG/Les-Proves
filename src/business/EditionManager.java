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

    /**
     * Aquesta funció comproba que l'any introduit per la nova edició sigui major a 2022 (L'any actual) per poder crear
     *  noves edicions
     * @param editionYears l'any que l'usuari ha introduit per comprobar
     * @return retorna un booleà sobre si l'any es vàlid o no
     */
    public boolean checkYear(int editionYears) {
        boolean validYear = false;
        if (editionYears < 2022) {
            System.out.println("\nL'any actual no és val·lid!\n");
        } else {
            validYear = true;
        }
        return validYear;
    }

    /**
     * Aquesta funció comproba al crear/duplicar una nova edició si per l'any introduit ja hi ha una altra edició creada
     * @param newEditionYears l'any que l'usuari ha introduit per comprobar
     * @return retorna un booleà sobre si ja existeix una edició aquell any o no
     */
    public boolean checkNewYear(int newEditionYears) {
        for (int i = 0; i < editionArrayList.size(); i++) {
            if (editionArrayList.get(i).getYear() == newEditionYears) {
                return false;
            }
        }
        return true;
    }

    /**
     * Aquesta funció comproba que si el nombre de jugadors introduit per a l'edició compleix amb els paràmetres establerts
     *  per poder crear una nova edició
     * @param initialNumberPlayers el nombre de jugadors que l'usuari vol afegir a l'edició a crear
     * @return retorna un booleà si el nombre introduit es vàlid o no
     */
    public boolean checkNumberPlayers(int initialNumberPlayers) {
        boolean validNumberPlayers = false;
        if (initialNumberPlayers < 1 || initialNumberPlayers > 5) {
            System.out.println("\nEnter a valid number of players!");
        } else {
            validNumberPlayers = true;
        }
        return validNumberPlayers;
    }

    /**
     * Aquesta funció comproba que el nombre de trials introduits per el jugador per a l'edició compleix amb els paràmetres
     *  establerts per poder crear una nova edició
     * @param numberTrials el nombre de trials que es volen afegir a la edició
     * @return retorna un booleà si el valor es vàlid o no
     */
    public boolean checkNumberTrials(int numberTrials) {
        boolean validNumberTrials = false;
        if (numberTrials < 3 || numberTrials > 12) {
            System.out.println("\nEnter a valid number of trials!");
        } else {
            validNumberTrials = true;
        }
        return validNumberTrials;
    }

    /**
     * Aquesta funció retorna un String que es troba compost d'un contador i el nombre total de trials per poder-ho
     *  passar-ho a la view (si no es fa aixó no ho podriem passar a la view)
     * @param i contador sobre el nombre actual de trial que es vol afegir a la edició
     * @param numTrials el nombre total de trials que s'afegiran a la edició
     * @return retorna el missatge que s'ha de passar a la view
     */
    public String createMessagePickEditionTrials(int i, int numTrials) {
        String message = "Pick a trial (" + i + "/" + numTrials + "): ";

        return message;
    }

    /**
     * Aquest procediment comproba si existeix un fitxer sobre les edicions (ho fa mitjançant un parametre que li indica
     *  si el fitxer es JSON o CSV); en cas afirmatiu només escriu al fitxer l'arrayList, si no existeix primer crear el
     *  fitxer en el format requerit
     * @param optionFaction opció per escollir el format del fitxer a escriure
     * @throws IOException
     */
    public void writeEditions(String optionFaction) throws IOException {
        if (optionFaction.equals("I")) {
            if (!checkFile(editionDAO.getPathEditionCSV())) {
                File editionFileCSV = new File(editionDAO.getPathEditionCSV());
                editionFileCSV.createNewFile();
                editionDAO.editionsWriteCSV(editionArrayList);
            } else {
                editionDAO.editionsWriteCSV(editionArrayList);
            }
        } else {
            if (!checkFile(editionDAO.getPathEditionJSON())) {
                File editionFileJSON = new File(editionDAO.getPathEditionJSON());
                editionFileJSON.createNewFile();
                editionDAO.editionsWriteJson(editionArrayList);
            } else {
                editionDAO.editionsWriteJson(editionArrayList);
            }
        }
    }

    /**
     * Aquest procediment afegeix una edició que s'ha creat/duplicat al arrayList d'edicions
     * @param edition Edició que es guardarà en el arrayList
     */
    public void addEditionToArrayList(Edition edition) {
        editionArrayList.add(edition);
    }

    /**
     * Aquesta funció comproba si existeix el fitxer que es desitja per poder llegir/guardar edicions
     * @param filePathString el path al fitxer que es vol comprobar
     * @return retorna un booleà sobre si el fitxer existeix o no
     */
    public boolean checkFile(String filePathString) {
        boolean fileFound = false;
        File f = new File(filePathString);
        if (f.exists() && !f.isDirectory()) {
            fileFound = true;
        }
        return fileFound;
    }

    /**
     * Aquest procediment crida a la funció per comprobar si existeix el fitxer JSON que es vol manipular, si no existeix el
     *  crea de nou; si ja existeix crida a la funció que permet llegir els continguts del fitxer JSON
     * @throws IOException
     */
    public void readEditionsJSON() throws IOException {
        boolean fileFound;
        fileFound = checkFile(editionDAO.getPathEditionJSON());

        if (!fileFound) {
            File editionFileJSON = new File(editionDAO.getPathEditionJSON());
            editionFileJSON.createNewFile();
            System.out.println("creando Editions JSON");
        } else {
            BufferedReader readerDT = new BufferedReader(new FileReader(editionDAO.getPathEditionJSON()));
            if (readerDT.readLine() != null) {
                editionArrayList = editionDAO.readJSONEditions();
            }
        }
    }

    /**
     * Aquesta funció retorna el tamany de l'arrayList de les edicions
     * @return editionArrayList.size()
     */
    public int getSizeArrayEditions() {
        return editionArrayList.size();
    }

    /**
     * Aquest procediment crea una nova edició amb el nou any i el nou nombre de jugadors juntament amb la resta d'informació
     *  que es vol agafar d'una altra edició que ja existeix; s'afegeix a l'arrayList d'edicions i es guarda en el fitxer
     *  que es destiji
     * @param year el nou any de l'edició duplicada
     * @param numPlayers el nou nombre de jugadors de l'edició duplicada
     * @param option l'index de posició de l'edició que es vol duplicar en l'arrayList d'edicions
     * @param optionFaction tipus de fitxer en el que es vol guardar l'informació
     * @throws IOException
     */
    public void duplicateEdition(int year, int numPlayers, int option, String optionFaction) throws IOException {

        Edition edition = new Edition(year, numPlayers, editionArrayList.get(option - 1).getNumTrials(),
                editionArrayList.get(option - 1).getStringArrayList());

        editionArrayList.add(edition);
        editionArrayList.get(editionArrayList.size() - 1).setYear(year);
        editionArrayList.get(editionArrayList.size() - 1).setNumberPlayers(numPlayers);

        if (Objects.equals(optionFaction, "I")) {
            editionDAO.editionsWriteCSV(editionArrayList);
        } else {
            editionDAO.editionsWriteJson(editionArrayList);
        }
    }

    /**
     * Aquesta funció elimina una edició que ja existeix, tant de l'arrayList com del fitxer
     * @param option índex de l'edició en l'arrayList
     * @param confirmationYear paràmetre que ha introduit l'usuari per confirmar que vol esborrar l'edició
     * @param optionFaction tipus de fitxer on vol esborrar l'edició
     * @return retorna un booleà indicant si s'ha pogut esborrar l'edició o no
     * @throws IOException
     */
    public boolean deleteEdition(int option, int confirmationYear, String optionFaction) throws IOException {
        if (editionArrayList.get(option - 1).getYear() == confirmationYear) {
            editionArrayList.remove(option - 1);

            if (Objects.equals(optionFaction, "I")) {
                editionDAO.editionsWriteCSV(editionArrayList);
            } else {
                editionDAO.editionsWriteJson(editionArrayList);
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Aquest procediment crida a la funció per comprobar si existeix el fitxer CSV que es vol manipular, si no existeix el
     *  crea de nou; si ja existeix crida a la funció que permet llegir els continguts del fitxer CSV
     * @throws IOException
     */
    public void readEditionsCSV() throws IOException {
        boolean fileFound;

        fileFound = checkFile(editionDAO.getPathEditionCSV());

        if (!fileFound) {
            File editionFileCSV = new File(editionDAO.getPathEditionCSV());
            editionFileCSV.createNewFile();
        } else {
            BufferedReader readerDT = new BufferedReader(new FileReader(editionDAO.getPathEditionCSV()));
            if (readerDT.readLine() != null) {
                editionDAO.editionReadCSV(editionArrayList);
            }
        }
    }

    /**
     * Aquesta funció comproba si ja existeix una edició per a l'any actual (2022)
     * @return retorna un booleà sobre si existeix una edició per aquest any
     */
    public boolean checkActualEdition() {

        boolean editionFound = false;
        int actualYear = 2022;

        for (int i = 0; i < editionArrayList.size(); i++) {
            if (editionArrayList.get(i).getYear() == actualYear) {
                editionFound = true;
            }
        }
        return editionFound;
    }

    /**
     * Aquesta funció comproba el nombre de jugadors en l'edició actual
     * @return retorna el nombre de jugadors de l'edició
     */
    public int getNumPlayers() {

        int numPlayers = 0;
        int actualYear = 2022;

        for (int i = 0; i < editionArrayList.size(); i++) {
            if (editionArrayList.get(i).getYear() == actualYear) {
                return editionArrayList.get(i).getNumPlayers();
            }
        }

        return numPlayers;

    }

    /**
     * Aquesta funció retorna el nom de totes les trials d'aquest any
     * @return retorna un arrayList amb el nom de totes les Trials de l'edició
     */
    public ArrayList<String> getTrialsName() {
        int actualYear = 2022;

        for (int i = 0; i < editionArrayList.size(); i++) {
            if (editionArrayList.get(i).getYear() == actualYear) {
                return editionArrayList.get(i).getStringArrayList();
            }
        }

        return null;
    }
}
