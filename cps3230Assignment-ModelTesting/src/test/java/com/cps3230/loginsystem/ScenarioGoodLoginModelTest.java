package com.cps3230.loginsystem;
import org.openqa.selenium.WebDriver;
import java.util.Random;
import com.cps3230.loginsystem.enums.loginEnums;
import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;
import nz.ac.waikato.modeljunit.GreedyTester;
import nz.ac.waikato.modeljunit.StopOnFailureListener;
import nz.ac.waikato.modeljunit.coverage.ActionCoverage;
import nz.ac.waikato.modeljunit.coverage.StateCoverage;
import nz.ac.waikato.modeljunit.coverage.TransitionPairCoverage;
import com.cps3230.screenscrappingLogin;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

public class ScenarioGoodLoginModelTest implements FsmModel {
    // State Variables
    boolean loginScreen = true; // by default set to true, as user is initial viewing the Login Page
    boolean validLogin, alertScreen = false;
    screenscrappingLogin sut = new screenscrappingLogin();
    int noOfTransitions = 5;
    // Starting state = logged out
    loginEnums fsmStates = loginEnums.LOGGEDOUT;
    // Chrome driver
    static WebDriver cd;

    // Model testing

    @Override // Give the state of model at any time
    public loginEnums getState() {
        return fsmStates;
    }

    @Override // Should reset the model to initial values
    public void reset(boolean var) {
        if(var){
            sut = new screenscrappingLogin();
        }
        validLogin = false;
        alertScreen = false;
        loginScreen = false;
        fsmStates = loginEnums.LOGGEDOUT; // starting state
    }

    // Guard
    // When user is successfully logged in the user can see the alert page
    public boolean goodLoginGuard(){
        return getState().equals(loginEnums.LOGGEDOUT);
    }

    // Transition
    public @Action void goodLogin(){
        // Checking correspondence between sut and model via assertions
        sut.correctUserIdGoodLogin();
        fsmStates = loginEnums.LOGGEDIN;// update state
        Assert.assertTrue( sut.getStateofGoodlogin()); // goodlogin and
        Assert.assertTrue( sut.viewAlertsPage()); // alertpage = true
    }

    // Running the test runner
    @Test
    public void Runner() {
        // Following model testing tutorial
        final GreedyTester tester = new GreedyTester(new ScenarioGoodLoginModelTest());
        tester.setRandom(new Random());
        tester.buildGraph();
        tester.addListener(new StopOnFailureListener());
        tester.addListener("verbose");
        tester.addCoverageMetric(new TransitionPairCoverage());
        tester.addCoverageMetric(new StateCoverage());
        tester.addCoverageMetric(new ActionCoverage());
        tester.generate(noOfTransitions);
        tester.printCoverage();
    }

    // Teardown driver code
    @AfterEach
    public void teardown(){
        cd.quit();
    }
}
