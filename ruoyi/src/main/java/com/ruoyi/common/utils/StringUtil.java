package com.ruoyi.common.utils;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {
    /**
     * 判断字符串是否为空
     *
     * @param str 字符串
     * @return 判断结果
     */
    public static boolean isEmptyOrNull(String str) {
        return str == null || "".equals(str.trim());
    }

    /**
     * 判断对象是否为空字符串。
     *
     * @param obj 字符串对象
     * @return 判断结果
     */
    public static boolean isEmptyOrNull(Object obj) {
        return obj == null ||  String.valueOf(obj).trim().length() == 0;
    }

    /**
     * 判断字符串是否不为空。
     *
     * @param str 字符串
     * @return 判断结果
     */
    public static boolean isNotEmptyAndNotNull(String str) {
        return !isEmptyOrNull(str);
    }

    /**
     * 二进制字符串转十六进制字符串
     *
     * @param   array       the byte array to convert
     * @return              a length*2 character string encoding the byte array
     */
    public static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0)
            return String.format("%0" + paddingLength + "d", 0) + hex;
        else
            return hex;
    }

    /**
     * 十六进制字符串转二进制字符串
     *
     * @param   hex         the hex string
     * @return              the hex string decoded into a byte array
     */
    public static byte[] fromHex(String hex) {
        byte[] binary = new byte[hex.length() / 2];
        for (int i = 0; i < binary.length; i++) {
            binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return binary;
    }

    public static Date StringToDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(date);
    }

    /**
     *
     * 计算字符串长度(一个中文字符长度为2)
     *
     * @param str 待计算的字符串
     * @return
     */
    public static int length(String str) {
        return length(str, 2);
    }

    /**
     * 计算字符串长度
     *
     * @param str 待计算的字符串
     * @param defaultChineseLen 一个中文字符占用的长度
     * @return
     */
    public static int length(String str, int defaultChineseLen) {
        int totalLength = 0;
        char[] chars = str.toCharArray();
        for (char c : chars) {
            if (isChinese(c)) {
                totalLength += defaultChineseLen;
            } else {
                totalLength++;
            }
        }
        return totalLength;
    }

    /**
     * 判断字符是否是中文
     *
     * @param c 字符
     * @return
     */
    public static boolean isChinese(char c) {
        // 假定英文范围为0-127
        int enEnd = 127;
        int code = c;
        return code > enEnd;
    }

}
