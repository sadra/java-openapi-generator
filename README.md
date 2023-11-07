# Java OpenAPI Generator

This is a Java library for the [OpenAPI Specification](https://swagger.io/specification/) (OAS) v3.0.2. It allows you to parse and generate OAS documents programmatically.

**Table of Contents**

* [How to Use](#how-to-use)
   * [1. Add Dependency](#1-add-dependency)
   * [2. Add Maven Plugin](#2-add-maven-plugin)
   * [3. Configuration](#3-configuration)
   * [4. Run](#4-run)

## How to Use

### 1. Add Dependency

Add the following dependency to your `pom.xml`:

```xml
        <dependency>
            <groupId>oapi.generator</groupId>
            <artifactId>oapi-generator-maven-plugin</artifactId>
            <version>1.0.1-SNAPSHOT</version>
        </dependency>
```

### 2. Add Maven Plugin

Add the generator plugin to you `pom.xml`

```xml
    <build>
    ...
        <plugins>
            <plugin>
                <groupId>oapi.generator</groupId>
                <artifactId>oapi-generator-maven-plugin</artifactId>
                <version>1.0.1-SNAPSHOT</version>
                <executions>
                    <execution>
                        <id>generate</id>
                        <phase>generate-sources</phase>
                        <goals>
                            <goal>generate</goal>
                        </goals>
                        <configuration>
                            <packageName>example.company.api</packageName>
                            <openApiSpec>${project.basedir}/src/main/resources/oapi.yml</openApiSpec>
                            <language>JAVA</language>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
```

### 3. Configuration

The following configuration options are available:

| Option | Description | Required | Default                                                                                                                                            | Example                                          |
| --- | --- |----------|----------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------|
| `packageName` | The package name for the generated classes | No       | `open.api`                                                                                                                                         | `example.company.api`                            |
| `openApiSpec` | The path to the OAS document, it could be a local openapi spec file, or from a remote url      | N/A       | `${project.basedir}/src/main/resources/oapi.yml` or `https://raw.githubusercontent.com/OAI/OpenAPI-Specification/main/examples/v3.0/petstore.yaml` |
| `language` | The target language for the generated classes | No       | `JAVA`                                                                                                                                             | `JAVA` or `KOTLIN`                                |

### 4. Run

Run the following command to generate the classes:

```bash
mvn clean package
```
