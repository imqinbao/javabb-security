package cn.javabb.sys.service;

import cn.javabb.common.web.PageParam;
import cn.javabb.sys.entity.UserDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 用户服务类
 */
public interface UserService extends IService<UserDO> {

    /**
     * 根据账号查询用户
     */
    UserDO getByUsername(String username);

    /**
     * 根据id查询用户(关联查询)
     */
    UserDO getFullById(Integer userId);

    /**
     * 查询用户角色和权限
     */
    UserDO selectRoleAndAuth(UserDO user);

    /**
     * 查询用户权限
     */
    UserDO selectUserAuth(UserDO user);

    /**
     * 关联分页查询用户
     */
    List<UserDO> listPage(PageParam<UserDO> page);

    /**
     * 关联查询全部用户
     */
    List<UserDO> listAll(Map<String, Object> page);

    /**
     * 添加用户(包含角色)
     */
    boolean saveUser(UserDO user);

    /**
     * 修改用户(包含角色)
     */
    boolean updateUser(UserDO user);

    /**
     * 比较用户密码
     *
     * @param dbPsw    数据库存储的密码
     * @param inputPsw 用户输入的密码
     * @return boolean
     */
    boolean comparePsw(String dbPsw, String inputPsw);

    /**
     * md5加密用户密码
     */
    String encodePsw(String psw);

}
