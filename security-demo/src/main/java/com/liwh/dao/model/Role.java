package com.liwh.dao.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author: Liwh
 * @ClassName: Role
 * @Description:
 * @version: 1.0.0
 * @date: 2018-09-22 15:16
 */
@Data
@TableName("sys_role")
public class Role {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("role_name")
    private String roleName;
    //权限的状态，normal-1，locked-2，useless-3
    @TableField("status")
    private Integer status;
    @TableField("create_time")
    private Timestamp createTime;
    @TableField("update_time")
    private Timestamp updateTime;
}
