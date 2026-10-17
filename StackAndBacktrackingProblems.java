import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public final class StackAndBacktrackingProblems {
    private StackAndBacktrackingProblems() {
    }

    public static final class MinStack {
        private final Deque<Integer> values = new ArrayDeque<>();
        private final Deque<Integer> minimums = new ArrayDeque<>();

        public void push(int value) {
            values.push(value);
            if (minimums.isEmpty() || value <= minimums.peek()) {
                minimums.push(value);
            }
        }

        public int pop() {
            ensureNotEmpty();
            int value = values.pop();
            if (value == minimums.peek()) {
                minimums.pop();
            }
            return value;
        }

        public int top() {
            ensureNotEmpty();
            return values.peek();
        }

        public int minimum() {
            ensureNotEmpty();
            return minimums.peek();
        }

        private void ensureNotEmpty() {
            if (values.isEmpty()) {
                throw new java.util.NoSuchElementException("Stack is empty");
            }
        }
    }

    public static long largestHistogramRectangle(int[] heights) {
        Deque<Integer> increasing = new ArrayDeque<>();
        long largest = 0;
        for (int i = 0; i <= heights.length; i++) {
            int height = i == heights.length ? 0 : heights[i];
            if (height < 0) {
                throw new IllegalArgumentException("Histogram heights must not be negative");
            }
            while (!increasing.isEmpty() && heights[increasing.peek()] > height) {
                int barHeight = heights[increasing.pop()];
                int left = increasing.isEmpty() ? -1 : increasing.peek();
                largest = Math.max(largest, (long) barHeight * (i - left - 1));
            }
            increasing.push(i);
        }
        return largest;
    }

    public static List<List<Integer>> subsets(int[] values) {
        List<List<Integer>> result = new ArrayList<>();
        buildSubsets(values, 0, new ArrayList<>(), result);
        return result;
    }

    private static void buildSubsets(
            int[] values, int index, List<Integer> current, List<List<Integer>> result) {
        if (index == values.length) {
            result.add(new ArrayList<>(current));
            return;
        }
        buildSubsets(values, index + 1, current, result);
        current.add(values[index]);
        buildSubsets(values, index + 1, current, result);
        current.remove(current.size() - 1);
    }

    public static List<String> stringPermutations(String value) {
        char[] characters = value.toCharArray();
        Arrays.sort(characters);
        List<String> result = new ArrayList<>();
        permute(characters, new boolean[characters.length], new StringBuilder(), result);
        return result;
    }

    private static void permute(
            char[] characters, boolean[] used, StringBuilder current, List<String> result) {
        if (current.length() == characters.length) {
            result.add(current.toString());
            return;
        }
        for (int i = 0; i < characters.length; i++) {
            if (used[i] || (i > 0 && characters[i] == characters[i - 1] && !used[i - 1])) {
                continue;
            }
            used[i] = true;
            current.append(characters[i]);
            permute(characters, used, current, result);
            current.deleteCharAt(current.length() - 1);
            used[i] = false;
        }
    }
}
