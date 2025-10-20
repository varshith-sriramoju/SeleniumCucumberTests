# Selenium Cucumber Tests \- Java + Maven

End\-to\-end UI tests using Selenium WebDriver, Cucumber \(BDD\), JUnit 4, and Maven. Verified build with `javac 23.0.2` on Windows via IntelliJ IDEA.

## Change log \- current state
- Fixed invalid POM header \(removed leading comment before XML declaration\).
- Aligned dependencies: `io.cucumber:cucumber\-java` and `io.cucumber:cucumber\-junit` with JUnit 4.
- Added Maven Surefire to discover JUnit 4 Cucumber runners \(`\*Runner.java`\).
- Defined safe default `cucumber.filter.tags` property.
- Updated runner imports and glue \(`stepDefinitions`, `Utility`\).
- Build completed successfully \(10/20/2025\).

## Tech stack \& versions
- Java 23
- Maven 3\.9\+
- Cucumber \(7\.15\.0\)
- JUnit 4 \(4\.13\.2\)
- Selenium WebDriver \(4\.24\.0\)
- Maven Surefire \(3\.2\.5\)

## Prerequisites
- JDK installed and `JAVA_HOME` set.
- Maven on `PATH`.
- Chrome/Edge installed.

## Quick start
- Clone and open in IntelliJ IDEA.
- Maven: Reload all projects.
- Run all tests:
    mvn clean test
- Run by tag:
    mvn clean test \-Dcucumber.filter.tags="@smoke"
- Run a single feature:
    mvn clean test \-Dcucumber.features="src/test/resources/features/sample.feature"

## Project structure
src  
└─ test  
&nbsp;&nbsp;├─ java  
&nbsp;&nbsp;│&nbsp;&nbsp;├─ stepDefinitions/  
&nbsp;&nbsp;│&nbsp;&nbsp;├─ Utility/  
&nbsp;&nbsp;│&nbsp;&nbsp;└─ runner/ \# JUnit runner\(s\)  
&nbsp;&nbsp;└─ resources  
&nbsp;&nbsp;&nbsp;&nbsp;└─ features/ \# \.feature files

## Maven setup \(`pom.xml`\)
Key configuration used in `pom.xml` \(pinned versions, Surefire includes, default tags\):

    <properties>
      <maven.compiler.source>23</maven.compiler.source>
      <maven.compiler.target>23</maven.compiler.target>
      <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>

      <cucumber.version>7.15.0</cucumber.version>
      <junit.version>4.13.2</junit.version>
      <selenium.version>4.24.0</selenium.version>
      <surefire.version>3.2.5</surefire.version>
      <compiler.plugin.version>3.13.0</compiler.plugin.version>

      <cucumber.filter.tags></cucumber.filter.tags>
    </properties>

    <dependencies>
      <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-java</artifactId>
        <version>${cucumber.version}</version>
        <scope>test</scope>
      </dependency>
      <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-junit</artifactId>
        <version>${cucumber.version}</version>
        <scope>test</scope>
      </dependency>
      <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>${junit.version}</version>
        <scope>test</scope>
      </dependency>
      <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>${selenium.version}</version>
        <scope>test</scope>
      </dependency>
    </dependencies>

    <build>
      <plugins>
        <plugin>
          <groupId>org.apache.maven.plugins</groupId>
          <artifactId>maven-compiler-plugin</artifactId>
          <version>${compiler.plugin.version}</version>
          <configuration>
            <source>${maven.compiler.source}</source>
            <target>${maven.compiler.target}</target>
            <encoding>${project.build.sourceEncoding}</encoding>
          </configuration>
        </plugin>
        <plugin>
          <groupId>org.apache.maven.plugins</groupId>
          <artifactId>maven-surefire-plugin</artifactId>
          <version>${surefire.version}</version>
          <configuration>
            <includes>
              <include>**/*Runner.java</include>
            </includes>
            <systemPropertyVariables>
              <cucumber.filter.tags>${cucumber.filter.tags}</cucumber.filter.tags>
            </systemPropertyVariables>
          </configuration>
        </plugin>
      </plugins>
    </build>

## Test runner \(`src/test/java/runner/TestRunner.java`\)
- Uses JUnit 4 with Cucumber.
- Glue points to `stepDefinitions` and `Utility`.
- Generates HTML and JSON reports under `target/`.

    package runner;

    import org.junit.runner.RunWith;
    import io.cucumber.junit.Cucumber;
    import io.cucumber.junit.CucumberOptions;

    @RunWith(Cucumber.class)
    @CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepDefinitions", "Utility"},
        plugin = {
            "pretty",
            "html:target/cucumber-reports",
            "json:target/cucumber.json"
        },
        monochrome = true
    )
    public class TestRunner { }

## IntelliJ IDEA
- Install plugins: Cucumber for Java, Gherkin.
- Right\-click a `\*.feature` or the runner to Run/Debug.
- Terminal on Windows: `mvn clean test`.

## Reports
- HTML: `target/cucumber\-reports` or `target/cucumber\-report.html`
- JSON: `target/cucumber.json`

## Maintenance checklist
- Update `pom.xml` versions and reflect here.
- Document new tags and how to run them.
- Record new features/steps/hooks and package changes.
- Note any report path or tooling changes.
- Ensure `mvn clean test` passes locally on Windows.

## Common issues
- Glue not found: ensure package names match `glue`.
- Driver/browser issues: install browser or manage drivers.
- Version conflicts: keep Cucumber, JUnit, Selenium compatible.add cucumber dependency to  from maven repository 
add dependency to pom.xml file  
both for cucumber-java and cucumber-junit
