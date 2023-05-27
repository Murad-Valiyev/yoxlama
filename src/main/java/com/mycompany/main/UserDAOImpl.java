/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Valiyev Murad
 */
public class UserDAOImpl extends AbstractDAO implements UserDAO {

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        try (Connection connectInstance = connect()) {
            Statement stmt = connectInstance.createStatement();
            stmt.execute("SELECT\n"
                    + "	u.*,"
                    + "	n.nationality,"
                    + "	b.birthplace "
                    + "FROM"
                    + "	USER AS u"
                    + "	LEFT JOIN country AS n ON u.nationality_id = n.id"
                    + "	LEFT JOIN country AS b ON u.birthplace_id = b.id");
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                
                list.add(getUser(rs));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public void update(User u) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("update user set name=?,surname=?,phone_number=?,nationality_id=?,birthplace_id=? where id=?");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getPhone_number());
            stmt.setInt(4, u.getNationality_id());
            stmt.setInt(5, u.getBirthplace_id());
            stmt.setInt(6, u.getId());
            stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try (Connection c = connect()) {
            Statement stmt = c.createStatement();
            stmt.execute("delete from user  where id=" + id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void add(User u) throws Exception {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("insert into user(name,surname,phone_number,nationality_id,birthplace_id) values(?,?,?,?,?)");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getPhone_number());
            stmt.setInt(4, u.getNationality_id());
            stmt.setInt(5, u.getBirthplace_id());
            stmt.execute();

        }
    }

    @Override
    public Country getCountryById(int id) throws Exception {
        Country c = null;
        Connection connectInstance = connect();
        Statement stmt = connectInstance.createStatement();
        stmt.execute("Select * from country where country.id=" + id);
        ResultSet rs = stmt.getResultSet();
        while (rs.next()) {
            String nationality = rs.getString("nationality");
            String birthPlace = rs.getString("birthplace");
            c = new Country(nationality, birthPlace);

        }
        return c;

    }

    @Override
    public User getUserBYId(int index) throws Exception {
        User u;
        try (Connection c = connect()) {
            Statement stmt = c.createStatement();
            stmt.execute("SELECT"
                    + "	u.*,"
                    + "	 n.nationality,"
                    + "	b.birthplace "
                    + "FROM"
                    + "	USER AS u"
                    + "	LEFT JOIN country AS n ON u.nationality_id = n.id"
                    + "	LEFT JOIN country AS b ON u.birthplace_id = b.id "
                    + "WHERE"
                    + "	u.id =1");

            ResultSet rs = stmt.getResultSet();
            rs.next();
            return getUser(rs);

        }
    }

    public static User getUser(ResultSet rs) throws SQLException {

        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String phone = rs.getString("phone_number");
        int nationality_id = rs.getInt("nationality_id");
        int birthplace_id = rs.getInt("birthplace_id");
        String nationality = rs.getString("nationality");
        String birthplace = rs.getString("birthplace");

        Country nationality_c = new Country(nationality, null);
        Country birthplace_c = new Country(null, birthplace);
        User u = new User(id, name, surname, phone, nationality_c, birthplace_c);
        return u;
    }

}
