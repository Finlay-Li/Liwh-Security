package com.liwh.dao.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author: Liwh
 * @ClassName: User
 * @Description:
 * @version: 1.0.0
 * @date: 2018-09-22 15:04
 */
@Data
@TableName("sys_admin")
public class Admin {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    //账户名称
    @TableField("account_name")
    private String accountName;
    //用户名
    @TableField("admin_name")
    private String adminName;
    //密码
    @TableField("password")
    private String password;
    //状态，normal-1，locked-2，useless-3'
    @TableField("status")
    private Integer status;
    //手机号
    @TableField("phone")
    private String phone;
    //邮箱
    @TableField("email")
    private String email;
    //创建者
    @TableField("create_by")
    private Long createBy;
    //修改者
    @TableField("update_by")
    private Long updateBy;
    //登录失败次数
    @TableField("login_failed_num")
    private Integer loginFailedNum;
    //密码上次更新时间
    @TableField("password_update_time")
    private Timestamp pswUpdateTime;
    //记录创建时间
    @TableField("create_time")
    private Timestamp createTime;
    //记录修改时间
    @TableField("update_time")
    private Timestamp updateTime;
}
