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
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

/**
 *
 * @author huico
 */
@Component
public class JdbcDaoImpl {

    public int getUserCount(String username) {
        String sql = "select count(*) from user where username = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{username}, Integer.class);
    }

    public User getUserById(String username) {
        String sql = "select *  from user where username = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{username}, new UserMapper());
    }
    
    public List<User> getAllUser() {
        String sql = "select * from user";
        return jdbcTemplate.query(sql, new UserMapper());
    }
    /*
    public void insertUser(User user) {
        String sql = "Insert into user values (?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, new Object[] {user.getUsername(), user.getPassword(), 
            user.getEmail(), user.getFirstname(), user.getLastname(), user.getAge(), 
            user.getCreatedate(), user.getLevel()});
    }*/

    public void insertUser(User user) {
        String sql = "Insert into user values (:username,:password,:email,:firstname,:lastname,:age,:createdate,:level)";
        SqlParameterSource namedParams = new MapSqlParameterSource("username", user.getUsername()).addValue("password", user.getPassword())
                .addValue("email", user.getEmail()).addValue("firstname", user.getFirstname()).addValue("lastname", user.getLastname())
                .addValue("age", user.getAge()).addValue("createdate", user.getCreatedate()).addValue("level", user.getLevel());
        namedParamJdbcTemplate.update(sql, namedParams);
    }
    
    public DataSource getDataSource() {
        return dataSource;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public NamedParameterJdbcTemplate getNamedParamJdbcTemplate() {
        return namedParamJdbcTemplate;
    }

    public void setNamedParamJdbcTemplate(NamedParameterJdbcTemplate namedParamJdbcTemplate) {
        this.namedParamJdbcTemplate = namedParamJdbcTemplate;
    }

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParamJdbcTemplate;    

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
