/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

import java.util.List;

/**
 *
 * @author Valiyev Murad
 */
public interface UserDAO {

    public List<User> getAll() throws Exception;

    public void update(User u) throws Exception;

    public void delete(Integer id) throws Exception;

    public void add(User u) throws Exception;

    public Country getCountryById(int id) throws Exception;

    public User getUserBYId(int id) throws Exception;
}
