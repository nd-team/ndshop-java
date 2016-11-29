##命名规范
---
 ##包名规则：
 PackName(包名) : 包名全部小写，连续的单词只是简单地连接起来，
不使用下划线顶级域名.模块名.小模块名.各组件的目录名 `org.ndshop.user.common.service`

1. - action(控制器) 以 **ctrl** 结束 org.ndshop.user.ctrl
2. - service(业务逻辑) 以 **service** 结束 org.ndshop.user.service
3. - dao(数据层)以 **dao** 结束 org.ndshop.user.dao
4. - entity(实体)以 **entity** 结束 org.ndshop.user.entity
5. - dto(数据传输)以 **dto** 结束 org.ndshop.user.dto

## 类命名规则:
  ClassName(类名) : 大写开头驼峰式，命名时应该使其简洁而又具有描述性 `UserDetail`
  

1. Service(业务逻辑层)
    - 异常类以 **SerException** 结束 `CustomSerException`
    - 接口类以大写 **I** 开头 以**Ser**结束 `IUserDetailSer`
    - 接口实现类以 **SerImpl** 结束 `UserDetailSerImpl`

2. Dao（数据控制层）
    - 异常类以 **SerException** 结束 `CustomDaoException`
    - 接口类以大写**I** 开头 以**Dao**结束  `IUserDetailDao`
    - 接口实现类以 **DaoImpl** 结束 `UserDetailDaoImpl`

3. Test（测试用例）
    - 测试类以 **Test** 结束 `UserDetailTest`
4. Ctrl（控制器）
    - 控制器类以 **Ctrl** 结束 `UserCtrl`
5. Dto（数据传输）
    - 控制器类以 **Dto** 结束 `UserDto`
    
## 方法命名规则:
 MethodName(方法名)：第一个单词应是动词，并且首字母小写，其它每个单词首字母大写 ,
    小写开头驼峰式`findByName()`， `openFile()`, `addAccount()`... 

1.   - 类的布尔型的判断方法一般要求方法名使用单词 **is** 做前缀 isPersistent() ）

2.   - Dao 层方法：只能以**save**（插入）,**remove**（删除）,**update**（更新）,**find**（查找）,**count**（统计）开头。
3.   - 其他层方法避免以这个5个单词开头，以免造成误解。
4.   - Service方法，根据方法的行为命名，只描述方法的意义，见名知意，避免命名上的冲突即可。
5.   - Web层方法最好是贴近web的语言，如**register**，**login**，**logout**等方法。

## 变量，常量命名规则:
 除了能见名知意，命名简写尽量少用,且不允许出现中文及拼音以及JAVA中的关键字命名。

1. - constantName(常量名) ：全部大写，多个单词以下划线分开`CONSTANT_CASE`

2. - 非常量字段：类属性名，参数名，局部变量名  首字母小写，
     其它每个单词的首字母大写小写开头驼峰式`userDetail`

3. - 约定变量:　　所谓约定变量，是指那些使用后即可抛弃（throwaway）的临时变量
     - 通常i、j、k、m和n代表整型变量；c、d和e代表字符型变量
     - 循环计数变量通常采用字母 i，j，k 或者 count。
     - 而数组应该总是用下面的方式来命名：**String[] names** 或 **Byte[] bytes**

4. 类属性及形参不允许或者尽量避免使用使用基础属性类型，应用包装类型（`Integer`，`Boolean`，`String`，`Long`,`Double`,`Float`,`Short`,`Character`）


## 注释规则:
 准确的描述类，方法，类属性，局部变量，常量的作用，简单明了，避免冗长却解释不清

    
    1：类注释
    /**
    * @Author: [liguiqin]
    * @Date: [2016-11-23 15:47]
    * @Description: [用户实体]
    * @Version: [1.0.0]
    * @Copy: [org.ndshop]
    */
    public class User(){
    
        2：类属性注释：
        private String username; //登录用户名
    
    
        private String phone;//登录手机(注册验证手机)
    
    
        private String email;//登录邮箱
    
        3：类方法注释：
        /**
         * 通过用户名查询用户
         * @param username 用户名
         * @return 用户
         * @throws SerException
         */
         User findByUsername(String username) throws SerException {
            return new User();
        }
    
    
    }
    

 
