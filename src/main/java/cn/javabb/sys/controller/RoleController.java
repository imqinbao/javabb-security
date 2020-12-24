package cn.javabb.sys.controller;

import cn.javabb.common.annotation.ApiPageParam;
import cn.javabb.common.annotation.OperLog;
import cn.javabb.common.utils.CoreUtil;
import cn.javabb.common.web.BaseController;
import cn.javabb.common.web.JsonResult;
import cn.javabb.common.web.PageParam;
import cn.javabb.common.web.PageResult;
import cn.javabb.sys.entity.RoleDO;
import cn.javabb.sys.service.RoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 */
@Api(tags = "角色管理")
@RestController
@RequestMapping("/api/sys/role")
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;

    @PreAuthorize("hasAuthority('sys:role:list')")
    @OperLog(value = "角色管理", desc = "分页查询")
    @ApiOperation("分页查询角色")
    @ApiPageParam
    @GetMapping("/page")
    public PageResult<RoleDO> page(HttpServletRequest request) {
        PageParam<RoleDO> pageParam = new PageParam<>(request);
        return new PageResult<>(roleService.page(pageParam, pageParam.getWrapper()).getRecords(), pageParam.getTotal());
    }

    @PreAuthorize("hasAuthority('sys:role:list')")
    @OperLog(value = "角色管理", desc = "查询全部")
    @ApiOperation("查询全部角色")
    @GetMapping()
    public JsonResult list(HttpServletRequest request) {
        PageParam<RoleDO> pageParam = new PageParam<>(request);
        return JsonResult.ok().setData(roleService.list(pageParam.getOrderWrapper()));
    }

    @PreAuthorize("hasAuthority('sys:role:list')")
    @OperLog(value = "角色管理", desc = "根据id查询")
    @ApiOperation("根据id查询角色")
    @GetMapping("/{id}")
    public JsonResult get(@PathVariable("id") Integer id) {
        return JsonResult.ok().setData(roleService.getById(id));
    }

    @PreAuthorize("hasAuthority('sys:role:save')")
    @OperLog(value = "角色管理", desc = "添加", param = false, result = true)
    @ApiOperation("添加角色")
    @PostMapping()
    public JsonResult save(@RequestBody RoleDO role) {
        if (roleService.count(new QueryWrapper<RoleDO>().eq("role_code", role.getRoleCode())) > 0) {
            return JsonResult.error("角色标识已存在");
        }
        if (roleService.count(new QueryWrapper<RoleDO>().eq("role_name", role.getRoleName())) > 0) {
            return JsonResult.error("角色名称已存在");
        }
        if (roleService.save(role)) {
            return JsonResult.ok("添加成功");
        }
        return JsonResult.error("添加失败");
    }

    @PreAuthorize("hasAuthority('sys:role:update')")
    @OperLog(value = "角色管理", desc = "修改", param = false, result = true)
    @ApiOperation("修改角色")
    @PutMapping()
    public JsonResult update(@RequestBody RoleDO role) {
        if (role.getRoleCode() != null && roleService.count(new QueryWrapper<RoleDO>().eq("role_code", role.getRoleCode())
                .ne("role_id", role.getRoleId())) > 0) {
            return JsonResult.error("角色标识已存在");
        }
        if (role.getRoleName() != null && roleService.count(new QueryWrapper<RoleDO>().eq("role_name", role.getRoleName())
                .ne("role_id", role.getRoleId())) > 0) {
            return JsonResult.error("角色名称已存在");
        }
        if (roleService.updateById(role)) {
            return JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }

    @PreAuthorize("hasAuthority('sys:role:remove')")
    @OperLog(value = "角色管理", desc = "删除", result = true)
    @ApiOperation("删除角色")
    @DeleteMapping("/{id}")
    public JsonResult remove(@PathVariable("id") Integer id) {
        if (roleService.removeById(id)) {
            return JsonResult.ok("删除成功");
        }
        return JsonResult.error("删除失败");
    }

    @PreAuthorize("hasAuthority('sys:role:save')")
    @OperLog(value = "角色管理", desc = "批量添加", param = false, result = true)
    @ApiOperation("批量添加角色")
    @PostMapping("/batch")
    public JsonResult saveBatch(@RequestBody List<RoleDO> list) {
        // 对集合本身进行非空和重复校验
        StringBuilder sb = new StringBuilder();
        sb.append(CoreUtil.listCheckBlank(list, "roleCode", "角色标识"));
        sb.append(CoreUtil.listCheckBlank(list, "roleName", "角色名称"));
        sb.append(CoreUtil.listCheckRepeat(list, "roleCode", "角色标识"));
        sb.append(CoreUtil.listCheckRepeat(list, "roleName", "角色名称"));
        if (sb.length() != 0) return JsonResult.error(sb.toString());
        // 数据库层面校验
        if (roleService.count(new QueryWrapper<RoleDO>().in("role_code",
                list.stream().map(RoleDO::getRoleCode).collect(Collectors.toList()))) > 0) {
            return JsonResult.error("角色标识已存在");
        }
        if (roleService.saveBatch(list)) {
            return JsonResult.ok("添加成功");
        }
        return JsonResult.error("添加失败");
    }

    @PreAuthorize("hasAuthority('sys:role:remove')")
    @OperLog(value = "角色管理", desc = "批量删除", result = true)
    @ApiOperation("批量删除角色")
    @DeleteMapping("/batch")
    public JsonResult removeBatch(@RequestBody List<Integer> ids) {
        if (roleService.removeByIds(ids)) {
            return JsonResult.ok("删除成功");
        }
        return JsonResult.error("删除失败");
    }

}
