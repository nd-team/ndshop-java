package org.ndshop.user.common;

import org.ndshop.user.common.boot.App;
import com.dounine.corgi.spring.ApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.concurrent.CountDownLatch;

public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args){
        LOGGER.info("user-common-provider starting...");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        ApplicationContext.setApplicationContext(context);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

