package cn.javabb.sys.service;

import cn.javabb.common.web.PageParam;
import cn.javabb.sys.entity.OrganizationDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 组织机构服务类
 */
public interface OrganizationService extends IService<OrganizationDO> {

    /**
     * 关联分页查询
     */
    List<OrganizationDO> listPage(PageParam<OrganizationDO> page);

    /**
     * 关联查询所有
     */
    List<OrganizationDO> listAll(Map<String, Object> page);

}
