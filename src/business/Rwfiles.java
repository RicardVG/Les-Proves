package business;

import java.util.Objects;

public class Rwfiles {

    public static void chooseFormat(String optionFaction) {

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

    private static void editionsWriteJson() {
    }

    private static void editionsWriteCSV() {
    }

    private static void trialsWriteJson() {
    }

    private static void trialsWriteCSV() {
    }
}
