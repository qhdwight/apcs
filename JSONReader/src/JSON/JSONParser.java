package JSON;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A class which can parse a json file given a path.
 *
 * <a href="https://www.json.org/">JSON Specifications</a>
 */
public class JSONParser {

    private static final boolean DEBUG = false;

    // Information about regex found at https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html"
    private static final String
            // -? is optional for negative, \\d matches for numbers 0-9, + is it appears once or more
            INTEGER_REGEX = "-?\\d+",
            // Same as above but must have dot separator
            DOUBLE_REGEX = "-?\\d+\\.\\d+",
            WHITE_SPACE_REGEX = "\\s+";

    /**
     * Reads a {@link String} from a given path to a text file and parses it into a {@link JSONObject}.
     *
     * @param pathToFile Path to the text file with the JSON.
     * @return {@link JSONObject} which extends a {@link java.util.HashMap} and contains the properties from the string.
     * @exception FileNotFoundException File was not found.
     * @exception IOException Something went wrong file trying to read from file.
     */
    public static @NotNull JSONObject parseFromFile(@NotNull final String pathToFile) throws IOException {

        final String rawJson = getRawStringFromFile(pathToFile);

        return parse(rawJson);
    }

    /**
     * Parse a {@link String} into it's JSON representation.
     * Uses helper method {@link #getObjectFromJSONValue(String)} to read each value.
     *
     * @param rawJson Raw json string to parse
     * @return {@link JSONObject} which extends a {@link java.util.HashMap} and contains the properties from the string.
     */
    public static @NotNull JSONObject parse(@NotNull final String rawJson) throws IllegalArgumentException {

        final JSONObject jsonObject = new JSONObject();

        // Get substring between first and last brackets
        final String allProperties = rawJson.substring(1, rawJson.length()-1);
        if (DEBUG) System.out.println(String.format("All properties: %s", allProperties));

        /*
         * Split string by commas to separate each name and value.
         * Only commas on the first level are used, since others denote elements in array or another object.
         */
        int level = 0, lastIndex = 0;
        List<String> properties = new ArrayList<>();
        for (int i = 0; i < allProperties.length(); i++) {
            final char charAt = allProperties.charAt(i);
            // Adjust level based on {} (another object) or [] (an array)
            if      (charAt == '{' || charAt == '[') --level;
            else if (charAt == '}' || charAt == ']') ++level;
            // Make sure we are on the first level and separate by the comma
            if (level == 0 && charAt == ',') {
                properties.add(allProperties.substring(lastIndex, i));
                lastIndex = i+1;
            // The last property will be skipped by the above block, so this takes care of that one
            } else if (i == allProperties.length()-1) {
                properties.add(allProperties.substring(lastIndex));
            }
        }
        if (DEBUG) System.out.println(String.format("Properties: %s", properties.toString()));

        /* Iterate over both names and values to find each and add them to the hash map */
        for (final String property : properties) {

            // Split by colon to find name and value.
            // Notice the limit of two to prevent splitting of colons in other objects.
            final String[] both = property.split("[:]", 2);
            if (DEBUG) System.out.println(String.format("Both: %s", Arrays.toString(both)));

            if (both.length == 2)
            {
                String name = both[0];
                // Remove first and last quotations
                name = both[0].substring(1, name.length()-1);

                if (DEBUG) System.out.println(String.format("Property name: %s", name));

                String valueAsString = both[1];
                if (DEBUG) System.out.println(String.format("Property value: %s", valueAsString));

                final Object value = getObjectFromJSONValue(valueAsString);

                if (DEBUG) System.out.println(String.format("Class: %s ", value != null ? value.getClass().toString() : "null"));

                jsonObject.put(name, value);
            }
            else
            {
                throw new IllegalArgumentException();
            }
        }

        return jsonObject;
    }

    /**
     * Tries to parse a {@link String} into what it should be according to the JSON specifications.
     *
     * @param valueAsString The string value of the JSON property
     * @return The object representing it
     */
    private static @Nullable Object getObjectFromJSONValue(@NotNull final String valueAsString) {

        final Object value;
        if (valueAsString.equals("null")) {
            value = null;
        } else if (valueAsString.startsWith("[") && valueAsString.endsWith("]")) {
            // Value is an array, split by commas without brackets
            final String[] entries = valueAsString.substring(1, valueAsString.length()-1).split("[,]");
            // Parse each entry and add it to an array of objects
            final Object[] arr = new Object[entries.length];
            for (int i = 0; i < entries.length; i++) arr[i] = getObjectFromJSONValue(entries[i]);
            value = arr;
        // Backslash because " is a reserved character (for Strings)
        } else if (valueAsString.startsWith("\"") && valueAsString.endsWith("\"")) {
            // Remove quotations, value is string
            value = valueAsString.substring(1, valueAsString.length()-1);
        } else if (valueAsString.startsWith("{") && valueAsString.endsWith("}")) {
            // Must be another object
            value = parse(valueAsString);
        } else if (valueAsString.matches(INTEGER_REGEX)) {
            // Number
            value = Integer.parseInt(valueAsString);
        } else if (valueAsString.matches(DOUBLE_REGEX)) {
            // Double
            value = Double.parseDouble(valueAsString);
        } else {
            value = Boolean.parseBoolean(valueAsString);
        }
        return value;
    }

    /**
     * Read all text from a file.
     * This does not add line separators.
     * It also removes all spaces.
     *
     * @param pathToFile Path to the text file.
     * @return The text contents of the file.
     * @exception FileNotFoundException File was not found.
     * @exception IOException Something went wrong file trying to read from file.
     */
    public static @NotNull String getRawStringFromFile(@NotNull final String pathToFile) throws IOException {

        final File file = new File(pathToFile);

        final BufferedReader reader = new BufferedReader(new FileReader(file));

        final StringBuilder all = new StringBuilder();
        String line;

        // Try to read every line and add it to a string until there is none left (null)
        while ((line = reader.readLine()) != null)
            all.append(line);

        reader.close();

        // Remove white spaces
        return all.toString().replaceAll(WHITE_SPACE_REGEX, "");
    }
}
