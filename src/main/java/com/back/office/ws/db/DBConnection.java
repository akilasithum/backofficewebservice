package com.back.office.ws.db;

import com.back.office.ws.persistence.HibernateUtil;
import org.hibernate.classic.Session;

import java.util.List;

public class DBConnection {

    public List<?> getAllValues(String className){
        try
        {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            return session.createCriteria(Class.forName(className)).list();
        } catch (Exception e) {
            return null;
        }
    }

    public List getFilterList(String filterName,String fieldName,Integer fieldValue,String className){
        try
        {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.enableFilter(filterName).setParameter(fieldName, fieldValue);
            session.beginTransaction();
            return session.createCriteria(Class.forName(className)).list();
        } catch (Exception e) {
            return null;
        }
    }
}
