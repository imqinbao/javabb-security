package cn.javabb.sys.mapper;

import cn.javabb.common.web.PageParam;
import cn.javabb.sys.entity.LoginRecordDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 登录日志Mapper接口
 */
public interface LoginRecordMapper extends BaseMapper<LoginRecordDO> {

    /**
     * 分页查询
     */
    List<LoginRecordDO> listPage(@Param("page") PageParam<LoginRecordDO> page);

    /**
     * 查询全部
     */
    List<LoginRecordDO> listAll(@Param("page") Map<String, Object> page);

}
