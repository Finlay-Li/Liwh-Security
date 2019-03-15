package com.liwh.authorize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Lwh
 * @ClassName: DefaultRBACServiceImpl
 * @Description: 默认的实现：除了我们配置的匿名，其他的服务全部拦截，需认证后才可访问
 * @version: 1.0.0
 * @date: 2019-03-15 11:35 AM
 */
@Component("RBACService")
public class DefaultRBACServiceImpl implements RBACService {

    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        //获取登录用户信息：要判断是不是UserDetails类型，避免匿名访问时AnonymousAuthenticationFilter 创建的"anonymous"
        Object principal = authentication.getPrincipal();
        Boolean hasPermission = false;
        if (principal instanceof UserDetails) {
            //已通过了token，信息被set 到 content了，可以拿到username等数据
            String username = ((UserDetails) principal).getUsername();
            //获取用户在数据的权限数据
            List<String> permissions = new ArrayList<>();
            //进行比对
            for (String p : permissions) {
                String requestURI = request.getRequestURI();
                if (antPathMatcher.match(requestURI, p)) {
                    //有权限
                    hasPermission = true;
                    break;
                }
            }
        }
        return hasPermission;
    }

}
