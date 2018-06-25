package life.qbic.portal;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import com.vaadin.ui.TextField;

/**
 * Tests for algorithm implemented in {@ PortalUtilsSample}.
 */
public class PortalUtilsTest {

    private TextField a;
    private TextField b;
    private PortalUtils algorithm;

    @Before
    public void setUpTest() {
        algorithm = new PortalUtils();
        a = new TextField();
        b = new TextField();
    }

    @Test
    public void testNormalSum() {
        a.setValue("3.0");
        b.setValue("5.0");
        assertEquals(8.0, algorithm.sum(a, b), 0.0);
    }

    @Test
    public void testSumZero() {
        a.setValue("-3.0");
        b.setValue("3.0");
        assertEquals(0.0, algorithm.sum(a, b), 0.0);
    }

    @Test
    public void testIntegers() {
        a.setValue("1");
        b.setValue("2.5");
        assertEquals(3.5, algorithm.sum(a, b), 0.0);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testOneInvalidNumber() {
        a.setValue("3a.0");
        b.setValue("5.0");
        algorithm.sum(a, b);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testAnotherInvalidNumber() {
        a.setValue("3.0");
        b.setValue("5a.0");
        algorithm.sum(a, b);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testAllInvalidNumbers() {
        a.setValue("3a.0");
        b.setValue("a5.0");
        algorithm.sum(a, b);
    }

    @Test(expected=NullPointerException.class)
    public void testNullField() {
        a = null;
        algorithm.sum(a, b);
    }

    @Test(expected=NullPointerException.class)
    public void testAnotherNullField() {
        b = null;
        algorithm.sum(a, b);
    }

    @Test(expected=NullPointerException.class)
    public void testAllNullFields() {
        a = b = null;
        algorithm.sum(a, b);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testNullValue() {
        a.setValue(null);
        b.setValue("12.0");
        algorithm.sum(a, b);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testAnotherNullValue() {
        a.setValue("12.0");
        b.setValue(null);
        algorithm.sum(a, b);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testAllNullValues() {
        a.setValue(null);
        b.setValue(null);
        algorithm.sum(a, b);
    }

}