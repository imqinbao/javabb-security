package cn.javabb.common.web;

import cn.javabb.sys.entity.UserDO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Controller基类
 */
public class BaseController {

    /**
     * 获取当前登录的user
     */
    public UserDO getLoginUser() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null) return null;
            Object object = authentication.getPrincipal();
            if (object != null) return (UserDO) object;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * 获取当前登录的userId
     */
    public Integer getLoginUserId() {
        UserDO loginUser = getLoginUser();
        return loginUser == null ? null : loginUser.getUserId();
    }

}
