import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;

public final class DSAProblemsSmokeTest {
    private DSAProblemsSmokeTest() {
    }

    public static void main(String[] args) {
        assert ArrayBasics.secondLargest(new int[] {-5, -1, -3}).getAsInt() == -3;
        assert ArrayBasics.missingNumber(new int[] {1, 2, 4}, 4) == 3;
        assert ArrayBasics.findDuplicates(new int[] {1, 2, 2, 1}).equals(Set.of(1, 2));
        int[] zeros = {0, 1, 0, 3};
        ArrayBasics.moveZerosToEnd(zeros);
        assert Arrays.equals(zeros, new int[] {1, 3, 0, 0});

        int[] rotated = {1, 2, 3, 4, 5};
        ArrayOptimization.rotateRight(rotated, 2);
        assert Arrays.equals(rotated, new int[] {4, 5, 1, 2, 3});
        assert ArrayOptimization.maximumSubarraySum(new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4}) == 6;
        assert ArrayOptimization.intersection(new int[] {1, 2, 2}, new int[] {2, 3}).equals(Set.of(2));
        assert ArrayOptimization.majorityElement(new int[] {1, 2, 1}).getAsInt() == 1;
        assert !ArrayOptimization.majorityElement(new int[] {1, 2, 3}).isPresent();

        assert Arrays.equals(StringAndStockProblems.twoSumIndices(new int[] {2, 7, 11}, 9), new int[] {0, 1});
        assert StringAndStockProblems.maximumStockProfit(new int[] {7, 1, 5, 3, 6, 4}) == 5;
        assert StringAndStockProblems.reverse("hello").equals("olleh");
        assert StringAndStockProblems.isPalindrome("racecar");

        assert StringFrequencyProblems.firstNonRepeatingCharacter("swiss").get() == 'w';
        assert StringFrequencyProblems.areAnagrams("listen", "silent");
        assert StringFrequencyProblems.longestSubstringWithoutRepeatingCharacters("abcabcbb") == 3;
        assert StringFrequencyProblems.characterFrequencies("aba").get('a') == 2;

        assert StringAndBinarySearchProblems.longestCommonPrefix(new String[] {"flower", "flow", "flight"}).equals("fl");
        assert StringAndBinarySearchProblems.groupAnagrams(new String[] {"eat", "tea", "tan"}).size() == 2;
        assert StringAndBinarySearchProblems.binarySearchIterative(new int[] {1, 3, 5}, 3) == 1;
        assert StringAndBinarySearchProblems.binarySearchRecursive(new int[] {1, 3, 5}, 4) == -1;

        assert Arrays.equals(SortingAndSearchProblems.firstAndLastOccurrence(new int[] {1, 2, 2, 2, 3}, 2),
                new int[] {1, 3});
        assert SortingAndSearchProblems.searchRotatedSortedArray(new int[] {4, 5, 6, 1, 2, 3}, 2) == 4;
        int[] mergeSorted = {4, 1, 3, 2};
        SortingAndSearchProblems.mergeSort(mergeSorted);
        assert Arrays.equals(mergeSorted, new int[] {1, 2, 3, 4});
        int[] quickSorted = {3, 3, -1, 4, 2};
        SortingAndSearchProblems.quickSort(quickSorted);
        assert Arrays.equals(quickSorted, new int[] {-1, 2, 3, 3, 4});

        int[] singles = ArraySequenceProblems.twoUniqueNumbers(new int[] {1, 2, 1, 3, 2, 5});
        Arrays.sort(singles);
        assert Arrays.equals(singles, new int[] {3, 5});
        assert ArraySequenceProblems.longestConsecutiveSequence(new int[] {100, 4, 200, 1, 3, 2}) == 4;
        assert ArraySequenceProblems.elementFrequencies(new int[] {1, 1, 2}).get(1) == 2;
        assert ArraySequenceProblems.subarraysWithSum(new int[] {1, 1, 1}, 2).size() == 2;

