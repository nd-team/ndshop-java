package org.ndshop.dbs.jpa.service;


import org.ndshop.dbs.jpa.dto.BaseDto;
import org.ndshop.dbs.jpa.entity.BaseEntity;
import org.ndshop.dbs.jpa.exception.SerException;

import java.util.Collection;
import java.util.List;

/**
 * Created by huanghuanlai on 16/9/3.
 */
public interface IService<BE extends BaseEntity, BD extends BaseDto> {

    /**
     * 查询所有数据
     *
     * @return
     */
    default  List<BE> findAll() throws SerException {
        return null;
    }

    /**
     * 查询分页数据
     *
     * @param dto
     * @return
     */
    default List<BE> findByPage(BD dto) throws SerException {
        return null;
    }

    /**
     * 查询数据量
     *
     * @param dto
     * @return
     */
    default Long count(BD dto) throws SerException {
        return  null;
    }


    default BE findOne(BD dto) throws SerException {
        return  null;
    }

    /**
     * 根据条件询对象列表
     *是否分页排序
     * @param dto
     * @return
     */
    default List<BE> findByCis(BD dto, Boolean pageAndSort) throws SerException {
        return null;
    }

    /**
     * 根据条件询对象列表
     *默认不分页排序
     * @param dto
     * @return
     */
    default List<BE> findByCis(BD dto) throws SerException {
        return null;
    }


    /**
     * 根据条件询对象列表数量
     *
     * @param dto
     * @return
     */
    default Long countByCis(BD dto) throws SerException {
        return null;
    }

    /**
     * 查询某个对象
     *
     * @param id
     * @return
     */
    default BE findById(String id) throws SerException {
        return null;
    }


    /**
     * 保存对象
     *
     * @param entity
     */
    default BE save(BE entity) throws SerException {
        return  null;
    }

    /**
     * 保存对象列表
     *
     * @param entities
     */
    default void save(Collection<BE> entities) throws SerException {

    }

    /**
     * 通过id删除对象
     *
     * @param id
     */
    default  void remove(String id) throws SerException {

    }

    /**
     * 删除对象
     *
     * @param entity
     */
    default void remove(BE entity) throws SerException {

    }

    /**
     * 删除对象列表
     *
     * @param entities
     */
    default void remove(Collection<BE> entities) throws SerException {

    }


    /**
     * 更新对象
     *
     * @param entity
     */
    default void update(BE entity) throws SerException {

    }

    /**
     * 更新对象
     *
     * @param entities
     */
    default void update(Collection<BE> entities) throws SerException {

    }

    default Boolean exists(String id) throws SerException {
        return null;
    }

    /**
     * 查询最大值字段
     * @param field
     * @return
     * @throws SerException
     */
    default String findByMaxField(String field, Class clazz) throws SerException {
        return null;
    }

    /**
     * 查询最小值字段
     * @param field
     * @return
     * @throws SerException
     */
    default  String findByMinField(String field, Class clazz) throws SerException {
        return null;
    }

}
