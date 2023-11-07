package oapi.generator.core.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {
    /**
     * Deletes a directory and all its contents
     *
     * @param pathToTheDir Path to the directory to delete
     * @throws IOException If an I/O error occurs
     */
    public static void deleteDirectory(String pathToTheDir) throws IOException {
        Path dir = Paths.get(pathToTheDir); //path to the directory

        if(dir.toFile().exists()){
            Files.walk(dir)
                    .map(Path::toFile)
                    .sorted((o1, o2) -> -o1.compareTo(o2))
                    .forEach(File::delete);
        }
    }

    /**
     * Builds a file with the given content
     *
     * @param outPutPath Path to the directory to build the file
     * @param fileName Name of the file to build with extension (e.g. "Hello.java")
     * @param content  Content of the file to build
     */
    public static void buildFile(String outPutPath, String fileName, String content){
        File path = new File(
                outPutPath
        );

        if (!path.exists()) {
            if (path.mkdirs()) {
                System.out.println("Directory created: " + path);
            } else {
                System.err.println("Error creating the directory: " + path);
                return;
            }
        }

        File getHelloResponse = new File(path, fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getHelloResponse))) {
            // Write the content to the file
            writer.write(content);
            System.out.println("File created: " + getHelloResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the output directory for the generated files
     *
     * @param pkg Package name
     * @return String Output directory
     */
    public static String getOutPutDirectory(String pkg){
        String[] pkgParts = pkg.split("\\.");
        String pkgPath = String.join("/", pkgParts);
        return "/generated-sources/openapi/src/main/java/" + pkgPath;
    }
}
