package cn.hwyee.security;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Stream;

public class alTest {
    @Test
    public void test3() {
        System.out.println("YOU KNOW WHAT?");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        List<Integer> list1 = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int j = 1; j <= i; j++) {
            int al = al(j);
            map.put(al, map.get(al) == null ? 1 : map.get(al) + 1);
        }

        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        int max = 0;
        HashMap<Integer, Integer> map1 = new HashMap<>();

        for (Map.Entry<Integer, Integer> entry : entries) {
            map.put(entry.getValue(), map.get(entry.getValue()) == null ? 1 : map.get(entry.getValue()) + 1);
        }
        Set<Map.Entry<Integer, Integer>> entries1 = map1.entrySet();

        for (Map.Entry<Integer, Integer> integerIntegerEntry : entries1) {
            max = integerIntegerEntry.getValue() > max ? integerIntegerEntry.getValue() : max;
        }
        System.out.println(max);
        Math.floorMod(1, 2);
    }

    @Test
    public void mod() {
        System.out.println(" 4 %  3 取余结果: " + 4 % 3);
        System.out.println(" 4 % -3 取余结果: " + 4 % -3);
        System.out.println("-4 %  3 取余结果: " + -4 % 3);
        System.out.println("-4 % -3 取余结果: " + -4 % -3);
        System.out.println(" 4 %  3 取模结果: " + Math.floorMod(4, 3));
        System.out.println(" 4 % -3 取模结果: " + Math.floorMod(4, -3));
        System.out.println("-4 %  3 取模结果: " + Math.floorMod(-4, 3));
        System.out.println("-4 % -3 取模结果: " + Math.floorMod(-4, -3));
    }

    public int al(int i) {
        int j = 0;
        while (i != 0) {
            j += i % 10;
            i = i / 10;
        }
        return j;
    }

    public static void main(String[] args) {
        System.out.println(a("abc", new int[]{10, 20, 30}));
    }

    public static String a(String s, int[] shifts) {
        StringBuilder ans = new StringBuilder();
        int X = 0;
        for (int shift : shifts)
            X = (X + shift) % 26;

        for (int i = 0; i < s.length(); ++i) {
            int index = s.charAt(i) - 'a';
            ans.append((char) ((index + X) % 26 + 97));
//            X = (X-shifts[i])%26;//Math.floorMod(X - shifts[i], 26);
            X = Math.floorMod(X - shifts[i], 26);
        }

        return ans.toString();
    }

    class CQueue {
        private Stack s1;
        private int f;

        public CQueue() {
            s1 = new Stack();
        }

        public void appendTail(int value) {
            if (s1.empty()) {
                f = value;
            }
            s1.push(value);
        }

        public int deleteHead() {
            if (s1.empty()) {
                return -1;
            }
            Object o = s1.get(0);
            s1.remove(0);
            return (int) o;
        }
    }

    /**
     * Your CQueue object will be instantiated and called as such:
     * CQueue obj = new CQueue();
     * obj.appendTail(value);
     * int param_2 = obj.deleteHead();
     * 输入：
     * ["CQueue","appendTail","deleteHead","deleteHead","deleteHead"]
     * [[],[3],[],[],[]]
     * 输出：[null,null,3,-1,-1]
     */

    public static class MinStack {
        private int[] ele;
        private int dateIndex;
//        private int min;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            dateIndex = 0;
//            min = 0;
            ele = new int[0];
        }

        public void push(int x) {
            if (dateIndex >= ele.length) {
                ele = Arrays.copyOf(ele, ele.length + 1);
            }
//            if (x < min) {
//                min = x;
//            }
            ele[dateIndex++] = x;
        }

        public void pop() {
            int[] i = new int[ele.length - 1];
            System.arraycopy(ele, 0, i, 0, dateIndex - 1);
            ele = i;
            dateIndex -= 1;
        }

        public int top() {
            return ele[ele.length - 1];
        }

        public int min() {
            return Arrays.stream(ele).min().getAsInt();
        }
    }
    @Test
    public void fib(){
        Solution solution = new Solution();
        System.out.println(solution.fib(5));
    }
    class Solution {

        public int fib(int n) {
            //动态规划

                    if (n < 2) {
                        return n;
                    }
                    int p = 0, q = 0, r = 1;
                    for (int i = 2; i <= n; ++i) {
                        p = q;
                        q = r;
                        r = p + q;
                    }
                    return r;
        //矩阵快速幂

//                    if (n < 2) {
//                        return n;
//                    }
//                    int[][] q = {{1, 1}, {1, 0}};
//                    int[][] res = pow(q, n - 1);
//                    return res[0][0];


        //通项公式
//            double sqrt5 = Math.sqrt(5);
//            double fibN = Math.pow((1 + sqrt5) / 2, n) - Math.pow((1 - sqrt5) / 2, n);
//            return (int) Math.round(fibN / sqrt5);

//            if (n == 0)
//                return 0;
//            if (n == 1 ) return  1;
//            return fib(n - 1) + fib(n-2)

        }

        public int tribonacci(int n) {
            //chao出时间限制
            if (n == 0)
                return 0;
            if (n == 1 ) return  1;
            if (n == 2 ) return 1;
            return tribonacci(n - 1) + tribonacci(n-2) + tribonacci(n-3);
        }
        public int[][] pow(int[][] a, int n) {
            int[][] ret = {{1, 0}, {0, 1}};
            while (n > 0) {
                if ((n & 1) == 1) {
                    ret = multiply(ret, a);
                }
                n >>= 1;
                a = multiply(a, a);
            }
            return ret;
        }

        public int[][] multiply(int[][] a, int[][] b) {
            int[][] c = new int[2][2];
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
                }
            }
            return c;
        }
    }
}
