package cn.javabb.sys.mapper;

import cn.javabb.common.web.PageParam;
import cn.javabb.sys.entity.MenuDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单Mapper接口
 */
public interface MenuMapper extends BaseMapper<MenuDO> {

    /**
     * 分页查询
     */
    List<MenuDO> listPage(@Param("page") PageParam<MenuDO> page);

    /**
     * 根据用户id查询
     */
    List<MenuDO> listByUserId(@Param("userId") Integer userId, @Param("menuType") Integer menuType);

}
