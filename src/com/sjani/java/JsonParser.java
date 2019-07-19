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
     * @param inputFile input file to be parsed to extract JSON array
     * @return List of String arrays
     */
    @Override
    public List<String[]> Parse(String inputFile) {
        if (inputFile.isEmpty()){
            return null;
        }
        List<String[]> lines = new ArrayList<>();
        try {
            StringBuilder builder = new StringBuilder();
            Files.lines(Paths.get(inputFile), StandardCharsets.UTF_8).forEach(jsonObject -> builder.append(jsonObject));
            JSONParser jsonParser = new JSONParser();
            String stream = builder.toString();
            JSONArray jsonArray = null;
            if (!stream.isEmpty()) {
                jsonArray = (JSONArray) jsonParser.parse(stream);
            }
            lines = new Converters().JSONArrayToList(jsonArray);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return lines;
    }

}

