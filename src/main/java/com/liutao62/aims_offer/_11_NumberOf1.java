package com.liutao62.aims_offer;

//输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
public class _11_NumberOf1 {
    public int NumberOf1(int n) {
        return n < 0 ? negativeNumber(n) : positiveNumber(n);
    }

    public int positiveNumber(int n) {
        String s = Integer.toBinaryString(n);
        char[] temp = new char[s.length()];
        s.getChars(0, s.length(), temp, 0);
        int count = 0;
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] == '1') {
                count++;
            }
        }
        return count;
    }

    public int negativeNumber(int n) {
        if (n == -2147483648) return 1;
        String s = Integer.toBinaryString(-n ^ 0xffffffff);
        char[] temp = new char[s.length()];
        s.getChars(0, s.length(), temp, 0);
        for (int i = temp.length - 1; i >= 0; i--) {
            if (temp[i] == '1') temp[i] = '0';
            else {
                temp[i] = '1';
                break;
            }
        }
        int aim = 0;
        for (int i = temp.length - 1; i >= 0; i--) {
            if (temp[i] == '1')
                aim += Math.pow(2,temp.length - i - 1);
        }
        return aim % getBigBinaryNumber(-n);
    }

    public int getBigBinaryNumber(int n){
        n -= 1;
        n |= n >> 1;
        n |= n >> 2;
        n |= n >> 4;
        n |= n >> 8;
        n |= n >> 16;
        System.out.println("n===" + n);
        return n;
    }

}
