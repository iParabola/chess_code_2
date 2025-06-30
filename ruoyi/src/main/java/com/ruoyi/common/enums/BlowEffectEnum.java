package com.ruoyi.common.enums;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author kyc
 * @date 2024/2/27 15:21
 */
@Getter
@Slf4j
public enum BlowEffectEnum {

    KF("Kf", "目标武器损坏",20),

    K("K", "击杀目标",50),

    KM("Km", "目标动力损坏",10),

    S("S", "压制目标",30),


    MOVE_SUCCESS("MOVE_SUCCESS", "机动成功",20),

    MOVE_FAIL("MOVE_FAIL", "机动失败",-10),

    ATTACK_FAIL("ATTACK_FAIL", "无毁伤",-10),

    ATTACK_MID("ATTACK_MID", "轻损",10),

    ATTACK_MODERATE ("ATTACK_MODERATE", "中损",20),

    ATTACK_SEVERE("ATTACK_SEVERE", "重损",30),

    SCOUT_FAIL("SCOUT_FAIL", "未发现",-10),

    SCOUT_FIND("SCOUT_FIND", "发现",10),

    SCOUT_RECOGNITION("SCOUT_RECOGNITION", "识别",20),

    SCOUT_CONFIRM("SCOUT_CONFIRM", "确认",30),

    COMMUNICATION_N("COMMUNICATION_N", "N",-10),

    COMMUNICATION_HY("COMMUNICATION_HY", "HY",10),

    COMMUNICATION_HY_SJ("COMMUNICATION_HY_SJ", "HY/SJ",20),

    COMMUNICATION_Q("COMMUNICATION_Q", "Q",30),

    REACTANCE_FAIL("REACTANCE_FAIL", "无效",-10),

    REACTANCE_MID("REACTANCE_MID", "轻微",10),

    REACTANCE_MODERATE("REACTANCE_MODERATE", "中度",20),

    REACTANCE_SEVERE("REACTANCE_SEVERE", "严重",30),

    COMMAND_CONTROL_FAIL("COMMAND_CONTROL_FAIL", "业务失败",-10),

    COMMAND_CONTROL_SUCCESS("REACTANCE_SEVERE", "业务成功",20);



    private final String code;
    private final String desc;
    private final Integer score;

    BlowEffectEnum(String code, String desc,Integer score) {
        this.code = code;
        this.desc = desc;
        this.score = score;
    }

    public static String getDescByCode(String code) {
        Optional<BlowEffectEnum> optional = Arrays.stream(BlowEffectEnum.values()).filter(item -> item.code.equals(code)).findFirst();
        if (optional.isPresent()) {
            return optional.get().getDesc();
        }
        return "无效攻击";
    }

    public static Integer getScoreByCode(String code) {
        Optional<BlowEffectEnum> optional = Arrays.stream(BlowEffectEnum.values()).filter(item -> item.code.equals(code)).findFirst();

        if (optional.isPresent()) {
            return optional.get().getScore();
        }
        return 0;
    }

}
