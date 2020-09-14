public class countClumps {

    public static void main(String[] args) {
        System.out.println(countClumps(new int[]{1, 1, 1, 1, 1}));
    }

    public static int countClumps(int[] nums) {
        int clumps = 0;
        for(int i=0; i<nums.length-1; i++) {
            if(nums[i] == nums[i+1]) {
                clumps++;
                for(int k=i; k < nums.length; k++) {
                    if(nums[k] != nums[i]) {
                        i = k;
                        break;
                    }
                }
            }
        }

        return clumps;
    }

}
