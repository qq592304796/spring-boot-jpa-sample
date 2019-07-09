package com.hannstar.jpa.with.mybatis.demo;

import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author jiangxinjun
 * @since 2019-06-12
 */
@Data
public class TestSimpleDO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 创建时间
     */
    private Date gmtCreate;

}
