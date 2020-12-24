package cn.javabb.sys.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 */
@ApiModel(description = "角色")
@TableName("sys_role")
public class RoleDO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("角色id")
    @TableId(value = "role_id", type = IdType.AUTO)
    private Integer roleId;

    @ApiModelProperty("角色标识")
    private String roleCode;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("备注")
    private String comments;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改时间")
    private Date updateTime;

    @ApiModelProperty("是否删除,0否,1是")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty("用户id")
    @TableField(exist = false)
    private Integer userId;

    public RoleDO() {
    }

    public RoleDO(Integer roleId, String roleCode, String roleName) {
        this(roleId, roleName, roleCode, null);
    }

    public RoleDO(Integer roleId, String roleName, String roleCode, String comments) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleCode = roleCode;
        this.comments = comments;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Role{" +
                ", roleId=" + roleId +
                ", roleName=" + roleName +
                ", comments=" + comments +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", deleted=" + deleted +
                "}";
    }
}
