package com.bjike.ndshop.dbs.jpa.dao;

import com.bjike.ndshop.dbs.jpa.boot.Constant;
import com.bjike.ndshop.dbs.jpa.dto.BaseDto;
import com.bjike.ndshop.dbs.jpa.dto.Condition;
import com.bjike.ndshop.dbs.jpa.entity.BaseEntity;
import com.bjike.ndshop.dbs.jpa.enums.RepExceptionType;
import com.bjike.ndshop.dbs.jpa.enums.RestrictionType;
import com.bjike.ndshop.dbs.jpa.exception.RepException;
import com.bjike.ndshop.dbs.jpa.utils.PrimitiveUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.lang.reflect.Method;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by lgq on 16-9-30.
 */
public class MySpecification<BE extends BaseEntity, BD extends BaseDto> implements Specification<BE> {

    private static final Pattern PATTERN = Pattern.compile("\\[[a-zA-Z0-9]+\\]");

    private BD dto;

    public MySpecification(BD dto) {
        this.dto = dto;
    }


    @Override
    public Predicate toPredicate(Root<BE> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> preList = null;
        try {
            preList = initPredicates(dto, root, cb);
        } catch (RepException e) {
            throw e;
        }

        Predicate[] predicates = preList.toArray(new Predicate[preList.size()]);
        return query.where(predicates).getRestriction();

    }


