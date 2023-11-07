package oapi.generator.core.type;

import io.swagger.codegen.v3.DefaultGenerator;
import io.swagger.v3.oas.models.OpenAPI;

import java.io.File;
import java.util.List;

public class SwaggerGenerator extends DefaultGenerator {
    @Override
    public List<File> generate() {
        return super.generate();
    }

    public OpenAPI getOpenAPI() {
        return this.openAPI;
    }
}
