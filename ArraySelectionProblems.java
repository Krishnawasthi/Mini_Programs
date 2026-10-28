import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public final class ArraySelectionProblems {
    private ArraySelectionProblems() {
    }

    public static int[][] mergeOverlappingIntervals(int[][] intervals) {
        int[][] sorted = new int[intervals.length][2];
        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i] == null || intervals[i].length != 2 || intervals[i][0] > intervals[i][1]) {
                throw new IllegalArgumentException("Each interval must contain ordered start and end values");
            }
            sorted[i] = intervals[i].clone();
        }
        java.util.Arrays.sort(sorted, Comparator.comparingInt(interval -> interval[0]));
        List<int[]> merged = new ArrayList<>();
        for (int[] interval : sorted) {
            if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < interval[0]) {
                merged.add(interval);
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(
                        merged.get(merged.size() - 1)[1], interval[1]);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    public static long trappedRainWater(int[] heights) {
        int left = 0;
        int right = heights.length - 1;
        int leftMaximum = 0;
        int rightMaximum = 0;
        long trapped = 0;
        for (int height : heights) {
            if (height < 0) {
                throw new IllegalArgumentException("Heights must not be negative");
            }
        }
        while (left < right) {
            if (heights[left] <= heights[right]) {
                leftMaximum = Math.max(leftMaximum, heights[left]);
                trapped += leftMaximum - heights[left++];
            } else {
                rightMaximum = Math.max(rightMaximum, heights[right]);
                trapped += rightMaximum - heights[right--];
            }
        }
        return trapped;
    }

    public static void sortColors(int[] values) {
        for (int value : values) {
            if (value < 0 || value > 2) {
                throw new IllegalArgumentException("Array values must be 0, 1, or 2");
            }
        }
        int low = 0;
        int current = 0;
        int high = values.length - 1;
        while (current <= high) {
            if (values[current] == 0) {
                swap(values, low++, current++);
            } else if (values[current] == 1) {
                current++;
            } else {
                swap(values, current, high--);
            }
        }
    }

    private static void swap(int[] values, int first, int second) {
        int temporary = values[first];
        values[first] = values[second];
        values[second] = temporary;
    }

    public static int kthLargest(int[] values, int k) {
        if (k < 1 || k > values.length) {
            throw new IllegalArgumentException("k must be between 1 and the array length");
        }
        PriorityQueue<Integer> smallestFirst = new PriorityQueue<>();
        for (int value : values) {
            smallestFirst.offer(value);
            if (smallestFirst.size() > k) {
                smallestFirst.poll();
            }
        }
        return smallestFirst.element();
    }
}
