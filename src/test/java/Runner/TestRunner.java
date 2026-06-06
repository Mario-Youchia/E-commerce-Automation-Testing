package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.*;

@CucumberOptions(
        features = "src/main/resources/features",
        glue = "stepDefinitions",
        tags = "@Smoke9",
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)

@Test
public class TestRunner extends AbstractTestNGCucumberTests {
}