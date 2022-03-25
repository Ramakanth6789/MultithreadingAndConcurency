package myworkingscrap;

import java.util.stream.IntStream;

public class Chetu {

    public static void main(String[] args) {
        int count = 0;


        long countStream = IntStream
                .range(100,200)
                .filter(Chetu::check)
                .count();

        System.out.println(countStream);


    }

    public static boolean check(int number){
        int currentNum;
        while(number>0){
            currentNum = number%10;
            if(!(currentNum==1 || currentNum==4 || currentNum==9)){
                return false;
            }
            number = number/10;
        }

        return true ;
    }

}
