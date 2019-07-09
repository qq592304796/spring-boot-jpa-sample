package com.hannstar.jpa.with.mybatis.demo.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hannstar.jpa.with.mybatis.demo.mybatis.entity.enums.StatusEnum2;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 测试表
 * </p>
 *
 * @author jiangxinjun
 * @since 2019-06-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("wms_test")
public class TestEntity extends BaseEntity<TestEntity> {

    private static final long serialVersionUID = 1L;

    public static final String NAME = "name";

    public static final String STATUS = "status";

    /**
     * 名称
     */
    @TableField("name")
    private String name;
    /**
     * 状态
     */
    @TableField("status")
    private StatusEnum2 status;


}
