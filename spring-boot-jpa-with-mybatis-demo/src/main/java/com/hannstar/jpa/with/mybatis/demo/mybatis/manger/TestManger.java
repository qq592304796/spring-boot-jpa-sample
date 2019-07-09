package com.hannstar.jpa.with.mybatis.demo.mybatis.manger;

import com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity;
import com.hannstar.jpa.with.mybatis.demo.mybatis.mapper.TestMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 测试表 服务实现类
 * </p>
 *
 * @author jiangxinjun
 * @since 2019-06-19
 */
@Service
public class TestManger extends BaseManger<TestMapper, TestEntity> {

}
