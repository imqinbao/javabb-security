package cn.javabb.sys.controller;

import cn.javabb.common.annotation.ApiPageParam;
import cn.javabb.common.annotation.OperLog;
import cn.javabb.common.web.*;
import cn.javabb.sys.entity.OrganizationDO;
import cn.javabb.sys.service.OrganizationService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 */
@Api(tags = "组织机构管理")
@RestController
@RequestMapping("/api/sys/organization")
public class OrganizationController extends BaseController {
    @Autowired
    private OrganizationService organizationService;

    @PreAuthorize("hasAuthority('sys:org:list')")
    @OperLog(value = "机构管理", desc = "分页查询")
    @ApiOperation("分页查询组织机构")
    @ApiPageParam
    @GetMapping("/page")
    public PageResult<OrganizationDO> page(HttpServletRequest request) {
        PageParam<OrganizationDO> pageParam = new PageParam<>(request);
        return new PageResult<>(organizationService.listPage(pageParam), pageParam.getTotal());
    }

    @PreAuthorize("hasAuthority('sys:org:list')")
    @OperLog(value = "机构管理", desc = "查询全部")
    @ApiOperation("查询全部组织机构")
    @GetMapping()
    public JsonResult list(HttpServletRequest request) {
        PageParam<OrganizationDO> pageParam = new PageParam<>(request);
        List<OrganizationDO> records = organizationService.listAll(pageParam.getNoPageParam());
        return JsonResult.ok().setData(pageParam.sortRecords(records));
    }

    @PreAuthorize("hasAuthority('sys:org:list')")
    @OperLog(value = "机构管理", desc = "根据id查询")
    @ApiOperation("根据id查询组织机构")
    @GetMapping("/{id}")
    public JsonResult get(@PathVariable("id") Integer id) {
        PageParam<OrganizationDO> pageParam = new PageParam<>();
        pageParam.put("organizationId", id);
        List<OrganizationDO> records = organizationService.listAll(pageParam.getNoPageParam());
        return JsonResult.ok().setData(pageParam.getOne(records));
    }

    @PreAuthorize("hasAuthority('sys:org:save')")
    @OperLog(value = "机构管理", desc = "添加", param = false, result = true)
    @ApiOperation("添加组织机构")
    @PostMapping()
    public JsonResult add(@RequestBody OrganizationDO organization) {
        if (organization.getParentId() == null) organization.setParentId(0);
        if (organizationService.count(new QueryWrapper<OrganizationDO>()
                .eq("organization_name", organization.getOrganizationName())
                .eq("parent_id", organization.getParentId())) > 0) {
            return JsonResult.error("机构名称已存在");
        }
        if (organizationService.save(organization)) {
            return JsonResult.ok("添加成功");
        }
        return JsonResult.error("添加失败");
    }

    @PreAuthorize("hasAuthority('sys:org:update')")
    @OperLog(value = "机构管理", desc = "修改", param = false, result = true)
    @ApiOperation("修改组织机构")
    @PutMapping()
    public JsonResult update(@RequestBody OrganizationDO organization) {
        if (organization.getOrganizationName() != null) {
            if (organization.getParentId() == null) organization.setParentId(0);
            if (organizationService.count(new QueryWrapper<OrganizationDO>()
                    .eq("organization_name", organization.getOrganizationName())
                    .eq("parent_id", organization.getParentId())
                    .ne("organization_id", organization.getOrganizationId())) > 0) {
                return JsonResult.error("机构名称已存在");
            }
        }
        if (organizationService.updateById(organization)) {
            return JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }

    @PreAuthorize("hasAuthority('sys:org:remove')")
    @OperLog(value = "机构管理", desc = "删除", result = true)
    @ApiOperation("删除组织机构")
    @DeleteMapping("/{id}")
    public JsonResult remove(@PathVariable("id") Integer id) {
        if (organizationService.removeById(id)) {
            return JsonResult.ok("删除成功");
        }
        return JsonResult.error("删除失败");
    }

    @PreAuthorize("hasAuthority('sys:org:save')")
    @OperLog(value = "机构管理", desc = "批量添加", param = false, result = true)
    @ApiOperation("批量添加组织机构")
    @PostMapping("/batch")
    public JsonResult saveBatch(@RequestBody List<OrganizationDO> organizationList) {
        if (organizationService.saveBatch(organizationList)) {
            return JsonResult.ok("添加成功");
        }
        return JsonResult.error("添加失败");
    }

    @PreAuthorize("hasAuthority('sys:org:update')")
    @OperLog(value = "机构管理", desc = "批量修改", result = true)
    @ApiOperation("批量修改组织机构")
    @PutMapping("/batch")
    public JsonResult updateBatch(@RequestBody BatchParam<OrganizationDO> batchParam) {
        if (batchParam.update(organizationService, "organization_id")) {
            return JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }

    @PreAuthorize("hasAuthority('sys:org:remove')")
    @OperLog(value = "机构管理", desc = "批量删除", result = true)
    @ApiOperation("批量删除组织机构")
    @DeleteMapping("/batch")
    public JsonResult removeBatch(@RequestBody List<Integer> ids) {
        if (organizationService.removeByIds(ids)) {
            return JsonResult.ok("删除成功");
        }
        return JsonResult.error("删除失败");
    }

}
