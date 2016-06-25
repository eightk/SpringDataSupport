/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.jdbc.demo;

import com.spring.jdbc.demo.dao.JdbcDaoImpl;
import com.spring.jdbc.demo.model.User;
import java.sql.SQLException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author huico
 */
public class JdbcMain {
    public static void main(String[] args) throws SQLException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

        JdbcDaoImpl dao = ctx.getBean("jdbcDaoImpl", JdbcDaoImpl.class);

        User user = dao.getUser("Richard");
        
        if(user != null) {
            System.out.println(user.getFirstname() + " " + user.getLastname());
        } else {
            System.out.println("Failed to load user");
        }
    }
}
