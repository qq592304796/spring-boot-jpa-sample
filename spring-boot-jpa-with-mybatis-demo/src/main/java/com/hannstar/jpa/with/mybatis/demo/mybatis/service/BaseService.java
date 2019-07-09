package com.hannstar.jpa.with.mybatis.demo.mybatis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hannstar.jpa.with.mybatis.demo.mybatis.entity.BaseEntity;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiangxinjun
 * @since 2019-06-11
 */
public interface BaseService<T extends BaseEntity> extends IService<T> {

}
