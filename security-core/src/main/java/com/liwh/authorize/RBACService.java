package com.liwh.authorize;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Lwh
 * @interfaceName: RBACService
 * @Description: 动态权限服务
 * @version: 1.0.0
 * @date: 2019-03-15 11:13 AM
 */
public interface RBACService {

    /*怎么定义这个方法？
     * 1 现在要自定义权限规则：access( ) ；Voter是根据返回 boolean 投票过不过，所以RBAC Service也是返回boolean
     * 2 要知道当前请求
     * 3 要知道当前用户在数据库的权限信息
     */
    Boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
