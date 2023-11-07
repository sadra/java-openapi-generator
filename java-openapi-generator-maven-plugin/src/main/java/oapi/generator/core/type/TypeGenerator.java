package oapi.generator.core.type;

import io.swagger.codegen.v3.ClientOptInput;
import io.swagger.codegen.v3.config.CodegenConfigurator;
import oapi.generator.core.models.Generator;
import oapi.generator.core.models.Languages;
import oapi.generator.core.utils.Commenter;
import oapi.generator.core.utils.Convertor;
import oapi.generator.core.utils.FileManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.swagger.codegen.v3.config.CodegenConfiguratorUtils.applySystemPropertiesKvpList;

public class TypeGenerator implements Generator {

    /**
     * {@inheritDoc}
     */
    @Override
    public void generate(Languages language, String packageName, String outPutDir, String openApiSpec) throws IOException {
        List<String> systemProperties = new ArrayList<>();

        CodegenConfigurator configurator = new CodegenConfigurator();

        String swaggerCodeGenPath = "target/generated-sources/generate-temp";
        configurator.setLang(language.getLan());
        configurator.setInputSpecURL(openApiSpec);
        configurator.setOutputDir(swaggerCodeGenPath);
        systemProperties.add("models");
        configurator.setModelPackage(packageName);
        configurator.setVerbose(false);

        applySystemPropertiesKvpList(systemProperties, configurator);
        final ClientOptInput clientOptInput = configurator.toClientOptInput();

        SwaggerGenerator generator = new SwaggerGenerator();
        generator.opts(clientOptInput);
        Map<String, String> fileContentMap = Convertor.fileListToContentMap(generator.generate());

        FileManager.deleteDirectory(swaggerCodeGenPath);

        String specInfoComment = Commenter.getSpecInfoComment(
                generator.getOpenAPI().getInfo().getTitle(),
                generator.getOpenAPI().getInfo().getVersion(),
                generator.getOpenAPI().getInfo().getDescription()
        );

        fileContentMap.entrySet().stream()
                .filter(entry -> !entry.getKey().endsWith(".md"))
                .forEach(entry -> {
                    //TODO: log to generate each file
                    String content = Commenter.addGeneratedSpecComment(
                            entry.getValue(),
                            specInfoComment
                    );
                    FileManager.buildFile(
                            outPutDir,
                            entry.getKey(),
                            content
                    );
                });
    }
}
