package com.hannstar.jpa.with.mybatis.demo.mybatis.entity.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.Getter;

/**
 * 状态枚举
 * @author yqj
 */
@Getter
public enum StatusEnum2 implements IEnum<Byte> {
    /**
     * 启用
     */
    Active((byte)1,"启用"),
    /**
     * 封存
     */
    UnActive((byte)0,"封存");

    private final Byte code;
    private final String msg;

    StatusEnum2(Byte code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Byte getValue() {
        return code;
    }
}
