package business;

import presentation.View;

public class DoctoralThesis extends Trial {

    private View view;
    private String thesisField;
    private int defenseDifficulty;

    public DoctoralThesis () {
        super();
        addData();
    }

    public void addData () {
        thesisField = askForString("Enter the thesis field of study: ");
        defenseDifficulty = view.askForOption("Enter the defense difficulty: ");
    }

    public String getThesisField() {
        return thesisField;
    }

    public int getDefenseDifficulty() {
        return defenseDifficulty;
    }
}
