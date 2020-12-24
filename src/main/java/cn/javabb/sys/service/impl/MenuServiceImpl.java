package cn.javabb.sys.service.impl;

import cn.javabb.common.web.PageParam;
import cn.javabb.sys.entity.MenuDO;
import cn.javabb.sys.mapper.MenuMapper;
import cn.javabb.sys.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单服务实现类
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, MenuDO> implements MenuService {
    @Override
    public List<MenuDO> listPage(PageParam<MenuDO> pageParam) {
        return baseMapper.listPage(pageParam);
    }

    @Override
    public List<MenuDO> getUserMenu(Integer userId, Integer menuType) {
        return baseMapper.listByUserId(userId, menuType);
    }

    @Override
    public List<MenuDO> toMenuTree(List<MenuDO> menus, Integer parentId) {
        List<MenuDO> list = new ArrayList<>();
        for (MenuDO menu : menus) {
            if (parentId.equals(menu.getParentId())) {
                menu.setChildren(toMenuTree(menus, menu.getMenuId()));
                list.add(menu);
            }
        }
        return list;
    }

}
