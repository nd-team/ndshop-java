package goods.provider.test;

import com.dounine.corgi.jta.impl.JTAApiFilterImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by huanghuanlai on 2016/12/19.
 */
@Aspect
@Component
public class MyJTAFilterImpl extends JTAApiFilterImpl {
    @Around("execution(* org.ndshop.goods.service..*.*(..))")
    public Object aroundMethod(ProceedingJoinPoint pjd) throws Throwable {
        return super.aroundMethod(pjd);
    }
}
