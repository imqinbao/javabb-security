package cn.javabb.sys.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.javabb.common.exception.BusinessException;
import cn.javabb.common.web.PageParam;
import cn.javabb.sys.entity.MenuDO;
import cn.javabb.sys.entity.RoleDO;
import cn.javabb.sys.entity.UserDO;
import cn.javabb.sys.entity.UserRoleDO;
import cn.javabb.sys.mapper.MenuMapper;
import cn.javabb.sys.mapper.UserMapper;
import cn.javabb.sys.mapper.UserRoleMapper;
import cn.javabb.sys.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户服务实现类
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public UserDO getByUsername(String username) {
        return baseMapper.selectOne(new QueryWrapper<UserDO>().eq("username", username));
    }

    @Override
    public UserDO getFullById(Integer userId) {
        List<UserDO> userList = baseMapper.listAll(new PageParam<UserDO>().put("userId", userId).getNoPageParam());
        if (userList == null || userList.size() == 0) return null;
        return selectRoleAndAuth(userList.get(0));
    }

    @Override
    public UserDO selectRoleAndAuth(UserDO user) {
        if (user == null) return user;
        user.setRoles(userRoleMapper.listByUserId(user.getUserId()));
        return selectUserAuth(user);
    }

    @Override
    public UserDO selectUserAuth(UserDO user) {
        if (user == null) return user;
        List<MenuDO> menus = menuMapper.listByUserId(user.getUserId(), null);
        List<MenuDO> authList = new ArrayList<>();
        for (MenuDO menu : menus) {
            if (StrUtil.isNotBlank(menu.getAuthority())) {
                authList.add(menu);
            }
        }
        user.setAuthorities(authList);
        return user;
    }

    @Override
    public List<UserDO> listPage(PageParam<UserDO> page) {
        List<UserDO> users = baseMapper.listPage(page);
        // 查询用户的角色
        selectUserRoles(users);
        return users;
    }

    @Override
    public List<UserDO> listAll(Map<String, Object> page) {
        List<UserDO> users = baseMapper.listAll(page);
        // 查询用户的角色
        selectUserRoles(users);
        return users;
    }

    @Transactional
    @Override
    public boolean saveUser(UserDO user) {
        if (user.getUsername() != null && baseMapper.selectCount(new QueryWrapper<UserDO>()
                .eq("username", user.getUsername())) > 0) {
            throw new BusinessException("账号已存在");
        }
        if (user.getPhone() != null && baseMapper.selectCount(new QueryWrapper<UserDO>()
                .eq("phone", user.getPhone())) > 0) {
            throw new BusinessException("手机号已存在");
        }
        if (user.getEmail() != null && baseMapper.selectCount(new QueryWrapper<UserDO>()
                .eq("email", user.getEmail())) > 0) {
            throw new BusinessException("邮箱已存在");
        }
        boolean result = baseMapper.insert(user) > 0;
        if (result) {
            addUserRoles(user.getUserId(), user.getRoleIds(), false);
        }
        return result;
    }

    @Transactional
    @Override
    public boolean updateUser(UserDO user) {
        if (user.getUsername() != null && baseMapper.selectCount(new QueryWrapper<UserDO>()
                .eq("username", user.getUsername()).ne("user_id", user.getUserId())) > 0) {
            throw new BusinessException("账号已存在");
        }
        if (user.getPhone() != null && baseMapper.selectCount(new QueryWrapper<UserDO>()
                .eq("phone", user.getPhone()).ne("user_id", user.getUserId())) > 0) {
            throw new BusinessException("手机号已存在");
        }
        if (user.getEmail() != null && baseMapper.selectCount(new QueryWrapper<UserDO>()
                .eq("email", user.getEmail()).ne("user_id", user.getUserId())) > 0) {
            throw new BusinessException("邮箱已存在");
        }
        boolean result = baseMapper.updateById(user) > 0;
        if (result) {
            addUserRoles(user.getUserId(), user.getRoleIds(), true);
        }
        return result;
    }

    @Override
    public boolean comparePsw(String dbPsw, String inputPsw) {
        return dbPsw != null && new BCryptPasswordEncoder().matches(inputPsw, dbPsw);
    }

    @Override
    public String encodePsw(String psw) {
        if (psw == null) return null;
        return new BCryptPasswordEncoder().encode(psw);
    }

    /**
     * 批量查询用户的角色
     */
    private void selectUserRoles(List<UserDO> users) {
        if (users != null && users.size() > 0) {
            List<Integer> userIds = users.stream().map(UserDO::getUserId).collect(Collectors.toList());
            List<RoleDO> userRoles = userRoleMapper.listByUserIds(userIds);
            for (UserDO user : users) {
                List<RoleDO> roles = userRoles.stream().filter(d -> user.getUserId().equals(d.getUserId())).collect(Collectors.toList());
                user.setRoles(roles);
            }
        }
    }

    /**
     * 添加用户角色
     */
    private void addUserRoles(Integer userId, List<Integer> roleIds, boolean deleteOld) {
        if (deleteOld) {
            userRoleMapper.delete(new UpdateWrapper<UserRoleDO>().eq("user_id", userId));
        }
        if (userRoleMapper.insertBatch(userId, roleIds) < roleIds.size()) {
            throw new BusinessException("操作失败");
        }
    }

}
