package cn.javabb.sys.controller;

import cn.javabb.common.annotation.ApiPageParam;
import cn.javabb.common.annotation.OperLog;
import cn.javabb.common.web.BaseController;
import cn.javabb.common.web.JsonResult;
import cn.javabb.common.web.PageParam;
import cn.javabb.common.web.PageResult;
import cn.javabb.sys.entity.OperRecordDO;
import cn.javabb.sys.service.OperRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 */
@Api(tags = "操作日志")
@RestController
@RequestMapping("/api/sys/operRecord")
public class OperRecordController extends BaseController {
    @Autowired
    private OperRecordService operLogService;

    /**
     * 分页查询操作日志
     */
    @PreAuthorize("hasAuthority('sys:oper_record:view')")
    @OperLog(value = "操作日志", desc = "分页查询")
    @ApiOperation("分页查询操作日志")
    @ApiPageParam
    @GetMapping("/page")
    public PageResult<OperRecordDO> page(HttpServletRequest request) {
        PageParam<OperRecordDO> pageParam = new PageParam<>(request);
        pageParam.setDefaultOrder(null, new String[]{"create_time"});
        return new PageResult<>(operLogService.listPage(pageParam), pageParam.getTotal());
    }

    /**
     * 查询全部操作日志
     */
    @PreAuthorize("hasAuthority('sys:oper_record:view')")
    @OperLog(value = "操作日志", desc = "查询全部")
    @ApiOperation("查询全部操作日志")
    @GetMapping()
    public JsonResult list(HttpServletRequest request) {
        PageParam<OperRecordDO> pageParam = new PageParam<>(request);
        List<OperRecordDO> records = operLogService.listAll(pageParam.getNoPageParam());
        return JsonResult.ok().setData(pageParam.sortRecords(records));
    }

    /**
     * 根据id查询操作日志
     */
    @PreAuthorize("hasAuthority('sys:oper_record:view')")
    @OperLog(value = "操作日志", desc = "根据id查询")
    @ApiOperation("根据id查询操作日志")
    @GetMapping("/{id}")
    public JsonResult get(@PathVariable("id") Integer id) {
        PageParam<OperRecordDO> pageParam = new PageParam<>();
        pageParam.put("id", id);
        List<OperRecordDO> records = operLogService.listAll(pageParam.getNoPageParam());
        return JsonResult.ok().setData(pageParam.getOne(records));
    }

}
