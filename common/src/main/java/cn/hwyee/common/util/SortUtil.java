package cn.hwyee.common.util;

import java.util.Arrays;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName SortUtil
 * @description 排序工具类
 * @date 2023/5/8
 * @since JDK 1.8
 */
public class SortUtil {
    public static void main(String[] args) {
        int[] a = new int[]{4, 2, 8, 0, 5};
        bubbleSort(a);
        Arrays.stream(a).forEach(System.out::println);
    }
    private SortUtil(){}

    /**
     * bubbleSort:
     * 冒泡排序
     * @author hui
     * @version 1.0
     * @param ints 要排序的数组
     * @return int[]
     * @date 2023/5/8 0:31
     */
    public static void bubbleSort(int[] ints){
        //循环每一个元素
        for (int i = 0; i < ints.length; i++) {
            //将元素与该元素后面的元素一一比对
            for (int i1 = i; i1 < ints.length; i1++) {
                if (ints[i] > ints[i1]){
                    swap(ints,i,i1);
                }
            }
        }
    }

    /**
     * swap:
     * 交换数组中的元素
     * @author hui
     * @version 1.0
     * @param ints 要交换的数组
     * @param i 数组元素索引
     * @param j 数组元素索引
     * @return void
     * @date 2023/5/8 0:52
     */
    private static void swap(int[] ints,int i,int j){
        int temp = ints[i];
        ints[i] = ints[j];
        ints[j] = temp;
    }
}
