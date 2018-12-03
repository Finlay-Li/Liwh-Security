package liwh.security.base;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlHelper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * @author: Liwh
 * @ClassName: SupportService
 * @Description:
 * @version: 1.0.0
 * @date: 2018-11-24 5:18 PM
 */
@SuppressWarnings("unchecked")
@Service
public class SupportService<M, T> {

    protected M baseMapper;

    /*批量插入*/
    @Deprecated
    public boolean saveBatch(Collection<T> collection) {
        return saveBatch(collection, 1000);
    }

    /**
     * 批量插入
     *
     * @param collection 实体对象集合
     * @param batchSize  每次插入的数量
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean saveBatch(Collection<T> collection, int batchSize) {
        //SqlStatement
        String statement = sqlStatement(SqlMethod.INSERT_ONE);
        //SqlSession
        SqlSession sqlSession = SqlHelper.sqlSessionBatch(invokeMethodClass());
        //插入并回填主键
        int i = 0;
        for (T t : collection) {
            sqlSession.insert(statement, t);
            if (i >= 1 && i % batchSize == 0) {
                sqlSession.flushStatements();
            }
            i++;
        }
        sqlSession.flushStatements();
        return true;
    }

    /*判断sql执行结果*/
    protected boolean retBool(Integer result) {
        return SqlHelper.retBool(result);
    }

    /*SqlStatement:插入的声明methoe,sql,*/
    /*Spring: sqlSession.insert("org.mybatis.spring.sample.mapper.UserMapper.insertUser", user);*/
    protected String sqlStatement(SqlMethod sqlMethod) {
        TableInfo table = SqlHelper.table(invokeMethodClass());
        String sqlStatement = table.getSqlStatement(sqlMethod.getMethod());
        return sqlStatement;
    }

    protected Class<T> invokeMethodClass() {
        //使用泛形的类型
        return ReflectionKit.getSuperClassGenericType(baseMapper.getClass(), 1);
    }
}

