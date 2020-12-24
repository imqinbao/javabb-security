package cn.javabb.sys.mapper;

import cn.javabb.common.web.PageParam;
import cn.javabb.sys.entity.UserDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 用户Mapper接口
 */
public interface UserMapper extends BaseMapper<UserDO> {

    /**
     * 分页查询
     */
    List<UserDO> listPage(@Param("page") PageParam<UserDO> page);

    /**
     * 查询全部
     */
    List<UserDO> listAll(@Param("page") Map<String, Object> page);

}
