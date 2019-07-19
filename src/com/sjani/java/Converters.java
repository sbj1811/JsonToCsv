package com.sjani.java;

import org.json.simple.JSONArray;

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
        if (jsonArray != null && jsonArray.size() > 0) {
            LinkedHashMap<String, Integer> columns = new LinkedHashMap<>();
            int columnCount = 0;
            for (int i = 0; i < jsonArray.size(); i++) {
                ArrayList<String> outputItem = new ArrayList<>();
                String s = jsonArray.get(i).toString();
                String[] fields = s.replaceAll("[\"{}]+", "").split("[,]+");
                for (int j = 0; j < fields.length; j++) {
                    String[] keyValuePair = fields[j].split(":");
                    if (!keyValuePair[0].isEmpty()) {
                        String value = "";
                        if (keyValuePair.length > 1) {
                            value = redactString(keyValuePair[1], BRANCH);
                        } else {
                            value = "";
                        }
                        if (!columns.containsKey(keyValuePair[0])) {
                            columns.put(keyValuePair[0], columnCount++);
                        }
                        if (outputItem.size() < columns.get(keyValuePair[0])) {
                            int index = 0;
                            if (outputItem.size() < columns.get(keyValuePair[0])) {
                                while (index <= outputItem.size() && outputItem.size() > 0) {
                                    index++;
                                }
                                while (outputItem.size() < columns.get(keyValuePair[0])) {
                                    outputItem.add("");
                                }
                                outputItem.add(value);
                            }
                        } else {
                            if(outputItem.size()>columns.get(keyValuePair[0])) {
                                outputItem.set(columns.get(keyValuePair[0]), value);
                            } else {
                                outputItem.add(columns.get(keyValuePair[0]), value);
                            }
                        }

                    }
                }
                lines.add(outputItem.toArray(new String[0]));
                outputItem.clear();
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