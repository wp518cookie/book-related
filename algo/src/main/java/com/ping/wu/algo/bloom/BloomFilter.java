package com.ping.wu.algo.bloom;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.BitSet;
import java.util.Collection;

/**
 * @author wuping
 * @date 2019-06-28
 */

public class BloomFilter<T> implements Serializable {
    private static final long serialVersionUID = -5031792269073716854L;

    private BitSet bitSet;

    private int bitSetSize;
    private double bitsPerElement;
    /**
     * 能够添加的元素的最大个数
     */
    private int expectedNumberOfFilterElements;
    /**
     * 过滤器容器中元素的实际数量
     */
    private int numberOfAddedElements;
    /**
     * 哈希函数的个数
     */
    private int k;

    /**
     * 存储哈希值的字符串的编码方式
     */
    static final Charset charset = Charset.forName("UTF-8");
    /**
     * 在大多数情况下，MD5提供了较好的散列精确度。如有必要，可以换成 SHA1算法
     */
    static final String hashName = "MD5";
    /**
     * MessageDigest类用于为应用程序提供信息摘要算法的功能，如 MD5 或 SHA 算法
     */
    static final MessageDigest digestFunction;

    static {
        MessageDigest tmp;
        try {
            tmp = java.security.MessageDigest.getInstance(hashName);
        } catch (NoSuchAlgorithmException e) {
            tmp = null;
        }
        digestFunction = tmp;
    }

    /**
     * 构造一个空的布隆过滤器. 过滤器的长度为c*n
     * @param c 每个元素占多少位
     * @param n 表示过滤器能添加的最大元素数量
     * @param k 表示需要使用的哈希函数个数
     */
    public BloomFilter(double c, int n, int k) {
        this.expectedNumberOfFilterElements = n;
        this.k = k;
        this.bitsPerElement = c;
        this.bitSetSize = (int) Math.ceil(c * n);
        numberOfAddedElements = 0;
        this.bitSet = new BitSet(bitSetSize);
    }

    /**
     *
     * @param bitSetSize    指定了过滤器的总大小
     * @param expectedNumberOElements   指定了过滤器能添加的最大的元素数量
     */
    public BloomFilter(int bitSetSize, int expectedNumberOElements) {
        this(bitSetSize / (double) expectedNumberOElements,  expectedNumberOElements, (int) Math.round((bitSetSize / (double) expectedNumberOElements)* Math.log(2.0)));
    }

    /**
     *
     * @param falsePositiveProbability   所期望误报率
     * @param expectedNumberOfElements  要添加的元素的数量
     */
    public BloomFilter(double falsePositiveProbability, int expectedNumberOfElements) {
        this(Math.ceil(-(Math.log(falsePositiveProbability) / Math.log(2)))/ Math.log(2), // c = k/ln(2)
                expectedNumberOfElements,
                (int) Math.ceil(-(Math.log(falsePositiveProbability) / Math.log(2)))); // k = ln(2)m/n
    }

    /**
     *
     * @param bitSetSize
     * @param expectedNumberOfFilterElements
     * @param actualNumberOfFilterElements
     * @param filterData
     */
    public BloomFilter(int bitSetSize, int expectedNumberOfFilterElements,
                       int actualNumberOfFilterElements, BitSet filterData) {
        this(bitSetSize, expectedNumberOfFilterElements);
        this.bitSet = filterData;
        this.numberOfAddedElements = actualNumberOfFilterElements;
    }

    /**
     *
     * @param val   字符串
     * @param charset  编码方式
     * @return
     */
    public static long createHash(String val, Charset charset) {
        return createHash(val.getBytes(charset));
    }

    /**
     *
     * @param val 输入的字符串
     * @return
     */
    public static long createHash(String val) {
        return createHash(val, charset);
    }

