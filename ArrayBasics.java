import java.util.LinkedHashSet;
import java.util.OptionalInt;
import java.util.Set;

public final class ArrayBasics {
    private ArrayBasics() {
    }

    public static OptionalInt secondLargest(int[] values) {
        int largest = 0;
        int second = 0;
        boolean hasLargest = false;
        boolean hasSecond = false;
        for (int value : values) {
            if (!hasLargest || value > largest) {
                if (hasLargest) {
                    second = largest;
                    hasSecond = true;
                }
                largest = value;
                hasLargest = true;
            } else if (value < largest && (!hasSecond || value > second)) {
                second = value;
                hasSecond = true;
            }
        }
        return hasSecond ? OptionalInt.of(second) : OptionalInt.empty();
    }

    public static int missingNumber(int[] values, int n) {
        if (n < 1 || values.length != n - 1) {
            throw new IllegalArgumentException("Expected n - 1 values from the range 1..n");
        }
        long expected = (long) n * (n + 1) / 2;
        long actual = 0;
        for (int value : values) {
            if (value < 1 || value > n) {
                throw new IllegalArgumentException("Value outside the range 1..n: " + value);
            }
            actual += value;
        }
        return Math.toIntExact(expected - actual);
    }

    public static Set<Integer> findDuplicates(int[] values) {
        Set<Integer> seen = new LinkedHashSet<>();
        Set<Integer> duplicates = new LinkedHashSet<>();
        for (int value : values) {
            if (!seen.add(value)) {
                duplicates.add(value);
            }
        }
        return duplicates;
    }

    public static void moveZerosToEnd(int[] values) {
        int nextNonZero = 0;
        for (int value : values) {
            if (value != 0) {
                values[nextNonZero++] = value;
            }
        }
        while (nextNonZero < values.length) {
            values[nextNonZero++] = 0;
        }
    }
}
