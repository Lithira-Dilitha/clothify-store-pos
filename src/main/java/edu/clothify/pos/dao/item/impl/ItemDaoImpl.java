package edu.clothify.pos.dao.item.impl;


import edu.clothify.pos.dao.item.ItemDao;
import edu.clothify.pos.dto.Employee;
import edu.clothify.pos.dto.Item;
import edu.clothify.pos.entity.EmployeeEntity;
import edu.clothify.pos.entity.ItemEntity;
import edu.clothify.pos.utill.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl implements ItemDao {

    @Override
    public boolean save(ItemEntity entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(String id, ItemEntity entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("update ItemEntity set name = :name" +
                ",size = :size" +
                ",qty = :qty,price = :price,supplier = :supplier,isActive = :isActive where itemCode =:itemCode");
        query.setParameter("name",entity.getName());
        query.setParameter("size",entity.getSize());
        query.setParameter("qty",entity.getQty());
        query.setParameter("price",entity.getPrice());
        query.setParameter("isActive",entity.getIsActive());
        query.setParameter("supplier",entity.getSupplier());
        query.setParameter("itemCode",id);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("update ItemEntity set isActive = :isActive " +
                "where itemCode = :itemCode");
        query.setParameter("isActive",false);
        query.setParameter("itemCode",id);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public List<Item> getAllItemByIsActiveTrue() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        List<ItemEntity> allItems = session.createQuery("from ItemEntity where isActive =true", ItemEntity.class)
                .getResultList();
        List<Item> itemList = new ArrayList<>();
        allItems.forEach(itemEntity ->{
            itemList.add(new ModelMapper().map(itemEntity,Item.class));
        });
        session.getTransaction().commit();
        session.close();
        return itemList;
    }

    @Override
    public Item getItemById(String itemId) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        ItemEntity itemEntity = session.createQuery("from ItemEntity where itemCode = :itemCode"
                        , ItemEntity.class)
                .setParameter("itemCode",itemId)
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return new ModelMapper().map(itemEntity, Item.class);
    }
}
