package org.ndshop.user.common.dao;

import org.ndshop.dbs.jpa.dao.MyRep;
import org.ndshop.user.common.dto.UserLoginLogDto;
import org.ndshop.user.common.entity.UserLoginLog;


/**
 * @Author: [liguiqin]
 * @Date: [2016-11-28 16:47]
 * @Description: [用户登录日志持久化接口, 继承基类可使用ｊｐａ命名查询]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public interface IUserLoginLogRep extends MyRep<UserLoginLog,UserLoginLogDto> {


}
