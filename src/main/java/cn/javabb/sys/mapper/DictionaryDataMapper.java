package cn.javabb.sys.mapper;

import cn.javabb.common.web.PageParam;
import cn.javabb.sys.entity.DictionaryDataDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 字典项Mapper接口
 */
public interface DictionaryDataMapper extends BaseMapper<DictionaryDataDO> {

    /**
     * 分页查询
     */
    List<DictionaryDataDO> listPage(@Param("page") PageParam<DictionaryDataDO> page);

    /**
     * 查询全部
     */
    List<DictionaryDataDO> listAll(@Param("page") Map<String, Object> page);

}
