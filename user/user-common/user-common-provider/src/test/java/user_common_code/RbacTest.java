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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;


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
        Optional<Role> roleOptional = roleSer.findOne(new RoleDto());
        Optional<User> userOptional = userSer.findByPhone("13257910244");
        UserRole userRole = new UserRole();
        userRole.setRole(roleOptional.get());
        userRole.setUser(userOptional.get());
        userRoleSer.save(userRole);
    }

    @Test
    public void addPermission() throws SerException {
        permissionSer.addPermission();
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
    public void findAllByUserId() throws SerException {
        Optional<User> op_user = userSer.findByPhone("13257910244");
        Optional<Set<Permission>> op_permissions = permissionSer.findAllByUserId(op_user.get().getId());
        System.out.println(op_permissions.get());
    }


}
