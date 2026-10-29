import java.util.ArrayDeque;
import java.util.Deque;
import java.util.IdentityHashMap;
import java.util.Map;

public final class MultilevelListAndRandomCloneProblems {
    private MultilevelListAndRandomCloneProblems() {
    }

    public static final class MultiLevelNode {
        public int value;
        public MultiLevelNode previous;
        public MultiLevelNode next;
        public MultiLevelNode child;

        public MultiLevelNode(int value) {
            this.value = value;
        }
    }

    public static MultiLevelNode flatten(MultiLevelNode head) {
        if (head == null) {
            return null;
        }
        Deque<MultiLevelNode> deferredNext = new ArrayDeque<>();
        MultiLevelNode current = head;
        while (current != null) {
            if (current.child != null) {
                if (current.next != null) {
                    deferredNext.push(current.next);
                }
                current.next = current.child;
                current.child.previous = current;
                current.child = null;
            } else if (current.next == null && !deferredNext.isEmpty()) {
                current.next = deferredNext.pop();
                current.next.previous = current;
            }
            current = current.next;
        }
        return head;
    }

    public static final class RandomNode {
        public int value;
        public RandomNode next;
        public RandomNode random;

        public RandomNode(int value) {
            this.value = value;
        }
    }

    public static RandomNode cloneRandomList(RandomNode head) {
        if (head == null) {
            return null;
        }
        Map<RandomNode, RandomNode> copies = new IdentityHashMap<>();
        Deque<RandomNode> pending = new ArrayDeque<>();
        copies.put(head, new RandomNode(head.value));
        pending.add(head);
        while (!pending.isEmpty()) {
            RandomNode original = pending.remove();
            RandomNode copy = copies.get(original);
            copy.next = getOrCreateCopy(original.next, copies, pending);
            copy.random = getOrCreateCopy(original.random, copies, pending);
        }
        return copies.get(head);
    }

    private static RandomNode getOrCreateCopy(
            RandomNode original, Map<RandomNode, RandomNode> copies, Deque<RandomNode> pending) {
        if (original == null) {
            return null;
        }
        RandomNode copy = copies.get(original);
        if (copy == null) {
            copy = new RandomNode(original.value);
            copies.put(original, copy);
            pending.add(original);
        }
        return copy;
    }
}
