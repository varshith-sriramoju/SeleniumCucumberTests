# Selenium Cucumber Tests - Java + Maven

This repository contains end-to-end UI tests using Selenium WebDriver + Cucumber (BDD) + JUnit 4, built with Maven. Use this README to explain the project architecture, how it runs, what decisions were made, and how to talk about it in an interview.

---

## Elevator pitch (one‑liner to tell an interviewer)
A maintainable Java-based E2E automation suite using Cucumber for readable scenarios (Gherkin) and Selenium WebDriver for browser interaction; tests are executed by Maven (Surefire) and reported as HTML/JSON.

---

## What to memorize and say in an interview
- Purpose: Validate user flows via browser automation; maintain readable business-facing specs (Gherkin) mapped to Java step definitions.
- Tools & versions used here: Java 23, Maven 3.9+, Cucumber 7.15.0, JUnit 4.13.2, Selenium 4.24.0, Surefire 3.2.5.
- Why Cucumber: separates intent (feature files) from implementation (step defs), improves collaboration with non‑dev stakeholders.
- Why JUnit 4 + Surefire: chosen to keep runner discovery compatible with this Cucumber version and current project structure.
- Driver management: Selenium 4.x includes Selenium Manager (automatically resolves drivers) — mention this when asked about driver handling.
- Improvements you can propose: Page Object Model, DI for step classes, stable locators, parallel execution, CI pipeline.

---

## Project structure (what to point to)
- src/test/resources/features/ — Gherkin feature files (.feature)
- src/test/java/stepDefinitions/ — step implementation classes (Cucumber steps)
- src/test/java/Utility/ — helper utilities, driver setup & teardown
- src/test/java/runner/TestRunner.java — JUnit 4 Cucumber runner used by Surefire
- pom.xml — Maven build, dependencies, Surefire config
- target/ — build outputs & reports (e.g., target/cucumber-reports, target/cucumber.json)

When walking through code, open:
- TestRunner.java to show @RunWith and @CucumberOptions
- A sample feature file to read Gherkin
- The matching step definition to show mapping and Selenium commands
- Utility class to show WebDriver init/quit and any wait/helper utilities

---

## How it runs (functional flow)
1. Maven Surefire plugin scans for test runners (includes pattern: **/*Runner.java).
2. TestRunner (JUnit 4) starts; Cucumber reads feature files from features path.
3. For each scenario, Cucumber locates step definitions (glue) and executes them.
4. Step defs use Utility classes to create WebDriver (Selenium) instances and interact with browsers.
5. Reports produced: HTML and JSON under target/ (configured in @CucumberOptions).

Example TestRunner highlights:
- features = "src/test/resources/features"
- glue = {"stepDefinitions", "Utility"}
- plugin = {"pretty", "html:target/cucumber-reports", "json:target/cucumber.json"}
- monochrome = true

---

## Key files to open during interview
- src/test/java/runner/TestRunner.java — explain the runner and options
- src/test/resources/features/*.feature — read a scenario aloud (Gherkin)
- src/test/java/stepDefinitions/* — show Given/When/Then mapping and assertions
- src/test/java/Utility/* — driver setup, explicit waits, common helpers
- pom.xml — point out pinned versions, Surefire includes, and systemPropertyVariables (cucumber.filter.tags)

---

## Commands (Windows examples)
Open PowerShell or Command Prompt from the project root.

- Run full suite:
  mvn clean test

- Run by tag (uses property forwarded to Cucumber via Surefire):
  mvn clean test -Dcucumber.filter.tags="@smoke"

- Run a single feature file:
  mvn clean test -Dcucumber.features="src/test/resources/features/sample.feature"

- Run a single scenario by line (Cucumber supports path:line syntax):
  mvn clean test -Dcucumber.features="src/test/resources/features/sample.feature:12"

- Increase logging / debug:
  mvn -X test

Notes:
- Use the project's Surefire configuration which forwards cucumber.filter.tags. You can also set any other system property and read it in code (e.g., browser selection).

---

## Drivers and browsers
- Selenium 4.24.0 includes Selenium Manager which will attempt to download the appropriate driver (chromedriver/msedgedriver). Mention this to demonstrate knowledge of modern Selenium features.
- If using manual drivers: place chromedriver.exe / msedgedriver.exe on PATH or set webdriver.chrome.driver system property.
- To run headless, either:
  - Add headless options in your WebDriver builder (ChromeOptions/EdgeOptions), or
  - Pass a system property (e.g., -Dheadless=true) and have Utility code read it to apply options.

---

## Test design notes to explain (common interview topics)
- Locators: prefer data-test-id or stable attributes; avoid brittle XPath if possible.
- Waits: use explicit waits (WebDriverWait) for elements/conditions; avoid Thread.sleep().
- Patterns: use Page Object Model (POM) to encapsulate page behavior; keeps step defs concise.
- Data: use fixtures, builders, or parameterize scenarios with Examples/Scenario Outline.
- CI: run with Maven in CI (GitHub Actions, Azure DevOps) and publish artifacts (HTML/JSON) as test reports.
- Parallelism: Cucumber 7 supports parallel execution; needs thread-safe driver management and distinct WebDriver instances per thread.
- Flakiness: isolate causes (timing, environment), add retries in CI cautiously, increase stability via waits and idempotent test data.

---

## Example talking points & short answers (practice)
- Q: How do you find elements reliably?
  A: Use stable attributes (data-*), prefer CSS selectors or meaningful IDs, fallback to robust relative XPath only when necessary.

- Q: How do you manage browser drivers?
  A: Use Selenium Manager (bundled with Selenium 4+) to auto-resolve drivers; otherwise manage executables centrally or via WebDriverManager dependency.

- Q: How to avoid brittle tests?
  A: Use POM, explicit waits, stable locators, isolate test data, and run tests in clean environments.

- Q: How to speed up E2E runs?
  A: Run only targeted smoke/regression suites via tags, parallelize tests (requires thread-safety), or mock backend services for deterministic tests.

- Q: How to debug failing tests locally?
  A: Re-run failing scenario in IDE (runner or feature), enable verbose logs, take screenshots on failure, and reproduce steps manually.

---

## Troubleshooting (common issues & fixes)
- Glue not found: ensure package names in @CucumberOptions glue match actual packages.
- Step undefined: check regex/annotation text matches Gherkin step exactly or use Cucumber's expression syntax.
- Browser/driver failures: verify browser installed, use Selenium Manager or ensure driver exe on PATH.
- Incompatible versions: match Cucumber + JUnit + Selenium versions; consult dependency release notes.
- Surefire not picking up tests: ensure runner file name matches includes pattern (**/*Runner.java).

