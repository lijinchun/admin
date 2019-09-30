package com.bluesky.admin.api.modules.sys.vo.req;

import com.bluesky.admin.api.modules.sys.model.AdminRole;
import com.bluesky.admin.api.modules.sys.vo.ModelWrap;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lijinchun
 * @Package com.bluesky.admin.api.modules.sys.vo.req
 * @date 2019/7/30 23:03
 */
@Data
public class AddRoleWithMenusReq implements ModelWrap<AdminRole> {
    /**
     * 角色代码
     */
    private String roleCode;

    /**
     * 名字
     */
    private String roleName;

    /**
     * 备注
     */
    private String remark;

    private List<Long> menuIds;
    public AddRoleWithMenusReq(){
        this.menuIds = new ArrayList<>();
    }
}
