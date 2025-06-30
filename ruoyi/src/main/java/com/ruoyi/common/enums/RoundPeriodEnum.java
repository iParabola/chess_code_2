package com.ruoyi.common.enums;

import lombok.Getter;

/**
 * @author kyc
 * @date 2024/2/27 14:41
 */
@Getter
public enum RoundPeriodEnum {

    first_stage(1, "直瞄阶段"),

    second_stage(2, "机动阶段"),

    third_stage(3, "压制解除阶段"),

    fourth_stage(4, "间瞄射击阶段");


    private final Integer code;
    private final String desc;

    RoundPeriodEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
