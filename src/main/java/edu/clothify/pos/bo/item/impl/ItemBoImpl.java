package edu.clothify.pos.bo.item.impl;

import edu.clothify.pos.bo.item.ItemBo;
import edu.clothify.pos.dao.DaoFactory;
import edu.clothify.pos.dao.item.ItemDao;
import edu.clothify.pos.dto.Item;
import edu.clothify.pos.entity.ItemEntity;
import edu.clothify.pos.utill.DaoType;
import org.modelmapper.ModelMapper;

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
}
