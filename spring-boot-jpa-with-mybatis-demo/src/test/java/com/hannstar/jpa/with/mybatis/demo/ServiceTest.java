package com.hannstar.jpa.with.mybatis.demo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hannstar.jpa.with.mybatis.demo.jap.entity.QTestEntity;
import com.hannstar.jpa.with.mybatis.demo.jap.entity.TestEntity;
import com.hannstar.jpa.with.mybatis.demo.jap.repository.TestRepository;
import com.hannstar.jpa.with.mybatis.demo.mybatis.entity.enums.StatusEnum2;
import com.hannstar.jpa.with.mybatis.demo.mybatis.enums.StatusEnum;
import com.hannstar.jpa.with.mybatis.demo.mybatis.mapper.TestMapper;
import com.hannstar.jpa.with.mybatis.demo.mybatis.service.TestService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wjh.common.leaf.client.service.LeafService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @author jiangxinjun
 * @date 2019/06/10
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private TestMapper testMapper;

    @Autowired
    private TestService testService;

    @Autowired
    private LeafService leafService;

    /**
     * 查询工厂实体
     */
    @Autowired
    protected JPAQueryFactory queryFactory;


    @Test
    public void queryDsl() {
        QTestEntity qTestEntity = QTestEntity.testEntity;
        List<TestEntity> fetch = queryFactory.select(qTestEntity).from(qTestEntity).fetch();
        Assert.assertNotNull(fetch);
    }

    @Ignore
    @Test
    public void save() {
        TestEntity testEntity = new TestEntity();
        testEntity.setId(leafService.segmentGet("leaf-segment-test"));
        testEntity.setName("test");
        testRepository.save(testEntity);
    }

    @Test
    public void saveManual() {
        TestEntity testEntity = new TestEntity();
        testEntity.setId(6L);
        testEntity.setTenantId(1L);
        testEntity.setName("test" + testEntity.getId());
        testEntity.setGmtCreate(new Date());
        testEntity.setGmtModified(new Date());
        testEntity.setVersion(0);
        testEntity.setIsValid((byte) 1);
        testEntity.setStatus(StatusEnum.Active.getCode());
        testRepository.save(testEntity);
    }

    @Test
    public void saveGenerated() {
        TestEntity testEntity = new TestEntity();
        testEntity.setTenantId(1L);
        testEntity.setName("test" + testEntity.getId());
        testEntity.setGmtCreate(new Date());
        testEntity.setGmtModified(new Date());
        testEntity.setVersion(0);
        testEntity.setIsValid((byte) 1);
        testEntity.setStatus(StatusEnum.Active.getCode());
        testRepository.save(testEntity);
    }

    @Test
    public void saveWithNull() {
        TestEntity testEntity = new TestEntity();
        testEntity.setId(5L);
        testEntity.setName("test5");
        testEntity.setGmtCreate(new Date());
        testEntity.setGmtModified(new Date());
        testRepository.save(testEntity);
    }

    @Test
    public void updateWithNull() {
        TestEntity testEntity = testRepository.findById(5L).get();
        testEntity.setName("test55");
        testRepository.save(testEntity);
    }

    @Ignore
    @Test
    public void save2() {
        com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity testDO = new com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity();
        testDO.setId(leafService.segmentGet("leaf-segment-test"));
        testDO.setName("test" + testDO.getId());
        testMapper.insert(testDO);
    }

    @Ignore
    @Test
    public void save3() {
        com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity testDO = new com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity();
        testDO.setId(leafService.segmentGet("leaf-segment-test"));
        testDO.setName("test" + testDO.getId());
        testDO.insert();
    }

    @Test
    public void save4() {
        com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity testDO = new com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity();
        testDO.setName("test19");
        testDO.setGmtCreate(new Date());
        testDO.setGmtModified(new Date());
        testDO.setVersion(0);
        testDO.setIsValid(true);
        testService.save(testDO);
    }

    @Test
    public void updateById() {
        com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity testDO = new com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity();
        testDO.setId(1L);
        testDO.setName("test111");
        testDO.setVersion(0);
        testService.updateById(testDO);
    }

    @Test
    public void lambdaUpdate() {
        testService.lambdaUpdate()
                .set(com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity::getVersion, 4).set(com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity::getGmtModified, new Date())
                .eq(com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity::getVersion, 3).eq(com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity::getName, "test4").update();
    }

    @Test
    public void updateWithWrapperAndEntity() {
        com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity testDO = new com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity();
        testDO.setGmtModified(new Date());
        testDO.setVersion(3);
        UpdateWrapper<com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity> wrapper = new UpdateWrapper<>();
        wrapper.eq("version", 2);
        wrapper.eq("name", "test4");
        testService.update(testDO, wrapper);
    }

    @Test
    public void updateWithWrapper() {
        UpdateWrapper<com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity> wrapper = new UpdateWrapper<>();
        wrapper.setSql("version=version+1");
        wrapper.set("gmt_modified", new Date());


        wrapper.eq("version", 4);
        wrapper.eq("name", "test4");
        testService.update(wrapper);
    }

    @Test
    public void page() {
        QueryWrapper<com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("version", 0);

        IPage<com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity> page = new Page<>();
        page.setCurrent(1);
        page.setSize(2);
        testService.page(page, wrapper);
        System.out.println(page);
    }

    @Test
    public void removeById() {
        testService.removeById(1);
    }

    @Transactional(rollbackFor = Exception.class)
    @Test
    public void mybatisAndJpaTransactional() {
        TestEntity testEntity = new TestEntity();
        testEntity.setId(1L);
        testEntity.setName("test111");
        testEntity.setGmtModified(new Date());
        testRepository.save(testEntity);

        com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity testDO = new com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity();
        testDO.setId(1L);
        testDO.setName("test111");
        testDO.setGmtModified(new Date());
        testService.updateById(testDO);
        System.out.println("in transactional");
    }


    @Test
    public void query() {
        com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity testDO = testService.lambdaQuery()
                .select(com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity::getVersion)
                .eq(com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity::getId, 1L).one();
        System.out.println(testDO);
    }

    @Test
    public void queryWithEntity() {
        com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity testDO = new com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity();
        testDO.setId(1L);
        LambdaQueryWrapper<com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity> wrapper = Wrappers.lambdaQuery(testDO);
        testService.list(wrapper);
    }

    @Test
    public void pageWithCommon() {
        Class resultClazz = TestSimpleDO.class;

        List<Field> resultFields = Arrays.stream(resultClazz.getDeclaredFields()).collect(Collectors.toList());

        TestSimpleParam param = new TestSimpleParam();
        param.setName("test");

        List<Field> paramFields = Arrays.stream(TestSimpleParam.class.getDeclaredFields()).collect(Collectors.toList());

        TableInfo tableInfo = TableInfoHelper.getTableInfo(com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity.class);
        List<TableFieldInfo> fieldList = tableInfo.getFieldList();

        QueryWrapper<com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity> wrapper = Wrappers.query();
        wrapper.select(com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity.class, tableFieldInfo -> {
            return resultFields.stream().anyMatch(field -> {
                return tableFieldInfo.getProperty().equals(field.getName());
            });
        });

        fieldList.forEach(tableFieldInfo -> {
            Optional<Field> optionalField = paramFields.stream().filter(field -> tableFieldInfo.getProperty().equals(field.getName())).findFirst();
            if (optionalField.isPresent()) {
                try {
                    Field field = optionalField.get();
                    field.setAccessible(true);
                    Object o = field.get(param);
                    if (o != null) {
                        wrapper.eq(field.getName(), o);
                    }
                } catch (IllegalAccessException e) {
                }
            }
        });

        IPage<com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity> page = new Page<>();
        IPage<Map<String, Object>> page2 = testService.pageMaps(page, wrapper);
        List<TestSimpleDO> list =  com.baomidou.mybatisplus.core.toolkit.BeanUtils.mapsToBeans(page2.getRecords(), TestSimpleDO.class);
        IPage<TestSimpleDO> page3 = new Page<>();
        BeanUtils.copyProperties(page2, page3, "records");
        page3.setRecords(list);
        System.out.println(page3);
        /*
        testService.page(page, wrapper);
        IPage<TestSimpleDO> page2 = new Page<>();
        BeanUtils.copyProperties(page, page2);
        List<TestSimpleDO> list = page.getRecords().stream().map(testDO -> {
            TestSimpleDO item = new TestSimpleDO();
            BeanUtils.copyProperties(testDO, item);
            return item;
        }).collect(Collectors.toList());
        page2.setRecords(list);
        System.out.println(page2);
        */
    }

    @Test
    public void getColumnsName() {
        Class resultClazz = TestSimpleDO.class;
        List<Field> resultFields = Arrays.stream(resultClazz.getDeclaredFields()).collect(Collectors.toList());
        TableInfo tableInfo = TableInfoHelper.getTableInfo(com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity.class);
        String sql = tableInfo.chooseSelect(tableFieldInfo -> {
            return resultFields.stream().anyMatch(field -> {
                System.out.println(tableFieldInfo.getProperty());
                return tableFieldInfo.getProperty().equals(field.getName());
            });
        });
        List<String> columns = tableInfo.getFieldList().stream().filter(tableFieldInfo -> {
            return resultFields.stream().anyMatch(field -> {
                return tableFieldInfo.getProperty().equals(field.getName());
            });
        }).map(tableFieldInfo -> {
            return tableFieldInfo.getColumn();
        }).collect(Collectors.toList());
        boolean includeKey = resultFields.stream().anyMatch(field -> {
            return tableInfo.getKeyProperty().equals(field.getName());
        });
        if (includeKey) {
            columns.add(tableInfo.getKeyProperty());
        }
        String[] columnsAry = columns.stream().toArray(String[]::new);
        System.out.println(sql);
        System.out.println(columns);
        System.out.println(columnsAry);
        QueryWrapper<com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity> wrapper = Wrappers.query();
        wrapper.select(columnsAry);
        List<com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity> list = testService.list(wrapper);
        System.out.println(list);
    }

    @Test
    public void queryWithColumnsName() {
        QueryWrapper<com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity> wrapper = Wrappers.query();
        wrapper.select(com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity.NAME, com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity.STATUS);
        List<com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity> list = testService.list(wrapper);
        System.out.println(list);
    }

    @Test
    public void queryWithCustom() {
        testMapper.selectEffective();
        testMapper.selectEffective();
        System.out.println("二级缓存");
    }

    @Test
    public void queryWithLambda() {
        LambdaQueryWrapper<com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.like(com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity::getName, "test");
        List<com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity> list = testService.list(wrapper);
        System.out.println(list);
    }

    @Test
    public void queryWithConsolidationCondition() {
        LambdaQueryWrapper<com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.like(com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity::getName, "%");
        wrapper.and(testEntityLambdaQueryWrapper ->
            testEntityLambdaQueryWrapper.eq(com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity::getStatus, StatusEnum.Active)
                    .or()
                    .eq(com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity::getStatus, StatusEnum.UnActive)
        );
        List<com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity> list = testService.list(wrapper);
        System.out.println(list);
    }

    @Test
    public void queryWithEnum() {
        QueryWrapper<com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity> wrapper = Wrappers.query();
        wrapper.eq(com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity.STATUS, StatusEnum.Active.getCode());
        List<com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity> list = testService.list(wrapper);
        System.out.println(list);
    }

    @Test
    public void queryWithLambdaAndEnum() {
        LambdaQueryWrapper<com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity::getStatus, StatusEnum2.Active);
        List<com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity> list = testService.list(wrapper);
        System.out.println(list);
    }

    @Test
    public void queryWithEntityAndEnum() {
        com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity testEntity = new com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity();
        testEntity.setStatus(StatusEnum2.Active);
        LambdaQueryWrapper<com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity> wrapper = Wrappers.lambdaQuery(testEntity);
        List<com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity> list = testService.list(wrapper);
        System.out.println(list);
    }

    @Test
    public void bulkInsert() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(100);
        int count = 100;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity testDO = new com.hannstar.jpa.with.mybatis.demo.mybatis.entity.TestEntity();
            testDO.setName("test21" + i);
            testDO.setGmtCreate(new Date());
            testDO.setGmtModified(new Date());
            testDO.setVersion(0);
            testDO.setIsValid(true);
            service.submit(() -> {
                try {
                    testService.updateAndInsert(testDO);
                } catch (DuplicateKeyException e) {
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
    }
}
