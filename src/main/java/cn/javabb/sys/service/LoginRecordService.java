package cn.javabb.sys.service;

import cn.javabb.common.web.PageParam;
import cn.javabb.sys.entity.LoginRecordDO;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 登录日志服务类
 */
public interface LoginRecordService extends IService<LoginRecordDO> {

    /**
     * 关联分页查询
     */
    List<LoginRecordDO> listPage(PageParam<LoginRecordDO> page);

    /**
     * 关联查询所有
     */
    List<LoginRecordDO> listAll(Map<String, Object> page);

    /**
     * 添加登录日志
     *
     * @param username 用户账号
     * @param type     操作类型
     * @param comments 备注
     * @param request  HttpServletRequest
     */
    void saveAsync(String username, Integer type, String comments, HttpServletRequest request);

}
