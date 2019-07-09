package com.hannstar.jpa.with.mybatis.demo.mybatis.service;

import com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity;

/**
 * <p>
 * 测试表 服务类
 * </p>
 *
 * @author jiangxinjun
 * @since 2019-06-14
 */
public interface TestService extends BaseService<TestEntity> {

    /**
     * 更新并且插入
     * @param entity
     */
    void updateAndInsert(TestEntity entity);

}
