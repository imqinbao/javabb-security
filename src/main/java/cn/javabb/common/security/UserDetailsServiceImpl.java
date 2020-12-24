package cn.javabb.common.security;

import cn.javabb.sys.entity.UserDO;
import cn.javabb.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 用户登录验证Service
 */
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDO user = userService.getByUsername(username);
        if (user == null) throw new UsernameNotFoundException("Username Not Found");
        return userService.selectUserAuth(user);
    }

}