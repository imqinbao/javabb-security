package cn.javabb.sys.controller;

import cn.javabb.common.annotation.OperLog;
import cn.javabb.common.exception.BusinessException;
import cn.javabb.common.web.BaseController;
import cn.javabb.common.web.JsonResult;
import cn.javabb.sys.entity.MenuDO;
import cn.javabb.sys.entity.RoleMenuDO;
import cn.javabb.sys.service.MenuService;
import cn.javabb.sys.service.RoleMenuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 */
@Api(tags = "角色菜单管理")
@RestController
@RequestMapping("/api/sys/role/menu")
public class RoleMenuController extends BaseController {
    @Autowired
    private RoleMenuService roleMenuService;
    @Autowired
    private MenuService menuService;

    /**
     * 查询角色菜单
     */
    @PreAuthorize("hasAuthority('sys:role:list')")
    @OperLog(value = "角色管理", desc = "查询角色菜单")
    @ResponseBody
    @GetMapping()
    public JsonResult list(Integer roleId) {
        List<MenuDO> menus = menuService.list(new QueryWrapper<MenuDO>().orderByAsc("sort_number"));
        List<RoleMenuDO> roleMenus = roleMenuService.list(new QueryWrapper<RoleMenuDO>().eq("role_id", roleId));
        for (MenuDO menu : menus) {
            menu.setOpen(true);
            menu.setChecked(false);
            for (RoleMenuDO roleMenu : roleMenus) {
                if (menu.getMenuId().equals(roleMenu.getMenuId())) {
                    menu.setChecked(true);
                    break;
                }
            }
        }
        return JsonResult.ok().setData(menus);
    }

    /**
     * 添加角色菜单
     */
    @PreAuthorize("hasAuthority('sys:role:update')")
    @OperLog(value = "角色管理", desc = "添加角色菜单")
    @ResponseBody
    @PostMapping()
    public JsonResult addRoleAuth(Integer roleId, Integer menuId) {
        RoleMenuDO roleMenu = new RoleMenuDO();
        roleMenu.setRoleId(roleId);
        roleMenu.setMenuId(menuId);
        if (roleMenuService.save(roleMenu)) {
            return JsonResult.ok();
        }
        return JsonResult.error();
    }

    /**
     * 移除角色菜单
     */
    @PreAuthorize("hasAuthority('sys:role:update')")
    @OperLog(value = "角色管理", desc = "移除角色菜单")
    @ResponseBody
    @DeleteMapping()
    public JsonResult removeRoleAuth(Integer roleId, Integer menuId) {
        if (roleMenuService.remove(new UpdateWrapper<RoleMenuDO>()
                .eq("role_id", roleId).eq("menuId", menuId))) {
            return JsonResult.ok();
        }
        return JsonResult.error();
    }

    /**
     * 批量修改角色菜单
     */
    @PreAuthorize("hasAuthority('sys:role:update')")
    @OperLog(value = "角色管理", desc = "修改角色菜单")
    @Transactional
    @ResponseBody
    @PutMapping("/{id}")
    public JsonResult setRoleAuth(@PathVariable("id") Integer roleId, @RequestBody List<Integer> menuIds) {
        roleMenuService.remove(new UpdateWrapper<RoleMenuDO>().eq("role_id", roleId));
        if (menuIds.size() > 0) {
            List<RoleMenuDO> roleMenuList = new ArrayList<>();
            for (Integer menuId : menuIds) {
                RoleMenuDO roleMenu = new RoleMenuDO();
                roleMenu.setRoleId(roleId);
                roleMenu.setMenuId(menuId);
                roleMenuList.add(roleMenu);
            }
            if (roleMenuService.saveBatch(roleMenuList)) {
                return JsonResult.ok("保存成功");
            } else {
                throw new BusinessException("操作失败");
            }
        }
        return JsonResult.ok("保存成功");
    }

}
