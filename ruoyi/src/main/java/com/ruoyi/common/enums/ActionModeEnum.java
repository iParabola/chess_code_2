package com.ruoyi.common.enums;

import lombok.Getter;

/**
 * @author kyc
 * @date 2024/2/27 14:15
 */
@Getter
public enum ActionModeEnum {

    NORMAL(10, "移动"),

    DESTROYED(20, "攻击"),

    HIDE(30, "隐藏"),

    SCOUT(40, "侦察"),

    COMMUNICATION(50, "通信"),

    REACTANCE(60, "干扰"),

    COMMAND_CONTROL(70, "指控"),

    RELEASE_PRESS(80, "解除压制");

    private final Integer code;
    private final String desc;

    ActionModeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}

