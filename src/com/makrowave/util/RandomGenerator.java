package com.makrowave.util;

import java.util.ArrayList;
import java.util.Random;

import static java.util.Collections.sort;

public class RandomGenerator {
    public static ArrayList<Integer> RandomInteger(int mMin, int mMax, int mStep, boolean mIsEven, boolean mIsOdd, int mQuantity, int mAdd, boolean mSort){
        ArrayList<Integer> arr = new ArrayList<>();
        arr.ensureCapacity(mQuantity);
        Random rng = new Random();
        for(int i=0; i<mQuantity; i++){
            if(mMax>0) {
                int number = rng.nextInt() % (mMax + 1);
                while ((number - mMin) % mStep != 0 || number < mMin || ((mIsEven && number % 2 == 1) || (mIsOdd && number % 2 == 0))) {
                    number = rng.nextInt() % (mMax + 1);
                }
                number += mAdd;
                arr.add(number);
            }
            //Probably temporary fix for negative numbers
            else{
                int number = rng.nextInt() % (mMin*-1 + 1);
                while ((number - mMax*-1) % mStep != 0 || number < mMax*-1 || ((mIsEven && number % 2 == 1) || (mIsOdd && number % 2 == 0))) {
                    number = rng.nextInt() % (mMin*-1 + 1);
                }
                number = number*-1 + mAdd;
                arr.add(number);
            }
        }
        if (mSort)
            sort(arr);
        return arr;
    }
    /*public static ArrayList<Float> RandomFloat(){

    }*/
    public static void main(String... args){
        ArrayList<Integer> arr = RandomInteger(0, 100, 1, false, false, 5, 0, false);
        for(Integer number : arr){
            System.out.println(number.toString());
        }
    }
}
