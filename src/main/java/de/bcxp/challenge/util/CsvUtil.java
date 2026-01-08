package de.bcxp.challenge.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class CsvUtil {

    public static List<String[]> readCsv(String filename, String delimiter) throws IOException {
        InputStream is = CsvUtil.class.getClassLoader().getResourceAsStream(filename);
        if (is == null) throw new IOException("File not found");

        List<String[]> fileContents = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))
                .lines()
                // Split each line by delimiter and collect split elements back into a list to create a matrix
                .map(line -> line.split(delimiter))
                // Skip header line
                .skip(1)
                .collect(Collectors.toList());

        return fileContents;
    }
}
