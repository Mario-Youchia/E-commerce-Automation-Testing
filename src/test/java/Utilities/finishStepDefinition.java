package Utilities;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class finishStepDefinition {
    public static int numberOfScenarios = 0;
    public static int numberOfScenariosDone = 0;
    public static int numberOfPassedScenarios = 0;

    @Given("ALl test scenarios are done")
    public void ALl_test_scenarios_are_done() {

        System.out.println("Total number of scenarios: " + numberOfScenarios);
        System.out.println("Total number of finished scenarios no matter what their status is: " + numberOfScenariosDone);
        System.out.println("Total number of scenarios done successfully: " + numberOfPassedScenarios);

        if (numberOfScenarios == numberOfScenariosDone) {
            System.out.println("All features have been tested.");
        } else {
            System.out.println("There is at least one untested feature.");
        }
    }

    @Then("Generate alert sounds")
    public void generateAlertSounds() throws Exception {
        Util.AlertFinish();
    }

    @And("Open report")
    public void openReport() {
        String reportName = "E-Commerce_Automation_Testing_Report.html";
        Util.OpenReport("GeneratedReports", "\\test-output\\HTMLReport\\" + reportName);
    }
}
