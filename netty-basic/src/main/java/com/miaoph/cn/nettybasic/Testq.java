package com.miaoph.cn.nettybasic;

import java.util.Scanner;

public class Testq {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        while (in.hasNextLine()) {
            String[]  val = in.nextLine().split(",");
            int[] x = new int[val.length];
            for (int i = 0; i < val.length; i++) {
                x[i] = Integer.parseInt(val[i]);
            }
            System.out.println(maximumGap(x));
        }

    }

    public static int maximumGap(int[] nums) {
        //TODO
        for (int i=0;i<nums.length;i++){
           for (int j=0;j<nums.length;j++){
               int temp=nums[i];
               if(nums[i]>nums[j]){
                   nums[i]=nums[j];
                   nums[j]=temp;
               }
           }
        }

    System.out.println(nums);
        return 0;
    }

}
