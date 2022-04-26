package business;

import presentation.View;

public class BudgetRequest extends Trial {

    private View view;
    private String entityName;
    private int budgetAmount;

    public BudgetRequest () {
        super();
        addData();
    }


    public void addData () {
        entityName = askForString("Enter the entity's name: ");
        budgetAmount = view.askForOption("Enter the budget amount: ");
    }

    public String getEntityName() {
        return entityName;
    }

    public int getBudgetAmount() {
        return budgetAmount;
    }
}
