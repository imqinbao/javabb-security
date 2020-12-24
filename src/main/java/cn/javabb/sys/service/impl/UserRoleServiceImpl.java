package cn.javabb.sys.service.impl;

import cn.javabb.sys.entity.UserRoleDO;
import cn.javabb.sys.mapper.UserRoleMapper;
import cn.javabb.sys.service.UserRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户角色服务实现类
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRoleDO> implements UserRoleService {

    @Override
    public Integer[] getRoleIds(String userId) {
        List<UserRoleDO> userRoles = baseMapper.selectList(new QueryWrapper<UserRoleDO>().eq("user_id", userId));
        Integer[] roleIds = new Integer[userRoles.size()];
        for (int i = 0; i < userRoles.size(); i++) {
            roleIds[i] = userRoles.get(i).getRoleId();
        }
        return roleIds;
    }
    
}
