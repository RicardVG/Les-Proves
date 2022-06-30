package business;

public class Player {

    private String name;
    private int score;
    private String status;

    public Player(String name) {
        this.name = name;
        this.score = 5;
        this.status = "Enginyer";
    }

    public void setMaster() {
        this.status = "Master";
        resetScore();
    }

    public void setDoctor() {
        this.status = "Doctor";
        resetScore();
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore(int score) {
        if (this.status.equals("Doctor")) {
            this.score += score * 2;
        } else {
            this.score += score;
        }
    }

    public void subtractScore(int score) {
        if (status.equals("Master") || status.equals("Doctor")) {
            this.score -= score / 2;
        } else {
            this.score -= score;
        }
    }

    public void resetScore() {
        this.score = 5;
    }

    public void checkStatus() {
        if (getScore() >= 10 && this.status.equals("Enginyer")) {
            setMaster();
            System.out.println(this.name + " is now a Master (with " + this.score + " PI)" + "\n");
        }
        if (getScore() >= 10 && this.status.equals("Master")) {
            setDoctor();
            System.out.println(this.name + " is now a Doctor (with " + this.score + " PI)" + "\n");
        }

    }

    private void updateStatus() {
        if (status.equals("Enginyer")) {
            System.out.println(this.name + " is now a Master (with " + this.score + " PI)" + "\n");
            setMaster();
        }
    }

    public void playPaper(PaperPublication paper) {
        int randNum = (int) (Math.random() * 100);
        boolean flag = false;

        System.out.print(this.name + " is submitting...");
        while (!flag) {

            if (randNum >= paper.getAcceptanceProbability()) {
                switch (paper.getJournalQuartile()) {
                    case "Q1":
                        addScore(4);
                        break;
                    case "Q2":
                        addScore(3);
                        break;
                    case "Q3":
                        addScore(2);
                        break;
                    case "Q4":
                        addScore(1);
                        break;
                }
                flag = true;
                System.out.println("Accepted! PI count: " + this.score);

            } else {
                if (randNum >= paper.getRevisionProbability()) {
                    randNum = (int) (Math.random() * 100);
                    System.out.print("Revisions... ");
                } else {
                    switch (paper.getJournalQuartile()) {
                        case "Q1":
                            subtractScore(5);
                            break;
                        case "Q2":
                            subtractScore(4);
                            break;
                        case "Q3":
                            subtractScore(3);
                            break;
                        case "Q4":
                            subtractScore(2);
                            break;
                    }
                    flag = true;
                    System.out.println("Rejected! PI count: " + this.score);

                    if (this.score <= 0) {
                        System.out.println(" - Disqualified!");
                    }
                }
            }
        }
        checkStatus();
    }

    public void playMaster(MasterStudies master) {
        int count = 0;
        int randNum;

        for (int i = 0; i < master.getMasterECTSNumber(); i++) {
            randNum = (int) (Math.random() * 100);
            if (randNum >= master.getCreditProbability()) {
                count++;
            }
        }
        if (count >= (master.getMasterECTSNumber() / 2)) {
            if (status.equals("Enginyer")) {
                updateStatus();
            }
            addScore(3);
            System.out.print("\n" + name + " passed " + count + "/" + master.getMasterECTSNumber()
                    + ". Congrats! PI count: " + this.score);
        } else {
            subtractScore(3);
            System.out.print("\n" + name + " passed " + count + "/" + master.getMasterECTSNumber()
                    + ". Sorry! PI count: " + this.score);
            if (this.score <= 0) {
                System.out.println(" - Disqualified!");
            }
        }
        checkStatus();
    }

    public boolean playBudget(BudgetRequest budget, int totalScore) {

        if (Math.log(budget.getBudgetAmount()) < totalScore) {
            System.out.println("The research group got the budget!\n");
            System.out.print("\n" + name + ", Phd. " + "PI count: " + this.score + "\n");
            return true;
        } else {
            System.out.println("\n" + name + ", was not successfull!" + "PI count: " + this.score);
            return false;
        }
    }

    public void playDoctoral(DoctoralThesis doctoral) {
        int aux = 0;

        for (int i = 0; i < doctoral.getDefenseDifficulty(); i++) {
            aux += (i + 1) * 2 - 1;
        }

        if ((int) Math.sqrt(aux) < getScore()) {
            if (status.equals("Master")) {
                setDoctor();
            }
            addScore(5);
            System.out.print("\n" + name + " was succesfull. Congrats! PI count: " + this.score);

        } else {
            subtractScore(5);
            System.out.print("\n" + name + " was no succesfull. Sorry! PI count: " + this.score);
            if (this.score <= 0) {
                System.out.println(" - Disqualified!");
            }
        }
        checkStatus();
    }
}
