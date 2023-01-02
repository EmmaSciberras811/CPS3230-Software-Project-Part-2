package com.cps3230.alertsystem;
// needed to rn the test runner
import com.cps3230.alertsystem.enums.alertSystemEnums;
import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;
import nz.ac.waikato.modeljunit.GreedyTester;
import nz.ac.waikato.modeljunit.StopOnFailureListener;
import nz.ac.waikato.modeljunit.coverage.ActionCoverage;
import nz.ac.waikato.modeljunit.coverage.StateCoverage;
import nz.ac.waikato.modeljunit.coverage.TransitionPairCoverage;
import com.cps3230.screenscrapingAlerts;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;
import java.util.Random;

public class ScenarioAddAlertsModelTest implements FsmModel {
    // Creation of sut
    screenscrapingAlerts sut = new screenscrapingAlerts();
    alertSystemEnums fsmStates = alertSystemEnums.LOGGEDIN;
    // Chrome driver
    static WebDriver cd;

    // State Variables
    boolean goodlogin,newalert ,viewList ,alertListSize= false;
    int noOfTransitions = 5;

    /* Model testing */

    // Gives the state of the model at any time
    @Override
    public alertSystemEnums getState() {
        return fsmStates;
    }

    // Reset the model to its initial values
    @Override
    public void reset(boolean var1)
    {
        if(var1)
        {
            // Reset system under test
            sut = new screenscrapingAlerts();
        }
        goodlogin = false;
        newalert = false;
        viewList = false; // view the alert list
        alertListSize = false;
        fsmStates = alertSystemEnums.LOGGEDIN;
    }
    /* Guard
     Test login and add 6 new alerts to get alert count status and ensure we have only 5 (max) alerts not more  */
    public boolean create6AlertsGuard(){  // guard check the state
        return getState().equals(alertSystemEnums.LOGGEDIN);
    }

    /* Transition */
    public @Action void create6Alerts(){
        sut.creationOf6Alerts(); // Executing and updating system under test

        fsmStates = alertSystemEnums.NEWALERT; // Update state of Enum
        goodlogin = true; // Update variables
        newalert = true;

        // Assertion - check correspondence between sut and model via assumption via assertions
        // assertTrue could also be used instead but using assertEquals makes the assertion clearer in this case
        Assert.assertEquals(goodlogin && newalert, sut.checkAlertCount());
    }

    /* Test view alerts
     Guard */
    public boolean checkAlertListGuard(){
        return getState().equals(alertSystemEnums.LOGGEDIN);
    }

    /* Transition */
    public @Action void checkAlertList() {
        sut.viewalerts(); // update sut

        fsmStates = alertSystemEnums.VIEWALERTS; // update state
        goodlogin = true; // update variables
        viewList = true;

        Assert.assertEquals(goodlogin && viewList, sut.checkAlertStatus());
    }

    // Test runner - following from tutorial of model testing
    @Test
    public void Runner() {
        final GreedyTester tester = new GreedyTester(new ScenarioAddAlertsModelTest()); //Creates a test generator that can generate random walks. A greedy random walk gives preference to transitions that have never been taken before. Once all transitions out of a state have been taken, it behaves the same as a random walk.
        tester.setRandom(new Random());  //Allows for a random path each time the model is run.
        tester.buildGraph();  //Builds a model of our FSM to ensure that the coverage metrics are correct.
        tester.addListener(new StopOnFailureListener()); //This listener forces the test class to stop running as soon as a failure is encountered in the model.
        tester.addListener("verbose"); //This gives you printed statements of the transitions being performed along with the source and destination states.
        tester.addCoverageMetric(new TransitionPairCoverage()); //Records the transition pair coverage i.e. the number of paired transitions traversed during the execution of the test.
        tester.addCoverageMetric(new StateCoverage()); //Records the state coverage i.e. the number of states which have been visited during the execution of the test.
        tester.addCoverageMetric(new ActionCoverage()); //Records the number of @Action methods which have been executed during the execution of the test.
        tester.generate(noOfTransitions); //Generates xamount(10,50,500 etc ect) transitions
        tester.printCoverage();//Prints the coverage metrics specified above.
    }
    // Teardown method
    @AfterEach
    public void teardown(){
        cd.quit();
    }
}
