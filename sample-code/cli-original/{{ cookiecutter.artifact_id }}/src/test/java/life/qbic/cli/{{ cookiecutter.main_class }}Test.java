package life.qbic.cli;

import org.apache.logging.log4j.Logger;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.contrib.java.lang.system.Assertion;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import org.powermock.reflect.Whitebox;

import picocli.CommandLine.MissingParameterException;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests.
 */
public class {{ cookiecutter.main_class }}Test  {

    @Mock
    private Logger mockLogger;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();


    @BeforeClass
    public static void loggerSetUp() {
      System.setProperty("log4j.defaultInitOverride", Boolean.toString(true));
      System.setProperty("log4j.ignoreTCL", Boolean.toString(true));
    }
  
    @Before
    public void setUpTest() {
        // inject mock logger
        Whitebox.setInternalState({{ cookiecutter.main_class }}.class, "LOG", mockLogger);
    }
  
    @Test
    public void testMissingUrl() {
        exit.expectSystemExitWithStatus(-1);
        exit.checkAssertionAfterwards(new Assertion() {
            public void checkAssertion() {
                Mockito.verify(mockLogger).error(ArgumentMatchers.contains("Missing"));
                Mockito.verify(mockLogger).error(ArgumentMatchers.contains("--url"));
            }
        });

        {{ cookiecutter.main_class }}.main(new String[] {"--username", "test", "--pass", "test"});
    }

    @Test
    public void testMissingName() {
        exit.expectSystemExitWithStatus(-1);
        exit.checkAssertionAfterwards(new Assertion() {
            public void checkAssertion() {
                Mockito.verify(mockLogger).error(ArgumentMatchers.contains("Missing"));
                Mockito.verify(mockLogger).error(ArgumentMatchers.contains("--username"));
            }
        });

        {{ cookiecutter.main_class }}.main(new String[] {"--url", "http://test.com", "--pass", "test"});
    }

    @Test
    public void testMissingPassword() {
        exit.expectSystemExitWithStatus(-1);
        exit.checkAssertionAfterwards(new Assertion() {
            public void checkAssertion() {
                Mockito.verify(mockLogger).error(ArgumentMatchers.contains("Missing"));
                Mockito.verify(mockLogger).error(ArgumentMatchers.contains("--pass"));
            }
        });
        
        {{ cookiecutter.main_class }}.main(new String[] {"--username", "test", "--url", "http://test.com"});
    }

    @Test
    public void testPrintVersion() {
        exit.expectSystemExitWithStatus(0);
        exit.checkAssertionAfterwards(new Assertion() {
            public void checkAssertion() {
                // check that version has been loaded from tool.properties
                Mockito.verify(mockLogger).info(ArgumentMatchers.anyString(), ArgumentMatchers.eq("1.2.3-SNAPSHOT"));
            }
        });
        
        {{ cookiecutter.main_class }}.main(new String[] {"--version"});
    }

    @Test
    public void testOpenBISOffline() {
        final String url = "http://openbis.test.offline";
        exit.expectSystemExitWithStatus(-1);
        exit.checkAssertionAfterwards(new Assertion() {
            public void checkAssertion() {
                Mockito.verify(mockLogger).error(ArgumentMatchers.contains("Could not establish connection"), ArgumentMatchers.eq(url), ArgumentMatchers.anyString());
            }
        });
        
        {{ cookiecutter.main_class }}.main(new String[] {"--username", "test", "--url", url, "--pass", "*****"});
    }
    
}