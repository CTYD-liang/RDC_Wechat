package com.rdc_wechat.service.role;

import java.sql.Connection;

/**
 * @author 86178
 */
public interface RoleService {

    /**
     * 查找用户角色
     * @param rid
     * @return
     */
    public String getRloe(int rid);
}
