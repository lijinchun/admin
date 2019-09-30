package com.bluesky.admin.api.modules.sys.mapper;

import com.bluesky.admin.api.modules.sys.model.AdminRoleMenu;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * DAO
 *
 * @author Lijinchun
 * @date 2019-07-31 00:14:52
 * Created by CodeGen .
 */
@Repository
public interface AdminRoleMenuMapper extends Mapper<AdminRoleMenu>, InsertListMapper<AdminRoleMenu> {
}
