package business;

public class DoctoralThesis extends Trial {
    private String thesisField;
    private int defenseDifficulty;

    public DoctoralThesis (String trialName, String thesisField, int defenseDifficulty) {
        super (trialName);
        this.thesisField = thesisField;
        this.defenseDifficulty = defenseDifficulty;
    }

    public String getThesisField () {
        return thesisField;
    }

    public int getDefenseDifficulty () {
        return defenseDifficulty;
    }

    public String getTrialName () {
        return super.trialName;
    }

    public String writeCSV() {
        return super.trialName + "," + thesisField + "," + defenseDifficulty + "\n";
    }
}
