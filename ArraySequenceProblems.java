import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class ArraySequenceProblems {
    private ArraySequenceProblems() {
    }

    public static int[] twoUniqueNumbers(int[] values) {
        int xor = 0;
        for (int value : values) {
            xor ^= value;
        }
        if (xor == 0) {
            throw new IllegalArgumentException("Expected exactly two distinct single-occurrence values");
        }
        int distinguishingBit = xor & -xor;
        int first = 0;
        int second = 0;
        for (int value : values) {
            if ((value & distinguishingBit) == 0) {
                first ^= value;
            } else {
                second ^= value;
            }
        }
        return new int[] {first, second};
    }

    public static int longestConsecutiveSequence(int[] values) {
        Set<Integer> numbers = new HashSet<>();
        for (int value : values) {
            numbers.add(value);
        }
        int longest = 0;
        for (int value : numbers) {
            if (value == Integer.MIN_VALUE || !numbers.contains(value - 1)) {
                int length = 1;
                int next = value;
                while (next != Integer.MAX_VALUE && numbers.contains(next + 1)) {
                    next++;
                    length++;
                }
                longest = Math.max(longest, length);
            }
        }
        return longest;
    }

    public static Map<Integer, Integer> elementFrequencies(int[] values) {
        Map<Integer, Integer> frequencies = new HashMap<>();
        for (int value : values) {
            frequencies.merge(value, 1, Integer::sum);
        }
        return frequencies;
    }

    public static List<int[]> subarraysWithSum(int[] values, long target) {
        Map<Long, List<Integer>> prefixIndices = new HashMap<>();
        prefixIndices.computeIfAbsent(0L, ignored -> new ArrayList<>()).add(-1);
        List<int[]> result = new ArrayList<>();
        long prefix = 0;
        for (int end = 0; end < values.length; end++) {
            prefix += values[end];
            List<Integer> starts = prefixIndices.get(prefix - target);
            if (starts != null) {
                for (int beforeStart : starts) {
                    result.add(new int[] {beforeStart + 1, end});
                }
            }
            prefixIndices.computeIfAbsent(prefix, ignored -> new ArrayList<>()).add(end);
        }
        return result;
    }
}
