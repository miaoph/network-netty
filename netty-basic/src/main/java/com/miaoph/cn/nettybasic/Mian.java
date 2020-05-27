package com.miaoph.cn.nettybasic;

import java.util.*;

public class Mian {
  public static void main(String[] args) {

    Scanner in = new Scanner(System.in);

    while (in.hasNextLine()) {
      String line = in.nextLine();
      System.out.println(sort(line));
    }
  }

  private static String sort(String line) {
    // TODO
    final int length = line.length();
    final TreeMap<Character, Integer> treeMap = new TreeMap<>();
    for (int i = 0; i < length; i++) {
      final char c = line.charAt(i);
      if (treeMap.containsKey(c)) {
        final Integer count = treeMap.get(c);
        treeMap.put(c, count + 1);
      } else {
        treeMap.put(c, 1);
      }
    }

    final Set<Map.Entry<Character, Integer>> entries = treeMap.entrySet();
     int[] intList = new int[entries.size()];
    char[] charList = new char[entries.size()];
    final StringBuffer sb = new StringBuffer();
    int k=0;

    for ( Map.Entry<Character, Integer> map: entries) {
        final Character key = map.getKey();
        final Integer value = map.getValue();
        charList[k]=key;
        intList[k]=value;
        k++;
    }
    for (int i = 0; i < intList.length; i++) {
        for (int j=0;j<intList.length;j++){
            if(intList[j] <intList[i]){
                int temp=intList[j];
                intList[j]=intList[i];
                intList[i]=temp;
                char tempChar=charList[j];
                charList[j]=charList[i];
                charList[i]=tempChar;
            }
        }
    }

    for (int i =0;i<intList.length;i++){
        final Character value = charList[i];
        final Integer count = intList[i];
        for(int j=0;j<count;j++){
            sb.append(value);
        }
    }
    return sb.toString();
  }
}
