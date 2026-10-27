import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class StringWindowProblems {
    private StringWindowProblems() {
    }

    public static String longestPalindromicSubstring(String value) {
        if (value.isEmpty()) {
            return "";
        }
        int bestStart = 0;
        int bestLength = 1;
        for (int center = 0; center < value.length(); center++) {
            int oddLength = expand(value, center, center);
            int evenLength = expand(value, center, center + 1);
            int length = Math.max(oddLength, evenLength);
            if (length > bestLength) {
                bestLength = length;
                bestStart = center - (length - 1) / 2;
            }
        }
        return value.substring(bestStart, bestStart + bestLength);
    }

    private static int expand(String value, int left, int right) {
        while (left >= 0 && right < value.length() && value.charAt(left) == value.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    public static List<List<Integer>> threeSumZero(int[] values) {
        int[] sorted = values.clone();
        Arrays.sort(sorted);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < sorted.length - 2; i++) {
            if (i > 0 && sorted[i] == sorted[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = sorted.length - 1;
            while (left < right) {
                long sum = (long) sorted[i] + sorted[left] + sorted[right];
                if (sum == 0) {
                    result.add(Arrays.asList(sorted[i], sorted[left], sorted[right]));
                    int leftValue = sorted[left];
                    int rightValue = sorted[right];
                    while (left < right && sorted[left] == leftValue) {
                        left++;
                    }
                    while (left < right && sorted[right] == rightValue) {
                        right--;
                    }
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }

    public static String minimumWindowSubstring(String source, String target) {
        if (target.isEmpty() || source.length() < target.length()) {
            return "";
        }
        int[] needed = new int[Character.MAX_VALUE + 1];
        for (int i = 0; i < target.length(); i++) {
            needed[target.charAt(i)]++;
        }
        int remaining = target.length();
        int bestStart = 0;
        int bestLength = Integer.MAX_VALUE;
        int left = 0;
        for (int right = 0; right < source.length(); right++) {
            char added = source.charAt(right);
            if (needed[added] > 0) {
                remaining--;
            }
            needed[added]--;
            while (remaining == 0) {
                int length = right - left + 1;
                if (length < bestLength) {
                    bestStart = left;
                    bestLength = length;
                }
                char removed = source.charAt(left++);
                needed[removed]++;
                if (needed[removed] > 0) {
                    remaining++;
                }
            }
        }
        return bestLength == Integer.MAX_VALUE ? "" : source.substring(bestStart, bestStart + bestLength);
    }

    public static int removeDuplicatesFromSortedArray(int[] values) {
        if (values.length == 0) {
            return 0;
        }
        int uniqueCount = 1;
        for (int read = 1; read < values.length; read++) {
            if (values[read] != values[uniqueCount - 1]) {
                values[uniqueCount++] = values[read];
            }
        }
        return uniqueCount;
    }

}
