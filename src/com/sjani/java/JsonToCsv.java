package com.sjani.java;


import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

/**
 * JSON to CSV converter
 */
public class JsonToCsv {

    private static final String INPUT_FILE_NAME = "inputfile";
    private static final String PARSER = "parser";
    private static final String WRITER = "writer";
    private static Path inputPath;
    private static Path outputPathJson;

    /**
     * Main function reads a JSON Array and writes to CSV file
     *
     * @param args Output File name
     */
    public static void main(String[] args) {
        Properties properties = new Properties();
        try {
            properties = new PropertiesValues().getProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (properties == null || properties.isEmpty()) {
            System.out.println("Error: Please check config/properties file");
            return;
        }
        inputPath = Paths.get(properties.getProperty(INPUT_FILE_NAME));
        if (args.length > 0) {
            outputPathJson = Paths.get(args[0]);
        } else {
            System.out.println("Error: Please check filename and extension in the argument");
            System.out.println("Example command:");
            System.out.println("java -jar JsonToCsv.jar sample.csv");
            return;
        }
        Parser jsonParser = getParser(properties.getProperty(PARSER));
        Writer csvWriter = getWriter(properties.getProperty(WRITER));
        List<String[]> listOfLines = jsonParser.Parse(inputPath.toAbsolutePath().toString());
        if (listOfLines != null || listOfLines.size() > 0) {
            csvWriter.write(listOfLines, outputPathJson.toAbsolutePath().toString());
        }
    }

    /**
     * Returns a CSV Writer
     *
     * @param writerType Type of Writer requested
     * @return Writer of requested type
     */
    private static Writer getWriter(String writerType) {
        WriterFactory writerFactory = new WriterFactory();
        return writerFactory.getWritier(writerType);
    }

    /**
     * Returns a JSON parser
     *
     * @param parserType Type of Parser requested
     * @return Parser of requested type
     */
    private static Parser getParser(String parserType) {
        ParseFactory parseFactory = new ParseFactory();
        return parseFactory.getParse(parserType);
    }

}
