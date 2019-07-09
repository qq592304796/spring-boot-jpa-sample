package com.hannstar.jpa.with.mybatis.demo.mybatis.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hannstar.jpa.with.mybatis.demo.mybatis.entity.BaseEntity;
import com.hannstar.jpa.with.mybatis.demo.mybatis.service.BaseService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiangxinjun
 * @since 2019-06-11
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseEntity> extends ServiceImpl<M, T> implements BaseService<T> {

}
