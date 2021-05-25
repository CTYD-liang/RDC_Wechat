package com.rdc_wechat.dao.role;


import java.sql.Connection;

/**
 * @author 86178
 */
public interface RoleDao {

    /**
     * 获取用户角色
     * @param connection
     * @param rid
     * @return
     * @throws Exception
     */
    public String getRloe(Connection connection, int rid) throws Exception;
}
