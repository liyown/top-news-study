package com.lyw.dao;

import com.lyw.utils.JDBCUtilsByDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author: liuyaowen
 * @poject: WEB-ALL
 * @create: 2024-03-23 15:53
 * @Description:
 */
public class BaseDao {
    /**
     * 查询表的单挑数据，返回给对应的实体类
     *
     * @param clazz 表的实体类
     * @param sql   查询语句，需要对应表的单个语句查询
     * @param args  sql参数设置
     * @return 返回查询到对应数据的实体类
     */
    public <T> T baseObjectQuery(Class<T> clazz, String sql, Object... args) {
        T result;
        Connection connection = JDBCUtilsByDruid.getConnection();
        QueryRunner queryRunner = new QueryRunner();

        try {
            result = queryRunner.query(connection, sql, new BeanHandler<>(clazz), args);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtilsByDruid.close(null, null, connection);
        }
        return result;
    }

    /**
     * 查询表的多条数据，返回给对应的实体类
     *
     * @param clazz 表的实体类
     * @param sql   查询语句，需要对应表的多个语句查询
     * @param args  sql参数设置
     * @return 返回查询到对应数据的实体类
     */
    public <T> List<T> baseMultiObjectQuery(Class<T> clazz, String sql, Object... args) {
        List<T> result;
        Connection connection = JDBCUtilsByDruid.getConnection();
        QueryRunner queryRunner = new QueryRunner();

        try {
            result = queryRunner.query(connection, sql, new BeanListHandler<>(clazz), args);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtilsByDruid.close(null, null, connection);
        }
        return result;
    }

    /**
     * 通用的增删改方法
     *
     * @param sql  查询语句
     * @param args 语句参数
     * @return 返回受到影响的行数
     */
    public int baseUpdate(String sql, Object... args) {
        int update;
        Connection connection = JDBCUtilsByDruid.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        try {
            update = queryRunner.update(connection, sql, args);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtilsByDruid.close(null, null, connection);
        }
        return update;
    }

    /**
     * 查询表的数据总数
     *
     * @param sql  查询语句
     * @param args 语句参数
     * @return 返回查询到的数据总数
     */
    public int selectCount(String sql, Object... args) {
        int count;
        Connection connection = JDBCUtilsByDruid.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        try {
            count = Math.toIntExact(queryRunner.query(connection, sql, new ScalarHandler<Long>(), args));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtilsByDruid.close(null, null, connection);
        }
        return count;
    }
}
