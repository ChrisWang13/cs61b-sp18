import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the auto grader might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }
    @Test
    public void testPalindrome() {
        assertTrue(palindrome.isPalindrome("aaa"));
        assertTrue(palindrome.isPalindrome("a man, a plan, a canal: panama"));
        assertTrue(palindrome.isPalindrome("amanaplanacanalpanama"));
        assertTrue(palindrome.isPalindrome("   "));
    }
    @Test
    public void testOffBy1() {
        CharacterComparator cc = new OffByOne();
        assertTrue(palindrome.isPalindrome("", cc));
        assertTrue(palindrome.isPalindrome("a", cc));
        assertTrue(palindrome.isPalindrome("flake", cc));
        assertFalse(palindrome.isPalindrome("aabaa", cc));
        assertFalse(palindrome.isPalindrome("noon", cc));
    }
    @Test
    public void testOffBy5() {
        CharacterComparator cc = new OffByN(5);
        assertTrue(palindrome.isPalindrome("", cc));
        assertTrue(palindrome.isPalindrome("a", cc));
        assertTrue(palindrome.isPalindrome("bing", cc));
        assertFalse(palindrome.isPalindrome("aabaa", cc));
        assertFalse(palindrome.isPalindrome("noon", cc));
    }
}
