package business;

import java.util.ArrayList;

public class Competition {

    private int numPlayers;
    private ArrayList<Player> players;
    private int state;
    private ArrayList<String> trialsName;

    public Competition() {
        this.state = 0;
        this.players = new ArrayList<Player>();
    }

    /**
     * Aquest procediment indica quin es el nombre de jugadors per a la competició
     * @param numPlayers
     */
    public void setNumberPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    /**
     * Aquesta funció retorna nombre de jugadors
     * @return numPlayers
     */
    public int getNumberPlayers() {
        return this.numPlayers;
    }

    /**
     * Aquest procediment afegeix un jugador a la competició
     * @param player
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * Aquest procediment incrementa l'estat de la competició al avançar a la seguent trial
     */
    public void setState() {
        this.state++;
    }

    /**
     * Aquesta funció retorna l'estat de la competició actual
     * @return
     */
    public int getState() {
        return state;
    }

    /**
     * Aquesta funció guarda els noms de les trials de la competició
     * @param trialsName
     */
    public void setTrialsName(ArrayList<String> trialsName) {
        this.trialsName = trialsName;
    }

    /**
     * Aquesta funció retorna els noms de les trials
     * @param index
     * @return
     */
    public String getTrialsName(int index) {
        return trialsName.get(index);
    }

    /**
     * Aquesta funció retorna
     * @return
     */
    public int getSizeTrials() {
        return trialsName.size();
    }

    /**
     * Aquest procediment va comprobant tota l'estona la puntuació dels jugadors, si un arriba a 0 o menys, es desqualificat
     */
    private synchronized void checkScorePlayers() {

        for (Player player : players) {
            if (player.getScore() <= 0) {
                players.remove(player);
                break;
            }
        }

    }

    /**
     * Aquest procediment comença l'execució de la competició
     * @param trialType
     * @param papers
     * @param masterStudies
     * @param budgetRequests
     * @param doctoralThesis
     */
    public void run(String trialType, ArrayList<PaperPublication> papers, ArrayList<MasterStudies> masterStudies,
            ArrayList<BudgetRequest> budgetRequests, ArrayList<DoctoralThesis> doctoralThesis) {
        PaperPublication paper;
        MasterStudies master;
        BudgetRequest budget;
        DoctoralThesis doctoral;
        int aux = 0;

        checkScorePlayers();

        switch (trialType) {
            case "PaperPublication":
                paper = searchPaper(getTrialsName(getState()), papers);
                for (Player player : players) {
                    player.playPaper(paper);
                }
                break;
            case "MasterStudies":
                master = searchMasterStudy(getTrialsName(getState()), masterStudies);
                for (Player player : players) {
                    player.playMaster(master);
                }
                break;
            case "BudgetRequest":
                budget = searchBudgetRequest(getTrialsName(getState()), budgetRequests);
                for (Player player : players) {
                    aux = player.getScore();
                }
                if (players.get(0).playBudget(budget, aux)) {
                    for (Player player : players) {
                        player.addScore(player.getScore() / 2);
                        player.winBudget();
                        player.checkStatus();
                    }
                } else {
                    for (Player player : players) {
                        player.subtractScore(2);
                        player.checkStatus();
                    }
                }
                break;
            case "DoctoralThesis":
                doctoral = searchDoctoralThesis(getTrialsName(getState()), doctoralThesis);
                for (Player player : players) {
                    player.playDoctoral(doctoral);
                }
                break;

        }
        setState();
    }

    /**
     * Aquesta funció busca un PaperPublication mitjançant el nom de la trial
     * @param title
     * @param papers
     * @return si s'ha pogut trobar, es retorna el paperPublication
     */

    private PaperPublication searchPaper(String title, ArrayList<PaperPublication> papers) {
        for (PaperPublication paper : papers) {
            if (paper.getTrialName().equals(title)) {
                return paper;
            }
        }
        return null;
    }

    /**
     * Aquesta funció busca un MasterStudies mitjançant el nom de la trial
     * @param title
     * @param masterStudies
     * @return si s'ha pogut trobar, es retorna el masterStudies
     */
    private MasterStudies searchMasterStudy(String title, ArrayList<MasterStudies> masterStudies) {
        for (MasterStudies masterStudy : masterStudies) {

            if (masterStudy.getTrialName().equals(title)) {
                return masterStudy;
            }
        }
        return null;
    }

    /**
     * Aquesta funció busca una BudgetRequest mitjançant el nom de la trial
     * @param title
     * @param budgetRequests
     * @return si s'ha pogut trobar, es retorna la budgetRequest
     */
    private BudgetRequest searchBudgetRequest(String title, ArrayList<BudgetRequest> budgetRequests) {
        for (BudgetRequest budgetRequest : budgetRequests) {
            if (budgetRequest.getTrialName().equals(title)) {
                return budgetRequest;
            }
        }
        return null;
    }

    /**
     * Aquesta funció busca una DoctoralThesis mitjançant el nom de la trial
     * @param title
     * @param doctoralThesis
     * @return si s'ha pogut trobar, es retorna la doctoralThesis
     */
    private DoctoralThesis searchDoctoralThesis(String title, ArrayList<DoctoralThesis> doctoralThesis) {
        for (DoctoralThesis doctoralThesi : doctoralThesis) {
            if (doctoralThesi.getTrialName().equals(title)) {
                return doctoralThesi;
            }
        }
        return null;
    }

    /**
     * Aquest procediment mostra per pantalla que s'ha finalitzat l'execució i para el programa
     */
    public void endCompetition() {
        if (players.size() > 0) {
            System.out.println("\nTHE TRIALS 2022 HAVE ENDED - PLAYERS WON");
        }

        System.out.println("\nShutting down...");
        System.exit(0);
    }
}
