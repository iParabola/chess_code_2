package com.ruoyi.common.enums;

import lombok.Getter;

/**
 * @author kyc
 * @date 2024/2/27 16:01
 */
@Getter
public enum IndirectRecordStatusEnum {

    INIT(0, "初始化"),

    PROCESSING(10, "处理中"),

    FAILURE(20, "失败"),

    SUCCESS(30, "成功");

    private final Integer code;
    private final String desc;

    IndirectRecordStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
