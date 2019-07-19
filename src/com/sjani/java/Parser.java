package com.sjani.java;

import java.util.List;

/**
 * Generic parser
 */
public interface Parser {
    /**
     * Generic method to parse input file
     * @param inputFile input file to be parsed
     * @return List of String arrays
     */
    public List<String[]> Parse(String inputFile);
}
