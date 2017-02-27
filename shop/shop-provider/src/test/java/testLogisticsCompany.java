import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.shop.entity.LogisticsCompany;
import org.ndshop.shop.entity.Shop;
import org.ndshop.shop.enums.ShopStatus;
import org.ndshop.shop.service.ILogisticsCompanySer;
import org.ndshop.shop.service.IShopSer;
import org.ndshop.testshop.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: [caixianyong]
 * @Date: [2016-11-29 14:39]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {App.class})
public class testLogisticsCompany {

    @Autowired
    private ILogisticsCompanySer logisticsCompanySer;

    @Autowired
    private IShopSer shopSer;

    @Before
    public void init() throws SerException {
        Shop shop = shopSer.findByName("BBB");
        if(logisticsCompanySer.findByShop(shop).size()==0){
            LogisticsCompany company = new LogisticsCompany();
            company.setName("TTKD");
            company.setStatus(ShopStatus.OFFLINE);
            logisticsCompanySer.addLogisComp(company,shop);
        }
    }

    @Test
    public void testFindByShop() throws SerException {
        Shop shop = shopSer.findByName("BBB");
        if(logisticsCompanySer.findByShop(shop).size()<2){
            LogisticsCompany company = new LogisticsCompany();
            company.setName("YDKD");
            company.setStatus(ShopStatus.OFFLINE);
            logisticsCompanySer.addLogisComp(company,shop);
        }
        System.out.println(logisticsCompanySer.findByShop(shop).get(0));
    }

    @Test
    public void testFindOne() throws SerException {
        Shop shop = new Shop();
        shop.setName("BBB");
        Assert.assertNotNull(logisticsCompanySer.findByShopAndName(shop,"TTKD"));
    }

    @Test
    public void testChangeStatus() throws SerException {
        Shop shop = new Shop();
        shop.setName("BBB");
        LogisticsCompany company = new LogisticsCompany();
        company.setName("TTKD");

        logisticsCompanySer.changeStatus(company,shop);
    }


    @Test
    public void testAddLogisticsCompany() throws SerException{
        Shop shop = new Shop();
        shop.setName("BBB");

        LogisticsCompany company = new LogisticsCompany();
        company.setName("STKD");
        company.setStatus(ShopStatus.OFFLINE);

        logisticsCompanySer.addLogisComp(company,shop);

    }

    @Test
    public void testRemoveLogisticsCompany() throws SerException{
        Shop shop = new Shop();
        shop.setName("BBB");

        LogisticsCompany company = logisticsCompanySer.findByShopAndName(shop,"STKD");
        Assert.assertTrue(logisticsCompanySer.removeLogisComp(company));
    }

}


