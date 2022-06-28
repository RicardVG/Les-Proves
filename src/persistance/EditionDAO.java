package persistance;

import business.Edition;
import business.PaperPublication;
import com.google.gson.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class EditionDAO {

    private final String pathEditionsJSON="editions/editions.json";
    private final String pathEditionsCSV="editions/editions.csv";



    public void editionsWriteJson(ArrayList<Edition> editionArrayList) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter fileWriter = new FileWriter(pathEditionsJSON);

        fileWriter.write(gson.toJson(editionArrayList));
        fileWriter.close();

    }

    public void editionReadCSV(ArrayList<Edition> editionArrayList){
        String line = "";  
        String splitBy = ",";  
        ArrayList<String> stringArrayList=new ArrayList<String>();

        try {
            Scanner scanner = new Scanner(new File(pathEditionsCSV));
            scanner.useDelimiter("\n");   //sets the delimiter pattern

            while (scanner.hasNext()) {
                line = scanner.next();
                String[] lineArray = line.split(splitBy);
                if(lineArray.length>1){
                    for (int i = 0; i < lineArray.length; i++) {
                        if(i>2){
                            stringArrayList.add(lineArray[i]);
                        }
                    }
                    editionArrayList.add(new Edition(Integer.parseInt(lineArray[0]), Integer.parseInt(lineArray[1]), Integer.parseInt(lineArray[2]), stringArrayList));
                }
            }


            scanner.close();  //closes the scanner
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void editionsWriteCSV(ArrayList<Edition> editionArrayList) {

        try {
            FileWriter writer = new FileWriter(pathEditionsCSV);

           for (Edition edition : editionArrayList) {
                writer.write(edition.writeCSV());
            }
            writer.close();

        } catch (IOException ex) {
            ex.printStackTrace();
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
