/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.jdbc.demo.dao;

import com.spring.jdbc.demo.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 *
 * @author huico
 */
public class SimpleJdbcDaoImpl extends JdbcDaoSupport {
    public int getUserCount(String username) {
        String sql = "select count(*) from user where username = ?";
        return getJdbcTemplate().queryForObject(sql, new Object[]{username}, Integer.class);
    }

    public User getUserById(String username) {
        String sql = "select *  from user where username = ?";
        return getJdbcTemplate().queryForObject(sql, new Object[]{username}, new UserMapper());
    }
    
    public List<User> getAllUser() {
        String sql = "select * from user";
        return getJdbcTemplate().query(sql, new UserMapper());
    }
    
    public void insertUser(User user) {
        String sql = "Insert into user values (?,?,?,?,?,?,?,?)";
        getJdbcTemplate().update(sql, new Object[] {user.getUsername(), user.getPassword(), 
            user.getEmail(), user.getFirstname(), user.getLastname(), user.getAge(), 
            user.getCreatedate(), user.getLevel()});
    }
    
    private static final class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int i) throws SQLException {
            User user = new User();
            user.setUsername(rs.getString(1));
            user.setPassword(rs.getString(2));
            user.setEmail(rs.getString(3));
            user.setFirstname(rs.getString(4));
            user.setLastname(rs.getString(5));
            user.setAge(rs.getInt(6));
            user.setCreatedate(rs.getDate(7));
            user.setLevel(rs.getInt(8));
            return user;
        }

    }
}
