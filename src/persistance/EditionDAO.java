package persistance;

import business.Edition;
import business.PaperPublication;
import com.google.gson.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class EditionDAO {

    private final String pathEditionsJSON="editions/editions.json";
    private final String pathEditionsCSV="editions/editions.csv";



    public void editionsWriteJson(ArrayList<Edition> editionArrayList) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter fileWriter = new FileWriter(pathEditionsJSON);

        fileWriter.write(gson.toJson(editionArrayList));
        fileWriter.close();

    }

    public void editionsWriteCSV(ArrayList<Edition> editionArrayList) {
        try {

            FileWriter writer = new FileWriter("editions/editions.csv");
            StringBuilder sb = new StringBuilder();
            int i = 0, k = 0;

            while (i < editionArrayList.size()) {

                if (!editionArrayList.get(i).equals("*")) {
                    sb.append(editionArrayList.get(i));
                    if (k < 2) {
                        sb.append(",");
                    }
                    if (k == 2) {
                        sb.append(",\"");
                    }
                    if (k > 2) {
                        sb.append(",");
                    }
                } else {
                    sb.deleteCharAt(sb.length()-1);  //eliminem lultima coma del string
                    sb.append("\"");
                    sb.append("\n");
                    writer.append(sb.toString());
                    sb.setLength(0);
                    k = -1;
                }
                k++;
                i++;
            }

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getPathEditionJSON() {
        return pathEditionsJSON;
    }

    public String getPathEditionCSV() {
        return pathEditionsCSV;
    }

    public ArrayList<Edition> readJSONEditions() throws IOException {

        ArrayList <Edition> editionArrayList = new ArrayList<>();
        String json = Files.readString(Paths.get(pathEditionsJSON));
        JsonElement element = JsonParser.parseString(json);
        JsonArray jsonArr = element.getAsJsonArray();
        Gson googleJson = new Gson();
        Collections.addAll(editionArrayList, googleJson.fromJson(new FileReader(pathEditionsJSON), Edition[].class));

        return editionArrayList;
    }
}
