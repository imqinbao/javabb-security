package cn.javabb.sys.service.impl;

import cn.javabb.common.utils.UserAgentGetter;
import cn.javabb.common.web.PageParam;
import cn.javabb.sys.entity.LoginRecordDO;
import cn.javabb.sys.mapper.LoginRecordMapper;
import cn.javabb.sys.service.LoginRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 登录日志服务实现类
 */
@Service
public class LoginRecordServiceImpl extends ServiceImpl<LoginRecordMapper, LoginRecordDO> implements LoginRecordService {

    @Override
    public List<LoginRecordDO> listPage(PageParam<LoginRecordDO> page) {
        return baseMapper.listPage(page);
    }

    @Override
    public List<LoginRecordDO> listAll(Map<String, Object> page) {
        return baseMapper.listAll(page);
    }

    @Async
    @Override
    public void saveAsync(String username, Integer type, String comments, HttpServletRequest request) {
        if (username == null) return;
        LoginRecordDO loginRecord = new LoginRecordDO();
        loginRecord.setUsername(username);
        loginRecord.setOperType(type);
        loginRecord.setComments(comments);
        UserAgentGetter agentGetter = new UserAgentGetter(request);
        loginRecord.setOs(agentGetter.getOS());
        loginRecord.setDevice(agentGetter.getDevice());
        loginRecord.setBrowser(agentGetter.getBrowser());
        loginRecord.setIp(agentGetter.getIp());
        baseMapper.insert(loginRecord);
    }

}
