import com.dounine.corgi.cluster.Balance;
import com.dounine.corgi.rpc.RpcApp;
import com.dounine.corgi.spring.rpc.Reference;
import com.dounine.corgi.spring.rpc.ReferenceImpl;
import org.junit.runner.RunWith;
import org.ndshop.shop.dao.ILogisticsRep;
import org.ndshop.shop.service.ILogisticsSer;
import org.ndshop.shop.service.LogisticsSerImpl;
import org.ndshop.testshop.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.PostConstruct;

/**
 * Created by ike on 16-11-21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {App.class})
public class testLogistics {

    @Reference
    private ILogisticsSer logisticsSer;

    @Autowired
    protected Balance balance;

    @PostConstruct
    public void initApi(){
        logisticsSer = RpcApp.instance().getProxy(ILogisticsSer.class, new ReferenceImpl(),balance);
    }
}
