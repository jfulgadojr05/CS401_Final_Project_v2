package test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class StoreTestRunner{
    public static void main(String[] args){
        Result testResults = JUnitCore.runClasses(StoreGUITestSuite.class);

        for (Failure failure : testResults.getFailures()){
            System.out.println(failure.toString());
        }
        System.out.println(testResults.wasSuccessful());
    }
}
