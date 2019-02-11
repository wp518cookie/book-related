package com.ping.wu.se.encrypt;

import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.Charset;

/**
 * @author wuping
 * @date 2019/1/17
 */

public class DesEncryptTest {
    private static final String  DEFAULTSKEY    = "yonghui1";
    private static final Charset CHARSET = Charset.forName("UTF-8");
    public static void main(String[] args) throws Exception {
        for (Long i = 100000L; i < 10000000000L; i++) {
            String result = generateUid(i);
//            System.out.println(result);
            Long parseResult = parseUidToMemberId(result);
            if (!i.equals(parseResult)) {
                System.out.println(i);
            }
        }
    }


    /**
     * 加密
     * @param srcStr
     * @param sKey
     * @return
     */
    public static String encrypt(String srcStr, String sKey) {
        byte[] src = srcStr.getBytes(CHARSET);
        byte[] buf = DesEncryptTest.encrypt(src, sKey);
        return DesEncryptTest.parseByte2HexStr(buf);
    }

    /**
     * 解密
     *
     * @param hexStr
     * @param sKey
     * @return
     * @throws Exception
     */
    public static String decrypt(String hexStr, String sKey) throws Exception {
        byte[] src = DesEncryptTest.parseHexStr2Byte(hexStr);
        byte[] buf = DesEncryptTest.decrypt(src, sKey);
        return new String(buf, CHARSET);
    }

    /**
     * 加密
     * @param data
     * @param sKey
     * @return
     */
    public static byte[] encrypt(byte[] data, String sKey) {
        try {
            byte[] key = sKey.getBytes();
            // 初始化向量
            IvParameterSpec iv = new IvParameterSpec(key);
            DESKeySpec desKey = new DESKeySpec(key);
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成securekey
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            // Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, iv);
            // 现在，获取数据并加密
            // 正式执行加密操作
            return cipher.doFinal(data);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     * @param src
     * @param sKey
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] src, String sKey) throws Exception {
        byte[] key = sKey.getBytes();
        // 初始化向量
        IvParameterSpec iv = new IvParameterSpec(key);
        // 创建一个DESKeySpec对象
        DESKeySpec desKey = new DESKeySpec(key);
        // 创建一个密匙工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        // 将DESKeySpec对象转换成SecretKey对象
        SecretKey securekey = keyFactory.generateSecret(desKey);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, iv);
        // 真正开始解密操作
        return cipher.doFinal(src);
    }

    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    private static final int radix = 36;
    private static final String DUIBA_MAGIC_CODE = "db";

    public static String generateUid(Long memberId) {
        StringBuilder sb = new StringBuilder(DUIBA_MAGIC_CODE);
        int hashcode = memberId.hashCode();
        String convertResult = Long.toString(memberId, 36);
        sb.append(convertResult.length());
        sb.append(convertResult);
        sb.append(Integer.toString(hashcode, 36));
        return sb.toString();
    }

    public static Long parseUidToMemberId(String uid) {
        if (StringUtils.isEmpty(uid)
                || uid.length() < 5
                || !uid.startsWith(DUIBA_MAGIC_CODE)) {
            throw new IllegalArgumentException("uid无法解析：" + uid);
        }
        uid = uid.substring(2, uid.length());
        int length = Integer.valueOf(String.valueOf(uid.charAt(0)), radix);
        String prePart = uid.substring(1, length + 1);
        String postPart = uid.substring(length + 1, uid.length());
        Long memberId = Long.valueOf(prePart, radix);
        if (memberId.hashCode() != Long.valueOf(postPart, radix)) {
            throw new RuntimeException("uid解析异常，不匹配：" + uid);
        }
        return memberId;
    }
}
