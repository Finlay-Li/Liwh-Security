package com.liwh.dao.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Liwh
 * @ClassName: Permission
 * @Description:
 * @version: 1.0.0
 * @Timestamp: 2018-09-22 15:20
 */
@Data
@TableName("sys_permission")
public class Permission {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("parent_id")
    private Long parentId;
    @TableField("perm_name")
    private String permName;
    //具体权限内容
    @TableField("perm_content")
    private String permContent;
    //url,button,menus...
    @TableField("type")
    private String type;
    //用户权限状态normal-1，locked-2，useless-3
    @TableField("status")
    private Integer status;
    //http请求方式
    @TableField("http_method")
    private String httpMethod;
    @TableField("create_time")
    private Timestamp createTime;
    @TableField("update_time")
    private Timestamp updateTime;
    //存储子菜单；
//    @Transient//不持久化的，表中不存在
//    private List<Permission> childMenus = new ArrayList<>();
}
