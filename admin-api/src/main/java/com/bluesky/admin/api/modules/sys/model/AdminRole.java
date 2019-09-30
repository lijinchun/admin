package com.bluesky.admin.api.modules.sys.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author lijinchun
 * @date 2019-07-31 00:14:52
 * Created by CodeGen .
 */
@Data
public class AdminRole implements Serializable {

    private static final long serialVersionUID = -8584632195450829856L;

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

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

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 创建者
     */
    private String createdBy;

    /**
     * 更新时间
     */
    private Date updatedAt;

    /**
     * 更新者
     */
    private String updatedBy;

}
