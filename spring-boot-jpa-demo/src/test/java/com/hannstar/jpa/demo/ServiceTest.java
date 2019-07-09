package com.hannstar.jpa.demo;

import com.hannstar.jpa.demo.entity.TestEntity;
import com.hannstar.jpa.demo.repository.TestRepository;
import com.wjh.common.leaf.client.service.LeafService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author jiangxinjun
 * @date 2019/06/10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private LeafService leafService;

    //@Rollback
    //@Transactional(rollbackFor = Exception.class)
    @Test
    public void save() {
        TestEntity testEntity = new TestEntity();
        testEntity.setId(leafService.segmentGet("leaf-segment-test"));
        testEntity.setName("test");
        testRepository.save(testEntity);
    }

}
