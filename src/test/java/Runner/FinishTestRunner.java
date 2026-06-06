package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.*;

@CucumberOptions(features = "src/main/resources/features/F13_FinishTesting.feature", glue = "Utilities", tags = "@Finish")
@Test
public class FinishTestRunner extends AbstractTestNGCucumberTests {}
