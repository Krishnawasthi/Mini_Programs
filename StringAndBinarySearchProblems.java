import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class StringAndBinarySearchProblems {
    private StringAndBinarySearchProblems() {
    }

    public static String longestCommonPrefix(String[] values) {
        if (values.length == 0) {
            return "";
        }
        String prefix = values[0];
        for (int i = 1; i < values.length && !prefix.isEmpty(); i++) {
            while (!values[i].startsWith(prefix)) {
                prefix = prefix.substring(0, prefix.length() - 1);
            }
        }
        return prefix;
    }

    public static List<List<String>> groupAnagrams(String[] values) {
        Map<String, List<String>> groups = new LinkedHashMap<>();
        for (String value : values) {
            char[] keyCharacters = value.toCharArray();
            Arrays.sort(keyCharacters);
            String key = new String(keyCharacters);
            groups.computeIfAbsent(key, ignored -> new ArrayList<>()).add(value);
        }
        return new ArrayList<>(groups.values());
    }

    public static int binarySearchIterative(int[] sortedValues, int target) {
        int low = 0;
        int high = sortedValues.length - 1;
        while (low <= high) {
            int middle = low + (high - low) / 2;
            if (sortedValues[middle] == target) {
                return middle;
            }
            if (sortedValues[middle] < target) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }
        return -1;
    }

    public static int binarySearchRecursive(int[] sortedValues, int target) {
        return binarySearchRecursive(sortedValues, target, 0, sortedValues.length - 1);
    }

    private static int binarySearchRecursive(int[] values, int target, int low, int high) {
        if (low > high) {
            return -1;
        }
        int middle = low + (high - low) / 2;
        if (values[middle] == target) {
            return middle;
        }
        return values[middle] < target
                ? binarySearchRecursive(values, target, middle + 1, high)
                : binarySearchRecursive(values, target, low, middle - 1);
    }
}
