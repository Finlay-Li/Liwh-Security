package com.liwh.utils;


import com.liwh.abstracts.AbstractUserDetails;
import com.liwh.dao.model.Admin;
import com.liwh.properties.SecurityProperties;
import com.liwh.service.AdminService;

/**
 * @author: Liwh
 * @ClassName: JwtUtils
 * @Description:
 * @version: 1.0.0
 * @date: 2018-10-09 14:59
 */
public class JwtUtils extends AbstractUserDetails<Admin> {
    private AdminService adminService;

    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    public JwtUtils(SecurityProperties securityProperties) {
        super(securityProperties);
    }

    @Override
    protected Admin parserUser(String phone) {
        return adminService.queryByPhone(phone);
    }
}
