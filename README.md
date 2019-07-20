# JSON to CSV Converter

Takes a file containing an array of flat, non-hierarchical JSON objects, convert it into CSV format, and write it to disk.

## Features

* Each JSON object gets its own row in the CSV.
* Each key in the JSON object should have its value written to the corresponding column in the CSV
* Keys that are in multiple JSON objects should only have one corresponding column created in the CSV
* There is no known schema for the input JSON – the keys for each object can be anything.
* Assumption: The word "Branch" is redacted using ******.

## Example command:
    java -jar JsonToCsv.jar sample.csv