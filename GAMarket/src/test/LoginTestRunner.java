package test;


import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class LoginTestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(LoginTestSuite.class);
        for (Failure failure : ((Result) result).getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}
