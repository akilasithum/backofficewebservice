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

    public Map<String,Item> getIemCodeItemsMap(){
        Map<String,Item> map = new HashMap<String, Item>();
        try
        {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Item.class);
            criteria.add(Restrictions.eq("recordStatus", 0));
            List<Item> items = criteria.list();
            for(Item item : items){
                map.put(item.getItemCode(),item);
            }
            return map;
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

    public List<PreOrderWeb> getPreOrdersForFlight(String flightNo,Date flightDate){
        try
        {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(PreOrderWeb.class);
            criteria.add(Restrictions.eq("flightNumber", flightNo));
            criteria.add(Restrictions.eq("flightDate", flightDate));
            return criteria.list();
        } catch (Exception e) {
            return null;
        }
    }

    public List<BondMessage> getBondMessages(String flightNo,Date flightDate){
        try
        {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(BondMessage.class);
            criteria.add(Restrictions.eq("flightNo", flightNo));
            criteria.add(Restrictions.eq("flightDate", flightDate));
            return criteria.list();
        } catch (Exception e) {
            return null;
        }
    }

    public List<PreOrderItemWeb> getPreOrderItems(int preOrderId){
        try
        {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(PreOrderItemWeb.class);
            criteria.add(Restrictions.eq("preOrderId", preOrderId));
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

    public HHCMaster getHHC(String hhcId){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(HHCMaster.class);
        criteria.add(Restrictions.eq("hhcId", hhcId));
        List<HHCMaster> hhcMasters = criteria.list();
        return (hhcMasters != null && !hhcMasters.isEmpty()) ? hhcMasters.get(0) : null;
    }

    public EquipmentMaster getEquipment(String cartNo){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(EquipmentMaster.class);
        criteria.add(Restrictions.eq("equipmentId", cartNo));
        List<EquipmentMaster> hhcMasters = criteria.list();
        return (hhcMasters != null && !hhcMasters.isEmpty()) ? hhcMasters.get(0) : null;
    }

    public List<EquipmentMaster> getEquipments(String flightNo,Date flightDate){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(EquipmentMaster.class);
        criteria.add(Restrictions.eq("flightNumber", flightNo));
        criteria.add(Restrictions.eq("lastUsedDate", flightDate));
        criteria.add(Restrictions.eq("recordStatus", 0));
        return criteria.list();
    }

    public EquipmentType getEquipmentType(String packType){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(EquipmentType.class);
        criteria.add(Restrictions.eq("packType", packType));
        List<EquipmentType> equipmentTypes = criteria.list();
        return (equipmentTypes != null && !equipmentTypes.isEmpty()) ? equipmentTypes.get(0) : null;
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
        criteria.add(Restrictions.eq("userRoleId", 9));
        criteria.add(Restrictions.eq("active", true));
        criteria.add(Restrictions.eq("recordStatus", 0));
        return criteria.list();
    }

    public List getFlightsFromBaseStation(String baseStation){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Flight.class);
        criteria.add(Restrictions.eq("baseStation", baseStation));
        criteria.add(Restrictions.eq("recordStatus", 0));
        return criteria.list();
    }

    public List getSectorsFromBaseStation(String baseStation){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Flight.class);
        criteria.add(Restrictions.eq("baseStation",baseStation));
        criteria.setProjection(Projections.property("flightId"));

        Criteria flightCriteria = session.createCriteria(Sector.class);
        criteria.add(Restrictions.in("flightId",criteria.list()));
        return flightCriteria.list();
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

    public void updatePreOrder(PreOrderWeb preOrder){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(PreOrderWeb.class);
        criteria.add(Restrictions.eq("preOrderId", preOrder.getPreOrderId()));
        List list = criteria.list();
        if(list != null && list.size() == 1){
            PreOrderWeb existingPreOrder = (PreOrderWeb) list.get(0);
            existingPreOrder.setPreOrderStatus(preOrder.getPreOrderStatus());
            session.beginTransaction();
            session.update(existingPreOrder);
            session.getTransaction().commit();
            session.close();

        }
        else{
            session.close();
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
