package com.liwh.service.impl;

import com.liwh.dao.mapper.AdminMapper;
import com.liwh.dao.model.Admin;
import com.liwh.service.AdminService;
import liwh.security.base.BaseServiceImpl;
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
@Slf4j
public class AdminServiceImpl extends BaseServiceImpl<AdminMapper,Admin> implements AdminService {

}
