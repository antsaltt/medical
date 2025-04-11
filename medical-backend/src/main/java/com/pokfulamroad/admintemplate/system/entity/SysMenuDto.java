package com.pokfulamroad.admintemplate.system.entity;

import lombok.Data;

import java.util.Date;


@Data
public class SysMenuDto {

    private String menuId;

    private String defaultMenu;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单类型
     */
    private String menuType;

    /**
     * 父菜单ID
     */
    private String parentId;

    /**
     * 显示顺序
     */
    private Integer orderNum;

    /**
     * 是否可见
     */
    private String visible;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 创建时间
     */
    private Date createTime;
}
