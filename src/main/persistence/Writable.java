package persistence;

import org.json.JSONObject;

// marks a class as JSON writable
// from JsonSerializationDemo project
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
