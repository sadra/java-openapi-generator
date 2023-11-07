package oapi.generator.core.models;

public enum Languages {
    JAVA("java"),
    KOTLIN("kotlin-client");

    private final String language;

    Languages(String language) {
        this.language = language;
    }

    public String getLan() {
        return language;
    }
}
