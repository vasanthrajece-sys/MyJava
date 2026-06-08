import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;




public class Main {
    public static void main(String[] args) {
        int[] numbers = {11, 7, 15, 2};
        int target = 9;
        int [] result = findPairSummingToTarget(numbers, target);
        System.out.println("Result: " + Arrays.toString(result));
    }


    /**
     * This is the implementation the junior team member has come up with, your task is to review this and provide
     * feedback as you would do in a standard PR review
     *
     */
    public static int[] findPairSummingToTarget(int[] arr, int target) {
        int sum = 0;
        int [] result = new int[2];
        Map< Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < arr.length; i++) {
            int diff = target - arr[i];
            if (map.containsValue(diff)){
                int x = map.get(diff);
                result[0] = x;
                result[1] = i;
            }
            map.put(i, diff);


        }



        return result;
    }
}