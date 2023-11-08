package oapi.generator.core.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommenterTest {

    @Test
    void testAddGeneratedSpecComment_NoExistingComment() {
        String input = "public class MyClass {\n}";
        String specInfoComment = Commenter.getSpecInfoComment("Test API", "1.0", "Test Description");

        String result = Commenter.addGeneratedSpecComment(input, specInfoComment);

        String expected = specInfoComment +"\n"+ input;
        assertEquals(expected, result);
    }

    @Test
    void testAddGeneratedSpecComment_WithExistingComment() {
        String existingComment = "/* Existing comment */\n";
        String input = existingComment + "public class MyClass {\n}";
        String specInfoComment = Commenter.getSpecInfoComment("Test API", "1.0", "Test Description");

        String result = Commenter.addGeneratedSpecComment(input, specInfoComment);

        String expected = specInfoComment + "\n" +"public class MyClass {\n}";
        assertEquals(expected, result);
    }

    @Test
    void testAddGeneratedSpecComment_MatchRegex() {
        String existingComment = "/* Existing comment */\n";
        String input = existingComment + "@javax.annotation.Generated(value = \"io.swagger.codegen.v3.generators.java.JavaClientCodegen\", date = \"2023-11-07T22:24:05.767922+01:00[Europe/Amsterdam]\")\n" +
                "\n" +
                "public class Pet {\n}";
        String specInfoComment = Commenter.getSpecInfoComment("Test API", "1.0", "Test Description");

        String result = Commenter.addGeneratedSpecComment(input, specInfoComment);

        String expected = specInfoComment + "\n@javax.annotation.Generated(value = \"oapi.generator\", date = \"2023-11-07T22:24:05.767922+01:00[Europe/Amsterdam]\")\n" +
                "\n" +
                "public class Pet {\n}";
        assertEquals(expected, result);
    }

    @Test
    void testGetSpecInfoComment_AllFieldsProvided() {
        String specTitle = "Test API";
        String specVersion = "1.0";
        String specDescription = "Test Description";

        String result = Commenter.getSpecInfoComment(specTitle, specVersion, specDescription);

        String expected = "/**\n * Test API\n * Test Description\n *\n * OpenAPI spec version: 1.0\n *\n *\n" +
                " * DO NOT CHANGE!\n" +
                " *\n" +
                " * This file is generated from the OpenAPI Spec.\n" +
                " * If changes are necessary, change the spec and regenerate this file.\n" +
                "*/";
        assertEquals(expected, result);
    }

    @Test
    void testGetSpecInfoComment_NullFields() {
        String specTitle = null;
        String specVersion = null;
        String specDescription = null;

        String result = Commenter.getSpecInfoComment(specTitle, specVersion, specDescription);

        String expected = "/**\n" +
                " * DO NOT CHANGE!\n" +
                " *\n" +
                " * This file is generated from the OpenAPI Spec.\n" +
                " * If changes are necessary, change the spec and regenerate this file.\n" +
                "*/";
        assertEquals(expected, result);
    }
}

