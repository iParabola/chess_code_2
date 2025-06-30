package com.ruoyi.common.enums;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author kyc
 * @date 2024/2/27 11:23
 */
@Getter
public enum UserPermissionEnum {
    ADMIN("admin", "管理员"),

    NORMAL("normal", "普通用户");

    private final String code;
    private final String desc;

    UserPermissionEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String getDescByCode(String code) {
        Optional<UserPermissionEnum> optional = Arrays.stream(UserPermissionEnum.values()).filter(item -> item.code.equals(code)).findFirst();
        if (optional.isPresent()) {
            return optional.get().getDesc();
        }
        return "";
    }

    public static List<String> queryCodeList() {
        return Arrays.stream(UserPermissionEnum.values()).map(e -> e.getCode()).collect(Collectors.toList());
    }

    public static List<Map<String, String>> queryPermissionSelections() {
        List<Map<String, String>> mapList = new ArrayList<>();
        Arrays.stream(UserPermissionEnum.values()).forEach(item -> {
            Map<String, String> map = new HashMap<>();
            map.put(item.getCode(), item.getDesc());
            mapList.add(map);
        });
        return mapList;
    }
}
