package com.ping.wu.se.bit;

/**
 * @author wuping
 * @date 2019/1/14
 */

public class HexUtils {
    private static char[] chars = "0123456789abcdef".toCharArray();
    private static String str = "0123456789abcdef";
    public static String toHex(String s) {
        StringBuilder sb = new StringBuilder();
        byte[] bs = s.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4; //高位
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
            sb.append(' ');
        }
        return sb.toString();
    }

    public static String hexToString(String hex) {
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] achar = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return new String(result);
    }

    public static long hexToLong(String hex) {
        return Long.parseLong(hex, 16);
    }

    private static byte toByte(char c) {
        byte b = (byte) "0123456789abcdef".indexOf(c);
        return b;
    }
}
