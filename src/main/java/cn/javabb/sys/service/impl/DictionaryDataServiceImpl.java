package cn.javabb.sys.service.impl;

import cn.javabb.common.web.PageParam;
import cn.javabb.sys.entity.DictionaryDataDO;
import cn.javabb.sys.mapper.DictionaryDataMapper;
import cn.javabb.sys.service.DictionaryDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 字典项服务实现类
 */
@Service
public class DictionaryDataServiceImpl extends ServiceImpl<DictionaryDataMapper, DictionaryDataDO> implements DictionaryDataService {

    @Override
    public List<DictionaryDataDO> listPage(PageParam<DictionaryDataDO> page) {
        return baseMapper.listPage(page);
    }

    @Override
    public List<DictionaryDataDO> listAll(Map<String, Object> page) {
        return baseMapper.listAll(page);
    }

    @Override
    public List<DictionaryDataDO> listByDictCode(String dictCode) {
        PageParam<DictionaryDataDO> pageParam = new PageParam<>();
        pageParam.put("dictCode", dictCode).setDefaultOrder(new String[]{"sort_number"}, null);
        List<DictionaryDataDO> records = baseMapper.listAll(pageParam.getNoPageParam());
        return pageParam.sortRecords(records);
    }

    @Override
    public DictionaryDataDO listByDictCodeAndName(String dictCode, String dictDataName) {
        PageParam<DictionaryDataDO> pageParam = new PageParam<>();
        pageParam.put("dictCode", dictCode).put("dictDataName", dictDataName);
        List<DictionaryDataDO> records = baseMapper.listAll(pageParam.getNoPageParam());
        return pageParam.getOne(records);
    }

}
