package cn.javabb.sys.service.impl;

import cn.javabb.common.web.PageParam;
import cn.javabb.sys.entity.OperRecordDO;
import cn.javabb.sys.mapper.OperRecordMapper;
import cn.javabb.sys.service.OperRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 操作日志服务实现类
 */
@Service
public class OperRecordServiceImpl extends ServiceImpl<OperRecordMapper, OperRecordDO> implements OperRecordService {

    @Override
    public List<OperRecordDO> listPage(PageParam<OperRecordDO> page) {
        return baseMapper.listPage(page);
    }

    @Override
    public List<OperRecordDO> listAll(Map<String, Object> page) {
        return baseMapper.listAll(page);
    }

    @Async
    @Override
    public void saveAsync(OperRecordDO operRecord) {
        baseMapper.insert(operRecord);
    }

}
