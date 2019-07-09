package com.hannstar.jpa.with.mybatis.demo.mybatis.service.impl;

import com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity;
import com.hannstar.jpa.with.mybatis.demo.mybatis.mapper.TestMapper;
import com.hannstar.jpa.with.mybatis.demo.mybatis.service.TestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 测试表 服务实现类
 * </p>
 *
 * @author jiangxinjun
 * @since 2019-06-14
 */
@Service
public class TestServiceImpl extends BaseServiceImpl<TestMapper, TestEntity> implements TestService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateAndInsert(TestEntity entity) {
//        try {
            //Thread.sleep(500);
            save(entity);
            //Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
