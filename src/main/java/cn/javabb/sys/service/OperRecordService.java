package cn.javabb.sys.service;

import cn.javabb.common.web.PageParam;
import cn.javabb.sys.entity.OperRecordDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 操作日志服务类
 */
public interface OperRecordService extends IService<OperRecordDO> {

    /**
     * 关联分页查询
     */
    List<OperRecordDO> listPage(PageParam<OperRecordDO> page);

    /**
     * 关联查询所有
     */
    List<OperRecordDO> listAll(Map<String, Object> page);

    /**
     * 异步添加
     */
    void saveAsync(OperRecordDO operRecord);

}
