package com.rdc_wechat.dao.role;

import com.rdc_wechat.dao.BaseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author 86178
 */
public class RoleDaoImpl implements RoleDao {


    /**
     * 获取用户角色
     * @param connection
     * @param rid
     * @return
     * @throws Exception
     */
    @Override
    public String getRloe(Connection connection, int rid) throws Exception {
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        String rName = null;
        if(connection!=null){
            String sql = "select * from mywechat_role where id = ?";
            Object[] params = {rid};
            resultSet = BaseDao.research(connection, pstm, resultSet, sql, params);
            if(resultSet.next()){
                rName = resultSet.getString("roleName");
            }
            BaseDao.release(connection,pstm,resultSet);
        }
        return rName;
    }
}
