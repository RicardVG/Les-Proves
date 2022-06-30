package business;

import persistance.TrialDAO;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class TrialManager {

    private TrialDAO trialDAO;
    private ArrayList<PaperPublication> paperPublicationArrayList = new ArrayList<PaperPublication>();
    private ArrayList<DoctoralThesis> doctoralThesisArrayList = new ArrayList<DoctoralThesis>();
    private ArrayList<MasterStudies> masterStudiesArrayList = new ArrayList<MasterStudies>();
    private ArrayList<BudgetRequest> budgetRequestArrayList = new ArrayList<BudgetRequest>();
    private ArrayList<Trial> arraylistTrials = new ArrayList<Trial>();

    public TrialManager(TrialDAO trialDAO) {
        this.trialDAO = trialDAO;
    }

    /**
     * Afegeix una nova prova de tipus PaperPublication a l'arrayList que conté tots els PaperPublications
     * @param paperPublication prova de tipus PaperPublication que s'ha d'afegir al arrayList
     */
    public void addtoPaperPublicationArrayList(PaperPublication paperPublication) {
        paperPublicationArrayList.add(paperPublication);
    }

    /**
     * Afegeix una nova prova de tipus DoctoralThesis a l'arrayList que conté tots els DoctoralThesis
     * @param doctoralThesis prova de tipus DoctoralThesis que s'ha d'afegir al arrayList
     */
    public void addDoctoralThesisArrayList(DoctoralThesis doctoralThesis) {
        doctoralThesisArrayList.add(doctoralThesis);
    }

    /**
     * Afegeix una nova prova de tipus MasterStudies a l'arrayList que conté tots els MasterStudies
     * @param masterStudies prova de tipus MasterStudies que s'ha d'afegir al arrayList
     */
    public void addMasterStudiesArrayList(MasterStudies masterStudies) {
        masterStudiesArrayList.add(masterStudies);
    }

    /**
     * Afegeix una nova prova de tipus BudgetRequest a l'arrayList que conté tots els BudgetRequest
     * @param budgetRequest prova de tipus BudgetRequesst que s'ha d'afegir al arrayList
     */
    public void addBudgetRequestArrayList(BudgetRequest budgetRequest) {
        budgetRequestArrayList.add(budgetRequest);
    }

    /**
     * Aquesta funció s'encarrega de comprobar si existeix el fitxer en el path que es demana
     * @param filePathString String que conté el path del fitxer a consultar
     * @return retorna un booleà que indica si el fitxer demanat existeix en la ubicació demanada
     */
    public boolean checkFile(String filePathString) {
        boolean fileFound = false;
        File f = new File(filePathString);
        if (f.exists() && !f.isDirectory()) {
            fileFound = true;
        }
        return fileFound;
    }

    /**
     * Aquesta funció s'encarrega de dues coses, primer de tot que existeixin els fitxers de tipus JSON de les diferentes proves,
     *  en el cas de que no existeixi, en crea un amb el nom i l'extensió que ahuria de tenir per tal de poder ser llegit;
     *  després, en el cas de que existeixi el fitxer en questió, llegeix el fitxer en questió per omplenar els arrayList pertinents al fitxer.
     * @throws IOException
     */
    public void trialsReadJson() throws IOException {
        boolean fileFound;
        fileFound = checkFile(trialDAO.getPathPaperPublicationJSON());

        if (!fileFound) {
            File paperPublicationFile = new File(trialDAO.getPathPaperPublicationJSON());
            paperPublicationFile.createNewFile();
        } else {
            BufferedReader readerDT = new BufferedReader(new FileReader(trialDAO.getPathPaperPublicationJSON()));
            if (readerDT.readLine() != null) {
                paperPublicationArrayList = trialDAO.readjsonTrialPP();
            }
        }

        fileFound = checkFile(trialDAO.getPathMasterStudiesJSON());
        if (!fileFound) {
            File paperPublicationFile = new File(trialDAO.getPathMasterStudiesJSON());
            paperPublicationFile.createNewFile();
        } else {
            BufferedReader readerDT = new BufferedReader(new FileReader(trialDAO.getPathMasterStudiesJSON()));
            if (readerDT.readLine() != null) {
                masterStudiesArrayList = trialDAO.readjsonTrialMS();
            }
        }
        fileFound = checkFile(trialDAO.getPathBudgetRequestJSON());

        if (!fileFound) {
            File paperPublicationFile = new File(trialDAO.getPathBudgetRequestJSON());
            paperPublicationFile.createNewFile();
        } else {
            BufferedReader readerDT = new BufferedReader(new FileReader(trialDAO.getPathBudgetRequestJSON()));
            if (readerDT.readLine() != null) {
                budgetRequestArrayList = trialDAO.readjsonTrialBR();
            }
        }
        fileFound = checkFile(trialDAO.getPathDoctoralThesisJSON());

        if (!fileFound) {
            File paperPublicationFile = new File(trialDAO.getPathDoctoralThesisJSON());
            paperPublicationFile.createNewFile();
        } else {
            BufferedReader readerDT = new BufferedReader(new FileReader(trialDAO.getPathDoctoralThesisJSON()));
            if (readerDT.readLine() != null) {
                doctoralThesisArrayList = trialDAO.readjsonTrialDT();
            }
        }
    }

    /**
     * Aquesta funció s'encarrega de dues coses, primer de tot que existeixin els fitxers de tipus CSV de les diferentes proves,
     *  en el cas de que no existeixi, en crea un amb el nom i l'extensió que ahuria de tenir per tal de poder ser llegit;
     *  després, en el cas de que existeixi el fitxer en questió, llegeix el fitxer en questió per omplenar els arrayList pertinents al fitxer.
     * @throws IOException
     */
    public void trialsReadCSV() throws IOException {
        boolean fileFound;

        fileFound = checkFile(trialDAO.getPathPaperPublicationCSV());

        if (!fileFound) {
            File paperPublicationFile = new File(trialDAO.getPathPaperPublicationCSV());
            paperPublicationFile.createNewFile();
        } else {
            BufferedReader readerDT = new BufferedReader(new FileReader(trialDAO.getPathPaperPublicationCSV()));
            if (readerDT.readLine() != null) {
                trialDAO.readCSVTrialPP(paperPublicationArrayList);
            }
        }

        fileFound = checkFile(trialDAO.getPathMasterStudiesCSV());

        if (!fileFound) {
            File masterStudiesFile = new File(trialDAO.getPathMasterStudiesCSV());
            masterStudiesFile.createNewFile();
        } else {
            BufferedReader readerDT = new BufferedReader(new FileReader(trialDAO.getPathMasterStudiesCSV()));
            if (readerDT.readLine() != null) {
                trialDAO.readCSVTrialMS(masterStudiesArrayList);
            }
        }

        fileFound = checkFile(trialDAO.getPathBudgetRequestCSV());

        if (!fileFound) {
            File budgetRequestFile = new File(trialDAO.getPathBudgetRequestCSV());
            budgetRequestFile.createNewFile();
        } else {
            BufferedReader readerDT = new BufferedReader(new FileReader(trialDAO.getPathBudgetRequestCSV()));
            if (readerDT.readLine() != null) {
                trialDAO.readCSVTrialBR(budgetRequestArrayList);
            }
        }

        fileFound = checkFile(trialDAO.getPathDoctoralThesisCSV());

        if (!fileFound) {
            File doctoralThesisFile = new File(trialDAO.getPathDoctoralThesisCSV());
            doctoralThesisFile.createNewFile();
        } else {
            BufferedReader readerDT = new BufferedReader(new FileReader(trialDAO.getPathDoctoralThesisCSV()));
            if (readerDT.readLine() != null) {
                trialDAO.readCSVTrialDT(doctoralThesisArrayList);
            }
        }

    }

    /**
     * Aquest procediment es dedica a escriure en el fitxer relatiu a PaperPublication, rep una variable que indica si el fitxer
     *  en questió es de tipus JSON o CSV; en cas de que el fitxer no existeixi, el crea de nou
     * @param optionFaction variable que rep la funció per poder escollir si el fitxer es CSV o JSON
     * @throws IOException
     */
    public void writeTrialPaperPublication(String optionFaction) throws IOException {
        if (optionFaction.equals("I")) {
            if (!checkFile(trialDAO.getPathPaperPublicationCSV())) {
                File paperPublicationFileCSV = new File(trialDAO.getPathPaperPublicationCSV());
                paperPublicationFileCSV.createNewFile();
            } else {
                trialDAO.writePaperPublicationCSV(paperPublicationArrayList);
            }
        } else {
            if (!checkFile(trialDAO.getPathPaperPublicationJSON())) {
                File paperPublicationFile = new File(trialDAO.getPathPaperPublicationJSON());
                paperPublicationFile.createNewFile();
            } else {
                trialDAO.writePaperPublicationJSON(paperPublicationArrayList);
            }
        }
    }

    /**
     * Aquest procediment es dedica a escriure en el fitxer relatiu a MasterStudies, rep una variable que indica si el fitxer
     *  en questió es de tipus JSON o CSV; en cas de que el fitxer no existeixi, el crea de nou
     * @param optionFaction variable que rep la funció per poder escollir si el fitxer es CSV o JSON
     * @throws IOException
     */
    public void writeTrialMasterStudies(String optionFaction) throws IOException {
        if (optionFaction.equals("I")) {
            if (!checkFile(trialDAO.getPathMasterStudiesCSV())) {
                File masterStudiesFileCSV = new File(trialDAO.getPathMasterStudiesCSV());
                masterStudiesFileCSV.createNewFile();
            } else {
                trialDAO.writeMasterStudiesCSV(masterStudiesArrayList);
            }
        } else {
            if (!checkFile(trialDAO.getPathMasterStudiesJSON())) {
                File paperPublicationFile = new File(trialDAO.getPathMasterStudiesJSON());
                paperPublicationFile.createNewFile();
            } else {
                trialDAO.writeMasterStudiesJSON(masterStudiesArrayList);
            }
        }
    }

    /**
     * Aquest procediment es dedica a escriure en el fitxer relatiu a DoctoralThesis, rep una variable que indica si el fitxer
     *  en questió es de tipus JSON o CSV; en cas de que el fitxer no existeixi, el crea de nou
     * @param optionFaction variable que rep la funció per poder escollir si el fitxer es CSV o JSON
     * @throws IOException
     */
    public void writeTrialDoctoralThesis(String optionFaction) throws IOException {
        if (optionFaction.equals("I")) {
            if (!checkFile(trialDAO.getPathDoctoralThesisCSV())) {
                File doctoralThesisFileCSV = new File(trialDAO.getPathDoctoralThesisCSV());
                doctoralThesisFileCSV.createNewFile();
            } else {
                trialDAO.writeDoctoralThesisCSV(doctoralThesisArrayList);
            }
        } else {
            if (!checkFile(trialDAO.getPathDoctoralThesisJSON())) {
                File budgetRequestFile = new File(trialDAO.getPathDoctoralThesisJSON());
                budgetRequestFile.createNewFile();
            } else {
                trialDAO.writeDoctoralThesisJSON(doctoralThesisArrayList);
            }
        }
    }

    /**
     * Aquest procediment es dedica a escriure en el fitxer relatiu a BudgetRequest, rep una variable que indica si el fitxer
     *  en questió es de tipus JSON o CSV; en cas de que el fitxer no existeixi, el crea de nou
     * @param optionFaction variable que rep la funció per poder escollir si el fitxer es CSV o JSON
     * @throws IOException
     */
    public void writeTrialBudgetRequest(String optionFaction) throws IOException {
        if (optionFaction.equals("I")) {
            if (!checkFile(trialDAO.getPathBudgetRequestCSV())) {
                File budgetRequestFileCSV = new File(trialDAO.getPathBudgetRequestCSV());
                budgetRequestFileCSV.createNewFile();
            } else {
                trialDAO.writeBudgetRequestCSV(budgetRequestArrayList);
            }
        } else {
            if (!checkFile(trialDAO.getPathBudgetRequestJSON())) {
                File budgetRequestFile = new File(trialDAO.getPathBudgetRequestJSON());
                budgetRequestFile.createNewFile();
            } else {
                trialDAO.writeBudgetRequestJSON(budgetRequestArrayList);
            }
        }
    }

    /**
     * Aquesta funció retorna el tamany total de l'array de trials
     * @return el tamany total de l'array de trials
     */
    public int getSizeArrayTrials() {
        return paperPublicationArrayList.size() + masterStudiesArrayList.size() + budgetRequestArrayList.size()
                + doctoralThesisArrayList.size();
    }

    /**
     * Aquesta funció agrupa totes les arrayLists dels diferents tipus de trials en una mateixa arrayList general; primer de tot s'esborra el contingut d'aquesta
     * @return arrayListTrials retorna l'arrayList general
     */
    public ArrayList<Trial> getAllArrayLists() {
        arraylistTrials.removeAll(arraylistTrials);

        arraylistTrials.addAll(paperPublicationArrayList);
        arraylistTrials.addAll(masterStudiesArrayList);
        arraylistTrials.addAll(budgetRequestArrayList);
        arraylistTrials.addAll(doctoralThesisArrayList);

        return arraylistTrials;
    }

    /**
     * Retorna l'arrayList de PaperPublication
     * @return paperPublicationArrayList
     */
    public ArrayList<PaperPublication> getPaperPublicationArrayList() {
        return paperPublicationArrayList;
    }

    /**
     * Retorna l'arrayList de DoctoralThesis
     * @return doctoralThesisArrayList
     */
    public ArrayList<DoctoralThesis> getDoctoralThesisArrayList() {
        return doctoralThesisArrayList;
    }

    /**
     * Retorna l'arrayList de MasterStudies
     * @return masterStudiesArrayList
     */
    public ArrayList<MasterStudies> getMasterStudiesArrayList() {
        return masterStudiesArrayList;
    }

    /**
     * Retorna l'arrayList de BudgetRequest
     * @return budgetRequestArrayList
     */
    public ArrayList<BudgetRequest> getBudgetRequestArrayList() {
        return budgetRequestArrayList;
    }

    /**
     * Retorna el tamany combinat dels arrayLists de PaperPublication i MasterStudies
     * @return retorna un int
     */
    public int getSizePPMS() {
        return paperPublicationArrayList.size() + masterStudiesArrayList.size();
    }

    /**
     * Retorna el tamany combinat dels arrayLists de PaperPublication, MasterStudies i BudgetRequest
     * @return retorna un int
     */
    public int getSizePPMSBR() {
        return paperPublicationArrayList.size() + masterStudiesArrayList.size() + budgetRequestArrayList.size();
    }


    /**
     * Aquest procediment s'encarrega d'esborrar una trial en específic; comparant les trials que hi han dins de cada arrayList
     *  amb el nom de la trial que es demana tant a l'arrayList com al fitxer corresponent amb la opció especificada
     * @param name nom de la trial que es vol esborrar
     * @param option opció sobre el tipus de fitxer que es vol fer servir
     * @throws IOException
     */
    public void removeTrial(String name, String option) throws IOException {
        int flag = 0;

        for (int i = 0; i < paperPublicationArrayList.size(); i++) {
            if (paperPublicationArrayList.get(i).getName().equals(name)) {
                paperPublicationArrayList.remove(i);
                if (Objects.equals(option, "I")) {
                    trialDAO.writePaperPublicationCSV(paperPublicationArrayList);
                } else {
                    trialDAO.writePaperPublicationJSON(paperPublicationArrayList);
                }
                flag = 1;
                System.out.println("\nThe trial was successfully deleted.\n");
            }
        }
        for (int i = 0; i < masterStudiesArrayList.size() && flag == 0; i++) {
            if (masterStudiesArrayList.get(i).getName().equals(name)) {
                masterStudiesArrayList.remove(i);
                if (Objects.equals(option, "I")) {
                    trialDAO.writeMasterStudiesCSV(masterStudiesArrayList);
                } else {
                    trialDAO.writeMasterStudiesJSON(masterStudiesArrayList);
                }
                flag = 1;
                System.out.println("\nThe trial was successfully deleted.\n");
            }
        }
        for (int i = 0; i < budgetRequestArrayList.size() && flag == 0; i++) {
            if (budgetRequestArrayList.get(i).getName().equals(name)) {
                budgetRequestArrayList.remove(i);
                if (Objects.equals(option, "I")) {
                    trialDAO.writeBudgetRequestCSV(budgetRequestArrayList);
                } else {
                    trialDAO.writeBudgetRequestJSON(budgetRequestArrayList);
                }
                flag = 1;
                System.out.println("\nThe trial was successfully deleted.\n");
            }
        }
        for (int i = 0; i < doctoralThesisArrayList.size() && flag == 0; i++) {
            if (doctoralThesisArrayList.get(i).getName().equals(name)) {
                doctoralThesisArrayList.remove(i);
                if (Objects.equals(option, "I")) {
                    trialDAO.writeDoctoralThesisCSV(doctoralThesisArrayList);
                } else {
                    trialDAO.writeDoctoralThesisJSON(doctoralThesisArrayList);
                }
                flag = 1;
                System.out.println("\nThe trial was successfully deleted.\n");
            }
        }

        if (flag == 0) {
            System.out.println("\nThe input confirmation doesn't match with name of a Trial\n");
        }
    }

    /**
     * Aquesta funció retorna en forma d'String el tipus de la trial
     * @param object String que inidca el tipus d'objecte desitjat
     * @return retorna en String el nom del tipus
     */
    public String getTypeObject(String object) {
        for (int i = 0; i < paperPublicationArrayList.size(); i++) {
            if (paperPublicationArrayList.get(i).getName().equals(object)) {
                return "PaperPublication";
            }
        }
        for (int i = 0; i < masterStudiesArrayList.size(); i++) {
            if (masterStudiesArrayList.get(i).getName().equals(object)) {
                return "MasterStudies";
            }
        }
        for (int i = 0; i < budgetRequestArrayList.size(); i++) {
            if (budgetRequestArrayList.get(i).getName().equals(object)) {
                return "BudgetRequest";
            }
        }
        for (int i = 0; i < doctoralThesisArrayList.size(); i++) {
            if (doctoralThesisArrayList.get(i).getName().equals(object)) {
                return "DoctoralThesis";
            }
        }
        return null;
    }

    /**
     * Aquesta funció es dedica a comprobar que el nom introduit per una trial no existeixi en un altre trial
     * @param trialNameConfirmation nom del trial a buscar
     * @return retorna un booleà sobre si ja existeix el nom o no
     */
    public boolean checkNameTrial(String trialNameConfirmation) {
        for (int i = 0; i < getAllArrayLists().size(); i++) {
            if (trialNameConfirmation.equals(getAllArrayLists().get(i).getName())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Aquesta funció comproba si el Quartile introduit correspon amb el que es demana a l'usuari o no
     * @param journalQuartile el Quartile que l'usuari ha introduit
     * @return un booleà sobre si el que s'ha introduit es correcte o no
     */
    public boolean checkQuartileTrial(String journalQuartile) {
        if (journalQuartile.equals("Q1") || journalQuartile.equals("Q2") || journalQuartile.equals("Q3")
                || journalQuartile.equals("Q4")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Aquesta funció comproba que la probabilitat introduida es troba dins dels paràmetres establerts
     * @param probability la probabilitat introduida per l'usuari
     * @return un booleà sobre si es correcte o no
     */
    public boolean checkProbability(int probability) {
        if (probability < 0 || probability > 100) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Aquesta funció comproba si la suma de la acceptanceProbability i revisionProbability no sigui major a 0
     * @param acceptanceProbability la probabilitat de que un jugador aprovi o no
     * @param revisionProbability la probabilitat de que la trial del jugador vagi a revisió
     * @return un booleà sobre si es correcte o no
     */
    public boolean checkRevisionProbability(int acceptanceProbability, int revisionProbability) {
        if ((acceptanceProbability + revisionProbability) > 100) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Aquesta funció comproba si la suma de les tres probabilitats es exacte a 100 o no
     * @param acceptanceProbability la probabilitat de que un jugador aprovi o no
     * @param revisionProbability la probabilitat de que la trial del jugador vagi a revisió
     * @param rejectionProbability la probabilitat de que un jugador suspengui
     * @return un booleà sobre si es correcte o no
     */
    public boolean checkRejectionProbability(int acceptanceProbability, int revisionProbability,
            int rejectionProbability) {
        if ((acceptanceProbability + revisionProbability + rejectionProbability) != 100) {
            return false;
        } else {
            return true;
        }
    }
}
