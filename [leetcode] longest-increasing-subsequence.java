public class Solution {
    public int lengthOfLIS(int[] nums) {
        int min = nums[0], max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }
        // We are going to use values as index later in the fenwick tree.
        // So all values are normalized. Since value - 1 can also be used
        // as index, the least value we can have is 1. Hence adding by min + 1.
        for (int i = 0; i < nums.length; i++) {
            nums[i] += (-min+1);
        }
        // FenwickTree here backs an array A, where 
        // A[val] gives the max length of subsequence
        // ending at value val. Instead of sum, 
        // FenwickTree.getPrefix(val) here provides the
        // max in A[0..val].
        FenwickTree f = new FenwickTree(max-min+2);
        int maxLengthOfLis;
        for (int i = 0; i < nums.length; i++) {
            maxLengthOfLis = f.getPrefixMax(nums[i]-1);
            f.addValueAt(nums[i], maxLengthOfLis + 1);
        }
        return f.getPrefixMax(max-min+1);
    }

    public static class FenwickTree {
        // tree[i] contains a value which is max of a[i] + a[i-1] + ... + a[i'+1]
        // i' is obtained by setting the least significant 1 (set) bit of i to 0
        // this array is referred to as tree because if atleast one index of a 
        // covered by tree[i] is also covered by tree[j] (where j>i), then all 
        // the values covered by tree[i] will be covered by tree[j]. Because of
        // this hierarchy we get a tree structure. 
        // Note that the least significant 1 bit of x can be found by x & (~x+1).
        // For unsigned integers (~x+1) is stored as -x.
        public int[] tree;

        public FenwickTree(int size) {
            tree = new int[size];
            for (int i = 0; i < size; i++) {
                tree[i] = 0;
            }
        }

        /*
        public FenwickTree(int[] a) {
            this(a.length);
            // since we have a tree structure, we can initialize the tree in O(n) 
            // by building the tree bottoms up. When we visit a node suppose tree[i],
            // then a[i] will be added to it and it will add tree[i] to tree[i`], 
            // where i` is the parent of i, in the tree structure.
            // i` is obtained by adding 1 to the least significant 1 (set) bit of i
            tree[0] = a[0];
            int pi;
            for (int i = 1; i < a.length; i++) {
                tree[i] += a[i];
                pi = i + (i & -i);
                if (pi < a.length) {
                    tree[pi] += tree[i];
                }
            }
        }
        */

        public int getPrefixMax(int idx) {
            int max = tree[0];
            while (idx > 0) {
                max = Math.max(max, tree[idx]);
                idx -= idx & -idx;
            }
            return max;
        }

        public void addValueAt(int idx, int value) {
            if (idx == 0) {
                tree[0] = Math.max(tree[0], value);
                return;
            } 
            while (idx < tree.length) {
                tree[idx] = Math.max(tree[idx], value);
                idx += (idx & -idx);
            }
        }
    }

    public static void main(String[] args) {
        s(new Solution().lengthOfLIS(new int[] {10,9,2,5,3,7,101,18,1000,-1000}));
    }

    private static void s(Object s) {
        System.out.println(s.toString());
    }
}