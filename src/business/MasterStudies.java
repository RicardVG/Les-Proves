package business;

public class MasterStudies extends Trial {

    private int creditProbability;
    private String masterName;
    private int masterECTSNumber;

    public MasterStudies(String trialName, String masterName, int masterECTSNumber, int creditProbability) {
        super(trialName);
        this.masterName = masterName;
        this.masterECTSNumber = masterECTSNumber;
        this.creditProbability = creditProbability;
    }

    /**
     * Aquesta funció retorna el nom del master
     * @return masterName
     */
    public String getMasterName() {
        return masterName;
    }

    /**
     * Aquesta funció retorna la probabilitat d'aprobar 1 credit
     * @return creditProbability
     */
    public int getCreditProbability() {
        return creditProbability;
    }

    /**
     * Aquesta funció retorna el nombre de ECTS que disposa el master
     * @return masterECTSNumber
     */
    public int getMasterECTSNumber() {
        return masterECTSNumber;
    }

    /**
     * Aquesta funció retorna el nom del trial
     * @return trialName
     */
    public String getTrialName() {
        return super.trialName;
    }

    /**
     * Aquesta funció retorna les dades del MasterStudies en el format correcte per poder-ho guardar en un fitxer CSV
     * @return retorna les dades de la trial en format CSV
     */
    public String writeCSV() {
        return super.trialName + "," + masterName + "," + masterECTSNumber + "," + creditProbability + "\n";
    }
}
