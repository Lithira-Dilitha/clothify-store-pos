package edu.clothify.pos.bo.item;

import edu.clothify.pos.bo.SuperBo;
import edu.clothify.pos.dto.Employee;
import edu.clothify.pos.dto.Item;

import java.util.List;

public interface ItemBo extends SuperBo {
    boolean saveItem(Item item);
    List<Item> getAllItemByIsActiveTrue();
    Item getItemById(String itemId);
    boolean updateItem(String itemId ,Item item);
    boolean deleteItem(String itemId);
}
