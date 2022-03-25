package myworkingscrap;

public class Solution {
/*
    You are given a array of integers .Find the maximum number of non-intersecting segments of length 2(two adjacent elements ),
     such that segments have an equal sum.
        for example,
        given A= [10,1,3,1,2,2,1,0,4] there are three non intersecting segments , each whose sum is equal to 4: (1,3), (2,2), (0,4).
        Another three non-intersecting segments are : (3,1), (2,2),(0,4)
*/
    public static void main(String[] args) {
        int[] A = {5,3,1,3,2,3,} ;//{10,1,3,1,2,2,1,0,4};
        int counter1 = 0;
        int counter2 =0;
        int sum = 4;
        for(int i=0; i<A.length-1; i++){
            if (A[i] + A[i + 1] == sum) {
                counter1++;
                i++;
            }
        }
        System.out.println(Math.max(counter1,counter2));
    }

}
