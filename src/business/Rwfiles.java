package business;

import persistance.TrialDAO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Rwfiles {

    public static void chooseFormat(String optionFaction) throws IOException {


        if (Objects.equals(optionFaction, "I")){
            trialsWriteCSV();
            editionsWriteCSV();
            System.out.println("Loading data from CSV Files...");
        }else{
            trialsWriteJson();
            editionsWriteJson();
            System.out.println("Loading data from JSON Files...");
        }
    }

    private static void trialsWriteJson() throws IOException {

        Trial trial = new Trial();
        TrialManager trialManager = new TrialManager(trial);

        BufferedReader br = new BufferedReader(new FileReader("jsonFiles/paperPublication.json"));
        if (br.readLine() == null) {
            TrialDAO.writejsonTrial(trialManager.arraylistTrials);
        }else{

        }
    }

    private static void trialsWriteCSV() {
    }

    private static void editionsWriteJson() {
    }

    private static void editionsWriteCSV() {
    }


}
