package com.example.admindemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.admindemo.entity.Resource;
import com.example.admindemo.mapper.ResourceMapper;
import com.example.admindemo.service.ResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.admindemo.vo.ResourceVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 资源表 服务实现类
 * </p>
 *
 * @author wa
 * @since 2021-12-05
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

    @Override
    public List<ResourceVO> listResourceByRoleId(Long roleId) {
        QueryWrapper<Resource> query = Wrappers.query();
        query.eq("r.role_id",roleId).isNull("re.parent_id");
        List<ResourceVO> resourceVOS = baseMapper.listResource(query);
        resourceVOS.forEach(r -> {
            Long resourceId = r.getResourceId();
            QueryWrapper<Resource> subQuery = Wrappers.query();
            subQuery.eq("r.role_id",roleId).eq("re.parent_id", resourceId);
            List<ResourceVO> subs = baseMapper.listResource(subQuery);
            if(CollectionUtils.isNotEmpty(subs)){
                r.setSubs(subs);
            }
        });
        return resourceVOS;
    }
}
