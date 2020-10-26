package utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


public class JdbcUtils {

    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();

    static{
        try {
            Properties properties = new Properties();
            //read in jdbc.properties
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //load data from stream
            properties.load(inputStream);
            //create database connection pool
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * get connection from connection pool
     *
     * */
    public static Connection getConnection(){
        Connection conn = conns.get();
        if(conn == null){
            try {
                conn = dataSource.getConnection();
                conns.set(conn);    //store into threadlocal object, for JDBC manipulation
                conn.setAutoCommit(false);  //set manual management
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
        }
       return  conn;
    }

    /**
     * submit service and close connection
     * @param
     * @return
     * @author Haoran Qi
     * @date
     */
    public static void commitAndClose(){
        Connection connection = conns.get();
        if (connection != null){
            try {
                connection.commit();    //submit service
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        conns.remove();
    }

    /**
     * rollback service and close connection
     * @param
     * @return
     * @author Haoran Qi
     * @date
     */
    public static void rollbackAndClose(){
        Connection connection = conns.get();
        if (connection != null){
            try {
                connection.rollback();    //submit service
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        conns.remove();
    }

    /**
     * @param conn
     * close connection
     * */
//    public static void close(Connection conn){
//        if(conn!=null){
//            try {
//                conn.close();
//            } catch (Exception throwables) {
//                throwables.printStackTrace();
//            }
//        }
//    }

}
