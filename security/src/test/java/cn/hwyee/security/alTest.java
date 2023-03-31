package cn.hwyee.security;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;
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
    public void fib() {
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
            if (n == 1) return 1;
            if (n == 2) return 1;
            return tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3);
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


    @Test
    public void d2t() {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        node1.next = node2;
        node2.next = node3;
        node1.random = node3;
        node2.random = null;
        node3.random = node1;
        D2 d2 = new D2();
        Node node = d2.copyRandomList(node1);
        System.out.println("" + node.val + node.next.val + (node.random == null ? "Null" : node.random.val));
    }


    class D2 {
        //1输入链表头节点,从尾到头打印节点的值,返回数组

        public int[] reversePrint(ListNode head) {
            if (head == null) {
                return new int[0];
            }
            Stack<Integer> listNodes = new Stack<>();
            listNodes.push(head.val);
            while (head.next != null) {
                head = head.next;
                listNodes.push(head.val);
            }
            int[] a = new int[listNodes.size()];
            for (int i = 0; i < a.length; i++) {
                a[i] = listNodes.pop();
            }
            return a;
        }

        //输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
        public ListNode reversePrintNode(ListNode head) {

            ListNode pre = null;
            ListNode now = head;
            while (now != null) {
                ListNode temp = now.next;
                now.next = pre;

                pre = now;
                now = temp;
            }
            return pre;
        }

        //请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，
        // 每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。
        public Node copyRandomList(Node head) {
            if (head == null) {
                return null;
            }
            Node node = new Node(head.val);
            Node pre = node;
            List<Node> oldL = new ArrayList<Node>();
            List<Node> newL = new ArrayList<Node>();
            oldL.add(head);
            newL.add(node);
            while (head.next != null) {
                oldL.add(head.next);
                Node n = new Node(head.next.val);
                newL.add(n);
                pre.next = n;
                pre = n;
                head = head.next;
            }
            AtomicInteger atomicInteger = new AtomicInteger(0);
            newL.forEach(t -> {
                int index = getIndex(oldL, oldL.get(atomicInteger.getAndAdd(1)).random);
                if (index == -1) {
                    t.random = null;
                } else {
                    t.random = newL.get(index);
                }
            });
            return newL.get(0);
        }

        private int getIndex(List<Node> old, Node node) {
            if (node == null) return -1;
            for (int i = 0; i < old.size(); i++) {
                if (node == old.get(i))
                    return i;
            }
            return -1;
        }

    }

    @Test
    public void d3T() {
        char a = '1';
        char b = 97;
        int c = 'c';
        System.out.println(a + "" + b);
    }

    @Test
    public void d4T() {
        int[] a = new int[]{5, 7, 7, 8, 8, 10};
        D4 d4 = new D4();
        int search = d4.search(a, 8);
        System.out.println(search);
        //异或解法：n-1 = 3 n =4
        int a1 = 0;
        int a2 = 1;
        int a3 = 2;
        int a4 = 4;
        System.out.println("0 ^ 1 :" + (0 ^ 1));
        System.out.println("1 ^ 2 :" + (1 ^ 2));
        System.out.println("3 ^ 4 :" + (3 ^ 4));
        System.out.println("**************");
        System.out.println("7 ^ 0 :" + (0 ^ 7));
        System.out.println("7 ^ 1 :" + (7 ^ 1));
        System.out.println("1 ^ 2 :" + (6 ^ 2));
        System.out.println("4 ^ 3 :" + (4 ^ 3));
        System.out.println("7 ^ 4 :" + (7 ^ 4));
    }

}

class d3 {
    // 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
    public String replaceSpace(String s) {
        char[] chars = s.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (char aChar : chars) {
            if (aChar == ' ') {
                stringBuilder.append("%20");
            } else {
                stringBuilder.append(aChar);
            }
        }
//           while (s.contains(" ")){
//              int i = s.indexOf(" ");
//              s = s.substring(0,i) + "%20" + s.substring(i+1);
//          }
        return stringBuilder.toString();
    }

