package myworkingscrap;

import java.util.Arrays;

public class Consecutive {
    public static void main(String[] args) {
        int[] nums = {3,1};
        System.out.println(solve(nums));
    }


    public static int solve(int[] nums) {
        if(nums.length==0) return 0;


        int longest = 1;
        int counter = 0;

        for(int j=0,i=2; i<=nums.length; i++){
            counter = checkConsecutive(Arrays.copyOfRange(nums, j, i));
            System.out.println("Counter: "+counter+" i-j: "+(i-j));
            if(counter!=(i-j)){
                j=i-1;
            }
            longest = Math.max(longest,counter);
        }

        return longest;
    }

    public static int checkConsecutive(int[] nums){
        Arrays.sort(nums);
        int count =1;

        for(int i=0; i<nums.length-1; i++){
            if(nums[i+1] - nums[i]==1){
                count++;

            }

        }
        return count;

    }

}
