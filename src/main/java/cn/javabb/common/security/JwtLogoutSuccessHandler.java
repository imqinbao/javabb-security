package cn.javabb.common.security;

import cn.javabb.sys.entity.LoginRecordDO;
import cn.javabb.sys.service.LoginRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 退出登录处理
 */
public class JwtLogoutSuccessHandler implements LogoutSuccessHandler {
    @Autowired
    private LoginRecordService loginRecordService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        // 记录退出登录日志
        loginRecordService.saveAsync(userDetails.getUsername(), LoginRecordDO.TYPE_LOGOUT, null, request);
    }

}