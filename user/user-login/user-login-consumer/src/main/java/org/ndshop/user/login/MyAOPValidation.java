package org.ndshop.user.login;

import com.dounine.corgi.validation.aop.GlobalValidation;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by huanghuanlai on 16/4/23.
 */
@Component
@Aspect
public class MyAOPValidation extends GlobalValidation{

    @Pointcut("execution(* com.dounine.corgi..boot..*.*(..))")
    public void pointCut() {
    }

}
