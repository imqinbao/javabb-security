package cn.javabb.sys.mapper;

import cn.javabb.sys.entity.RoleDO;
import cn.javabb.sys.entity.UserRoleDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户角色Mapper接口
 */
public interface UserRoleMapper extends BaseMapper<UserRoleDO> {

    /**
     * 批量添加用户角色
     */
    int insertBatch(@Param("userId") Integer userId, @Param("roleIds") List<Integer> roleIds);

    /**
     * 批量查询用户角色
     */
    List<RoleDO> listByUserIds(@Param("userIds") List<Integer> userIds);

    /**
     * 查询某个用户的角色
     */
    List<RoleDO> listByUserId(@Param("userId") Integer userId);

}
