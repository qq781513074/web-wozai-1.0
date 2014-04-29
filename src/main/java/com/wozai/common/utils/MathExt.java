
package com.wozai.common.utils;

import java.util.Arrays;

import static java.lang.Math.*;
import static java.util.Arrays.*;
/**
 * @author Lucifer
 *
 */
final class MathExt {

    private MathExt() {
    }

    static double cot(double a) {
        return cos(a) / sin(a);
    }

    static double sec(double a) {
        return 1 / cos(a);
    }

    static double csc(double a) {
        return 1 / sin(a);
    }

    static double arcsin(double a) {
        return asin(a);
    }

    static double arccos(double a) {
        return acos(a);
    }

    static double arctan(double a) {
        return atan(a);
    }

    static double arccot(double a) {
        return arctan(1 / a);
    }

    static double arcsec(double a) {
        return arccos(1 / a);
    }

    static double arccsc(double a) {
        return arcsin(1 / a);
    }

    /**
     * 正矢函数
     * @param a
     * @return
     */
    static double versin(double a) {
        return 1 - cos(a);
    }

    /**
     * 正矢函数
     * @param a
     * @return
     */
    static double vercosin(double a) {
        return 1 + cos(a);
    }

    /**
     * 余矢函数
     * @param a
     * @return
     */
    static double coversin(double a) {
        return 1 - sin(a);
    }

    /**
     * 余矢函数
     * @param a
     * @return
     */
    static double covercosin(double a) {
        return 1 + sin(a);
    }

    /**
     * 半正矢函数
     * @param a
     * @return
     */
    static double haversin(double a) {
        return (1 - cos(a)) / 2;
    }

    /**
     * 半正矢函数
     * @param a
     * @return
     */
    static double havercosin(double a) {
        return (1 + cos(a)) / 2;
    }

    /**
     * 半余矢函数
     * @param a
     * @return
     */
    static double hacoversin(double a) {
        return (1 - sin(a)) / 2;
    }

    /**
     * 半余矢函数
     * @param a
     * @return
     */
    static double hacovercosin(double a) {
        return (1 + sin(a)) / 2;
    }

    /**
     * 外正割函数
     * @param a
     * @return
     */
    static double exsec(double a) {
        return sec(a) - 1;
    }

    /**
     * 外余割函数
     * @param a
     * @return
     */
    static double excsc(double a) {
        return csc(a) - 1;
    }

    static double log2(double a) {
        return logN(2, a);
    }

    /**
     * bNum为底zNum的对数。
     * @param bNum 底数。
     * @param zNum 真数。
     * @return 对数值。
     */
    static double logN(double bNum, double zNum) {
        return log(zNum) / log(bNum);
    }

    /**
     * 对num进行四舍五入操作。
     * @param num 要进行舍入操作的数。
     * @param bit 要保留小数的精确位数。
     * @return 舍入后的结果。
     */
    static double round(double num, int bit) {
        Double tmp = pow(10, bit);
        return Math.round(num * tmp) / tmp;
    }
}
