import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.shop.entity.Logistics;
import org.ndshop.shop.entity.LogisticsCompany;
import org.ndshop.shop.entity.Shop;
import org.ndshop.shop.enums.ShopStatus;
import org.ndshop.shop.service.ILogisticsCompanySer;
import org.ndshop.shop.service.ILogisticsSer;
import org.ndshop.shop.service.IShopSer;
import org.ndshop.testshop.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ike on 16-11-21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {App.class})
public class testLogistics {

    private static final Logger logger = Logger.getLogger(testLogistics.class);
    @Autowired
    private ILogisticsSer logisticsSer;

    @Autowired
    private IShopSer shopSer;

    @Autowired
    private ILogisticsCompanySer logisticsCompanySer;

    @Before
    public void init() throws SerException {

        Shop shop = new Shop();
        shop.setName("AAA");

        List<LogisticsCompany> companyList = logisticsCompanySer.findByShop(shop);
        LogisticsCompany company;
        if (companyList == null || companyList.size() == 0) {
            company = new LogisticsCompany();
            company.setStatus(ShopStatus.OFFLINE);
            company.setName("TTKD");
            logisticsCompanySer.addLogisComp(company, shop);
        } else {
            company = companyList.stream()
                    .filter(logisticsCompany -> logisticsCompany.getName().equals("TTKD"))
                    .findFirst().get();
        }

        Logistics logis = logisticsSer.findByShopAndName(shop, "one");
        if (logis == null) {
            logis = new Logistics();
            logis.setName("one");
            logis.setStatus(ShopStatus.OFFLINE);
            logis.setSeq(4);
            logis.setArea("23432");

            logisticsSer.addLogistics(logis, shop,company);
        }
    }

    @Test
    public void testAddLogistices() throws SerException {

        Logistics logistics = new Logistics();
        logistics.setName("two");
        logistics.setSeq(2);
        logistics.setStatus(ShopStatus.OFFLINE);

        Shop shop = new Shop();
        shop.setName("AAA");
        LogisticsCompany company = new LogisticsCompany();
        company.setName("TTKD");
        List<Logistics> list1 = logisticsSer.findByShop(shop);
        logisticsSer.addLogistics(logistics, shop,company);
        List<Logistics> list2 = logisticsSer.findByShop(shop);

        Assert.assertTrue(list1.size() < list2.size());
    }

    @Test
    public void testRemoveLogistics() throws SerException {
        Logistics logis = logisticsSer.findByShopAndName(shopSer.findByName("AAA"), "one");
        Assert.assertTrue(logis != null);

        logisticsSer.removeLogistics(logis);

        logis = logisticsSer.findByShopAndName(shopSer.findByName("AAA"), "one");
        Assert.assertTrue(logis == null);
    }

    @Test
    public void testChangeStatus() throws SerException {
        Shop shop = new Shop();
        shop.setName("AAA");

        LogisticsCompany company = new LogisticsCompany();
        company.setName("TTKD");


        Logistics one = logisticsSer.findByShopAndName(shop, "one");
        if (one == null) {
            one = new Logistics();
            one.setName("one");
            one.setStatus(ShopStatus.OFFLINE);
            one.setSeq(4);
            one.setArea("23432");

            logisticsSer.addLogistics(one, shop,company);
        }
        logisticsSer.changeStatus(one);
    }

    @Test
    public void testFindByShop() throws SerException {
        Shop shop = new Shop();
        shop.setName("AAA");

        /*Logistics logis = new Logistics();
        logis.setName("three");
        logis.setStatus(ShopStatus.OFFLINE);
        logis.setSeq(7);
        logis.setArea("23432");
        logisticsSer.addLogistics(logis,shop);*/

        List<Logistics> list = logisticsSer.findByShop(shop);
        for (Logistics log : list) {
            System.out.println(log);
            System.out.println(log.getSeq());
//            logger.info(JSON.toJSONString(log));
        }

    }


    @Test
    public void testModifyLogistics() throws SerException {
        Shop shop = new Shop();
        shop.setName("AAA");

        Logistics logis = logisticsSer.findByShopAndName(shop, "one");
        Assert.assertNotNull(logis);

        logis.setName("four");
        logisticsSer.modifyLogistics(logis);

        logis = null;

        logis = logisticsSer.findByShopAndName(shop, "four");
        Assert.assertNotNull(logis);
    }


    @Test
    public void testBatch() throws SerException {

        String[] listName = {"five", "six", "seven", "eight"};
        Shop shop = new Shop();
        shop.setId("e25c76e5-8bd0-4377-b2af-daf3dd944c7e");
        LogisticsCompany company = new LogisticsCompany();
        company.setName("TTKD");

        Logistics logistics;
        for (int i = 0; i < listName.length; i++) {
            logistics = new Logistics();
            logistics.setId(null);
            logistics.setName(listName[i]);
            logistics.setStatus(ShopStatus.OFFLINE);
            logistics.setArea("fdsaf");
            logistics.setSeq(i + 8);

            logisticsSer.addLogistics(logistics, shop,company);
        }

        List<Logistics> list = logisticsSer.findByShop(shop);
        List<Logistics> newList = new ArrayList<Logistics>();
        List<String> ids = new ArrayList<String>();
        for (Logistics log : list) {
            System.out.println(log);
            System.out.println(log.getSeq());
            if (Arrays.asList(listName).contains(log.getName())) {
                newList.add(log);
                ids.add(log.getId());
            }
        }

        //测试批量更改状态
        logisticsSer.changeStatusBatch(shop, ids);

        logistics = logisticsSer.findByShopAndName(shop, listName[listName.length - 1]);
        Assert.assertTrue(logistics.getStatus() == ShopStatus.ONLINE);

        //测试批量删除
        boolean flag = logisticsSer.removeLogisticsBatch(newList);
        Assert.assertTrue(flag);


    }

}
