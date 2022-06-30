package business;

public class PaperPublication extends Trial {

    private String journalName;
    private String journalQuartile;
    private int acceptanceProbability;
    private int revisionProbability;
    private int rejectionProbability;

    public PaperPublication(String trialName, String journalName, String journalQuartile, int acceptanceProbability,
            int revisionProbability, int rejectionProbability) {
        super(trialName);
        this.journalName = journalName;
        this.journalQuartile = journalQuartile;
        this.acceptanceProbability = acceptanceProbability;
        this.revisionProbability = revisionProbability;
        this.rejectionProbability = rejectionProbability;

    }

    /**
     * Aquesta funció retorna les dades del PaperPublication en el format correcte per poder-ho guardar en un fitxer CSV
     * @return retorna les dades de la trial en format CSV
     */
    public String writeCSV() {
        return super.trialName + "," + journalName + "," + journalQuartile + "," + acceptanceProbability + ","
                + revisionProbability + "," + rejectionProbability + "\n";
    }

    /**
     * Aquesta funció retorna el nom de la trial
     * @return trialName
     */
    public String getTrialName() {
        return super.trialName;
    }

    /**
     * Aquesta funció retorna el nom del journal
     * @return journalName
     */
    public String getJournalName() {
        return this.journalName;
    }

    /**
     * Aquesta funció retorna el Quartile del journal
     * @return journalQuartile
     */
    public String getJournalQuartile() {
        return this.journalQuartile;
    }

    /**
     * Aquesta funció retorna la probabilitat de que la trial sigui acceptada
     * @return acceptanceProbability
     */
    public int getAcceptanceProbability() {
        return acceptanceProbability;
    }

    /**
     * Aquesta funció retorna la probabilitat de que la trial sigui revisada
     * @return revisionProbability
     */
    public int getRevisionProbability() {
        return revisionProbability;
    }

    /**
     * Aquesta funció retorna la probabilitat de que la trial NO sigui acceptada
     * @return rejectionProbability
     */
    public int getRejectionProbability() {
        return rejectionProbability;
    }
}
