package cn.hwyee.security;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Stream;

public class alTest {
    @Test
    public void test3() {
        System.out.println("YOU KNOW WHAT?");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        List<Integer> list1 = new ArrayList<>();
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int j = 1; j <= i; j++){
            int al = al(j);
            map.put(al,map.get(al)==null?1:map.get(al) + 1);
        }

        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        int max = 0;
        HashMap<Integer,Integer> map1 = new HashMap<>();

        for (Map.Entry<Integer, Integer> entry : entries) {
            map.put(entry.getValue(),map.get(entry.getValue())==null?1:map.get(entry.getValue()) + 1);
        }
        Set<Map.Entry<Integer, Integer>> entries1 = map1.entrySet();

        for (Map.Entry<Integer, Integer> integerIntegerEntry : entries1) {
            max = integerIntegerEntry.getValue() > max ? integerIntegerEntry.getValue():max;
        }
        System.out.println(max);
        Math.floorMod(1,2);
    }

    public int al(int i) {
        int j = 0;
        while (i != 0) {
            j += i % 10;
            i = i / 10;
        }
        return j;
    }
    public String a(String s, int[] shifts){
        StringBuilder ans = new StringBuilder();
        int X = 0;
        for (int shift: shifts)
            X = (X + shift) % 26;

        for (int i = 0; i < s.length(); ++i) {
            int index = s.charAt(i) - 'a';
            ans.append((char) ((index + X) % 26 + 97));
            X = Math.floorMod(X - shifts[i], 26);
        }

        return ans.toString();
    }
}
