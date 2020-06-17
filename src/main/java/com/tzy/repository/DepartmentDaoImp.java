package com.tzy.repository;

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

public class DepartmentDaoImp implements DepartmentDao {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public List<Department> getDepartments() {

        String hql = "FROM Department dep left join fetch dep.employees";
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Department> res = new ArrayList<>();
        Transaction transaction = null;

        try{
            transaction = s.beginTransaction();
            Query<Department> query = s.createQuery(hql);
            res = query.list();
            transaction.commit();
        } catch (HibernateException e){
            logger.error("exception happened");
            transaction.rollback();
        } finally {
            s.close();
        }

        return res;
    }

    @Override
    public void save(Department department) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = s.beginTransaction();
            s.save(department);
            logger.info("save completed!");
            transaction.commit();
        } catch (HibernateException e){
            logger.error("Exception happened");
            transaction.rollback();
        } finally {
            s.close();
        }
    }

    @Override
    public boolean delete(Department department) {

        String hql = "DELETE FROM Department where id =: Id";
        Transaction transaction = null;
        Session s = HibernateUtil.getSessionFactory().openSession();
        int deletedCount = 0;

        try{
            transaction = s.beginTransaction();
            Query<Department> query = s.createQuery(hql);
            query.setParameter("Id", department.getId());
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
    public Department findByName(String name) {
        String hql = "FROM Department where name =: Name";

        Session s = HibernateUtil.getSessionFactory().openSession();
        Department department =new Department();


        Transaction transaction = null;
        try {
            transaction = s.beginTransaction();
            Query<Department> query = s.createQuery(hql);
            query.setParameter("Name",name);
            department = query.list().get(0);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            logger.error("session exception, try again");
        }

        return department;
    }

    public Department getDepartmentEagerBy(Long id){
//        select * from departments as dep left join employees as e on a.employee_id=dep.id where dep.id=:Id
        String hql = "FROM Department d LEFT JOIN FETCH d.employees where d.id=:Id";
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Department> query = session.createQuery(hql);
            query.setParameter("Id",id);
            Department result = query.uniqueResult();
            session.close();
            return result;
        }catch (HibernateException e){
            logger.error("failure to retrieve data record",e);
            session.close();
            return null;
        }
    }

}
