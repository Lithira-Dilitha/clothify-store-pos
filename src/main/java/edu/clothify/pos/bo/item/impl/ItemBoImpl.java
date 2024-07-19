package edu.clothify.pos.bo.item.impl;

import edu.clothify.pos.bo.item.ItemBo;
import edu.clothify.pos.bo.orderdetails.OrderDetailsId;
import edu.clothify.pos.dao.DaoFactory;
import edu.clothify.pos.dao.item.ItemDao;
import edu.clothify.pos.dto.Item;
import edu.clothify.pos.dto.OrderDetails;
import edu.clothify.pos.entity.ItemEntity;
import edu.clothify.pos.entity.OrderDetailsEntity;
import edu.clothify.pos.utill.DaoType;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class ItemBoImpl implements ItemBo {
    ItemDao itemDao = DaoFactory.getInstance().getDao(DaoType.ITEM);
    ModelMapper modelMapper = new ModelMapper();
    @Override
    public boolean saveItem(Item item) {
        return itemDao.save(modelMapper.map(item, ItemEntity.class));
    }

    @Override
    public List<Item> getAllItemByIsActiveTrue() {
        return itemDao.getAllItemByIsActiveTrue();
    }

    @Override
    public Item getItemById(String itemId) {
        return itemDao.getItemById(itemId);
    }

    @Override
    public boolean updateItem(String itemId, Item item) {
        return itemDao.update(itemId,modelMapper.map(item, ItemEntity.class));
    }

    @Override
    public boolean deleteItem(String itemId) {
        return itemDao.delete(itemId);
    }

    @Override
    public boolean updateStock(List<OrderDetails> list) {
        System.out.println("this is Update Stock :"+list);
       List<OrderDetailsEntity> itemEntities = new ArrayList<>();
       for (OrderDetails orderDetails : list){
           itemEntities.add(new OrderDetailsEntity(
                   new OrderDetailsId(orderDetails.getOrderId(),orderDetails.getItemCode()),
                   orderDetails.getQty()
           ));
       }
       return itemDao.updateStock(itemEntities);
    }

    @Override
    public String generateItemId() {
        return itemDao.generateItemId();
    }


}
