package business;

public class Trial {
    protected String trialName;

    public Trial(String trialName) {
        this.trialName = trialName;
    }

    /**
     * Aquesta funció retorna el nom del trial que es vol consultar
     * @return trialName
     */
    public String getName() {
        return trialName;
    }
}
