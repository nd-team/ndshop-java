package test_java_service;

import com.alibaba.fastjson.JSON;
import com.bjike.ndshop.dbs.jpa.dto.Condition;
import com.bjike.ndshop.dbs.jpa.enums.DataType;
import com.bjike.ndshop.dbs.jpa.enums.RestrictionType;
import com.bjike.ndshop.dbs.jpa.exception.SerException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import test_java_service.code.ApplicationConfiguration;
import test_java_service.code.dto.UserDto;
import test_java_service.code.entity.User;
import test_java_service.code.service.IUserSer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by huanghuanlai on 2016/10/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class JunitTest {

    /**
     * 基础增删改查，批量操作等
     */

    @Autowired
    private IUserSer userSer;


    @Before
    public void init() throws SerException {
        if (null == userSer.findByUsername("liguiqin")) {
            User user = new User();
            user.setUsername("liguiqin");
            user.setPassword("123456");
            user.setMoney(5000.0);
            user.setAge(55);
            user.setHeight(1.2f);
            user.setNickname("xiaoming");
            user.setSuperMan(true);
            userSer.save(user);
        }
    }

    /**
     * 查询全部
     */
    @Test
    public void findAll() throws SerException {
        List<User> users = userSer.findAll();
        for (User u : users) {
            System.out.println(u.getUsername());
        }
    }


    /**
     * 分页查询,可带条件及排序
     */
    @Test
    public void findByPage() throws SerException {
        UserDto dto = new UserDto();
        dto.setPage(3);
        dto.setSorts(Arrays.asList("username"));
        List<User> users = userSer.findByPage(dto);
        for (User u : users) {
            System.out.println(u.getUsername());
        }
    }


    //条件查询
    @Test
    public void findByCis() throws SerException {
        UserDto dto = new UserDto();
        Condition condition = new Condition("superMan", DataType.BOOLEAN,false);
        condition.setRestrict(RestrictionType.EQ);
        dto.getConditions().add(condition);
        List<User> users = userSer.findByCis(dto, true); //按条件查询并分页
        System.out.println(JSON.toJSONString(users));
    }


    /**
     * 更新对象
     */
    @Test
    public void update() throws SerException {

        User user = userSer.findByUsername("liguiqin");
        user.setPassword("666 this is a pass");
        System.out.println(JSON.toJSONString(user));
    }

    /**
     * 删除对象
     */
    @Test
    public void remove() throws SerException {
        User user = userSer.findByUsername("liguiqin");
        userSer.remove(user.getId());
        System.out.println("remove user success!");
    }

    /**
     * 模糊查询
     */
    @Test
    public void findByLike() throws SerException {
        UserDto dto = new UserDto();
        Condition c = new Condition("username", DataType.STRING);
        c.setValues(new String[]{"gui"});
        c.setRestrict(RestrictionType.LIKE);
        dto.getConditions().add(c);
        User user = userSer.findOne(dto);
        System.out.println(JSON.toJSONString(user));
    }

    /**
     * 批量添加数据
     */
    @Test
    public void addAll() throws SerException {
        List<User> users = new ArrayList<>(5);
        for (int i = 0; i < 15; i++) {
            User user = new User();
            user.setUsername("test" + i);
            user.setAge(20 + i);
            user.setPassword("password" + i);
            users.add(user);

        }
        userSer.save(users);
        System.out.println(JSON.toJSONString(users));

    }

    /**
     * 批量修改数据
     */
    @Test
    public void updateAll() throws SerException {
        UserDto dto = new UserDto();
        List<User> users = null;
        Condition c = new Condition("username", DataType.STRING);
        c.setValues(new String[]{"testName"});
        c.setRestrict(RestrictionType.LIKE);
        dto.getConditions().add(c);
        users = userSer.findByCis(dto, true);
        for (User user : users) {
            user.setUsername("update" + new Random().nextInt(9999));
        }
        userSer.update(users);
        System.out.println(JSON.toJSONString(users));

    }

    /**
     * 批量删除数据
     */
    @Test
    public void removeAll() throws SerException {
        List<User> users = userSer.findAll();
        userSer.remove(users);
        System.out.println(JSON.toJSONString(userSer.findAll()));

    }

    /**
     * 数据回滚
     *
     * @throws SerException
     */
    @Transactional
    @Test
    public void rollBack() throws SerException {
        User user = userSer.findByUsername("liguiqin");
        user.setAge(555);
        userSer.update(user);
        int i = 9 / 0; // fail
    }


    /**
     * 查询缓存(区别与实体缓存，该缓存可支持查询语句)
     * 配置：com.bjike.ndshop.user.dao/userRep
     *
     * @QueryHints(value={@QueryHint(name="org.hibernate.cacheable",value="true")}) findByNickname()
     * 集合缓存
     * 配置：test_java_service/com.bjike.ndshop.user.entity/User
     * @Cache(usage = CacheConcurrencyStrategy.READ_WRITE,region ="entityCache" )
     * private Set<UserInterest> interests;
     */
    @Test
    public void cache_findByNickname() throws SerException {
        String name = "xiaoming";
        userSer.findByNickname(name);
        userSer.findByNickname(name);
        userSer.findByNickname(name);
        userSer.findByNickname(name);
        userSer.findByNickname(name);
        userSer.findByNickname(name);
    }


    /**
     * com.bjike.ndshop.user.service/com.bjike.ndshop.user.dao 缓存
     * com.bjike.ndshop.user.service （缓存生效查看service调用次数）
     * com.bjike.ndshop.user.dao(缓存生效查看sql查询次数)
     * <p>
     * com.bjike.ndshop.user.service 配置： @Cacheable("serviceCache")
     * User findByUsername(String username) throws SerException;
     * <p>
     * com.bjike.ndshop.user.dao 配置： @Cacheable("daoCache")
     * User findByUsername(String username);
     *
     * @throws Throwable
     */
    @Test
    public void cache_findByUsername() throws SerException {
        String name = "liguiqin";
        userSer.findByUsername(name);
        userSer.findByUsername(name);
        userSer.findByUsername(name);
        userSer.findByUsername(name);
        userSer.findByUsername(name);
        userSer.findByUsername(name);
    }


}
