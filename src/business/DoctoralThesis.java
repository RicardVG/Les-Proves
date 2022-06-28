package business;

import java.util.ArrayList;

public class DoctoralThesis extends Trial {
    private String thesisField;
    private int defenseDifficulty;

    public DoctoralThesis (String trialName, String thesisField, int defenseDifficulty) {
        super (trialName);
        this.thesisField = thesisField;
        this.defenseDifficulty = defenseDifficulty;
    }

    public DoctoralThesis(ArrayList<String> stringArrayList) {
        super(stringArrayList.get(0));
        this.thesisField = stringArrayList.get(1);
        this.defenseDifficulty = Integer.parseInt(stringArrayList.get(2));
    }

    public String getThesisField () {
        return thesisField;
    }

    public int getDefenseDifficulty () {
        return defenseDifficulty;
    }

    public String writeCSV() {
        return super.trialName + "," + thesisField + "," + defenseDifficulty + "\n";
    }
}
