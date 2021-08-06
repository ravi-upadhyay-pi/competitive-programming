import java.util.*;

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        List<Integer> a1 = new ArrayList<Integer>();
        List<Integer> a2 = new ArrayList<Integer>();
        for (int i = 0; i < nums1.length; i++) {
            a1.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            a2.add(nums2[i]);
        }
        int totalSize = a1.size() + a2.size();
        if (totalSize % 2 == 0) {
            double first  = (double) findElementAt(a1, a2, (totalSize / 2) - 1);
            double second = (double) findElementAt(a1, a2, totalSize / 2);
            return (first + second) / 2;
        } else {
            return (double) findElementAt(a1, a2, totalSize/2);
        }
    }

    int findElementAt(List<Integer> a1, List<Integer> a2, int idx) {
        if (a1.size() < a2.size()) {
            var temp = a1;
            a1 = a2;
            a2 = temp;
        }
        if (a2.size() == 0) {
            return a1.get(idx);
        }
        // In the merged array [smaller than element, equal to element, greater than element]
        // the required value at idx can either be in the 
        // [smaller than element],  [equal to element] or [greater than element]
        int element = a1.get(a1.size() / 2);
        // lowerBound equals the count of values less than element in the array
        int a1LB = lowerBound(a1, element);
        int a2LB = lowerBound(a2, element);
        // upperBound gives the count of values less than equal to element in array
        int a1UB = upperBound(a1, element);
        int a2UB = upperBound(a2, element);
        // count of values in [smaller than element] is a1LB + a2LB
        // count of values in [smaller than element] and [element] is a1UB + a2UB
        if (a1LB + a2LB >= idx + 1) {
            return findElementAt(a1.subList(0, a1LB), a2.subList(0, a2LB), idx);
        } else if (a1UB + a2UB >= idx + 1) {
            return element;
        } else {
            return findElementAt(
                    a1.subList(a1UB, a1.size()), 
                    a2.subList(a2UB, a2.size()), 
                    idx - a1UB - a2UB);
        }
    }

    int lowerBound(List<Integer> array, int element) {
        int l = 0;
        int h = array.size() - 1;
        int m;
        if (array.get(h) < element) {
            return h+1;
        }
        while (l < h) {
            m = (l+h) >> 1;
            if (array.get(m) >= element) {
                h = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

    int upperBound(List<Integer> array, int element) {
        int l = 0;
        int h = array.size() - 1;
        int m;
        if (array.get(h) <= element) {
            return h+1;
        }
        while (l < h) {
            m = (l+h) >> 1;
            if (array.get(m) > element) {
                h = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findMedianSortedArrays(
                new int[]{1,2,5,7},
                new int[]{3,4,6}));
    }
}
