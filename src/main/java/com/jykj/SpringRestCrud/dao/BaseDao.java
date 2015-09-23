package com.jykj.SpringRestCrud.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by albert on 2015/9/17.
 */
public interface BaseDao<E, PK extends Serializable> {
    void save(E newObject);
    void update(E transientObject);
    void saveOrUpdate(E transientObject);
    void delete(E persistentObject);
    void delete(PK id);
    E findById(PK id);
    List<E> findAll();
    List<E> findAll(int pageNo, int pageSize);
    List<E> findByStr(String queryStr, Map<String, Object> attributes, int pageNo, int pageSize);
}