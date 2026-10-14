import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class WindowAndSimpleLinkedListProblems {
    private WindowAndSimpleLinkedListProblems() {
    }

    public static List<Integer> distinctCountInEachWindow(int[] values, int windowSize) {
        if (windowSize < 1 || windowSize > values.length) {
            throw new IllegalArgumentException("Window size must be between 1 and array length");
        }
        Map<Integer, Integer> counts = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            counts.merge(values[i], 1, Integer::sum);
            if (i >= windowSize) {
                int outgoing = values[i - windowSize];
                int remaining = counts.get(outgoing) - 1;
                if (remaining == 0) {
                    counts.remove(outgoing);
                } else {
                    counts.put(outgoing, remaining);
                }
            }
            if (i >= windowSize - 1) {
                result.add(counts.size());
            }
        }
        return result;
    }

    public static int[] intersectionWithDuplicates(int[] first, int[] second) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int value : second) {
            counts.merge(value, 1, Integer::sum);
        }
        List<Integer> result = new ArrayList<>();
        for (int value : first) {
            int available = counts.getOrDefault(value, 0);
            if (available > 0) {
                result.add(value);
                if (available == 1) {
                    counts.remove(value);
                } else {
                    counts.put(value, available - 1);
                }
            }
        }
        int[] intersection = new int[result.size()];
        for (int i = 0; i < intersection.length; i++) {
            intersection[i] = result.get(i);
        }
        return intersection;
    }

    public static final class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node reverseList(Node head) {
        Node previous = null;
        Node current = head;
        while (current != null) {
            Node next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        return previous;
    }

    public static Node middleNode(Node head) {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
