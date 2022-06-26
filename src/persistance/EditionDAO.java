package persistance;

import business.Edition;
import com.google.gson.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class EditionDAO {

    private final String pathEditionsJSON="editions/editions.json";
    private final String pathEditionsCSV="editions/editions.csv";
    private ArrayList<Edition> editionArrayList;


    public void editionsWriteJson(ArrayList<Edition> editionArrayList) throws IOException {


        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        JSONObject general = new JSONObject();
        JSONObject object1 = new JSONObject();
        JSONArray jsonArray = new JSONArray();


        int i;
        for (i = 0; i < editionArrayList.size(); i++){
            object1.put("yearEdition",editionArrayList.get(i).getYear());
            object1.put("numPlayers",editionArrayList.get(i).getNumPlayers());
            object1.put("numTrials",editionArrayList.get(i).getNumTrials());
            object1.put("trials",editionArrayList.get(i).getStringArrayList());
            jsonArray.addAll(Arrays.asList(object1));
        }

        general.put("editions",jsonArray);
        FileWriter fileWriter = new FileWriter(pathEditionsJSON);
        fileWriter.write(gson.toJson(general));
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

    public ArrayList<Edition> readJSONEditions() {
        try {
            String json = Files.readString(Paths.get(pathEditionsJSON));
            JsonElement element = JsonParser.parseString(json);
            JsonObject object = element.getAsJsonObject();

            System.out.println("object= "+object);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return editionArrayList;
    }
}
