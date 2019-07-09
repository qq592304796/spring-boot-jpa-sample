package com.hannstar.jpa.with.mybatis.demo.mybatis.manger;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hannstar.jpa.with.mybatis.demo.mybatis.entity.BaseEntity;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiangxinjun
 * @since 2019-06-11
 */
public class BaseManger<M extends BaseMapper<T>, T extends BaseEntity> extends ServiceImpl<M, T> {

}
