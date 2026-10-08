import java.util.LinkedHashSet;
import java.util.OptionalInt;
import java.util.Set;

public final class ArrayOptimization {
    private ArrayOptimization() {
    }

    public static void rotateRight(int[] values, int k) {
        if (values.length == 0) {
            return;
        }
        int shift = Math.floorMod(k, values.length);
        reverse(values, 0, values.length - 1);
        reverse(values, 0, shift - 1);
        reverse(values, shift, values.length - 1);
    }

    private static void reverse(int[] values, int left, int right) {
        while (left < right) {
            int temp = values[left];
            values[left++] = values[right];
            values[right--] = temp;
        }
    }

    public static long maximumSubarraySum(int[] values) {
        if (values.length == 0) {
            throw new IllegalArgumentException("Array must not be empty");
        }
        long current = values[0];
        long maximum = current;
        for (int i = 1; i < values.length; i++) {
            current = Math.max(values[i], current + values[i]);
            maximum = Math.max(maximum, current);
        }
        return maximum;
    }

    public static Set<Integer> intersection(int[] first, int[] second) {
        Set<Integer> available = new LinkedHashSet<>();
        for (int value : second) {
            available.add(value);
        }
        Set<Integer> result = new LinkedHashSet<>();
        for (int value : first) {
            if (available.contains(value)) {
                result.add(value);
            }
        }
        return result;
    }

    public static OptionalInt majorityElement(int[] values) {
        int candidate = 0;
        int count = 0;
        for (int value : values) {
            if (count == 0) {
                candidate = value;
            }
            count += value == candidate ? 1 : -1;
        }
        count = 0;
        for (int value : values) {
            if (value == candidate) {
                count++;
            }
        }
        return count > values.length / 2 ? OptionalInt.of(candidate) : OptionalInt.empty();
    }
}