        assert WindowAndSimpleLinkedListProblems.distinctCountInEachWindow(
                new int[] {1, 2, 1, 3, 4, 2, 3}, 4).equals(Arrays.asList(3, 4, 4, 3));
        assert Arrays.equals(WindowAndSimpleLinkedListProblems.intersectionWithDuplicates(
                new int[] {1, 2, 2, 1}, new int[] {2, 2}), new int[] {2, 2});
        WindowAndSimpleLinkedListProblems.Node list = new WindowAndSimpleLinkedListProblems.Node(1);
        list.next = new WindowAndSimpleLinkedListProblems.Node(2);
        list.next.next = new WindowAndSimpleLinkedListProblems.Node(3);
        assert WindowAndSimpleLinkedListProblems.middleNode(list).value == 2;
        assert WindowAndSimpleLinkedListProblems.reverseList(list).value == 3;

        CycleLinkedListProblems.Node cycleHead = new CycleLinkedListProblems.Node(1);
        cycleHead.next = new CycleLinkedListProblems.Node(2);
        cycleHead.next.next = new CycleLinkedListProblems.Node(3);
        cycleHead.next.next.next = cycleHead.next;
        assert CycleLinkedListProblems.hasCycle(cycleHead);
        assert CycleLinkedListProblems.cycleStart(cycleHead) == cycleHead.next;
        CycleLinkedListProblems.Node first = new CycleLinkedListProblems.Node(1);
        first.next = new CycleLinkedListProblems.Node(3);
        CycleLinkedListProblems.Node second = new CycleLinkedListProblems.Node(2);
        second.next = new CycleLinkedListProblems.Node(4);
        assert CycleLinkedListProblems.mergeSortedLists(first, second).value == 1;
        CycleLinkedListProblems.Node removal = new CycleLinkedListProblems.Node(1);
        removal.next = new CycleLinkedListProblems.Node(2);
        removal.next.next = new CycleLinkedListProblems.Node(3);
        assert CycleLinkedListProblems.removeNthFromEnd(removal, 2).next.value == 3;

        StackQueueProblems.ArrayStack stack = new StackQueueProblems.ArrayStack(2);
        stack.push(4);
        assert stack.pop() == 4;
        StackQueueProblems.QueueUsingStacks queue = new StackQueueProblems.QueueUsingStacks();
        queue.enqueue(2);
        queue.enqueue(3);
        assert queue.dequeue() == 2;
        assert StackQueueProblems.hasValidParentheses("([]{})");
        assert Arrays.equals(StackQueueProblems.nextGreaterElements(new int[] {2, 1, 3}),
                new int[] {3, 3, -1});

        StackAndBacktrackingProblems.MinStack minStack = new StackAndBacktrackingProblems.MinStack();
        minStack.push(3);
        minStack.push(1);
        assert minStack.minimum() == 1;
        assert StackAndBacktrackingProblems.largestHistogramRectangle(new int[] {2, 1, 5, 6, 2, 3}) == 10;
        assert StackAndBacktrackingProblems.subsets(new int[] {1, 2}).size() == 4;
        assert StackAndBacktrackingProblems.stringPermutations("aab").size() == 3;

        assert TreeAndRecursionProblems.solveNQueens(4).size() == 2;
        assert TreeAndRecursionProblems.combinationSum(new int[] {2, 3, 6, 7}, 7).size() == 2;
        TreeAndRecursionProblems.TreeNode root = new TreeAndRecursionProblems.TreeNode(2);
        root.left = new TreeAndRecursionProblems.TreeNode(1);
        root.right = new TreeAndRecursionProblems.TreeNode(3);
        assert TreeAndRecursionProblems.maximumDepth(root) == 2;
        assert TreeAndRecursionProblems.inorder(root).equals(Arrays.asList(1, 2, 3));
        assert TreeAndRecursionProblems.preorder(root).equals(Arrays.asList(2, 1, 3));
        assert TreeAndRecursionProblems.postorder(root).equals(Arrays.asList(1, 3, 2));

        assert TreeAndDynamicProgrammingProblems.isValidBst(root);
        Optional<TreeAndRecursionProblems.TreeNode> ancestor =
                TreeAndDynamicProgrammingProblems.lowestCommonAncestor(root, root.left, root.right);
        assert ancestor.get() == root;
        assert TreeAndDynamicProgrammingProblems.fibonacci(10) == 55;
        assert TreeAndDynamicProgrammingProblems.zeroOneKnapsack(
                new int[] {1, 3, 4, 5}, new int[] {1, 4, 5, 7}, 7) == 9;
        System.out.println("All 52 DSA solution smoke checks passed.");
    }
}
