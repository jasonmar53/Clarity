package test.martin.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

// Alex Martin

@RunWith(Suite.class)
@SuiteClasses({TestState.class, TestStateManager.class})
public class JUnitSuite {

}
