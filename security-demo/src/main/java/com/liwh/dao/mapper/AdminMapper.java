package com.liwh.dao.mapper;


import com.liwh.dao.model.Admin;
import com.liwh.base.SuperMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author: Liwh
 * @InterfaceName: UserMapper
 * @Description:
 * @version: 1.0.0.
 * @date: 2018-09-22 16:39
 */
public interface AdminMapper extends SuperMapper<Admin> {

    Admin queryById(@Param("id") Long id);

    Admin queryByPhone(@Param("phone") String phone);
}
