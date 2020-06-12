package com.ping.wu.strings.substring;

/**
 * @author wuping
 * @date 2020-04-12
 */

public class KMP {
    private final int R;
    private int[][] dfa;

    private char[] pattern;
    private String pat;

    public KMP(String pat) {
        this.R = 256;
        this.pat = pat;

        int m = pat.length();
        dfa = new int[R][m];
        dfa[pat.charAt(0)][0] = 1;

    }
}
