package com.liwh.service;


import com.liwh.dao.model.Admin;
import com.liwh.base.BaseService;

/**
 * @author: Liwh
 * @ClassName: AdminService
 * @Description:
 * @version: 1.0.0
 * @date: 2018-09-28 20:37
 */
public interface AdminService extends BaseService<Admin> {


    Admin queryById(Long id);

    Admin queryByPhone(String phone);
}
