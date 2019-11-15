package com.bluesky.admin.api.modules.sys.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lijinchun
 * @date 2019-07-31 00:14:52
 * Created by CodeGen .
 */
@Data
public class AdminRoleMenu implements Serializable {

    private static final long serialVersionUID = -1080513689894327436L;

    /**
     * id
     */
    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;
}
