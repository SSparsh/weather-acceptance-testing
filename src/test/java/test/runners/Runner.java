package test.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = "src/test/Features",
        glue = {"test","src/test/java/test"},
        plugin = {
                "html:target/cucumber-html-report",
                "json:target/cucumber.json"
        },
        tags = {"@test"}
        )

public class Runner extends AbstractTestNGCucumberTests {
}
