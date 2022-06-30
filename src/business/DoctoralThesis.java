package business;

public class DoctoralThesis extends Trial {
    private String thesisField;
    private int defenseDifficulty;

    public DoctoralThesis(String trialName, String thesisField, int defenseDifficulty) {
        super(trialName);
        this.thesisField = thesisField;
        this.defenseDifficulty = defenseDifficulty;
    }

    /**
     * Aquesta funció retorna el l'àrea de treball de la tesis doctoral
     * @return thesisField
     */
    public String getThesisField() {
        return thesisField;
    }

    /**
     * Aquesta funció retorna la dificultat de la defensa de la tesis doctoral
     * @return defenseDifficulty
     */
    public int getDefenseDifficulty() {
        return defenseDifficulty;
    }

    /**
     * Aquesta funció retorna el nom de la trial
     * @return trialName
     */
    public String getTrialName() {
        return super.trialName;
    }

    /**
     * Aquesta funció retorna les dades del DoctoralThesis en el format correcte per poder-ho guardar en un fitxer CSV
     * @return retorna les dades de la trial en format CSV
     */
    public String writeCSV() {
        return super.trialName + "," + thesisField + "," + defenseDifficulty + "\n";
    }
}
