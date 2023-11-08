package oapi.generator.core.utils;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class ConvertorTest {

    @Test
    void testFileListToContentMap() throws IOException {
        // Create temporary files with content
        File file1 = createTempFile("file1.txt", "Content of file 1");
        File file2 = createTempFile("file2.txt", "Content of file 2");

        List<File> fileList = new ArrayList<>();
        fileList.add(file1);
        fileList.add(file2);

        Map<String, String> result = Convertor.fileListToContentMap(fileList);

        // Verify the result
        assertEquals("Content of file 1", result.get(file1.getName()));
        assertEquals("Content of file 2", result.get(file2.getName()));

        // Clean up temporary files
        Files.delete(file1.toPath());
        Files.delete(file2.toPath());
    }

    @Test
    void testFileListToContentMap_FileNotFound() {
        // Create a list with a non-existing file
        List<File> fileList = new ArrayList<>();
        fileList.add(new File("nonexistentfile.txt"));

        // Suppress the error output for the purpose of this test
        System.err.close();

        Map<String, String> result = Convertor.fileListToContentMap(fileList);

        // Verify that the result map is empty
        assertTrue(result.isEmpty());

        // Restore the error output
        System.setErr(System.err);
    }

    private File createTempFile(String fileName, String content) throws IOException {
        File tempFile = File.createTempFile(fileName, null);
        tempFile.deleteOnExit();

        // Write content to the temporary file
        Files.write(tempFile.toPath(), content.getBytes());

        return tempFile;
    }
}
