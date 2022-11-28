public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> res = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); ++i) {
            res.addLast(word.charAt(i));
        }
        return res;
    }
    public boolean isPalindrome(String word) {
        // 2. Head and tail solution
        int head = 0, tail = word.length() - 1;
        // break loop when head = tail + 1
        while (head <= tail) {
            // Use while loop might cause index out of bound,
            // boundary check is needed, also character needs to be updated.
            // Use [if else if] to check one character at a time.
            if (!Character.isLetterOrDigit(word.charAt(head))) {
                head = head + 1;
            } else if (!Character.isLetterOrDigit(word.charAt(tail))) {
                tail = tail - 1;
            } else if (word.charAt(head) != word.charAt(tail)) {
                return false;
            } else {
                // update character
                head = head + 1;
                tail = tail - 1;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        // 1. Deque based solution
        Deque<Character> d = wordToDeque(word);
        while (d.size() > 1) {
            if (!cc.equalChars(d.removeLast(), d.removeFirst())) {
                return false;
            }
        }
        return true;
    }
    
}
