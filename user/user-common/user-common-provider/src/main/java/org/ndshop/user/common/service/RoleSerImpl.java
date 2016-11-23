package org.ndshop.user.common.service;


import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.ndshop.user.common.dto.RoleDto;
import org.ndshop.user.common.entity.Role;
import org.ndshop.user.common.entity.UserRole;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lgq on 16-11-22.
 */
@Service
public class RoleSerImpl extends ServiceImpl<Role, RoleDto> implements IRoleSer {


}
