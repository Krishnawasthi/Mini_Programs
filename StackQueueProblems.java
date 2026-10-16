import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

public final class StackQueueProblems {
    private StackQueueProblems() {
    }

    public static final class ArrayStack {
        private final int[] values;
        private int size;

        public ArrayStack(int capacity) {
            if (capacity < 0) {
                throw new IllegalArgumentException("Capacity must not be negative");
            }
            values = new int[capacity];
        }

        public void push(int value) {
            if (size == values.length) {
                throw new IllegalStateException("Stack is full");
            }
            values[size++] = value;
        }

        public int pop() {
            ensureNotEmpty();
            return values[--size];
        }

        public int peek() {
            ensureNotEmpty();
            return values[size - 1];
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        private void ensureNotEmpty() {
            if (isEmpty()) {
                throw new NoSuchElementException("Stack is empty");
            }
        }
    }

    public static final class QueueUsingStacks {
        private final Deque<Integer> incoming = new ArrayDeque<>();
        private final Deque<Integer> outgoing = new ArrayDeque<>();

        public void enqueue(int value) {
            incoming.push(value);
        }

        public int dequeue() {
            transferIfNeeded();
            if (outgoing.isEmpty()) {
                throw new NoSuchElementException("Queue is empty");
            }
            return outgoing.pop();
        }

        public int peek() {
            transferIfNeeded();
            if (outgoing.isEmpty()) {
                throw new NoSuchElementException("Queue is empty");
            }
            return outgoing.peek();
        }

        public boolean isEmpty() {
            return incoming.isEmpty() && outgoing.isEmpty();
        }

        private void transferIfNeeded() {
            if (outgoing.isEmpty()) {
                while (!incoming.isEmpty()) {
                    outgoing.push(incoming.pop());
                }
            }
        }
    }

    public static boolean hasValidParentheses(String value) {
        Deque<Character> openings = new ArrayDeque<>();
        for (int i = 0; i < value.length(); i++) {
            char character = value.charAt(i);
            if (character == '(' || character == '[' || character == '{') {
                openings.push(character);
            } else if (character == ')' || character == ']' || character == '}') {
                if (openings.isEmpty() || !matches(openings.pop(), character)) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return openings.isEmpty();
    }

    private static boolean matches(char opening, char closing) {
        return opening == '(' && closing == ')'
                || opening == '[' && closing == ']'
                || opening == '{' && closing == '}';
    }

    public static int[] nextGreaterElements(int[] values) {
        int[] nextGreater = new int[values.length];
        Deque<Integer> indices = new ArrayDeque<>();
        for (int i = 0; i < values.length; i++) {
            while (!indices.isEmpty() && values[indices.peek()] < values[i]) {
                nextGreater[indices.pop()] = values[i];
            }
            indices.push(i);
        }
        while (!indices.isEmpty()) {
            nextGreater[indices.pop()] = -1;
        }
        return nextGreater;
    }
}
