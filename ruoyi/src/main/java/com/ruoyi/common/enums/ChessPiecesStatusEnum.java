package com.ruoyi.common.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author kyc
 * @date 2024/2/27 14:13
 */
@Getter
public enum ChessPiecesStatusEnum {

    NORMAL(10, "正常", false),

    DESTROYED(20, "被击毁", true),

    REPRESSED(30, "被压制", true);

    private final Integer code;
    private final String desc;
    private final Boolean isCanBeRemoved;

    ChessPiecesStatusEnum(Integer code, String desc, Boolean isCanBeRemoved) {
        this.code = code;
        this.desc = desc;
        this.isCanBeRemoved = isCanBeRemoved;
    }

    public static String getDescByCode(Integer code) {
        Optional<ChessPiecesStatusEnum> optional = Arrays.stream(ChessPiecesStatusEnum.values()).filter(item -> item.code.equals(code)).findFirst();
        if (optional.isPresent()) {
            return optional.get().getDesc();
        }
        return ChessPiecesStatusEnum.NORMAL.getDesc();
    }

    public static Boolean getIsCanBeRemovedByCode(Integer code) {
        Optional<ChessPiecesStatusEnum> optional = Arrays.stream(ChessPiecesStatusEnum.values()).filter(item -> item.code.equals(code)).findFirst();
        if (optional.isPresent()) {
            return optional.get().getIsCanBeRemoved();
        }
        return Boolean.FALSE;
    }

}

