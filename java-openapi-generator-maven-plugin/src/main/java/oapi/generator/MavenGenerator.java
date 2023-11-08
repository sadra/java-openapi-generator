package oapi.generator;

import oapi.generator.core.models.Languages;
import oapi.generator.core.type.TypeGenerator;
import oapi.generator.core.utils.FileManager;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.io.IOException;

/**
 *  Maven plugin for generating types from OpenAPI files.
 *
 *  @goal generate
 *  @phase generate-sources
 *  @requiresDependencyResolution compile
 *  @threadSafe true
 *  @requiresProject true
 */

@Mojo(name = "generate", defaultPhase = LifecyclePhase.GENERATE_SOURCES, threadSafe = true)
public class MavenGenerator extends AbstractMojo
{
    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    MavenProject project;

    @Parameter(name = "packageName", defaultValue = "open.api" )
    private String packageName;

    @Parameter(name = "openApiSpec", required = true )
    private String openApiSpec;

    @Parameter(name = "language", defaultValue = "JAVA")
    private Languages language;

    public void execute() throws MojoExecutionException
    {
        final String DIRECTORY = project.getBuild().getDirectory();
        String typesOutPutDirectory = String.format("%s/%s/types", DIRECTORY, FileManager.getOutPutDirectory(packageName));

        try {
            FileManager.deleteDirectory(DIRECTORY+"/generated-sources/openapi");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        project.addCompileSourceRoot(new File(
                "generated-sources"
        ).getAbsolutePath());

        getLog().info("===========>>>>>>> Generating types from OpenAPI files started.");

        try {
            new TypeGenerator().generate(
                    language,
                    packageName,
                    typesOutPutDirectory,
                    openApiSpec
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        getLog().info("===========>>>>>>> Generating types from OpenAPI files finished!");
    }
}
