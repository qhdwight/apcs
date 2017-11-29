package JSON;

import java.util.HashMap;

/**
 * Represents a JSON strucuture in the form of a {@link HashMap}.
 * Since the {@link HashMap.Values} are of the type {@link Object}, the super of everything in Java.
 * Ideally, the type of the {@link Object} should be known so an explicit cast can be used.
 */
public class JSONObject extends HashMap<String, Object> { }