---

## Suggested improvements & roadmap to discuss
- Introduce Page Objects and a BaseTest for shared setup/teardown.
- Add dependency injection (PicoContainer or Guice) for shared state between steps.
- Add robust logging & screenshots on failure.
- Add test tagging strategy (smoke, regression, ui-slow) and CI pipelines that run tags selectively.
- Add a contract/mocking layer to reduce dependence on slow or flaky backend services.

---

## How to present this repo in a 5-min demo
1. Open TestRunner.java — explain CucumberOptions and report outputs.
2. Open a .feature — read a simple scenario.
3. Open the matching step definition — point out WebDriver calls and assertions.
4. Show target/cucumber-reports after a test run.
5. Summarize improvements and next steps.

---

## Useful snippets to show during interview
Sample Gherkin (readable):
    Feature: Login
      Scenario: Successful login
        Given I open the login page
        When I submit valid credentials
        Then I should see the dashboard

Sample Step Definition (explain mapping and Selenium usage):
    @Given("I open the login page")
    public void openLogin() {
      driver.get("https://example.com/login");
    }

Explain assertions (use JUnit asserts or AssertJ for fluent assertions).

---

## What to run before an interview demo (checklist)
- mvn clean test (run locally and fix obvious failures)
- Open IDE to the feature and step files you will demo
- Ensure Chrome/Edge installed; if using driver exe, ensure on PATH
- Optionally clear target/ folder so generated reports are fresh

---

## Appendices

### pom.xml highlights you should know
- Compiler set to Java 23
- cucumber.version and junit.version pinned to avoid compatibility drift
- surefire includes pattern: **/*Runner.java so runners must match that naming
- systemPropertyVariables forwards cucumber.filter.tags for tag-based runs

### Reports
- HTML: target/cucumber-reports
- JSON: target/cucumber.json
- Use these for CI artifacts or to feed into reporting tools (Allure, ReportPortal).

---

## Final tips for the interview
- Be concise: explain flow, then open code and show a live example.
- Focus on trade-offs (why Cucumber? why not pure unit tests?).
- Emphasize maintainability: POM, DI, stable locators, and CI integration.
- Prepare to discuss a failing test you fixed — show root cause and remedy.
