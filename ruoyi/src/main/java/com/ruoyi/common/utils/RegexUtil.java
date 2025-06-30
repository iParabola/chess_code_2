package com.ruoyi.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author kyc
 * @date 2024/2/27 12:52
 */
public class RegexUtil {
    private static final String MOBILE_CHECK_REGEX = "^1[3-9]\\d{9}$";

    /**
     * 校验手机号格式是否正确
     *
     * @param mobile
     * @return true:格式正确，false:格式错误
     */
    public static Boolean checkMobileFormatIsRight(String mobile) {
        // 创建Pattern对象
        Pattern pattern = Pattern.compile(MOBILE_CHECK_REGEX);
        // 创建Matcher对象
        Matcher matcher = pattern.matcher(mobile);
        if (matcher.matches()) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
}
