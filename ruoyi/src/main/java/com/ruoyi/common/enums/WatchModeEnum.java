package com.ruoyi.common.enums;

import lombok.Getter;

/**
 * @author kyc
 * @date 2024/2/27 13:58
 */
@Getter
public enum WatchModeEnum {

    PERMIT_WATCH(10, "观战"),

    FORBID_WATCH(20, "禁止观战");

    private final Integer code;
    private final String desc;

    WatchModeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
