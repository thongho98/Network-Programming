/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baitap1;

import java.util.Scanner;

/**
 *
 * @author HOQUOCTHONG
 */
public class BaiTap1 {

    public static void main(String[] args) {
        int a, b;
        int ucln, bcnn;
        Scanner scan = new Scanner(System.in);
        System.out.println("Nhap a: ");
        a = scan.nextInt();
        System.out.println("Nhap b: ");
        b = scan.nextInt();

        ucln = UCLN(a, b);
        System.out.println("UCLN: " + ucln);

        bcnn = BCNN(a, b);
        System.out.println("BCNN: " + bcnn);

    }

    public static int UCLN(int a, int b) {
        if (a == 0 || b == 0) {
            return a + b;
        }
        while (a != b) {
            if (a > b) {
                a = a - b;
            } else {
                b = b - a;
            }
        }
        return a;
    }

    public static int BCNN(int a, int b) {
        return (a * b) / UCLN(a, b);
    }
}