    /**
     * 连表查询set集合暂时无效，暂时仅支持单属性（model）查询
     * @param dto
     * @param root
     * @param cb
     * @return
     * @throws RepException
     */
    private List<Predicate> initPredicates(BD dto, Root<BE> root, CriteriaBuilder cb) throws RepException {

        List<Predicate> preList = new ArrayList<>(0); //条件列表
        List<Condition> conditions = dto.getConditions() != null ? dto.getConditions() : new ArrayList<>(0);//避免条件列表为空


        Boolean or_predicate = false; //标志处理 or 条件
        try {
            for (Condition model : conditions) {
                Predicate predicate = null;
                Class clazz = PrimitiveUtil.switchType(model.getFieldType()); //得到数据类型
                String field = model.getField(); //字段

                RestrictionType type = model.getRestrict();
                String[] fields = model.getField().split("#");
                Join<BE, Object> leftJoin = handlerJoinTable(model, root, fields);  //是否有连接查询
                Boolean existJoin = leftJoin != null;
                Method method = handlerMethod(cb, model);//获得反射调用方法

                if (existJoin) {
                    field = fields[fields.length - 1]; //有连接查询取最后的分割字段
                }
                switch (type) {
                    case LIKE:
                        if (existJoin) {
                            predicate = cb.like(leftJoin.get(field).as(clazz), "%" + model.getValues()[0] + "%");
                        } else {
                            predicate = cb.like(root.get(field).as(clazz), "%" + model.getValues()[0] + "%");
                        }
                        break;
                    case ISNULL:
                        if (existJoin) {
                            predicate = cb.isNull(leftJoin.get(field).as(clazz));
                        } else {
                            predicate = cb.isNull(root.get(field).as(clazz));
                        }
                        break;
                    case ISNOTNULL:
                        if (existJoin) {
                            predicate = cb.isNotNull(leftJoin.get(field).as(clazz));
                        } else {
                            predicate = cb.isNotNull(root.get(field).as(clazz));
                        }
                        break;
                    case OR:
                        //仅做一次处理，一次处理完所有or
                        if (!or_predicate) {
                            List<Predicate> _predicates = new ArrayList<>();
                            for (Condition cdis : conditions) {
                                if (cdis.getRestrict().equals(RestrictionType.OR)) {
                                    if (existJoin) {
                                        predicate = cb.equal(leftJoin.get(cdis.getField()).as(clazz), cdis.getValues()[0]);
                                    } else {
                                        predicate = cb.isNotNull(root.get(field).as(clazz));
                                    }
                                    _predicates.add(predicate);
                                }
                            }
                            Predicate[] arr_pre = new Predicate[_predicates.size()];
                            _predicates.toArray(arr_pre);
                            predicate = cb.or(arr_pre);
                            or_predicate = true;
                        }
                        break;
                    default:
                        Object[] values = PrimitiveUtil.convertValuesByType(model.getValues(), model.getFieldType());
                        if (type == RestrictionType.IN) {
                            if (existJoin) {
                                predicate = (Predicate) method.invoke(cb, leftJoin.get(field).as(clazz), values);
                            } else {
                                predicate = (Predicate) method.invoke(cb, root.get(field).as(clazz), values);
                            }
                        } else {
                            if (existJoin) {
                                predicate = (Predicate) method.invoke(cb, ArrayUtils.add(values, 0, leftJoin.get(field).as(clazz)));
                            } else {
                                predicate = (Predicate) method.invoke(cb, ArrayUtils.add(values, 0, root.get(field).as(clazz)));
                            }
                        }
                }
                if (null != predicate) {
                    preList.add(predicate);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            exceptionHandler(e);

        }

        return preList;
    }

    private Method handlerMethod(CriteriaBuilder cb, Condition condition) {
        Method[] methods = cb.getClass().getDeclaredMethods();
        Method method = null;

        for (Method m : methods) {
            Class<?>[] types = m.getParameterTypes();
            String name = RestrictionType.getRestrict(condition.getRestrict());
            if (m.getName().equals(name) &&
                    types[types.length - 1] != Expression.class) {
                method = m;
                break;
            }
        }
        return method;
    }


    /**
     * 左连接查询
     *
     * @param c
     * @param root
     * @return
     */
    private Join<BE, Object> handlerJoinTable(Condition c, Root<BE> root, String[] fields) {

        int fields_length = fields.length;
        if (fields_length > 1) {
            String[] _tempField = fields[0].split("\\.");
            String str = _tempField[_tempField.length - 1];
            String firstLetter = String.valueOf(str.charAt(0));
            String entityName = str.replaceFirst(firstLetter, firstLetter.toLowerCase());
            Join<BE, Object> join = root.join(entityName, JoinType.LEFT);
            if (fields_length > 2) {
                for (int i = 0; i < fields_length; i++) {
                    if (i > 1 && i < fields_length) {
                        _tempField = fields[i - 1].split("\\.");
                        str = _tempField[_tempField.length - 1];
                        firstLetter = String.valueOf(str.charAt(0));
                        entityName = str.replaceFirst(firstLetter, firstLetter.toLowerCase());
                        join = join.join(entityName, JoinType.LEFT);
                    }
                }
            }
            return join;
        }
        return null;

    }

    private RepException exceptionHandler(Exception e) {
        String msg = "";
        RepExceptionType type = RepExceptionType.UNDEFINE;

        if (e instanceof IllegalArgumentException) {
            Matcher matcher = PATTERN.matcher(e.getMessage());
            boolean isFind = matcher.find();
            if (isFind) { //字段不匹配
                msg = StringUtils.substring(matcher.group(), 1, -1);
                type = RepExceptionType.NOT_FIND_FIELD;
            } else if (e.getMessage().indexOf("wrong number of arguments") != -1) { //其他错误
                msg = "wrong number of arguments";
                type = RepExceptionType.ERROR_ARGUMENTS;
            } else if (e instanceof NumberFormatException) {
                msg = e.getMessage();
                type = RepExceptionType.ERROR_NUMBER_FORMAT;
            }
        } else {
            if (e instanceof DateTimeParseException) {
                msg = e.getMessage();
                type = RepExceptionType.ERROR_PARSE_DATE;
            } else {
                msg = e.getMessage();
                type = RepExceptionType.UNDEFINE;
            }

        }
        throw new RepException(type, msg);
    }


    /**
     * 分页及排序
     *
     * @param dto
     * @return
     */
    public PageRequest getPageRequest(BD dto) {
        PageRequest pageRequest = null;
        if (dto.getSorts() != null && dto.getSorts().size() > 0) {
            Sort.Direction dct = Sort.Direction.ASC;
            if (dto.getOrder().equals("desc")) {
                dct = Sort.Direction.DESC;
            }
            pageRequest = new PageRequest(dto.getPage(), dto.getLimit(), new Sort(dct, dto.getSorts())); //分页带排序
        } else {
            pageRequest = new PageRequest(dto.getPage(), dto.getLimit()); //分页不带排序
        }
        return pageRequest;
    }


}
