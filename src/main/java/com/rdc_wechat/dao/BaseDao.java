package com.rdc_wechat.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 操作数据库的公共类
 * @author 86178
 */
public class BaseDao {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    //静态代码块，类加载的时候就执行
    static {
        init();
    }

    /**
     * 初始化连接参数,从配置文件里获取
     */
    public static void init(){
        Properties properties=new Properties();
        String configFile = "db.properties";
        InputStream input=BaseDao.class.getClassLoader().getResourceAsStream(configFile);
        try {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver=properties.getProperty("driver");
        url=properties.getProperty("url");
        username=properties.getProperty("username");
        password=properties.getProperty("password");
    }

    /**
     * 获得数据库连接
     * @return
     */
    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,username,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 查询数据操作
     * @param connection
     * @param preparedStatement
     * @param resultSet
     * @param sql
     * @param params
     * @return
     * @throws Exception
     */
    public static ResultSet research(Connection connection,PreparedStatement preparedStatement,ResultSet resultSet,String sql,Object[] params) throws Exception{
       //预编译语句不需要传参
        preparedStatement = connection.prepareStatement(sql);
        for(int i = 0; i < params.length; i++){
            preparedStatement.setObject(i+1, params[i]);
        }
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    /**
     * 更新数据库操作
     * @param connection
     * @param preparedStatement
     * @param resultSet
     * @param sql
     * @param params
     * @return
     * @throws Exception
     */
    public static int update(Connection connection,PreparedStatement preparedStatement,ResultSet resultSet,String sql,Object[] params) throws Exception{
        preparedStatement = connection.prepareStatement(sql);
        for(int i = 0; i < params.length; i++){
            preparedStatement.setObject(i+1, params[i]);
        }
        return preparedStatement.executeUpdate();
    }

    /**
     * 释放资源
     * @param connection
     * @param preparedStatement
     * @param resultSet
     * @return
     * @throws Exception
     */
    public static boolean release(Connection connection,PreparedStatement preparedStatement,ResultSet resultSet){
      boolean flag = true;
      //先释放resultSet
      if(resultSet != null){
          try {
              resultSet.close();
              resultSet = null;
          } catch (SQLException e) {
              e.printStackTrace();
              flag = false;
          }
      }

      //然后释放preparedStatement
      if(preparedStatement != null){
          try {
                preparedStatement.close();
                preparedStatement = null;
          } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
          }
      }

      //最后释放connection
      if(connection != null){
          try {
                connection.close();
                connection = null;
          } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
          }
      }
      return flag;
    }
}
