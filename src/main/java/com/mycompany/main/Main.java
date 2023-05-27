/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.main;

/**
 *
 * @author Valiyev Murad
 */
public class Main {

    public static void main(String[] args) throws Exception {
        User u = new UserDAOImpl().getUserBYId(6);
        System.out.println(u);
    }
}
