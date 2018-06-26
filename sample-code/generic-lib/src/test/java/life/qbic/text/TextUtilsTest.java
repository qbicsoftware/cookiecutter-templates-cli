package life.qbic.text;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Unit tests.
 */
public class TextUtilsTest  {

    private TextUtils algorithm;

    @Before
    public void setUpTest() {
        algorithm = new TextUtils();
    }

    @Test
    public void testNull() {
        assertTrue(algorithm.isPalindrome(null));
    }

    @Test
    public void testEmpty() {
        assertTrue(algorithm.isPalindrome(""));
    }

    @Test
    public void testWhitespace() {
        assertTrue(algorithm.isPalindrome("      "));
    }

    @Test
    public void testWithWhitespace() {
        assertTrue(algorithm.isPalindrome("      abba "));
    }

    @Test
    public void testNonPalindrome() {
        assertFalse(algorithm.isPalindrome("this is not a palindrome"));
    }

    @Test
    public void testCaseInsensitive() {
        assertTrue(algorithm.isPalindrome("F123abccBa321f"));
    }
}