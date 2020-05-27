package com.miaoph.cn.nettybasic;

import java.util.Scanner;

public class Main1 {

        public static void main(String[] args) {

            Scanner in = new Scanner(System.in);

            while (in.hasNextLine()) {
                String line = in.nextLine();
                System.out.println(change(line));
            }

        }

        private static String change(String line) {
            final StringBuffer sb = new StringBuffer();
            final String s = line.replaceAll("[^[A-Za-z]+]", "");
            for (int i=0;i<s.length();i++){
                final char c = s.charAt(i);
                if(Character.isLowerCase(c)){
                    sb.append(Character.toUpperCase(c));
                }else {
                    sb.append(Character.toLowerCase(c));
                }
            }
            return sb.toString();
        }



}
