package org.ndshop.user.common.service;


import org.ndshop.dbs.jpa.dto.Condition;
import org.ndshop.dbs.jpa.enums.DataType;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.ndshop.user.common.dto.UserLoginLogDto;
import org.ndshop.user.common.entity.User;
import org.ndshop.user.common.entity.UserLoginLog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-28 14:47]
 * @Description: [用户登录注册业务实现]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Service
public class UserLoginLogSerImpl extends ServiceImpl<UserLoginLog, UserLoginLogDto> implements IUserLoginLogSer {

    /**
     * 每个用户仅保存最近的五条登录记录
     * @param loginLog
     * @return
     * @throws SerException
     */
    @Transactional
    @Override
    public UserLoginLog save(UserLoginLog loginLog) throws SerException {
        UserLoginLogDto dto = new UserLoginLogDto();
        Condition cond = new Condition("id", DataType.STRING,loginLog.getUser().getId());
        cond.fieldToModels(User.class);
        dto.getConditions().add(cond);
        dto.setOrder(DESC);
        dto.setSorts(Arrays.asList("loginTime"));
        List<UserLoginLog> loginLogs = findByCis(dto,true,false);
        if(null!=loginLogs && loginLogs.size()>=5){
            super.remove(loginLogs.get(4)); //删除最旧的数据
        }
        return super.save(loginLog);
    }

    @Transactional
    @Override
    public void save(Collection<UserLoginLog> userLoginLogs) throws SerException {
        for(UserLoginLog log:userLoginLogs){ //批量添加
            this.save(log);
        }
    }

    @Override
    public List<UserLoginLog> findUserLogin(String userId)throws SerException {
        UserLoginLogDto dto = new UserLoginLogDto();
        Condition cond = new Condition("id", DataType.STRING,userId);
        cond.fieldToModels(User.class);
        dto.getConditions().add(cond);
        dto.setOrder(DESC);
        dto.setSorts(Arrays.asList("loginTime"));
        return findByCis(dto);
    }
}
