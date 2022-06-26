package business;

import java.util.ArrayList;

public class BudgetRequest extends Trial {

    private String entityName;
    private int budgetAmount;

    public BudgetRequest(String trialName, String entityName, int budgetAmount) {
        super(trialName);
        this.entityName = entityName;
        this.budgetAmount = budgetAmount;
    }

    public BudgetRequest(ArrayList<String> stringArrayList) {
        super(stringArrayList.get(0));
        this.entityName = stringArrayList.get(1);
        this.budgetAmount = Integer.parseInt(stringArrayList.get(2));
    }

    public String getEntityName () {
        return entityName;
    }
    
    public int getBudgetAmount () {
        return budgetAmount;
    }

}
