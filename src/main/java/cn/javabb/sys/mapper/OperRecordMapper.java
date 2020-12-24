package cn.javabb.sys.mapper;

import cn.javabb.common.web.PageParam;
import cn.javabb.sys.entity.OperRecordDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 操作日志Mapper接口
 */
public interface OperRecordMapper extends BaseMapper<OperRecordDO> {

    /**
     * 分页查询
     */
    List<OperRecordDO> listPage(@Param("page") PageParam<OperRecordDO> page);

    /**
     * 查询全部
     */
    List<OperRecordDO> listAll(@Param("page") Map<String, Object> page);

}
