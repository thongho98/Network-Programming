/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baitap17;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HOQUOCTHONG
 */
public class BaiTap17 {

    public static void main(String[] args) {
        System.out.println("Các số 7 chữ số là số nguyên tố, số thuận nghịch và tổng là số thuận nghịch: ");
        for (int i = 1000000; i <= 9999999; i++) {
            if (isSoNguyenTo(i) && isSoThuanNghich(i) && tongLaThuanNghich(i)) {
                System.out.println(i);
            }
        }

    }

    public static boolean isSoNguyenTo(int number) {
        if (number < 2) {
            return false;
        }
        int range = (int) Math.sqrt(number);
        for (int i = 2; i <= range; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isSoThuanNghich(int number) {
        List<Integer> list = new ArrayList<Integer>();
        do {
            list.add(number % 10);
            number /= 10;
        } while (number > 0);
        int size = list.size();
        for (int i = 0; i < (size / 2); i++) {
            if (list.get(i) != list.get(size - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static boolean tongLaThuanNghich(int number) {
        int sum = 0;
        List<Integer> list = new ArrayList<Integer>();
        do {
            list.add(number % 10);
            number /= 10;
        } while (number > 0);
        for (Integer x : list) {
            sum += x;
        }
        if (isSoThuanNghich(sum)) {
            return true;
        }
        return false;
    }
}
