package com.hannstar.jpa.with.mybatis.demo.mybatis.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 状态枚举
 * @author yqj
 */
@Getter
public enum StatusEnum {
    /**
     * 启用
     */
    Active((byte)1,"启用"),
    /**
     * 封存
     */
    UnActive((byte)0,"封存");

    @EnumValue
    private final Byte code;
    private final String msg;

    StatusEnum(Byte code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
