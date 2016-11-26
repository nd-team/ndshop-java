package org.ndshop.user.login.user;

import com.dounine.corgi.spring.rpc.Service;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.user.login.service.IUserAuthCodeSer;
import org.ndshop.user.login.session.validfail.ValidErrSession;
import org.ndshop.user.login.session.validfail.VerifyCode;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-26 09:36]
 * @Description: [用户验证码业务实现]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Service
public class UserAuthCodeSerImpl implements IUserAuthCodeSer {

    @Override
    public boolean showAuthCode(String account) throws SerException {
        VerifyCode code = ValidErrSession.get(account);
        if (null != code && code.getCount() >= 5) {
            return true;
        }
        return false;
    }

    @Override
    public boolean generateCode(String account) throws SerException {
        return false;
    }
}
