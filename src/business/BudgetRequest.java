package business;

public class BudgetRequest extends Trial {

    private String entityName;
    private int budgetAmount;

    public BudgetRequest(String trialName, String entityName, int budgetAmount) {
        super(trialName);
        this.entityName = entityName;
        this.budgetAmount = budgetAmount;
    }

    /**
     * Aquesta funció retorna el nom de l'entitat
     * @return entityName
     */
    public String getEntityName() {
        return entityName;
    }

    /**
     * Aquesta funció retorna el pressupost de l'entitat de l'any
     * @return budgetAmount
     */
    public int getBudgetAmount() {
        return budgetAmount;
    }

    /**
     * Aquesta funció retorna el nom de la trial
     * @return trialName
     */
    public String getTrialName() {
        return super.trialName;
    }

    /**
     * Aquesta funció retorna les dades del BudgetRequest en el format correcte per poder-ho guardar en un fitxer CSV
     * @return retorna les dades de la trial en format CSV
     */
    public String writeCSV() {
        return super.trialName + "," + entityName + "," + budgetAmount + "\n";
    }
}
