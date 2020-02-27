package com.bluesky.admin.api.modules.sys.controller;

import com.bluesky.admin.common.client.ApiResult;
import com.bluesky.admin.common.controller.BaseController;
import com.bluesky.admin.api.modules.sys.vo.AdminRoleVO;
import com.bluesky.admin.api.modules.sys.vo.req.AddRoleWithMenusReq;
import com.bluesky.admin.api.modules.sys.vo.req.AdminRoleRetrieveReq;
import com.bluesky.admin.api.modules.sys.vo.req.ModifyRoleWithMenusReq;
import com.bluesky.admin.api.modules.sys.vo.req.RemoveRoleReq;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 李金春
 * @Package com.bluesky.admin.api.modules.sys.controller
 * @date 2019/6/28 18:08
 */
@RestController
@RequestMapping("/admin/role")
@Slf4j
public class AdminRoleController extends BaseController {
    @Autowired
    private com.bluesky.admin.api.modules.sys.service.IAdminRoleService IAdminRoleService;

    /**
     * 检索角色
     * @param adminRoleRetrieveReq
     * @return
     */
    @PostMapping("retrieve")
    public ApiResult<PageInfo<AdminRoleVO>> retrieve(@RequestBody AdminRoleRetrieveReq adminRoleRetrieveReq){
        return ApiResult.newSuccess(IAdminRoleService.retrieve(adminRoleRetrieveReq));
    }

    /**
     * 添加角色附加菜单
     * @param addRoleWithMenusReq
     * @return
     */
    @PostMapping("addRoleWithMenus")
    public ApiResult<Boolean> addRoleWithMenus(@RequestBody AddRoleWithMenusReq addRoleWithMenusReq){
        return ApiResult.newSuccess(IAdminRoleService.addRoleWithMenus(addRoleWithMenusReq));
    }

    /**
     * 修改
     * @param modifyRoleWithMenusReq
     * @return
     */
    @PostMapping("modifyRoleWithMenus")
    public ApiResult<Boolean> modifyRoleWithMenus(@RequestBody ModifyRoleWithMenusReq modifyRoleWithMenusReq){
        return ApiResult.newSuccess(IAdminRoleService.modifyRoleWithMenus(modifyRoleWithMenusReq));
    }

    /**
     * 删除角色
     * @param removeRoleReq
     * @return
     */
    @PostMapping("removeRole")
    public ApiResult<Boolean> removeRole(@RequestBody RemoveRoleReq removeRoleReq){
        return ApiResult.newSuccess(IAdminRoleService.removeRole(removeRoleReq));
    }
}