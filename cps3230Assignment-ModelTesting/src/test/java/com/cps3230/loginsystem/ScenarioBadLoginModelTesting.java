package com.cps3230.loginsystem;
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
import org.openqa.selenium.WebDriver;

public class ScenarioBadLoginModelTesting implements FsmModel {

    // Setting the sut
    screenscrappingLogin sut = new screenscrappingLogin();
    // Number of transitions used n test runner
    int noOfTransitions = 5;
    // Starting state = logged out
    loginEnums fsmStates = loginEnums.LOGGEDOUT;
    // Chrome Driver
    static WebDriver driver;

    // State Variables
    boolean validLogin, alertScreen = false;
    boolean loginScreen = true; // By default, set to true

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
    // Bad login via invalid user id was entered to loggin into marketalertum
    public boolean badloginGuard(){
        return getState().equals(loginEnums.LOGGEDOUT);
    }

    // Transition
    public @Action void badlogin(){
        sut.badlogin();
        fsmStates = loginEnums.LOGGEDOUT;
        loginScreen = true; //  Re-enter the user id
        // Checking correspondence between sut and model via assertions
        Assert.assertTrue(sut.userNotLoggedinIEinLoginPage()); // inLoginPAge = true since goodlogin = false and alertpage = false
        Assert.assertFalse(sut.getStateofGoodlogin());
    }

    // Running the test runner
    @Test
    public void Runner() {
        // ollowing model testing tutorial
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
        driver.quit();
    }
}
