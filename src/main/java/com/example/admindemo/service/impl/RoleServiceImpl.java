package com.example.admindemo.service.impl;

import com.example.admindemo.entity.Role;
import com.example.admindemo.mapper.RoleMapper;
import com.example.admindemo.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author wa
 * @since 2021-12-05
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
