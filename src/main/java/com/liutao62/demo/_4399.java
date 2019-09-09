package com.liutao62.demo;

public class _4399 {


    public static void main(String[] args) {
        _4399 test = new _4399();
        assert true == test.verify(2016, 8, 13);

        assert true == test.verify(2016, 8, 20);
        assert true == test.verify(2016, 8, 14);
        assert false == test.verify(2016, 8, 15);

        assert true == test.verify(2016, 8, 6);
        assert true == test.verify(2016, 8, 7);
        assert false == test.verify(2016, 8, 5);
    }

    /**
     * 已知2016年8月13-14日为双休，根据输入年月日判断是否为节假日。（法定节假日除外）
     * 不能使用任何库函数
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public boolean verify(int year, int month, int day) {
        int totalDays = 2016 * 365 + 483 + 213 + 13;
        // (year / 4 - year / 100)是否闰年已经将多的那一天算进去了，而不知道知否超过二月所以要减一
        int verifyDays = year * 365 + (year / 4 - year / 100) - 1;

        // 判断是否为闰年
        boolean flag = year % 4 == 0 && year % 100 != 0;

        switch (month) {
            case 12:
                verifyDays += 30;
            case 11:
                verifyDays += 31;
            case 10:
                verifyDays += 30;
            case 9:
                verifyDays += 31;
            case 8:
                verifyDays += 31;
            case 7:
                verifyDays += 30;
            case 6:
                verifyDays += 31;
            case 5:
                verifyDays += 30;
            case 4:
                verifyDays += 31;
            case 3:
                if (flag) verifyDays += 29;
                else verifyDays += 28;
            case 2:
                verifyDays += 31;
            default:
                break;

        }
        verifyDays += day;

        // 用于判断在给定日期之前还是之后
        flag = verifyDays > totalDays;

        int days = verifyDays - totalDays;

        if (flag) {
            if (days % 7 == 0 || days % 7 == 1) return true;
            return false;
        }
        if (-days % 7 == 0 || -days % 7 == 6) return true;
        return false;
    }
}
