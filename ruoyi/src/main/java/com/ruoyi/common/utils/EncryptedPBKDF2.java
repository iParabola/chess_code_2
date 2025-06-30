package com.ruoyi.common.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class EncryptedPBKDF2 {
    public static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";

    // 盐的长度 由32位改为16位
    private static final int SALT_BYTE_SIZE = 8/2;

    // 生成密文的长度 由128位改为64位
    private static final int HASH_BIT_SIZE = 64*4;

    // 迭代次数 由10000次改为5次
    private static final int PBKDF2_ITERATIONS = 5;

    // 以下是原密码算法的参数
    private static final int SALT_BYTE_SIZE_old = 32/2;

    private static final int HASH_BIT_SIZE_old = 128*4;

    private static final int PBKDF2_ITERATIONS_old = 10000;

    /**  加密算法 */
    public final static String hashAlgorithmName = "SHA-256";
    /**  循环次数 */
    public final static int hashIterations = 16;


    /**
     * 生成密文   新的密码生成算法
     *
     * @param password
     *            明文密码
     * @param salt
     *            盐值
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static String getEncryptedPwd(String password, String salt)  {
        SecretKeyFactory f = null;
        try {
            f = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        KeySpec spec = new PBEKeySpec(password.toCharArray(), StringUtil.fromHex(salt), PBKDF2_ITERATIONS, HASH_BIT_SIZE);

        String result = null;
        try {
            result = StringUtil.toHex(f.generateSecret(spec).getEncoded());
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 生成密文   原密码生成算法
     *
     * @param password
     *            明文密码
     * @param salt
     *            盐值
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static String getEncryptedPwdOld(String password, String salt)  {
        SecretKeyFactory f = null;
        try {
            f = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        KeySpec spec = new PBEKeySpec(password.toCharArray(), StringUtil.fromHex(salt), PBKDF2_ITERATIONS_old, HASH_BIT_SIZE_old);

        String result = null;
        try {
            result = StringUtil.toHex(f.generateSecret(spec).getEncoded());
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 通过提供加密的强随机数生成器 生成盐
     *
     * @return salt
     * @throws NoSuchAlgorithmException
     */
    public static String generateSalt(){
        SecureRandom random = null;
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] salt = new byte[SALT_BYTE_SIZE];
        random.nextBytes(salt);
        return StringUtil.toHex(salt);
    }

    /**
     * 原生成盐的算法
     * 通过提供加密的强随机数生成器 生成盐
     *
     * @return salt
     * @throws NoSuchAlgorithmException
     */
    public static String generateSaltOld(){
        SecureRandom random = null;
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] salt = new byte[SALT_BYTE_SIZE_old];
        random.nextBytes(salt);
        return StringUtil.toHex(salt);
    }

    /**
     * 对输入的密码进行验证
     *
     * @param attemptedPwd
     * @param encryptedPwd
     * @param salt
     */
    public static boolean authenticate(String attemptedPwd, String encryptedPwd, String salt) {
        // 用相同的盐值对用户输入的密码进行加密
        String encryptedAttemptedPassword = getEncryptedPwd(attemptedPwd, salt);
        // 把加密后的密文和原密文进行比较，相同则验证成功，否则失败
        return encryptedAttemptedPassword.equals(encryptedPwd);
    }

    /**
     * sha256加密
     *
     * @param password
     * @param salt
     */
    public static String sha256(String password, String salt) {
        return new SimpleHash(hashAlgorithmName, password, salt, hashIterations).toString();
    }

    /**
     * 对输入的密码进行验证
     *
     * @param attemptedPwd
     * @param encryptedPwd
     * @param salt
     */
    public static boolean sha256Authenticate(String attemptedPwd, String encryptedPwd, String salt) {
        // 用相同的盐值对用户输入的密码进行加密
        String encryptedAttemptedPassword = sha256(attemptedPwd, salt);
        return encryptedAttemptedPassword.equals(encryptedPwd);
    }
}
