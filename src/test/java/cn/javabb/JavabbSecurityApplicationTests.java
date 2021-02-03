package cn.javabb;

import cn.javabb.sys.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

@SpringBootTest
class JavabbSecurityApplicationTests {

    @Autowired
    UserService userService;
    @Test
    void contextLoads() {
    }

    @Test
    void getPwd() {
        String pwd = userService.encodePsw("admin");
        System.out.println(pwd);
    }
}
