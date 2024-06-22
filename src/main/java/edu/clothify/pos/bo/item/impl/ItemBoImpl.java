package edu.clothify.pos.bo.item.impl;

import edu.clothify.pos.bo.item.ItemBo;
import edu.clothify.pos.dto.Item;

import java.util.List;

public class ItemBoImpl implements ItemBo {
    @Override
    public boolean saveItem(Item item) {
        return false;
    }

    @Override
    public List<Item> getAllItemByIsActiveTrue() {
        return List.of();
    }

    @Override
    public Item getItemById(String itemId) {
        return null;
    }

    @Override
    public boolean updateItem(String itemId, Item item) {
        return false;
    }

    @Override
    public boolean deleteItem(String itemId) {
        return false;
    }
}
