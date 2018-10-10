package life.qbic.text;

import org.apache.commons.lang3.StringUtils;

/**
 * Text utilities.
 */
public class TextUtils {

    /**
     * Checks whether the passed argument is a palindrome. Any leading/trailing whitespace will be ignored. 
     * 
     * @param word the word to check.
     * 
     * @return {@code true} if the passed word is a palindrome, {@code false} otherwise.
     * 
     */
    public boolean isPalindrome(final String word) {
        boolean isPalindrome = false;
        if (StringUtils.isBlank(word)) {
            isPalindrome = true;
        } else {
            final String cleanWord = StringUtils.trim(word).toLowerCase();
            isPalindrome = cleanWord.equals(StringUtils.reverse(cleanWord));
        }
        return isPalindrome;
    }
}