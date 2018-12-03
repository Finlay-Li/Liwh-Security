package com.liwh.dao.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author: Liwh
 * @ClassName: adminRole
 * @Description:
 * @version: 1.0.0
 * @Timestamp: 2018-09-22 15:48
 */
@Data
@TableName("sys_admin_role")
public class AdminRole {
    @TableId(value ="id",type = IdType.AUTO)
    private Long id;
    @TableField("admin_id")
    private Long adminId;
    @TableField("role_id")
    private Long roleId;
    @TableField("status")
    private Integer status;
    @TableField("create_time")
    private Timestamp createTime;
    @TableField("update_time")
    private Timestamp updateTime;
}
