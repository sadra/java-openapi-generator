package oapi.generator.core.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Convertor {
    /**
     * Converts a list of files to a map of file names and file contents
     *
     * @param fileList List of files to convert to a map
     * @return Map<String, String> Map of file names and file contents
     */
    public static Map<String, String> fileListToContentMap(List<File> fileList) {
        Map<String, String> fileContentMap = new HashMap<>();

        for (File file : fileList) {
            try {
                List<String> fileLines = Files.readAllLines(file.toPath());
                String content = String.join(System.lineSeparator(), fileLines);
                fileContentMap.put(file.getName(), content);
            } catch (IOException e) {
                System.err.println("Error reading file: " + file.getName());
                e.printStackTrace();
            }
        }

        return fileContentMap;
    }

}
