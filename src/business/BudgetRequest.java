package business;

public class BudgetRequest extends Trial {

    private String entityName;
    private int budgetAmount;

    public BudgetRequest(String trialName, String entityName, int budgetAmount) {
        super(trialName);
        this.entityName = entityName;
        this.budgetAmount = budgetAmount;
    }
    
    public String getEntityName () {
        return entityName;
    }
    
    public int getBudgetAmount () {
        return budgetAmount;
    }

}
