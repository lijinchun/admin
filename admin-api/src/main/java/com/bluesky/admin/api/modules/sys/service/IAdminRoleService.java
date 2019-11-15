package com.bluesky.admin.api.modules.sys.service;

import com.bluesky.admin.api.modules.sys.vo.AdminRoleVO;
import com.bluesky.admin.api.modules.sys.vo.req.AddRoleWithMenusReq;
import com.bluesky.admin.api.modules.sys.vo.req.AdminRoleRetrieveReq;
import com.bluesky.admin.api.modules.sys.vo.req.ModifyRoleWithMenusReq;
import com.bluesky.admin.api.modules.sys.vo.req.RemoveRoleReq;
import com.github.pagehelper.PageInfo;

/**
 * @author Lijinchun
 * @Package com.bluesky.admin.api.modules.sys.service
 * @date 2019/7/30 22:54
 */
public interface IAdminRoleService {
    /**
     * 检索角色信息
     * @param adminRoleRetrieveReq
     * @return
     */
    PageInfo<AdminRoleVO> retrieve(AdminRoleRetrieveReq adminRoleRetrieveReq);

    /**
     * 添加角色附加菜单信息
     * @param addRoleWithMenusReq
     * @return
     */
    boolean addRoleWithMenus(AddRoleWithMenusReq addRoleWithMenusReq);

    /**
     * 修改角色附加菜单
     * @param modifyRoleWithMenusReq
     * @return
     */
    boolean modifyRoleWithMenus(ModifyRoleWithMenusReq modifyRoleWithMenusReq);

    /**
     * 删除角色信息
     * @param removeRoleReq
     * @return
     */
    boolean removeRole(RemoveRoleReq removeRoleReq);
}
