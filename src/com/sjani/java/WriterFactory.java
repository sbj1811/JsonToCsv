package com.sjani.java;

/**
 * Factory to get requested Writer type
 */
public class WriterFactory {

    private static final String CSV = "csv";

    /**
     * Gets requested writer type
     *
     * @param writerType type of writer requested
     * @return writer type
     */
    public Writer getWritier(String writerType) {
        if (writerType == null) {
            return null;
        }
        if (writerType.equalsIgnoreCase(CSV)) {
            return new CSVWriter();
        }
        return null;
    }

}
