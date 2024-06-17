package edu.clothify.pos.dao;

public interface CrudDao <T> extends SuperDao {
    boolean save(T dto);
}
