package com.bluesky.admin.api.modules.sys.service.impls;

import com.bluesky.admin.common.annotation.DS;
import com.bluesky.admin.common.configuration.DataSourceEnum;
import com.bluesky.admin.common.exception.ServiceException;
import com.bluesky.admin.api.modules.sys.mapper.AdminRoleMapper;
import com.bluesky.admin.api.modules.sys.mapper.AdminRoleMenuMapper;
import com.bluesky.admin.api.modules.sys.model.AdminRole;
import com.bluesky.admin.api.modules.sys.model.AdminRoleMenu;
import com.bluesky.admin.api.modules.sys.service.IAdminRoleService;
import com.bluesky.admin.api.modules.sys.vo.AdminRoleVO;
import com.bluesky.admin.api.modules.sys.vo.req.AddRoleWithMenusReq;
import com.bluesky.admin.api.modules.sys.vo.req.AdminRoleRetrieveReq;
import com.bluesky.admin.api.modules.sys.vo.req.ModifyRoleWithMenusReq;
import com.bluesky.admin.api.modules.sys.vo.req.RemoveRoleReq;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lijinchun
 * @Package com.bluesky.admin.api.modules.sys.service.impls
 * @date 2019/7/30 23:07
 */
@Service
public class AdminRoleServiceImpl implements IAdminRoleService {

    @Autowired
    private AdminRoleMapper adminRoleMapper;
    @Autowired
    private AdminRoleMenuMapper adminRoleMenuMapper;
    @Override
    public PageInfo<AdminRoleVO> retrieve(AdminRoleRetrieveReq adminRoleRetrieveReq) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addRoleWithMenus(AddRoleWithMenusReq addRoleWithMenusReq) {
        AdminRole query = new AdminRole();
        query.setRoleCode(addRoleWithMenusReq.getRoleCode());
        AdminRole result = adminRoleMapper.selectOne(query);
        if(result != null){
            throw new ServiceException("该角色代码已存在");
        }

        //1、插入角色数据
        AdminRole role = addRoleWithMenusReq.toModelWithBaseInfo(AdminRole.class);
        adminRoleMapper.insert(role);

        //2、添加角色与菜单的对应关系
        if(!addRoleWithMenusReq.getMenuIds().isEmpty()){
            AdminRoleMenu delCons = new AdminRoleMenu();
            delCons.setRoleId(role.getId());
            adminRoleMenuMapper.delete(delCons);
            List<AdminRoleMenu> roleMenus = new ArrayList<>();
            for (Long menuId : addRoleWithMenusReq.getMenuIds()) {
                AdminRoleMenu tmp = new AdminRoleMenu();
                tmp.setMenuId(menuId);
                tmp.setRoleId(role.getId());
                roleMenus.add(tmp);
            }
            adminRoleMenuMapper.insertList(roleMenus);
        }
        return true;
    }

    @Override
    public boolean modifyRoleWithMenus(ModifyRoleWithMenusReq modifyRoleWithMenusReq) {
        return false;
    }

    @Override
    public boolean removeRole(RemoveRoleReq removeRoleReq) {
        return false;
    }
}
