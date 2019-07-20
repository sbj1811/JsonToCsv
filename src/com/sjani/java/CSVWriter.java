package com.sjani.java;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


/**
 * Writes list of lines into a CSV file
 */
public class CSVWriter implements Writer {

    private static final String COMMA = ",";

    /**
     * Writes list of String arrays line by line to a CSV file on the disk
     *
     * @param listOfLines list of String arrays to be written
     * @param outputFile  output CSV file
     */
    @Override
    public void write(List<String[]> listOfLines, String outputFile) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(outputFile)));
            for (String[] line : listOfLines) {
                StringBuilder builder = new StringBuilder();
                for (String item : line) {
                    builder.append(item);
                    builder.append(COMMA);
                }
                bufferedWriter.write(builder.toString() + System.lineSeparator());
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
