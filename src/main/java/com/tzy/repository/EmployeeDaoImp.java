package com.tzy.repository;

import com.github.fluent.hibernate.H;
import com.tzy.model.Employee;
import com.tzy.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImp implements EmployeeDao {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Employee findById(long id) {
        String hql = "FROM Employee where id =: Id";
        Session s = HibernateUtil.getSessionFactory().openSession();
        Employee employee = new Employee();

        Transaction transaction = null;
        try {
            transaction = s.beginTransaction();
            Query<Employee> query = s.createQuery(hql);
            query.setParameter("Id",id);
            employee = query.list().get(0);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            logger.error("session exception, try again");
        }

        return employee;
    }

    @Override
    public List<Employee> getEmployee() {
        String hql = "FROM Employee";
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Employee> res = new ArrayList<>();

        try{
            transaction = s.beginTransaction();
            Query<Employee> query = s.createQuery(hql);
            res = query.list();
            transaction.commit();
            s.close();
        } catch (HibernateException e){
            transaction.rollback();
            logger.error("session exception, try again");
            s.close();
        }
        return res;
    }

    @Override
    public boolean delete(Employee employee) {

        String hql = "DELETE Employee where id = :Id";
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        int deletedCount = 0;

        try{
            transaction = s.beginTransaction();
            Query<Employee> query = s.createQuery(hql);
            query.setParameter("Id", employee.getId());
            deletedCount = query.executeUpdate();
            transaction.commit();
            s.close();
            return deletedCount >= 1 ? true:false;
        } catch (HibernateException e) {
            logger.error("Exception happened");
            transaction.rollback();
            s.close();
        }
        return false;
    }

    @Override
    public void save(Employee employee) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try{
            transaction = s.beginTransaction();
            s.save(employee);

            transaction.commit();
            logger.info("save completed!");
        } catch (HibernateException e){
            transaction.rollback();
            logger.error("exception happened");
        } finally {
            s.close();
        }
    }
}
