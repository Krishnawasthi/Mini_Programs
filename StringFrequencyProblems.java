import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public final class StringFrequencyProblems {
    private StringFrequencyProblems() {
    }

    public static Optional<Character> firstNonRepeatingCharacter(String value) {
        Map<Character, Integer> counts = characterFrequencies(value);
        for (int i = 0; i < value.length(); i++) {
            char character = value.charAt(i);
            if (counts.get(character) == 1) {
                return Optional.of(character);
            }
        }
        return Optional.empty();
    }

    public static boolean areAnagrams(String first, String second) {
        if (first.length() != second.length()) {
            return false;
        }
        Map<Character, Integer> counts = new HashMap<>();
        for (int i = 0; i < first.length(); i++) {
            counts.merge(first.charAt(i), 1, Integer::sum);
            counts.merge(second.charAt(i), -1, Integer::sum);
        }
        for (int count : counts.values()) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

    public static int longestSubstringWithoutRepeatingCharacters(String value) {
        Map<Character, Integer> lastSeen = new HashMap<>();
        int start = 0;
        int longest = 0;
        for (int i = 0; i < value.length(); i++) {
            Integer previous = lastSeen.put(value.charAt(i), i);
            if (previous != null) {
                start = Math.max(start, previous + 1);
            }
            longest = Math.max(longest, i - start + 1);
        }
        return longest;
    }

    public static Map<Character, Integer> characterFrequencies(String value) {
        Map<Character, Integer> frequencies = new LinkedHashMap<>();
        for (int i = 0; i < value.length(); i++) {
            frequencies.merge(value.charAt(i), 1, Integer::sum);
        }
        return frequencies;
    }
}
