/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.hibernate.demo;

import com.spring.hibernate.demo.dao.HibernateDaoImpl;
import java.sql.SQLException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author huico
 */
public class HiberanteMain {
    public static void main(String[] args) throws SQLException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        HibernateDaoImpl dao = ctx.getBean(HibernateDaoImpl.class);
        System.out.println(dao.getUserCount());
    }
}
