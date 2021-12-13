package com.example.admindemo.service;

import com.example.admindemo.entity.Resource;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.admindemo.vo.ResourceVO;

import java.util.List;

/**
 * <p>
 * 资源表 服务类
 * </p>
 *
 * @author wa
 * @since 2021-12-05
 */
public interface ResourceService extends IService<Resource> {

    List<ResourceVO> listResourceByRoleId(Long roleId);
}
