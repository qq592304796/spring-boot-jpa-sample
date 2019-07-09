package com.hannstar.jpa.with.mybatis.demo.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity;

import java.util.List;

/**
 * <p>
 * 测试表 Mapper 接口
 * </p>
 *
 * @author jiangxinjun
 * @since 2019-06-14
 */
public interface TestMapper extends BaseMapper<TestEntity> {

    List<TestEntity> selectEffective();

}