    //字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。
    // 请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，
    // 该函数将返回左旋转两位得到的结果"cdefgab"。
    public String reverseLeftWords(String s, int n) {
        StringBuilder left = new StringBuilder();
        StringBuilder right = new StringBuilder();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (i < n) {
                right.append(chars[i]);
            } else {
                left.append(chars[i]);
            }
        }
        return left.append(right).toString();

    }

    //给定两个大小分别为 m 和 n 的正序（从小到大）数组nums1 和nums2。请你找出并返回这两个正序数组的 中位数 。
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
//           int[] sum =  new int[nums1.length+nums2.length];
//           System.arraycopy(nums1,0,sum,0,nums1.length);
//           System.arraycopy(nums2,0,sum,nums1.length,nums2.length);
//           Arrays.sort(sum);
//
//           if (sum.length%2==0){
//              return  (sum[sum.length/2 - 1] + sum[sum.length/2])/2.0;
//           }
//           return (double) sum[sum.length / 2];
//
//               int[] nums;
//               int m = nums1.length;
//               int n = nums2.length;
//               nums = new int[m + n];
//               if (m == 0) {
//                   if (n % 2 == 0) {
//                       return (nums2[n / 2 - 1] + nums2[n / 2]) / 2.0;
//                   } else {
//
//                       return nums2[n / 2];
//                   }
//               }
//               if (n == 0) {
//                   if (m % 2 == 0) {
//                       return (nums1[m / 2 - 1] + nums1[m / 2]) / 2.0;
//                   } else {
//                       return nums1[m / 2];
//                   }
//               }
//
//               int count = 0;
//               int i = 0, j = 0;
//               while (count != (m + n)) {
//                   if (i == m) {
//                       while (j != n) {
//                           nums[count++] = nums2[j++];
//                       }
//                       break;
//                   }
//                   if (j == n) {
//                       while (i != m) {
//                           nums[count++] = nums1[i++];
//                       }
//                       break;
//                   }
//
//                   if (nums1[i] < nums2[j]) {
//                       nums[count++] = nums1[i++];
//                   } else {
//                       nums[count++] = nums2[j++];
//                   }
//               }
//
//               if (count % 2 == 0) {
//                   return (nums[count / 2 - 1] + nums[count / 2]) / 2.0;
//               } else {
//                   return nums[count / 2];
//               }
//           }

        int m = nums1.length;
        int n = nums2.length;
        int len = m + n;//两个数组总长度
        int left = -1, right = -1;
        int aStart = 0, bStart = 0;
        for (int i = 0; i <= len / 2; i++) {
            left = right;
            if (aStart < m && (bStart >= n || nums1[aStart] < nums2[bStart])) {
                right = nums1[aStart++];
            } else {
                right = nums2[bStart++];
            }
        }
        if ((len & 1) == 0)//偶数
            return (left + right) / 2.0;
        else//奇数
            return right;
    }

}

class D4 {
    //在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，
    // 但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
    public int findRepeatNumber(int[] nums) {
        int i = 1;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
            i++;
            if (i != set.size()) {
                return num;
            }
        }
        return 0;
    }

    //统计一个数字在排序数组中出现的次数。
    public int search(int[] nums, int target) {
        if (nums.length == 1) return 0;
        int start = -1;
        int end = -1;
        for (int i = 0; i < nums.length; i++) {
            if (start != -1 && end != -1) {
                break;
            }
            if (nums[i] == target && start == -1) {
                start = i;
            }
            if (nums[nums.length - i - 1] == target && end == -1) {
                end = nums.length - i;
            }
        }
        if (start == -1 || end == -1) return 0;
        return end - start + 1;
    }

    public int search1(int[] nums, int target) {
        int leftIdx = binarySearch(nums, target, true);
        int rightIdx = binarySearch(nums, target, false) - 1;
        if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target) {
            return rightIdx - leftIdx + 1;
        }
        return 0;
    }

    public int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0, right = nums.length - 1, ans = nums.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    //一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。
    // 在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int a = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i++)
                return i;
        }
        return n - 1;
    }

}

class D5 {
    //在一个 n * m 的二维数组中，每一行都按照从左到右非递减的顺序排序，
    // 每一列都按照从上到下非递减的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int m = matrix[0].length;
        int n = matrix.length;
        int a = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][m - 1] >= target) {
                a = i;
                break;
            }
        }
        while (a < matrix.length && matrix[a][0] <= target) {
            for (int i = 0; i < m; i++) {
                if (matrix[a][i] == target) {
                    return true;
                }
            }
            a++;
        }

        return false;
    }

    //把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
    //
    //给你一个可能存在重复元素值的数组numbers，它原来是一个升序排列的数组，并按上述情形进行了一次旋转。
    // 请返回旋转数组的最小元素。例如，数组[3,4,5,1,2] 为 [1,2,3,4,5] 的一次旋转，该数组的最小值为 1。
    //
    //注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
    public int minArray(int[] numbers) {
        if (numbers[numbers.length - 1] > numbers[0]) {
            return numbers[0];
        }
        int start = 0;
        int end = numbers.length - 1;
        int mid = 0;
        while (start < end) {
            mid = start + (end - start) / 2;// 等同于(start + start) / 2,为了防止int溢出,所以不用这种写法。
            if (numbers[mid] < numbers[end]) {
                end = mid;
            } else if (numbers[mid] > numbers[end]) {
                start = mid + 1;
            } else {
                end -= 1;
            }
        }
        return numbers[start];
    }

    //在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
    public char firstUniqChar(String s) {
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            if (s.indexOf(aChar) == s.lastIndexOf(aChar)) {
                return aChar;
            }
        }
        return ' ';
    }

}

class D6 {
    //从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。广度优先搜索（BFS）
    public int[] levelOrder(TreeNode root) {
        if(root == null) return new int[0];
        Queue<TreeNode> queue = new LinkedList<>(){
            { add(root); }
        };
        ArrayList<Integer> ans = new ArrayList<>();
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            ans.add(node.val);
            if(node.left != null) queue.add(node.left);
            if(node.right != null) queue.add(node.right);
        }
        int[] res = new int[ans.size()];
        for(int i = 0; i < ans.size(); i++)
            res[i] = ans.get(i);
        return res;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
