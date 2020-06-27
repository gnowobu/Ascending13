package com.tzy.repository;

import com.tzy.model.User;
import com.tzy.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
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
}
