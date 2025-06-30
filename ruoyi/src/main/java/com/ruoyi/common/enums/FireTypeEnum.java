package com.ruoyi.common.enums;

import lombok.Getter;

/**
 * @author kyc
 * @date 2024/2/27 15:44
 */
@Getter
public enum FireTypeEnum {

    WAIT_FOR_ORDER(10, "待命"),

    CONTINGENCY_TARGET(20, "临机目标"),

    PREFERRED_TARGET(30, "优先目标");

    private final Integer code;
    private final String desc;

    FireTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
