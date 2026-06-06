package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@CucumberOptions(features = "src/main/resources/features/F00_Initialization.feature", glue = "InitializationStepDefinition", tags = "@Initialization")
@Test
public class initializationTestRunner extends AbstractTestNGCucumberTests {}
