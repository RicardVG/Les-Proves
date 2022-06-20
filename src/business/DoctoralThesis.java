package business;

public class DoctoralThesis extends Trial {
    private String thesisField;
    private int defenseDifficulty;

    public DoctoralThesis (String trialName, String thesisField, int defenseDifficulty) {
        super (trialName);
        this.thesisField = thesisField;
        this.defenseDifficulty = defenseDifficulty;
    }
}
