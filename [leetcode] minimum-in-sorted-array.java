class Solution {
    // Let's consider [left elements, mid element, right elements]
    // if mid element < rightmost element, least element in in 
    // [left elements, mid element]
    // otherwise if mid element > rightmost element, least element is in 
    // [right elements] 
    public int findMin(int[] nums) {
        int l = 0;
        int h = nums.length - 1;
        int m;
        while (l < h) {
            m = (l+h) >> 1;
            if (nums[m] < nums[h]) {
                h = m;
            } else {
                l = m + 1;
            }
        }
        return nums[l];
    }
}
