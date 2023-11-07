package oapi.generator.core.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Commenter {

    /**
     * Adds a comment block to the beginning of the generated file
     *
     * @param input File text content
     * @param specInfoComment Comment block to add
     * @return String File text content formatted with comment block added
     */
    public static String addGeneratedSpecComment(String input, String specInfoComment) {
        // Define a regular expression pattern to match the first Swagger comment block
        String pattern = "(\\/\\*([^*]|[\\r\\n]|(\\*+([^*/]|[\\r\\n])))*\\*+\\/)|(\\/\\/.*)";
        Pattern regexPattern = Pattern.compile(pattern, Pattern.DOTALL);

        // Create a matcher to find the first matching block of text
        Matcher matcher = regexPattern.matcher(input);

        if (matcher.find()) {
            String result = input.substring(0, matcher.start()) + input.substring(matcher.end());
            result = result.replaceAll("io.swagger.codegen.v3.generators.java.JavaClientCodegen", "oapi.generator");
            return specInfoComment + result;
        }

        return input;
    }

    /**
     * Generates a comment block for the generated file
     *
     * @param specTitle OpenApi spec title
     * @param specVersion OpenApi spec version
     * @param specDescription OpenApi spec description
     * @return String Comment block
     */
    public static String getSpecInfoComment(String specTitle, String specVersion, String specDescription){
        StringBuilder comment = new StringBuilder();
        final String LINE_SEPARATOR = System.getProperty("line.separator");
        comment.append("/**\n");

        if(specTitle != null){
           comment.append(String.format(" * %s\n", specTitle));
        }

        if(specDescription != null){
            comment.append(String.format(" * %s\n", specDescription));
        }

        if(specVersion != null){
            comment.append(String.format(" *\n * OpenAPI spec version: %s\n *\n *\n", specVersion));
        }

        comment.append(" * DO NOT CHANGE!\n" +
                " *\n" +
                " * This file is generated from the OpenAPI Spec.\n" +
                " * If changes are necessary, change the spec and regenerate this file.\n" +
                "*/");

        return comment.toString();
    }
}
