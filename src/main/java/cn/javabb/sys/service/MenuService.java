package cn.javabb.sys.service;

import cn.javabb.common.web.PageParam;
import cn.javabb.sys.entity.MenuDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 菜单服务类
 */
public interface MenuService extends IService<MenuDO> {

    /**
     * 关联分页查询菜单
     */
    List<MenuDO> listPage(PageParam<MenuDO> pageParam);

    /**
     * 根据用户id查询菜单列表
     *
     * @param userId   用户id
     * @param menuType 菜单类型，为null不筛选
     * @return List<Menu>
     */
    List<MenuDO> getUserMenu(Integer userId, Integer menuType);

    /**
     * 转化为树形结构
     *
     * @param menus    菜单list
     * @param parentId 最顶级id
     * @return List<Menu>
     */
    List<MenuDO> toMenuTree(List<MenuDO> menus, Integer parentId);

}
