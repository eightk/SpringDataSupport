/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.hibernate.demo.dao;

import com.spring.jdbc.demo.model.User;
import java.util.List;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author huico
 */
@Repository
public class HibernateDaoImpl extends HibernateDaoSupport {

    public int getUserCount() {
        List<User> result = (List<User>) getSessionFactory().openSession().createQuery("from User").list();
        return result.size();
    }

}
