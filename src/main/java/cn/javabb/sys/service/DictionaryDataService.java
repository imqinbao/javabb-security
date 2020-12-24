package cn.javabb.sys.service;

import cn.javabb.common.web.PageParam;
import cn.javabb.sys.entity.DictionaryDataDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 字典项服务类
 */
public interface DictionaryDataService extends IService<DictionaryDataDO> {

    /**
     * 关联分页查询
     */
    List<DictionaryDataDO> listPage(PageParam<DictionaryDataDO> page);

    /**
     * 关联查询所有
     */
    List<DictionaryDataDO> listAll(Map<String, Object> page);

    /**
     * 根据字典代码查询字典项
     */
    List<DictionaryDataDO> listByDictCode(String dictCode);

    /**
     * 根据字典代码和字典项名称查询字典项
     */
    DictionaryDataDO listByDictCodeAndName(String dictCode, String name);

}
