package com.sjani.java;

import java.util.List;

/**
 * Generic Writer
 */
public interface Writer {
    /**
     * Writes listOfLines of String arrays line by line to a file on the disk
     *
     * @param listOfLines listOfLines of String arrays to be written
     * @param outputFile  output file
     */
    void write(List<String[]> listOfLines, String outputFile);
}
