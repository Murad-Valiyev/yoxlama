/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newpackage;

import java.util.Scanner;

/**
 *
 * @author Valiyev Murad
 */
public class Test {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ededi daxil edin:");
        int eded = sc.nextInt();

        System.out.println("Bu ededde: ");
        System.out.println((eded % 10) + " dene 1 manatliq var");
        System.out.println(((eded % 100) - eded % 10)/10 + " dene on manatdix var");
        System.out.println((eded / 100) + " dene yuz manatdix  var");

    }
}
