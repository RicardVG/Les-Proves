package persistance;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;

import business.Trial;
import com.google.gson.*;


public class TrialDAO {

    public static void writejsonTrial(ArrayList<Trial> arraylistTrials) throws IOException {
        try (Writer writer = new FileWriter("jsonFiles/paperPublication.json")) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(arraylistTrials,writer);
        }
    }
}
