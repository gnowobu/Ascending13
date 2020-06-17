package com.tzy.repository;

import com.tzy.model.Accounts;
import com.tzy.model.Department;
import com.tzy.model.Employee;
import com.tzy.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class AccountsDaoImp implements AccountsDao {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public List<Accounts> getAccounts() {

        List<Accounts> res = new ArrayList<>();
        String hql = "FROM Accounts a left join fetch a.employee";
        Transaction transaction = null;
        Session s = HibernateUtil.getSessionFactory().openSession();

        try{
            transaction = s.beginTransaction();
            Query<Accounts> query = s.createQuery(hql);
            res = query.list();
            logger.info("query complete");
            transaction.commit();

        } catch (HibernateException e) {
            if(transaction != null){
                transaction.rollback();
                logger.error("exception happened");
            }
        } finally {
            s.close();
        }

        return res;
    }

    @Override
    public void save(Accounts account) {
        Transaction transaction = null;
        Session s = HibernateUtil.getSessionFactory().openSession();

        try{
            transaction = s.beginTransaction();
            s.save(account);
            logger.info("save complete");
            transaction.commit();

        } catch (HibernateException e){
            if(transaction != null){
                transaction.rollback();
                e.printStackTrace();
            }
        } finally {
            s.close();
        }
    }

    @Override
    public boolean delete(Accounts account) {
        String hql = "DELETE FROM Accounts where id =: Id";
        Transaction transaction = null;
        Session s = HibernateUtil.getSessionFactory().openSession();
        int deletedCount = 0;

        try{
            transaction = s.beginTransaction();
            Query<Department> query = s.createQuery(hql);
            query.setParameter("Id",account.getId());
            deletedCount = query.executeUpdate();
            transaction.commit();
            return deletedCount >= 1;

        } catch (HibernateException e){
            logger.error("Exception happened");
            transaction.rollback();
        } finally {
            s.close();
        }
        return false;
    }

    @Override
    public Accounts findById(long id) {
        String hql = "FROM Accounts where id =: Id";
        Session s = HibernateUtil.getSessionFactory().openSession();
        Accounts account = new Accounts();
        Transaction transaction = null;
        try {
            transaction = s.beginTransaction();
            Query<Accounts> query = s.createQuery(hql);
            query.setParameter("Id",id);
            account = query.list().get(0);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            logger.error("session exception, try again");
        } finally {
            s.close();
        }
        return account;
    }
}
