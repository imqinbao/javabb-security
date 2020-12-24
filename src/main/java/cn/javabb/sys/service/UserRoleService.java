package cn.javabb.sys.service;

import cn.javabb.sys.entity.UserRoleDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 用户角色服务类
 */
public interface UserRoleService extends IService<UserRoleDO> {

    /**
     * 查询用户的角色id
     */
    Integer[] getRoleIds(String userId);

}
