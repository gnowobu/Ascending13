package com.tzy.repository;

import com.tzy.model.Role;

import com.tzy.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {
    @Override
    public List<Role> findAllRoles() {
        String hql = "FROM Role";
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Role> res = new ArrayList<>();
        Transaction transaction = null;

        try{
            transaction = s.beginTransaction();
            Query<Role> query = s.createQuery(hql);
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
    public Role getRoleByName(String name) {
        return null;
    }

    @Override
    public void save(Role role) {

    }

    @Override
    public boolean delete(Role role) {
        return false;
    }
}
