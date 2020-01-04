/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baitap2;

import java.util.Scanner;

/**
 *
 * @author HOQUOCTHONG
 */
public class BaiTap2 {

    public static void main(String[] args) {
        int thang, nam;
        int day;
        Scanner scan = new Scanner(System.in);
        System.out.println("Nhap thang: ");
        thang = scan.nextInt();
        System.out.println("Nhap nam: ");
        nam = scan.nextInt();

        day = xuatngay(thang, nam);
        System.out.println("Thang: "+thang +", Nam:"+nam+" co" + day);

    }

    public static boolean CheckYear(int year) {
        if (year % 400 == 0) {
            return true;
        }
        if (year % 4 == 0 && year % 100 != 0) {

            return true;
        }
        return false;
    }

    public static int xuatngay(int thang, int nam) {
        int day = 0;
        switch (thang) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                day = 31;
            case 4:
            case 6:
            case 9:
            case 11:
                day = 30;
            case 2: {
                if (CheckYear(nam)) {
                    day = 29;
                } else {
                    day = 28;
                }
            }
        }
        return day;
    }
}
