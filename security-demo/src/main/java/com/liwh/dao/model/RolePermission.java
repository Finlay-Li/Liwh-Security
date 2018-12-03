package com.liwh.dao.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author: Liwh
 * @ClassName: RolePermission
 * @Description:
 * @version: 1.0.0
 * @date: 2018-09-22 15:52
 */
@Data
@TableName("sys_role_permission")
public class RolePermission {
    @TableId(value = "id", type = IdType.AUTO)

    private Long id;
    @TableField("role_id")
    private Long roleId;
    @TableField("permission_id")
    private Long permissionId;
    @TableField("status")
    private Integer status;
    @TableField("create_time")
    private Timestamp createTime;
    @TableField("update_time")
    private Timestamp updateTime;
}
