package com.an.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class test {

    public static void main(String[] args) {
        int[] A = {3,4,1,2};
        Arrays.sort( A);
        System.out.println(A[0]+""+A[1]+A[2]);
}

}
class com<T> implements Comparator<T> {
    //对abcdefg进行比较大小，排序
    public int compare(T o1, T o2) {
        int i = Integer.parseInt(String.valueOf(o1));
        int j = Integer.parseInt(String.valueOf(o2));
        if (i > j)
            return 1;
        if (i < j)
            return -1;
        return 0;
    }
}