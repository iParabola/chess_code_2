package com.ruoyi.common.enums;

import lombok.Getter;

/**
 * @author kyc
 * @date 2024/2/27 19:07
 */
@Getter
public enum VerdictResultEnum {

    FIRST_WIN(10, "甲方胜乙方"),

    DEAD_HEAT(20, "平"),

    SECOND_WIN(30, "甲方负乙方");

    private final Integer code;
    private final String desc;

    VerdictResultEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
