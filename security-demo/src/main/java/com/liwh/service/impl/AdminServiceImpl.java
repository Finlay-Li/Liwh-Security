package com.liwh.service.impl;

import com.liwh.dao.mapper.AdminMapper;
import com.liwh.dao.model.Admin;
import com.liwh.service.AdminService;
import com.liwh.base.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author: Liwh
 * @ClassName: AdminServiceImpl
 * @Description:
 * @version: 1.0.0
 * @date: 2018-09-28 20:37
 */
@Service
public class AdminServiceImpl extends BaseServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin queryById(Long id) {
        return adminMapper.queryById(id);
    }
}
