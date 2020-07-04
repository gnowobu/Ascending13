package com.tzy.repository;

import com.tzy.model.Department;
import com.tzy.model.User;
import com.tzy.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public List<User> findAllUsers() {
        String hql = "FROM User";
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<User> res = new ArrayList<>();
        Transaction transaction = null;

        try{
            transaction = s.beginTransaction();
            Query<User> query = s.createQuery(hql);
            res = query.list();
            transaction.commit();
        } catch (HibernateException e){
            //logger.error("exception happened");
            transaction.rollback();
        } finally {
            s.close();
        }
        return res;
    }

    @Override
    public User getUserByCredentials(String username, String password) {
        String hql = "from User where name =: name and password =: password";

        Session s = HibernateUtil.getSessionFactory().openSession();
        User user = new User();

        Transaction transaction = null;
        try {
            transaction = s.beginTransaction();
            Query<User> query = s.createQuery(hql);
            query.setParameter("name",username);
            query.setParameter("password",password);
            user = query.getSingleResult();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            logger.error("session exception, try again");
        }

        return user;
    }


    @Override
    public User getByName(String name) {
        String hql = "FROM User where name =: Name";

        Session s = HibernateUtil.getSessionFactory().openSession();
        User user = new User();

        Transaction transaction = null;
        try {
            transaction = s.beginTransaction();
            Query<User> query = s.createQuery(hql);
            query.setParameter("Name",name);
            user = query.getSingleResult();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            logger.error("session exception, try again");
        }

        return user;
    }

}
