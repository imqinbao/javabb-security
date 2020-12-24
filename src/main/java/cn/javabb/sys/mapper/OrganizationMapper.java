package cn.javabb.sys.mapper;

import cn.javabb.common.web.PageParam;
import cn.javabb.sys.entity.OrganizationDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 组织机构Mapper接口
 */
public interface OrganizationMapper extends BaseMapper<OrganizationDO> {

    /**
     * 分页查询
     */
    List<OrganizationDO> listPage(@Param("page") PageParam<OrganizationDO> page);

    /**
     * 查询全部
     */
    List<OrganizationDO> listAll(@Param("page") Map<String, Object> page);

}
