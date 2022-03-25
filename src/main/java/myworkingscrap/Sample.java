package myworkingscrap;

import java.util.Arrays;

public class Sample {
    public static void main(String[] args) {
        int[] list = {3,4,5,2,5,3,4};
        int[] array  = Arrays.copyOfRange(list,0,3);
        for(int num: array){
            System.out.println(num);
        }
    }
}
