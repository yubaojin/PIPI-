package com.pipi.dao.pojo;

public class Menu {
    private Integer id;

    private String title;

    private Integer parentId;

    private Integer isParentid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getIsParentid() {
        return isParentid;
    }

    public void setIsParentid(Integer isParentid) {
        this.isParentid = isParentid;
    }
}