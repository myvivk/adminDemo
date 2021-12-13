package com.example.admindemo.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.admindemo.entity.Resource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.admindemo.vo.ResourceVO;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * <p>
 * 资源表 Mapper 接口
 * </p>
 *
 * @author wa
 * @since 2021-12-05
 */
public interface ResourceMapper extends BaseMapper<Resource> {

    /**
     * 查询当前登录人的资源
     * @param wrapper
     * @return
     */
    List<ResourceVO> listResource(@Param(Constants.WRAPPER) Wrapper<Resource> wrapper);
}
