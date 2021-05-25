package com.rdc_wechat.service.role;


import com.rdc_wechat.dao.BaseDao;
import com.rdc_wechat.dao.role.RoleDao;
import com.rdc_wechat.dao.role.RoleDaoImpl;


import java.sql.Connection;

/**
 * @author 86178
 */
public class RoleServiceImpl implements RoleService {


    /**
     * 业务层调用Dao层
     */
    private RoleDao roleDao;
    public RoleServiceImpl(){
        roleDao = new RoleDaoImpl();
    }


    /**
     * 获取用户角色
     * @param rid
     * @return
     */
    @Override
    public String getRloe(int rid) {
        Connection connection = null;
        String rName = null;
        try {
            connection = BaseDao.getConnection();
            //通过业务层调用对应的数据库操作
            rName = roleDao.getRloe(connection,rid);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.release(connection,null,null);
        }
        return rName;
    }
}
