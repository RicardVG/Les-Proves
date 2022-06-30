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

    /**
     * Aquest procediment posa el status a "Master" i reseteja el score del jugador
     */
    public void setMaster() {
        this.status = "Master";
        resetScore();
    }

    /**
     * Aquest procediment posa el status a "Doctor" i reseteja el score del jugador
     */
    public void setDoctor() {
        this.status = "Doctor";
        resetScore();
    }

    /**
     * Aquesta funció retorna el nom del jugador
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Aquesta funció retorna la puntuació del jugador
     * @return score
     */
    public int getScore() {
        return score;
    }

    /**
     * Aquesta funció indica quina es la puntuació del jugador
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Aquest procediment suma puntuació al jugador al fer correcte una trial
     * @param score
     */
    public void addScore(int score) {
        if (this.status.equals("Doctor")) {
            this.score += score * 2;
        } else {
            this.score += score;
        }
    }

    /**
     * Aquest procediment resta puntuació al jugador al equivocarse en una trial
     * @param score
     */
    public void subtractScore(int score) {
        if (status.equals("Master") || status.equals("Doctor")) {
            this.score -= score / 2;
        } else {
            this.score -= score;
        }
    }

    /**
     * Aquest procediment torna a posar la puntuació del jugador a 5
     */
    public void resetScore() {
        this.score = 5;
    }

    /**
     * Aquest procediment comproba l'estatus del jugador i la seva puntuació, si es superior a 10, el jugador puja d'estatus
     */
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

    /**
     * Aquesta funció otorga al jugador un nou estatus al obtenir la puntuació suficient
     */
    private void updateStatus() {
        if (status.equals("Enginyer")) {
            System.out.println(this.name + " is now a Master (with " + this.score + " PI)" + "\n");
            setMaster();
        }
    }

    /**
     * Aquest procediment executa la trial de tipus PaperPublication
     * @param paper
     */
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

    /**
     * Aquest procediment executa la trial de tipus MasterStudies
     * @param master
     */
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

    /**
     * Aquesta funció executa el trial de tipus BudgetRequest
     * @param budget
     * @param totalScore
     * @return retorna si el trial ha sigut un éxit o no
     */
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

    /**
     * Aquest procediment executa la trial de tipus DoctoralThesis
     * @param doctoral
     */
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

    /**
     * Aquest procediment mostra el guanyador de la trial BudgetRequest
     */
    public void winBudget() {
        System.out.print("\n" + name + ", Phd. " + "PI count: " + this.score + "\n");
    }
}
