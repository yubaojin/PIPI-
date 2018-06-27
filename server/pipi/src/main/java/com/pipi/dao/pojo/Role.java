package com.pipi.dao.pojo;

import java.util.Date;

public class Role {
    private Long roleId;

    private String roleName;

    private String remark;

    private Long createUserId;

    private Date createTime;

    private Byte isDefaultRole;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Byte getIsDefaultRole() {
        return isDefaultRole;
    }

    public void setIsDefaultRole(Byte isDefaultRole) {
        this.isDefaultRole = isDefaultRole;
    }
}