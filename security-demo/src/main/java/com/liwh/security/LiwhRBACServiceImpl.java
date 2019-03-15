package com.liwh.security;

import com.liwh.authorize.RBACService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Lwh
 * @ClassName: LiwhRBACServiceImpl
 * @Description:
 * @version: 1.0.0
 * @date: 2019-03-15 11:37 AM
 */
//@Component
public class LiwhRBACServiceImpl implements RBACService {
    @Override
    public Boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        return null;
    }
}
