public final class CycleLinkedListProblems {
    private CycleLinkedListProblems() {
    }

    public static final class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static boolean hasCycle(Node head) {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    public static Node cycleStart(Node head) {
        Node slow = head;
        Node fast = head;
        do {
            if (fast == null || fast.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        } while (slow != fast);

        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public static Node mergeSortedLists(Node first, Node second) {
        Node dummy = new Node(0);
        Node tail = dummy;
        while (first != null && second != null) {
            if (first.value <= second.value) {
                tail.next = first;
                first = first.next;
            } else {
                tail.next = second;
                second = second.next;
            }
            tail = tail.next;
        }
        tail.next = first != null ? first : second;
        return dummy.next;
    }

    public static Node removeNthFromEnd(Node head, int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be positive");
        }
        Node dummy = new Node(0);
        dummy.next = head;
        Node fast = dummy;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
            if (fast == null) {
                throw new IllegalArgumentException("n exceeds the list length");
            }
        }
        Node slow = dummy;
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}
