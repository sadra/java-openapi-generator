package oapi.generator.core.models;

import java.io.IOException;

public interface Generator {

    /**
     * Generate code from open api spec
     *
     * @param language Target language to generate code
     * @param packageName Package name for generated code
     * @param outPutDir Output directory for generated code
     * @param openApiSpec OpenAPI spec file path
     * @throws IOException If any I/O error occurs
     */
    public void generate(Languages language, String packageName, String outPutDir, String openApiSpec) throws IOException;
}
