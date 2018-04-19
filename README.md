# cxf-swagger-example
Example project for generating cxf service from swagger.json or swagger.yaml

## Steps to generate cxf sources from Swagger.json or swagger.yaml.

1. Create swagger directory under src directory and place swagger.json or swagger.yaml.
2. Add `swagger-codegen-maven-plugin` pluggin to `pom.xml`. With congiguration as shown below.
    ``` xml
    <plugin>
		<groupId>io.swagger</groupId>
		<artifactId>swagger-codegen-maven-plugin</artifactId>
		<version>2.3.1</version>
		<executions>
			<execution>
		    	<id>gen-sources</id>
				<goals>
			    	<goal>generate</goal>
				</goals>
				<configuration>
					<inputSpec>${project.basedir}/src/swagger/petstore-simple.yml</inputSpec>
					<language>jaxrs-cxf</language>
						        <modelPackage>${project.groupId}.swagger.models</modelPackage>								            <apiPackage>${project.groupId}.swagger</apiPackage>
				</configuration>
			</execution>
		</executions>
	</plugin>
    ```
3. Add generate-sources and swagger file to classpath using `builder-helper` plugin.
   ``` xml
  	<plugin>
		<groupId>org.codehaus.mojo</groupId>
		<artifactId>build-helper-maven-plugin</artifactId>
		<version>3.0.0</version>
		    <executions>
				<execution>
					<id>add-source</id>
					<phase>generate-sources</phase>
					<goals>
						<goal>add-source</goal>
					</goals>
					<configuration>
			    		<sources>
						<source>${project.build.directory}/generated-sources/swagger/src/gen/java</source>
							<source>src/swagger/</source>
						</sources>
					</configuration>
				</execution>
			</executions>
		</plugin>
    ```
4. Add swagger dependencies.
    ```xml
    <!-- https://mvnrepository.com/artifact/io.swagger.core.v3/swagger-jaxrs2 -->
	<dependency>
		<groupId>io.swagger</groupId>
		<artifactId>swagger-annotations</artifactId>
		<version>1.5.17</version>
	</dependency>
	<!-- https://mvnrepository.com/artifact/io.swagger/swagger-jaxrs -->
	<dependency>
		<groupId>io.swagger</groupId>
		<artifactId>swagger-jaxrs</artifactId>
		<version>1.5.17</version>
	</dependency>
    ```
5. Generate sources.
   ```shell
   $ mvn generate-sources
   or
   $ mvn compile
   ```
## Implementation of service and stating application.

1. By Default plugin generates DefaultApi.java. Implement and add `@Service` Annotation.
    ```java
    @Service
    public class MyService implements DefaultApi {
     // ...
    }
    ```
2. Add property `cxf.jaxs.component-scan=true` to `applcation.properies` or `application.yml`
3. Add cxf swagger dependency and jackson jaxrs dependency.
   ```xml
   <!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.jaxrs/jackson-jaxrs-json-provider -->
		<dependency>
			<groupId>com.fasterxml.jackson.jaxrs</groupId>
			<artifactId>jackson-jaxrs-json-provider</artifactId>
			<version>2.9.5</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/org.apache.cxf/cxf-rt-rs-service-description-swagger -->
		<dependency>
			<groupId>org.apache.cxf</groupId>
			<artifactId>cxf-rt-rs-service-description-swagger</artifactId>
			<version>3.2.4</version>
		</dependency>
   ```
4. Create `Swagger2Feature` and `JacksonJaxbJsonProvider` Bean.
    ```java
    @Configuration
    public class CxfConfig {

        @Bean
	    public JacksonJaxbJsonProvider jaxbJsonProvider(ObjectMapper mapper) {
		    JacksonJaxbJsonProvider provider = new JacksonJaxbJsonProvider();
		    provider.setMapper(mapper);
		    return provider;
	    }
	
	    @Bean
	    public Swagger2Feature swaggerFeature() {
		    Swagger2Feature feature = new Swagger2Feature();
		    feature.setDescription("KP Description");
		    return feature;
	    }
    }
    ```
5. Run the application.
    ```shell
    $ mvn spring-boot:run
    ```
6. You can access `swagger.json` at `http://localhost:8080/services/swagger.json` and a sample service at `http://localhost:8080/services/pets`

# Useful Links.

1. https://github.com/swagger-api/swagger-codegen/tree/master/modules/swagger-codegen/src/main/resources/JavaJaxRS/cxf/server

