public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        LinkedListDeque<Character> res = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            res.addLast(word.charAt(i));
        }
        return res;
    }

    private boolean isPalindromeHelper(Deque<Character> d) {
        if (d.isEmpty() || d.size() == 1) {
            return true;
        }
        Character first = d.removeFirst();
        Character last = d.removeLast();
        if (!first.equals(last)) {
            return false;
        }
        return isPalindromeHelper(d);
    }
    public boolean isPalindrome(String word) {
        /* Using Deque recursively */
        return isPalindromeHelper(wordToDeque(word));

        /* Without using Deque
        if (word.isEmpty() || word.length() == 1) {
            return true;
        }
        for (int i = 0;i <= (word.length() - 1) / 2; i++) {
            if (word.charAt(i) != word.charAt(word.length() - 1 - i)) {
                return false;
            }
        }
        return true;
        */
    }

    private boolean isPalindromeHelperCc(Deque<Character> d, CharacterComparator cc) {
        if (d.isEmpty() || d.size() == 1) {
            return true;
        }
        Character first = d.removeFirst();
        Character last = d.removeLast();
        if (!cc.equalChars(first, last)) {
            return false;
        }
        return isPalindromeHelperCc(d, cc);
    }
    public boolean isPalindrome(String word, CharacterComparator cc) {
        return isPalindromeHelperCc(wordToDeque(word), cc);
    }
}
