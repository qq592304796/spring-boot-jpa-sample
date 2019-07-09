package com.hannstar.jpa.with.mybatis.demo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity;
import com.hannstar.jpa.with.mybatis.demo.mybatis.entity.enums.StatusEnum2;
import com.hannstar.jpa.with.mybatis.demo.mybatis.manger.TestManger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author jiangxinjun
 * @date 2019/06/10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MangerTest {

    @Autowired
    private TestManger testManger;

    @Test
    public void QueryFromManger() {
        com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity testEntity = new com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity();
        testEntity.setStatus(StatusEnum2.Active);
        LambdaQueryWrapper<TestEntity> wrapper = Wrappers.lambdaQuery(testEntity);
        List<TestEntity> list = testManger.list(wrapper);
        System.out.println(list);
    }

}
