package com.sjani.java;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

/**
 * Container for various format conversions.
 */
public class Converters {

    private static final String BRANCH = "Branch";
    private static final String STARS = "******";

    /**
     * Converts JSONArray to List of string array
     *
     * @param jsonArray JSON Array
     * @return List of string array
     */
    public List<String[]> JSONArrayToList(JSONArray jsonArray) {
        List<String[]> lines = new ArrayList<>();
        if (jsonArray != null && jsonArray.length() > 0) {
            LinkedHashMap<String, Integer> columns = new LinkedHashMap<>();
            int columnCount = 0;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                HashMap<Integer, String> columnPositionToValueMap = new HashMap<>();
                Set<String> fields = jsonObject.keySet();
                for (String field : fields) {
                    String value = redactString(jsonObject.get(field).toString(), BRANCH);
                    if (!columns.containsKey(field)) {
                        columns.put(field, columnCount++);
                    }
                    columnPositionToValueMap.put(columns.get(field), value);
                }
                int columnIndex = 0;
                List<String> lineList = new ArrayList<>();
                while (columnIndex < columns.size()) {
                    lineList.add(columnPositionToValueMap.getOrDefault(columnIndex, ""));
                    columnIndex++;
                }
                lines.add(lineList.toArray(new String[0]));
            }
            String[] columnNames = columns.keySet().toArray(new String[0]);
            lines.add(0, columnNames);
        }
        return lines;
    }

    /**
     * Redacts specific string in the input String if found otherwise leaves the input string unmodified
     *
     * @param inputString     input string
     * @param stringToReplace Target String to be redacted
     * @return redacted string
     */
    private String redactString(String inputString, String stringToReplace) {
        if (inputString.contains(stringToReplace)) {
            return inputString.replace(stringToReplace, STARS);
        } else {
            return inputString;
        }
    }

}