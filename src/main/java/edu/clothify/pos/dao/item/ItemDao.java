package edu.clothify.pos.dao.item;

import edu.clothify.pos.dao.CrudDao;
import edu.clothify.pos.dto.Item;
import edu.clothify.pos.dto.OrderDetails;
import edu.clothify.pos.entity.ItemEntity;
import edu.clothify.pos.entity.OrderDetailsEntity;

import java.util.List;

public interface ItemDao extends CrudDao<ItemEntity> {
    List<Item> getAllItemByIsActiveTrue();
    Item getItemById(String itemId);
    boolean updateStock(List<OrderDetailsEntity> list);
    boolean updateStock(Integer qty,String itemCode);
}
