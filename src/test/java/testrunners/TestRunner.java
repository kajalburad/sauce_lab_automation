package testrunners;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepdefinition/",
        plugin = {"pretty", "html:target/cucumber-reports"},
        tags = "@sauceLabDemo"
)
public class TestRunner {
    // This is the JUnit 4 version of the test runner
}

