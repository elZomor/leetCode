package com.company;

import com.company.dataStructure.I_DataStructure;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {


    public static void main(String[] args) {

    }


    public static String convertToZigzag(String s, int numRows) {
        String zigzag = "";
        Map<Integer, String> myMap = new HashMap<>();
        for (int i = 0; i < numRows; i++) {
            myMap.put(i, "");
        }
        char[] arr = s.toCharArray();
        int loop = 0;
        for (Integer i = 0; i < arr.length; i += numRows) {
            if (loop % 2 == 0) {
                for (Integer j = 0 ; j < numRows ; j++) {
                    if (i + j < arr.length)
                        myMap.put(j, myMap.get(j) + arr[i + j]);

                }
            } else {
                for (Integer j = numRows - 1 ; j >= 0 ; j--) {
                    if (i + j < arr.length)
                        myMap.put(j, myMap.get(j) + arr[i + j]);

                }
            }
            loop++;

        }
        myMap.values().forEach(System.out::println);
        zigzag = myMap.values().stream().reduce(zigzag, (acc, x) -> acc + x);
        return zigzag;
    }


}
