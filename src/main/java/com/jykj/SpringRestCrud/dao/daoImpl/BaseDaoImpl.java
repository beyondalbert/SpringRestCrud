package com.jykj.SpringRestCrud.dao.daoImpl;

import com.jykj.SpringRestCrud.dao.BaseDao;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * Created by albert on 2015/9/17.
 */
public abstract class BaseDaoImpl<E, PK extends Serializable>  implements BaseDao<E, PK> {
    static final Logger logger = Logger.getLogger(BaseDaoImpl.class);

    @Autowired
    protected SessionFactory sessionFactory;

    private Class<E> clazz;

    public BaseDaoImpl() {
        ParameterizedType parameterizedType = (ParameterizedType)this.getClass().getGenericSuperclass();

        this.clazz = (Class<E>)parameterizedType.getActualTypeArguments()[0];
        logger.info("clazz:" + clazz);
    }

    /**
     * @Description getCurrentSession会自动关闭session
     * @return
     */
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * @Description 获取一个新的session
     * @return
     */
    public Session getNewSession() {
        return sessionFactory.openSession();
    }

    @SuppressWarnings("unchecked")
    public void save(E newObject) {
        getSession().save(newObject);
    }

    public void update(E transientObject) {
        getSession().update(transientObject);
    }

    public void saveOrUpdate(E transientObject) {
        getSession().saveOrUpdate(transientObject);
    }

    public void delete(E persistentObject) {
        getSession().delete(persistentObject);
    }

    public void delete(PK id) {
        Session session = getNewSession();
        E persistentObject = (E)session.get(clazz, id);
        Transaction tx = session.getTransaction();
        session.beginTransaction();
        session.delete(persistentObject);
        tx.commit();
//        E persistentObject = findById(id);
//        getSession().delete(persistentObject);
    }

    @SuppressWarnings("unchecked")
    public E findById(PK id) {
        return (E)getSession().get(clazz, id);
    }

    @SuppressWarnings("unchecked")
    public List<E> findAll() {
        return (List<E>)getSession().createCriteria(clazz).list();
    }

    /**
     * 支持分页查询所有数据
     * @param pageNo
     * @param pageSize
     * @return
     */
    public List<E> findAll(int pageNo, int pageSize) {
        Criteria criteria = getSession().createCriteria(clazz);
        criteria.setFirstResult((pageNo - 1) * pageSize);
        criteria.setMaxResults(pageSize);
        return criteria.list();
    }

    /**
     *  通过HQL的方式查询数据
     * @param queryStr
     * @param attributes
     * @param pageNo
     * @param pageSize
     * @return
     */
    public List<E> findByStr(String queryStr, Map<String, Object> attributes, int pageNo, int pageSize) {
        Session session = this.getSession();
        List<E> results = null;
        try {
            Query query = session.createQuery(queryStr);
            if (attributes != null) {
                for (String key : attributes.keySet()) {
                    Object value = attributes.get(key);
                    if (value != null)
                        query.setParameter(key, value);
                    else
                        query.setParameter(key, "%");
                }
            }

            if (pageNo > 0) {
                query.setFirstResult((pageNo - 1) * pageSize);
                query.setMaxResults(pageSize);
            }

            results = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }
}
