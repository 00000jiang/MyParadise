package com.paradise.common.utils;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author jrf
 * @date 2023-2-21 15:18
 */
public class AesUtil {

    private static final String privateKeyFile = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCuwaTU1+Kq3/a4cS3eGSnR8AlU2hQmIB12SOxDEplXQNPHDHxsi4Y2165r9KtIY9Qbgl+2elC0eRvLDuR4ZqOP1LFyI/bGjV3ypXz2xcXzgYtAtXzypgtk/xJAK3PKfW2991PkO8SF1KQ90nJaH3ptTAbUyBqbTrHuEo6Hhy/vxmpe/rZ4rn0ZY2fw7Ec0RxNkbm4h5pAJeE6SPyDonUUGvyo2zj45uzvZh56xH5p5B8s4y58si8dKTZO5tlqlvCO1yZI2v1aipGevj7aCb8DiU5gA0i6UBJkhQGF8F53g0DDKUcNp4v7tjwZM5pTHIvx82raFCexSDNhUmQgGEp03AgMBAAECggEAQ/OB700ekwjQ+3jut9ya+UnXpwnBKe305siGLbJus5G+hzhgrSg3gN2uscWKUHSYxjWUl/TJqtMornVU+0gVs1ilwtcfHqdbb31PsPjH9t5ZUkj/k65i4duYsiDzPjo1UNd55FYlHVDIqE1LL6XrydH1GgnQ5rDb2NGYYDR9vEKKvRKzqw2+GuIYNn5+TVdJkj/TqSr5QN/d3zYjP4L/046pD8lYweJ7nJf4tCY5B14LCCTkltJti2AGbiDhnUiPuyAKxK91Zlfd3VRCsssukebIPwR3cg4y6bmwFAuOYuPJrUo37KiOCfo1O3+uilJIrx+3GdXDp4i9M/8eBh+jYQKBgQDiassCxzxQEA2rMcJucSqVpqePyVSU7uRPN5T0mONNcVDJAAZmgb137l7MEYXlVJLDDoBMgrHxjGOyO2NB+tTSPHJCdt2Rru/rZucfZEdvfjhyxsEr+EgAS/zVt+2plUYXF/Tbxqmhup2hGl579Z41j7CCpcLF42XJiKpdmqqeZwKBgQDFluaxS2z1416REjxvB9RXobwUg3rhjv++EKLlLgo9mR+If8KVSvXnh9Rk1ypEOG1+UsevFrfi1/ywUTH6QcgI/vGl4Do7mKBH8+PPiPynnUDyWziJRMCyMJCrn+G4RrxauR/21AhIY3jnCzPm2Zt9pb6UGLERvqTrsFU7+O0osQKBgD5ughH/DANrmclFzD4V2E8xh+ye8EKs6KlrQxh6+9pDz6nM5vbm+W6TfPI6Nrh4rIs6eWgfdZ+MPYVM2N69omdiIYNwMUJVgXRgiIRga04z+K61XViZeokSauIneFIVhiEiCvN7ARVGVuawVbNKk9hjCaHMwTs9q7h5hbiPyFIrAoGBAJwiWRsiWfCXgaOrQFwQWMgCgsfG+bQBNq+vH7lvvyHbvOI06sLkIBqb58bji3+rA0K+ERKXJim3uPJuxspVbsdFBhiobfun6IfS/mKBgfzFM4wPb0ZxML/dxoQH6+qc4rvh65M1C3KAQAp6fMHflJIeBdGrDUjTWCjyBvhEBMuRAoGBANT0Mry18QUNVTUtbFEYUsWDrMludcii10wRYYYMJ6aDOuap1K57KlWXQtkU+NzBTbOUp3OyUoN+bLtL1Syu95lT7ojSttSa4bJtzEXUh6dqBCMHaO7xKiqCgOAJyOLryalW0EzmZM+Jbl/kYUCpaXGZ6nmFehdyU/G6v2fb/KV2";
    private static final String pubilcKeyFile = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArsGk1Nfiqt/2uHEt3hkp0fAJVNoUJiAddkjsQxKZV0DTxwx8bIuGNteua/SrSGPUG4JftnpQtHkbyw7keGajj9SxciP2xo1d8qV89sXF84GLQLV88qYLZP8SQCtzyn1tvfdT5DvEhdSkPdJyWh96bUwG1Mgam06x7hKOh4cv78ZqXv62eK59GWNn8OxHNEcTZG5uIeaQCXhOkj8g6J1FBr8qNs4+Obs72YeesR+aeQfLOMufLIvHSk2TubZapbwjtcmSNr9WoqRnr4+2gm/A4lOYANIulASZIUBhfBed4NAwylHDaeL+7Y8GTOaUxyL8fNq2hQnsUgzYVJkIBhKdNwIDAQAB";

    /**
     * 验签
     * @param data 原始数据
     * @param signature 签名字符串（Base64编码）
     * @return 验证结果，true表示验证通过，false表示验证失败
     */
    public static boolean verifySignature(byte[] data, String signature) throws Exception {
        // Base64解码公钥和签名
        byte[] publicKeyBytes = Base64.decodeBase64(pubilcKeyFile);
        byte[] signatureBytes = Base64.decodeBase64(signature);
        // 创建公钥对象
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        // 创建签名对象
        Signature sign = Signature.getInstance("SHA256withRSA");
        sign.initVerify(publicKey);
        // 更新原始数据
        sign.update(data);
        // 验证签名
        boolean result = sign.verify(signatureBytes);
        return result;
    }

    /**
     * 使用私钥进行解密
     * @param encryptedText 加密数据（Base64编码）
     * @return 原始数据
     */
    public static byte[] decrypt(String encryptedText) throws Exception {
        // Base64解码私钥和加密数据
        byte[] privateKeyBytes = Base64.decodeBase64(privateKeyFile);
        byte[] encryptedBytes = Base64.decodeBase64(encryptedText);
        // 创建私钥对象
        PrivateKey privateKey = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));
        // 创建解密器
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        // 解密数据
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        // 返回原始数据
        return decryptedBytes;
    }
    /**
     * 使用私钥进行加签
     * @param data 原始数据
     * @return 签名结果（Base64编码）
     */
    public static String sign(byte[] data) throws Exception {
        // Base64解码私钥
        byte[] privateKeyBytes = Base64.decodeBase64(privateKeyFile);
        // 创建私钥对象
        PKCS8EncodedKeySpec  keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        // 创建签名对象
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(data);
        // 计算签名值
        byte[] signBytes = signature.sign();
        // 返回签名值（Base64编码）
        return Base64.encodeBase64String(signBytes);
    }

    /**
     * 使用公钥进行加密
     * @param plainText 原始数据
     * @return 加密结果（Base64编码）
     */
    public static String encrypt(byte[] plainText) throws Exception {
        // Base64解码公钥
        byte[] publicKeyBytes = Base64.decodeBase64(pubilcKeyFile);
        // 创建公钥对象
        PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(publicKeyBytes));
        // 创建加密器
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        // 加密数据
        byte[] encryptedBytes = cipher.doFinal(plainText);
        // 返回加密结果（Base64编码）
        return Base64.encodeBase64String(encryptedBytes);
    }
}
