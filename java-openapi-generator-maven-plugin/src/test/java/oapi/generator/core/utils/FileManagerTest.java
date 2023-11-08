package oapi.generator.core.utils;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class FileManagerTest {

    @Test
    void testDeleteDirectory() throws IOException {
        // Create a temporary directory with some files
        File tempDir = createTempDirectory("tempDir");
        File file1 = createTempFile(tempDir.toPath(), "file1.txt", "Content of file 1");
        File file2 = createTempFile(tempDir.toPath(), "file2.txt", "Content of file 2");

        assertTrue(tempDir.exists());
        assertTrue(file1.exists());
        assertTrue(file2.exists());

        // Delete the directory
        FileManager.deleteDirectory(tempDir.getAbsolutePath());

        // Verify that the directory and its contents are deleted
        assertFalse(tempDir.exists());
        assertFalse(file1.exists());
        assertFalse(file2.exists());
    }

    @Test
    void testBuildFile() throws IOException {
        // Create a temporary directory
        File tempDir = createTempDirectory("tempDir");

        String outputPath = tempDir.getAbsolutePath();
        String fileName = "TestFile.txt";
        String content = "Test file content";

        // Build the file
        FileManager.buildFile(outputPath, fileName, content);

        // Verify that the file is created with the correct content
        File createdFile = new File(tempDir, fileName);
        assertTrue(createdFile.exists());

        List<String> fileLines = Files.readAllLines(createdFile.toPath());
        assertEquals(content, String.join(System.lineSeparator(), fileLines));
    }

    @Test
    void testGetOutPutDirectory() {
        String packageName = "oapi.generator.core.utils";
        String result = FileManager.getOutPutDirectory(packageName);

        String expected = "/generated-sources/openapi/src/main/java/oapi/generator/core/utils";
        assertEquals(expected, result);
    }

    private File createTempFile(Path directory, String fileName, String content) throws IOException {
        File tempFile = Files.createTempFile(directory, fileName, null).toFile();
        tempFile.deleteOnExit();

        // Write content to the temporary file
        Files.write(tempFile.toPath(), content.getBytes());

        return tempFile;
    }

    private File createTempDirectory(String directoryName) throws IOException {
        File tempDir = Files.createTempDirectory(directoryName).toFile();
        tempDir.deleteOnExit();
        return tempDir;
    }
}

