package com.sjani.java;

/**
 * Factory to get requested Parser type
 */
public class ParseFactory {

    private static final String JSON = "json";

    /**
     * Gets requested parser type
     *
     * @param parseType type of parser requested
     * @return parser type
     */
    public Parser getParse(String parseType) {
        if (parseType == null) {
            return null;
        }
        if (parseType.equalsIgnoreCase(JSON)) {
            return new JsonParser();
        }
        return null;
    }

}
