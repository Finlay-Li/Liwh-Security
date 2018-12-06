package com.liwh.base;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author: Liwh
 * @ClassName: BaseServiceImpl
 * @Description:
 * @version: 1.0.0
 * @date: 2018-11-25 1:46 PM
 */
public class BaseServiceImpl<M extends SuperMapper<T>,T> extends ServiceImpl<M,T> implements BaseService<T> {
}
