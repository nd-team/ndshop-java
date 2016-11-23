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
import java.util.Set;


/**
 * Created by lgq on 16-11-22.
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
    public void addRole()throws SerException {
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
    public void addUserRole()throws SerException {
        Role role = roleSer.findOne(new RoleDto());
      //  User u =  userSer.findByPhone("13257910244");
        UserRole userRole = new UserRole();
        userRole.setRole(role);
     //   userRole.setUser(u);
        userRoleSer.save(userRole);
    }

    @Test
    public void addPermission()throws SerException {
        permissionSer.addPermission();
    }

    @Transactional
    @Test
    public void findUserRole()throws SerException {
        List<UserRole> users = userRoleSer.findAll();
        for(UserRole userRole : users){
            userRole.getRole().getPermissions().size();
            System.out.println(userRole);
        }
    }

    @Test
    public void findAllByUserId()throws SerException {
//       User user = userSer.findByPhone("13257910244");
//       Set<Permission> permissionSet =  permissionSer.findAllByUserId(user.getId());
//        System.out.println(permissionSet);
    }



}
