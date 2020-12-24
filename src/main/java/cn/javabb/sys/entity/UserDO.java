package cn.javabb.sys.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.List;

/**
 */
@ApiModel(description = "用户")
@TableName("sys_user")
public class UserDO implements UserDetails {

    @ApiModelProperty("用户id")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    @ApiModelProperty("账号")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("性别")
    private Integer sex;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("邮箱是否验证,0否,1是")
    private Integer emailVerified;

    @ApiModelProperty("真实姓名")
    private String trueName;

    @ApiModelProperty("身份证号")
    private String idCard;

    @ApiModelProperty("出生日期")
    private Date birthday;

    @ApiModelProperty("个人简介")
    private String introduction;

    @ApiModelProperty("机构id")
    private Integer organizationId;

    @ApiModelProperty("状态，0正常，1冻结")
    private Integer state;

    @ApiModelProperty("是否删除,0否,1是")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty("注册时间")
    private Date createTime;

    @ApiModelProperty("修改时间")
    private Date updateTime;

    @ApiModelProperty("性别名称")
    @TableField(exist = false)
    private String sexName;

    @ApiModelProperty("机构名称")
    @TableField(exist = false)
    private String organizationName;

    @ApiModelProperty("角色id")
    @TableField(exist = false)
    private List<Integer> roleIds;

    @ApiModelProperty("角色列表")
    @TableField(exist = false)
    private List<RoleDO> roles;

    @ApiModelProperty("权限列表")
    @TableField(exist = false)
    private List<MenuDO> authorities;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Integer emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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

    public List<MenuDO> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<MenuDO> authorities) {
        this.authorities = authorities;
    }

    public List<RoleDO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDO> roles) {
        this.roles = roles;
    }

    public List<Integer> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Integer> roleIds) {
        this.roleIds = roleIds;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return state == 0;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                ", userId=" + userId +
                ", username=" + username +
                ", password=" + password +
                ", nickname=" + nickname +
                ", avatar=" + avatar +
                ", sex=" + sex +
                ", phone=" + phone +
                ", email=" + email +
                ", emailVerified=" + emailVerified +
                ", trueName=" + trueName +
                ", idCard=" + idCard +
                ", birthday=" + birthday +
                ", introduction=" + introduction +
                ", organizationId=" + organizationId +
                ", state=" + state +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", deleted=" + deleted +
                ", organizationName=" + organizationName +
                "}";
    }

}
