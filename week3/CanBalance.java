/************************
 * CSC254 Homework #2
 * Fall 2020
 * Jon Doe
 */

public class CanBalance {


    public static void main(String[] args) {
        System.out.println(canBalance(new int[] {23, 4653,2, 3, 632,325}));
    }

    /** solves p158767 on codingbat
     * Problem description: Given a non-empty array, return true if there
     * is a place to split the array so that the sum of the numbers on one
     * side is equal to the sum of the numbers on the other side.
     */
    public static boolean canBalance(int[] nums) {
        // check every possible position the array can be split
        for(int i=0; i<nums.length; i++)
            // if the sum of the left half is equal to the sum of the right, we're good
            if(sumArray(getFirstHalf(nums, i)) == sumArray(getSecondHalf(nums, i)))
                return true;
        // we were never able to find a position i where the left and right slices were equal
        return false;
    }

    /** slices the array in two at position n and returns the first slice
     * the value at position n is not included in the first slice
     * @param nums array to be sliced
     * @param n position in which to slice the array
     * @return the left half of the sliced array
     */
    public static int[] getFirstHalf(int[] nums, int n) {
        var firstHalfOfNums = new int[n];
        for(int i=0; i<firstHalfOfNums.length; i++)
            firstHalfOfNums[i] = nums[i];
        return firstHalfOfNums;
    }


    /** slices the array in two at position n and returns the second slice
     * the value at position n is included in the first slice
     * @param nums array to be sliced
     * @param n position in which to slice the array
     * @return the right half of the sliced array
     */
    public static int[] getSecondHalf(int[] nums, int n) {
        var secondHalfOfNums = new int[nums.length - n];
        for(int i=n; i<nums.length; i++)
            secondHalfOfNums[i-n] = nums[i];
        return secondHalfOfNums;
    }

    /** sums the values in an array of integers.
     *
     * @param nums array of values to be summed
     * @return sum of all of the values in nums
     */
    public static int sumArray(int[] nums) {
        int total = 0;

        for(var num : nums)
            total += num;

        return total;
    }
}