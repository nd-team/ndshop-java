package org.ndshop.dbs.jpa.service;


import org.ndshop.dbs.jpa.dto.BaseDto;
import org.ndshop.dbs.jpa.entity.BaseEntity;
import org.ndshop.dbs.jpa.exception.SerException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [基础的业务查询接口]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public interface IService<BE extends BaseEntity, BD extends BaseDto> {

    /**
     * 查询所有数据
     *
     * @return
     * @throws SerException
     */
    default Optional<List<BE>> findAll() throws SerException {
        return null;
    }

    /**
     * 查询分页数据
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default Optional<List<BE>> findByPage(BD dto) throws SerException {
        return null;
    }

    /**
     * 查询数据量
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default Optional<Long> count(BD dto) throws SerException {
        return null;
    }

    /**
     * 查询第一个对象
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default Optional<BE> findOne(BD dto) throws SerException {
        return null;
    }

    /**
     * 根据条件询对象列表
     * 是否分页排序
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default Optional<List<BE>> findByCis(BD dto, Boolean pageAndSort) throws SerException {
        return null;
    }

    /**
     * 根据条件询对象列表
     * 默认不分页排序
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default Optional<List<BE>> findByCis(BD dto) throws SerException {
        return null;
    }


    /**
     * 根据条件询对象列表数量
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default Optional<Long> countByCis(BD dto) throws SerException {
        return null;
    }

    /**
     * 通过id查询某个对象
     *
     * @param id
     * @return
     * @throws SerException
     */
    default Optional<BE> findById(String id) throws SerException {
        return null;
    }


    /**
     * 保存对象
     *
     * @param entity
     * @throws SerException
     */
    default Optional<BE> save(BE entity) throws SerException {
        return null;
    }

    /**
     * 保存对象列表
     *
     * @param entities
     * @throws SerException
     */
    default void save(Collection<BE> entities) throws SerException {

    }

    /**
     * 通过id删除对象
     *
     * @param id
     * @throws SerException
     */
    default void remove(String id) throws SerException {

    }

    /**
     * 删除对象
     *
     * @param entity
     * @throws SerException
     */
    default void remove(BE entity) throws SerException {

    }

    /**
     * 删除对象列表
     *
     * @param entities
     * @throws SerException
     */
    default void remove(Collection<BE> entities) throws SerException {

    }


    /**
     * 更新对象
     *
     * @param entity
     * @throws SerException
     */
    default void update(BE entity) throws SerException {

    }

    /**
     * 更新对象
     *
     * @param entities
     * @throws SerException
     */
    default void update(Collection<BE> entities) throws SerException {

    }

    /**
     * 通过id查询对象是否存在
     *
     * @param id
     * @return
     * @throws SerException
     */
    default Optional<Boolean> exists(String id) throws SerException {
        return null;
    }

    /**
     * 查询最大值字段
     *
     * @param field
     * @return
     * @throws SerException
     */
    default Optional<String> findByMaxField(String field, Class clazz) throws SerException {
        return null;
    }

    /**
     * 查询最小值字段
     *
     * @param field
     * @return
     * @throws SerException
     */
    default Optional<String> findByMinField(String field, Class clazz) throws SerException {
        return null;
    }

}
