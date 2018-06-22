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
 * Unit tests for {{ cookiecutter.main_class }}Tool.
 */
public class {{ cookiecutter.main_class }}Test  {

    @Test
    public void testToolDoesNotOpenPandorasBox() {
        // TODO: test your implementation as if you actually got paid for it
    }
}