import java.util.*;

public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode mergedList = null, mergedListTail = null, top;
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((ln1, ln2) -> {
            return ln1.val - ln2.val;
        });
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] == null) {
                continue;
            }
            minHeap.add(lists[i]);
        }
        while (minHeap.size() != 0) {
            top = minHeap.poll();
            if (mergedList == null) {
                mergedList = new ListNode(top.val, null);
                mergedListTail = mergedList;
            } else {
                mergedListTail.next = new ListNode(top.val, null);
                mergedListTail = mergedListTail.next;
            }
            if (top.next != null) {
                minHeap.add(top.next);
            }
        }
        return mergedList;
    }

    /*
    static class ListNode {
        public int val;
        public ListNode next;
        
        ListNode() {}
        
        ListNode(int val) { 
            this.val = val; 
        }
        
        ListNode(int val, ListNode next) { 
            this.val = val; 
            this.next = next; 
        }
    } 
    */   
}
