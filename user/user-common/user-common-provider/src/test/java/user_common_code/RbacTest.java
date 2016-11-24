package user_common_code;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.user.common.dto.RoleDto;
import org.ndshop.user.common.entity.Permission;
import org.ndshop.user.common.entity.Role;
import org.ndshop.user.common.entity.User;
import org.ndshop.user.common.entity.UserRole;
import org.ndshop.user.common.service.IPermissionSer;
import org.ndshop.user.common.service.IRoleSer;
import org.ndshop.user.common.service.IUserRoleSer;
import org.ndshop.user.common.service.IUserSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Stream;


/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [权限认证业务测试]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class RbacTest {
    @Autowired
    private IUserSer userSer;
    @Autowired
    private IRoleSer roleSer;
    @Autowired
    private IUserRoleSer userRoleSer;
    @Autowired
    private IPermissionSer permissionSer;

    @Test
    public void addRole() throws SerException {
        Role root = new Role();
        root.setName("根角色１");
        root.setDescription("无描述");
        Role child = new Role();
        child.setName("子角色１");
        child.setDescription("无描述");
        child.setParent(root);
        roleSer.save(child);
    }

    @Test
    public void addUserRole() throws SerException {
        Optional<Role> roleOptional = roleSer.findById("939aa9a9-4b5e-4542-80ea-de7374a25b5c");
        Optional<User> userOptional = userSer.findByPhone("13257910244");
        UserRole userRole = new UserRole();
        userRole.setRole(roleOptional.get());
        userRole.setUser(userOptional.get());
        userRoleSer.save(userRole);
    }

    @Test
    public void addPermission() throws SerException {
        Permission root = new Permission();
        root.setDescription("无描述");
        root.setResource("/");
        root.setName("根资源");
        Permission child = new Permission();
        child.setDescription("无描述");
        child.setResource("/user");
        child.setName("用户资源");
        child.setParent(root);
        permissionSer.save(child);
    }

    @Transactional
    @Test
    public void findUserRole() throws SerException {
        Optional<List<UserRole>> op_userRole = userRoleSer.findAll();
        if (op_userRole.isPresent()) {
            for (UserRole userRole : op_userRole.get()) {
                userRole.getRole().getPermissions().size();
                System.out.println(userRole);
            }
        }
    }


    @Test
    public void addRolePermission() throws SerException {

        Optional<Role> op_role = roleSer.findById("939aa9a9-4b5e-4542-80ea-de7374a25b5c");
        if (op_role.isPresent()) {
            Optional<List<Permission>> op_permission = permissionSer.findAll();
            Set<Permission> permissions = new HashSet<>(op_permission.get().size());
            permissions.addAll(op_permission.get());
            op_role.get().setPermissions(permissions);
            roleSer.update(op_role.get());
        }
    }


    @Test
    public void findAllByUserId() throws SerException {

        Optional<Set<Permission>> op_permissions = permissionSer.findAllByUserId("9d7f591b-6388-4346-aaee-0304481d82ca");
        System.out.println(op_permissions.get());
    }

    @Test
    public void updatePermissions() throws SerException {

        Optional<Permission> op_permissions = permissionSer.findById("72ae9d8f-9a25-45c1-b068-4387b2667b31"); //儿子
        Permission permission = op_permissions.get();
        Permission parent = new Permission();
        parent.setId("99ae9d8f-9a25-45c1-b068-4387b2667b33");//更改父节点为孙节点测试
        permission.setParent(parent);
        permissionSer.update(permission);
        System.out.println(permission);
    }


}
