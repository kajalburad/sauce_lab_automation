package testrunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features", glue = "stepdefinition", plugin = {"pretty", "html:target/cucumber-reports"}, monochrome = true, tags = "@sauceLabDemo"

)

public class TestRunner extends AbstractTestNGCucumberTests {


}

