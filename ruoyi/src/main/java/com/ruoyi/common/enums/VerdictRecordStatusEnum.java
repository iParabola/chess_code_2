package com.ruoyi.common.enums;

import lombok.Getter;

/**
 * @author kyc
 * @date 2024/2/26 17:45
 */
@Getter
public enum VerdictRecordStatusEnum {

    INIT(10, "初始化"),

    DEPLOYING(20, "部署中"),

    ONE_DEPLOY_DONE(25, "一方部署完成"),

    TWO_DEPLOY_DONE(30, "双方部署完成"),

    PROCESSING(40, "比赛进行中"),

    END(50, "比赛结束");

    private final Integer code;
    private final String desc;

    VerdictRecordStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