    /**
     * 输入数据
     * @param data
     * @return
     */
    public static long createHash(byte[] data) {
        long h = 0;
        byte[] res;

        synchronized (digestFunction) {
            res = digestFunction.digest(data);
        }

        for (int i = 0; i < 4; i++) {
            h <<= 8;
            h |= ((int) res[i]) & 0xFF;
        }
        return h;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BloomFilter<T> other = (BloomFilter<T>) obj;
        if (this.expectedNumberOfFilterElements != other.expectedNumberOfFilterElements) {
            return false;
        }
        if (this.k != other.k) {
            return false;
        }
        if (this.bitSetSize != other.bitSetSize) {
            return false;
        }
        if (this.bitSet != other.bitSet
                && (this.bitSet == null || !this.bitSet.equals(other.bitSet))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + (this.bitSet != null ? this.bitSet.hashCode() : 0);
        hash = 61 * hash + this.expectedNumberOfFilterElements;
        hash = 61 * hash + this.bitSetSize;
        hash = 61 * hash + this.k;
        return hash;
    }

    /**
     * 根据最大元素数量和过滤器的大小来计算误报率。
     * 方法的返回值为误报率。如果插入的元素个数小于最大值，则误报率会比返回值要小。
     *
     * @return 期望的误报率.
     */
    public double expectedFalsePositiveProbability() {
        return getFalsePositiveProbability(expectedNumberOfFilterElements);
    }

    /**
     * 通过插入的元素数量和过滤器容器大小来计算实际的误报率。
     *
     * @param numberOfElements
     *            插入的元素的个数.
     * @return 误报率.
     */
    public double getFalsePositiveProbability(double numberOfElements) {
        // (1 - e^(-k * n / m)) ^ k
        return Math.pow((1 - Math.exp(-k * (double) numberOfElements
                / (double) bitSetSize)), k);

    }

    /**
     * 通过实际插入的元素数量和过滤器容器大小来计算实际的误报率。
     *
     * @return 误报率.
     */
    public double getFalsePositiveProbability() {
        return getFalsePositiveProbability(numberOfAddedElements);
    }

    /**
     * 返回哈希函数的个数 k
     *
     * @return  k.
     */
    public int getK() {
        return k;
    }

    /**
     * 清空过滤器元素
     */
    public void clear() {
        bitSet.clear();
        numberOfAddedElements = 0;
    }

    /**
     * 向过滤器中添加元素。
     * 添加的元素的toString()方法将会被调用，返回的字符串作为哈希函数的输出。
     *
     * @param element
     *            要添加的元素
     */
    public void add(T element) {
        long hash;
        String valString = element.toString();
        for (int x = 0; x < k; x++) {
            hash = createHash(valString + Integer.toString(x));
            hash = hash % (long) bitSetSize;
            bitSet.set(Math.abs((int) hash), true);
        }
        numberOfAddedElements++;
    }

    /**
     * 添加一个元素集合到过滤器中
     *
     * @param c
     *            元素集合.
     */
    public void addAll(Collection<? extends T> c) {
        for (T element : c) {
            add(element);
        }
    }

    /**
     * 用来判断元素是否在过滤器中。如果已存在，返回 true。
     *
     * @param element
     *            要检查的元素.
     * @return 如果估计该元素已存在，则返回true
     */
    public boolean contains(T element) {
        long hash;
        String valString = element.toString();
        for (int x = 0; x < k; x++) {
            hash = createHash(valString + Integer.toString(x));
            hash = hash % (long) bitSetSize;
            if (!bitSet.get(Math.abs((int) hash))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断一个集合中的元素是否都在过滤器中。
     *
     * @param c
     *            要检查的元素集合
     * @return 如果集合所有的元素都在过滤器中，则返回true。
     */
    public boolean containsAll(Collection<? extends T> c) {
        for (T element : c) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 得到某一位的值
     *
     * @param bit
     *            bit的位置.
     * @return 如果该位被设置，则返回true。
     */
    public boolean getBit(int bit) {
        return bitSet.get(bit);
    }

    /**
     * 设置过滤器某一位的值
     *
     * @param bit
     *            要设置的位置.
     * @param value
     *            true表示已经成功设置。false表示改为被清除。
     */
    public void setBit(int bit, boolean value) {
        bitSet.set(bit, value);
    }

    /**
     * 返回存放信息的位数组.
     *
     * @return 位数组.
     */
    public BitSet getBitSet() {
        return bitSet;
    }

    /**
     * 得到过滤器中位数组个大小。
     *
     * @return 数组大小.
     */
    public int size() {
        return this.bitSetSize;
    }

    /**
     * 返回已添加的元素的个数
     *
     * @return 元素个数.
     */
    public int count() {
        return this.numberOfAddedElements;
    }

    /**
     * 得到能添加的元素的最大数量
     *
     * @return  最大数量.
     */
    public int getExpectedNumberOfElements() {
        return expectedNumberOfFilterElements;
    }

    /**
     * 得到每个元素占用的位的个数的期望值
     *
     * @return 每个元素占用的位数
     */
    public double getExpectedBitsPerElement() {
        return this.bitsPerElement;
    }

    /**
     * 得到每个元素占用位数的实际值
     *
     * @return 每个元素占用的位数.
     */
    public double getBitsPerElement() {
        return this.bitSetSize / (double) numberOfAddedElements;
    }

    public static void main(String[] args) {

    }
}
