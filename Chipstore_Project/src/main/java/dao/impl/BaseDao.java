package dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import pojo.User;
import utils.JdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {

    //使用DBUtils操作数据库
    private QueryRunner queryRunner = new QueryRunner();

    public int update(String sql,Object ...args) {
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.update(conn,sql,args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }

    }

    /**
     *
     * @param type the return object type
	 * @param sql  sql implemented
     * @param args   parameter that passes into sql
     * @return
     * @author Haoran Qi
     * @date
     */
    public <T> T queryForOne(Class<T> type, String sql,Object ...args){
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn,sql, new BeanHandler<T>(type),args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }

    /**
     * query for several javaBean SQL
     * @param type the return object type
     * @param sql  sql implemented
     * @param args   parameter that passes into sql
     * @return
     * @author Haoran Qi
     * @date
     */
    public <T> List<T> queryForList(Class<T> type, String sql, Object ...args){
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn,sql, new BeanListHandler<T>(type),args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }

    /**
     * query for single value ex:count
     * @param sql  sql implemented
     * @param args   parameter that passes into sql
     * @return
     * @author Haoran Qi
     * @date
     */
    public Object queryForSingleValue(String sql,Object ...args){
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn,sql, new ScalarHandler(),args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }

}
