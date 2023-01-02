package com.cps3230.alertsystem;
import java.util.Random;
import org.openqa.selenium.WebDriver;
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


public class ScenarioDeleteModelTestAlerts implements FsmModel {
    screenscrapingAlerts sut = new screenscrapingAlerts();
    alertSystemEnums fsmStates = alertSystemEnums.LOGGEDIN;
    static WebDriver cd;
    // no alerts to be found after delete req
    int noalerts = 0; // size of alerts list should be 0 after del
    // number  of transitions used in test runner
    int transitions = 10;
    boolean goodlogin, newAlert, delAll, alertListSize = false;

    @Override // Should give the state of the model at all times
    public alertSystemEnums getState() {
        return fsmStates;
    }

    @Override // Reset to initial values
    public void reset(boolean var) {
        if(var){
            sut = new screenscrapingAlerts();
        }
        goodlogin= false;
        delAll = false;
        alertListSize = false;
        newAlert = false;
        fsmStates = alertSystemEnums.LOGGEDIN;
    }

    /* Guard */
   // Delete all the alerts
    public boolean delReqOnAlertListGuard(){
        return getState().equals(alertSystemEnums.LOGGEDIN);
    }

    /* Transition */
    public @Action void delReqOnAlertList(){
        sut.delReq();
        fsmStates = alertSystemEnums.DELALERTS;
        goodlogin = true;
        delAll = true; // removed all alerts
        alertListSize = true;

        // Check correspondence between sut and the model via assertions
        // The transition needs multiple asserts to ensure we have a valid delete on logged-in user and ensure the list is empty after delete req
        Assert.assertEquals(goodlogin && delAll, sut.getDelStatus());
        Assert.assertEquals(noalerts, sut.noAlertsIfSuccess()); // empty list as a del request removes all alerts
        Assert.assertTrue(alertListSize);
    }

    /* Guard */
    // Ensure alert list is empty
    public boolean emptyMarketAlertUMGuard(){
        return getState().equals(alertSystemEnums.DELALERTS);
    }

    /* Transition */
    public @Action void emptyMarketAlertUM(){
        sut.noAlertsIfSuccess();
        fsmStates = alertSystemEnums.DELALERTS;
        goodlogin = true;
        delAll = true;
        alertListSize = true;

        Assert.assertEquals(0, sut.noAlertsIfSuccess());
        Assert.assertTrue(alertListSize);
    }

    /* Guard */
    // Add to empty alert list
    public boolean newAlertsGuard(){
        return getState().equals(alertSystemEnums.LOGGEDIN);
    }

    /* Transition */
    public @Action void newAlerts(){
        sut.creationSomeAlertsToDel();
        fsmStates = alertSystemEnums.NEWALERT;
        goodlogin = true;
        newAlert = true; // Add new alerts
        alertListSize = true;
        int maxalerts = 5;

        Assert.assertEquals(goodlogin && newAlert, sut.checkIfAlertsPosted());
        Assert.assertEquals(maxalerts, sut.getAlertListSize());
        Assert.assertTrue(alertListSize);
    }

    /* Guard */
    // Delete again
    public boolean deleteAgainGuard(){
        return getState().equals(alertSystemEnums.NEWALERT);
    }

    /* Transition */
    public @Action void deleteAgain(){
        sut.delReq();

        fsmStates = alertSystemEnums.DELALERTS;
        goodlogin = true;
        delAll = true; // deleted the alerts
        Assert.assertEquals(goodlogin && delAll, sut.getDelStatus());
        Assert.assertEquals(noalerts, sut.noAlertsIfSuccess());
    }

    // Run test runner
    @Test
    public void Runner() {
        final GreedyTester tester = new GreedyTester(new ScenarioDeleteModelTestAlerts());  //Creates a test generator that can generate random walks. A greedy random walk gives preference to transitions that have never been taken before. Once all transitions out of a state have been taken, it behaves the same as a random walk.
        tester.setRandom(new Random()); //Allows for a random path each time the model is run.
        tester.buildGraph();  //Builds a model of our FSM to ensure that the coverage metrics are correct.
        tester.addListener(new StopOnFailureListener());   //This listener forces the test class to stop running as soon as a failure is encountered in the model.
        tester.addListener("verbose"); //This gives you printed statements of the transitions being performed along with the source and destination states.
        tester.addCoverageMetric(new TransitionPairCoverage()); //Records the transition pair coverage i.e. the number of paired transitions traversed during the execution of the test.
        tester.addCoverageMetric(new StateCoverage()); //Records the state coverage i.e. the number of states which have been visited during the execution of the test.
        tester.addCoverageMetric(new ActionCoverage());  //Records the number of @Action methods which have been executed during the execution of the test.
        tester.generate(transitions);  //Generates xamount of transitions
        tester.printCoverage();  //Prints the coverage metrics specified above.
    }

    // Teardown driver method
    @AfterEach
    public void teardown(){
        cd.quit();
    }

}

