package com.back.office.ws.db;

import com.back.office.ws.entity.*;
import com.back.office.ws.persistence.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.*;

public class DBConnection {

    public List<?> getAllValues(String className){
        try
        {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Class.forName(className));
            criteria.add(Restrictions.eq("recordStatus", 0));
            return criteria.list();
        } catch (Exception e) {
            return null;
        }
    }

    public List<?> getAllValuesNoRecordStatus(String className){
        try
        {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Class.forName(className));
            return criteria.list();
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

    public int insertObjectHBM(Object details){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        int id = (Integer)session.save(details);
        session.getTransaction().commit();
        session.close();
        return id;
    }

    public void updateObjectHBM(Object details){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(details);
        session.getTransaction().commit();
        session.close();
    }

    public SIFDetails getSIF(int sifId){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(SIFDetails.class);
        criteria.add(Restrictions.eq("sifNoInt", sifId));
        return (SIFDetails) criteria.list().get(0);
    }

    public List getCurrentPromotions(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Promotion.class);
        criteria.add(Restrictions.le("activateDate", yesterday(new Date())));
        criteria.add(Restrictions.ge("endDate", tommorow(new Date())));
        return criteria.list();
    }

    public List getUsers(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(User.class);
        Integer[] roleIds = {9,10};
        criteria.add(Restrictions.in("userRoleId", roleIds));
        criteria.add(Restrictions.eq("active", true));
        criteria.add(Restrictions.eq("recordStatus", 0));
        return criteria.list();
    }

    public byte[] getItemImageFromItemCode(String itemCode){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Item.class);
        criteria.add(Restrictions.eq("itemCode", itemCode));
        criteria.add(Restrictions.eq("recordStatus", 0));
        criteria.setProjection(Projections.distinct(Projections.property("image")));
        List list = criteria.list();
        if(list != null && list.size() > 0){
            return (byte[]) criteria.list().get(0);
        }
        return null;
    }

    public DepartureFlights getFlightNameDepFlightMap(String flightNo){
        try
        {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Criteria criteria = session.createCriteria(DepartureFlights.class);
            criteria.add(Restrictions.eq("flightNo", flightNo));
            List itemDetails = criteria.list();
            if(itemDetails != null && !itemDetails.isEmpty()){
                return (DepartureFlights)itemDetails.get(0);
            }
            else return null;
        } catch (Exception e) {


            return null;
        }
    }

    private Date yesterday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, - 1);
        return cal.getTime();
    }

    private Date tommorow(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, + 1);
        return cal.getTime();
    }
}
