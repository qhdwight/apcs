import JSON.JSONObject;
import JSON.JSONParser;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    private final static String JSON_PATH = "res/data.json";

    public static void main(String[] args) {

        try {
            final JSONObject jsonObject = JSONParser.parseFromFile(JSON_PATH);

            // Tests
            System.out.println((int) jsonObject.get("a"));
            System.out.println((String) jsonObject.get("b"));
            System.out.println((double) jsonObject.get("c"));
            System.out.println((boolean) jsonObject.get("d"));
            System.out.println((int) ((Object[]) jsonObject.get("e"))[1]);
            System.out.println(Boolean.toString(jsonObject.get("f") == null));
            System.out.println((double) ((JSONObject) jsonObject.get("g")).get("a"));
            // etc...

        } catch (IllegalArgumentException iae) {
            System.out.println("Error, JSON string is not valid!");
        } catch (FileNotFoundException fnf) {
            System.out.println(String.format("Error, file with path: %s could not be found!", JSON_PATH));
            fnf.printStackTrace();
        } catch (IOException ioe) {
            System.out.println(String.format("Error in reading file with path: %s, do you have the correct privileges?", JSON_PATH));
            ioe.printStackTrace();
        }
    }
}
