/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.jdbc.demo.dao;

import com.spring.jdbc.demo.DatabaseDictionary;
import com.spring.jdbc.demo.model.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author huico
 */
@Component
public class JdbcDaoImpl {
    
    public User getUser(String id) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User user = new User();

        try {
            conn = dataSource.getConnection();
            String sql = "SELECT * from user where username = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            while (rs.next() && id.equals(rs.getString(1))) {
                String username = rs.getString(1);
                String email = rs.getString(3);
                String firstname = rs.getString(4);
                String lastname = rs.getString(5);
                int age = rs.getInt(6);
                Date createdate = rs.getDate(7);
                int level = rs.getInt(8);
                user.setUsername(username);
                user.setPassword(rs.getString(2));
                user.setEmail(email);
                user.setFirstname(firstname);
                user.setLastname(lastname);
                user.setAge(age);
                user.setCreatedate(createdate);
                user.setLevel(level);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            rs.close();
            pstmt.close();
            conn.close();
        }
        return user;
    }
    
    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    @Autowired
    private DataSource dataSource;
}
