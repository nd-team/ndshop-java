package org.ndshop.dbs.jpa.service;

import org.ndshop.dbs.jpa.constant.FinalCommons;
import org.ndshop.dbs.jpa.dao.MyRep;
import org.ndshop.dbs.jpa.dao.MySpecification;
import org.ndshop.dbs.jpa.dto.BaseDto;
import org.ndshop.dbs.jpa.entity.BaseEntity;
import org.ndshop.dbs.jpa.exception.RepException;
import org.ndshop.dbs.jpa.exception.SerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [基础的业务查询s实现]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public class ServiceImpl<BE extends BaseEntity, BD extends BaseDto> extends FinalCommons implements IService<BE, BD> {

    private static final Logger CONSOLE = LoggerFactory.getLogger(ServiceImpl.class);


    @Autowired
    protected MyRep<BE, BD> myRepository;
    @Autowired
    protected EntityManager entityManager;


    @Override
    public List<BE> findAll() throws SerException {
        return myRepository.findAll();
    }

    @Override
    public List<BE> findByPage(BD dto) throws SerException {
        try {
            MySpecification mySpecification = new MySpecification<BE, BD>(dto);
            PageRequest pageRequest = mySpecification.getPageRequest(dto);
            return myRepository.findAll(mySpecification, pageRequest).getContent();
        } catch (RepException e) {
            throw repExceptionHandler(e);
        }
    }


    @Override
    public Long count(BD dto) throws SerException {
        MySpecification mySpecification = new MySpecification<BE, BD>(dto);
        return myRepository.count(mySpecification);
    }

    @Override
    public BE findOne(BD dto) throws SerException {
        MySpecification mySpecification = new MySpecification<BE, BD>(dto);
        List<BE> list = myRepository.findAll(mySpecification);
        return null != list && list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<BE> findByCis(BD dto, Boolean pageAndSort) throws SerException {
        MySpecification mySpecification = new MySpecification<BE, BD>(dto);
        PageRequest pageRequest = mySpecification.getPageRequest(dto);
        return myRepository.findAll(mySpecification, pageRequest).getContent();

    }

    @Override
    public List<BE> findByCis(BD dto) throws SerException {
        MySpecification mySpecification = new MySpecification<BE, BD>(dto);
        if (null != dto.getSorts() && dto.getSorts().size() > 0) { //排序
            Sort.Direction dct = Sort.Direction.ASC;
            if (DESC.equalsIgnoreCase(dto.getOrder())) {
                dct = Sort.Direction.DESC;
            }
            return myRepository.findAll(new Sort(dct, dto.getSorts()));
        }
        return myRepository.findAll(mySpecification);
    }

    @Override
    public Long countByCis(BD dto) throws SerException {
        MySpecification mySpecification = new MySpecification<BE, BD>(dto);
        return myRepository.count(mySpecification);
    }

    @Override
    public BE findById(String id) throws SerException {
        return myRepository.findById(id);
    }

    @Transactional
    @Override
    public BE save(BE entity) throws SerException {
        return myRepository.save(entity);
    }

    @Transactional
    @Override
    public void save(Collection<BE> entities) throws SerException {
        myRepository.save(entities);
    }

    @Transactional
    @Override
    public void remove(String id) throws SerException {
        myRepository.delete(id);
    }

    @Transactional
    @Override
    public void remove(BE entity) throws SerException {
        myRepository.delete(entity);
    }

    @Transactional
    @Override
    public void remove(Collection<BE> entities) {
        myRepository.deleteInBatch(entities);
    }

    @Transactional
    @Override
    public void update(BE entity) throws SerException {
        myRepository.saveAndFlush(entity);
    }

    @Transactional
    @Override
    public void update(Collection<BE> entities) throws SerException {
        Stream<BE> stream = entities.stream();
        stream.forEach(entity -> {
            myRepository.saveAndFlush(entity);
        });

    }

    @Override
    public Boolean exists(String id) throws SerException {
        return myRepository.exists(id);
    }

    private SerException repExceptionHandler(RepException e) {
        String msg = "";
        switch (e.getType()) {
            case NOT_FIND_FIELD:
                msg = "非法查询";
                break;
            case ERROR_ARGUMENTS:
                msg = "参数不匹配";
                break;
            case ERROR_PARSE_DATE:
                msg = "时间类型转换错误,字段类型不匹配";
                break;
            case ERROR_NUMBER_FORMAT:
                msg = "整形转换错误,字段类型不匹配";
                break;
            default:
                msg = e.getMessage();
        }
        return new SerException(msg);
    }

    @Override
    public String findByMaxField(String field, Class clazz) throws SerException {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT MAX ( ");
        jpql.append(field);
        jpql.append(") FROM ");
        jpql.append(clazz.getSimpleName());
        Object obj = entityManager.createQuery(jpql.toString()).getSingleResult();
        return obj != null ? obj.toString() : "0";
    }

    @Override
    public String findByMinField(String field, Class clazz) throws SerException {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT MIN (");
        jpql.append(field);
        jpql.append(")FROM ");
        jpql.append(clazz.getSimpleName());
        Object obj = entityManager.createQuery(jpql.toString()).getSingleResult();
        return obj != null ? obj.toString() : null;
    }
}
