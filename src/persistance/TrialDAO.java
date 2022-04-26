package persistance;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import business.Trial;
import com.google.gson.*;


public class TrialDAO {

    public void writejsonTrial(ArrayList<Trial> arraylistTrials, String path) throws IOException {
        try (Writer writer = new FileWriter(path)) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(arraylistTrials,writer);
        }catch(FileNotFoundException fileNotFoundException){
            fileNotFoundException.printStackTrace();
        }
    }

    public ArrayList<Trial> readjsonTrial(ArrayList<Trial> arraylistTrials, String path) throws FileNotFoundException {
        Gson gson = new Gson();
        Collections.addAll(arraylistTrials, gson.fromJson(new FileReader(path), Trial[].class));
        return arraylistTrials;
    }

    public boolean checkFile(String filePathString){
        boolean fileFound = false;
        File f = new File(filePathString);
        if(f.exists() && !f.isDirectory()) {
            fileFound = true;
        }
        return fileFound;
    }

}
