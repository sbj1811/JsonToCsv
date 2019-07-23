package com.sjani.java;


import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


/**
 * JSON Parser to convert into list of lines
 */
public class JsonParser implements Parser {

    /**
     * Parses a input file of JSON array and returns a list of String arrays
     *
     * @param inputFile input file to be parsed to extract JSON array
     * @return List of String arrays
     */
    @Override
    public List<String[]> Parse(String inputFile) {
        if (inputFile.isEmpty()) {
            return null;
        }
        List<String[]> lines = new ArrayList<>();
        try {
            StringBuilder builder = new StringBuilder();
            Files.lines(Paths.get(inputFile), StandardCharsets.UTF_8).forEach(builder::append);
            String FileAsString = builder.toString();
            if (!FileAsString.isEmpty()) {
                JSONParser jsonParser = new JSONParser();
                JSONArray jsonArray = (JSONArray) jsonParser.parse(FileAsString);
                if (!(jsonArray == null || jsonArray.size() == 0)) {
                    lines = new Converters().JSONArrayToList(jsonArray);
                }
            } else {
                System.out.println("Input JSON File is empty. Please check the file contents");
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return lines;
    }

}

