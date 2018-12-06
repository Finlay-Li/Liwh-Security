package com.liwh.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * @author: Liwh
 * @ClassName: BaseMapper
 * @Description:
 * @version: 1.0.0
 * @date: 2018-11-23 6:56 PM
 */
//不能让mp扫描到,也一定不能和BaseMapper同名，否则多实现时BaseMapper类型会报错找到多个
public interface SuperMapper<T> extends BaseMapper<T> {
    List<T> selectPageVo(Page page, Object... var);
}
