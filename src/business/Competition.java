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

    public void setNumberPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public int getNumberPlayers() {
        return this.numPlayers;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void setState() {
        this.state++;
    }

    public int getState() {
        return state;
    }

    public void setTrialsName(ArrayList<String> trialsName) {
        this.trialsName = trialsName;
    }

    public String getTrialsName(int index) {
        return trialsName.get(index);
    }

    public int getSizeTrials() {
        return trialsName.size();
    }

    private synchronized void checkScorePlayers() {

        for (Player player : players) {
            if (player.getScore() <= 0) {
                players.remove(player);
                break;
            }
        }

    }

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

    private PaperPublication searchPaper(String title, ArrayList<PaperPublication> papers) {
        for (PaperPublication paper : papers) {
            if (paper.getTrialName().equals(title)) {
                return paper;
            }
        }
        return null;
    }

    private MasterStudies searchMasterStudy(String title, ArrayList<MasterStudies> masterStudies) {
        for (MasterStudies masterStudy : masterStudies) {

            if (masterStudy.getTrialName().equals(title)) {
                return masterStudy;
            }
        }
        return null;
    }

    private BudgetRequest searchBudgetRequest(String title, ArrayList<BudgetRequest> budgetRequests) {
        for (BudgetRequest budgetRequest : budgetRequests) {
            if (budgetRequest.getTrialName().equals(title)) {
                return budgetRequest;
            }
        }
        return null;
    }

    private DoctoralThesis searchDoctoralThesis(String title, ArrayList<DoctoralThesis> doctoralThesis) {
        for (DoctoralThesis doctoralThesi : doctoralThesis) {
            if (doctoralThesi.getTrialName().equals(title)) {
                return doctoralThesi;
            }
        }
        return null;
    }

    public void endCompetition() {
        if (players.size() > 0) {
            System.out.println("\nTHE TRIALS 2022 HAVE ENDED - PLAYERS WON");
        }

        System.out.println("\nShutting down...");
        System.exit(0);
    }
}
