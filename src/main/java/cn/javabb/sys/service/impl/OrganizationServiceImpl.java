package cn.javabb.sys.service.impl;

import cn.javabb.common.web.PageParam;
import cn.javabb.sys.entity.OrganizationDO;
import cn.javabb.sys.mapper.OrganizationMapper;
import cn.javabb.sys.service.OrganizationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 组织机构服务实现类
 */
@Service
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, OrganizationDO> implements OrganizationService {

    @Override
    public List<OrganizationDO> listPage(PageParam<OrganizationDO> page) {
        return baseMapper.listPage(page);
    }

    @Override
    public List<OrganizationDO> listAll(Map<String, Object> page) {
        return baseMapper.listAll(page);
    }

}
