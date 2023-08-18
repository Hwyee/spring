package cn.hwyee.common.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName SortUtil
 * @description 排序工具类
 * @date 2023/5/8
 * @since JDK 1.8
 */
@Slf4j
public class SortUtil {
    public static void main(String[] args) {
        int[] a = new int[]{4, 2, 8, 0, 5 ,111 , 0, 2, 7, 88};
        //1. 冒泡
//        bubbleSort(a);
        //2. 快排
        quickSort(a,0,a.length-1);
        Arrays.stream(a).forEach(t -> log.info(String.valueOf(t)));
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
     * quickSort:
     * 快排,递归 + 分治
     * @author hui
     * @version 1.0
     * @param ints
     * @return void
     * @date 2023/5/8 9:26
     */
    public static void quickSort(int[] ints,int start,int end){
        if (start >= end){
            return;
        }
        //计算分界值索引
        int boundaryIndex = quickSortSwap(ints, start, end);
        if ( boundaryIndex  <= end ){
            //递归
            quickSort(ints,start,boundaryIndex);
            quickSort(ints,boundaryIndex + 1,end);
        }
    }

    /**
     * quickSortSwap:
     * 快排交换,以开始(start)索引的值为分界值,进行比较：
     * 小于该值的放在左边,大于该值的放在右边
     * 即记录一个分界值索引变量，该变量自增后与小于分界值索引进行交换,大于分界值的则不操作,
     * 最后分界值再与分界值索引变量所在的值进行交换
     * 返回分界值索引
     * @author hui
     * @version 1.0
     * @param ints 进行快排的数组
     * @param start 快排开始索引
     * @param end 快排结束索引
     * @return int
     * @date 2023/5/8 14:49
     */
    private static int quickSortSwap(int[] ints,int start,int end){
        //以第一个值作为分界值,小于该值的放在左边,大于该值的放在右边
        int boundary = ints[start];
        int boundaryIndex = start;
        for (int i = start + 1; i <= end; i++) {
            if (ints[i] < boundary){
                swap(ints,++boundaryIndex,i);
            }
        }
        swap(ints,start,boundaryIndex);
        return boundaryIndex;
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
