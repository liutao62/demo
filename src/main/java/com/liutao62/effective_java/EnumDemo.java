package com.liutao62.effective_java;

/**
 * @author liutao
 * @date Created in 2020/8/25 0:13
 * @description
 */
public class EnumDemo {
    // 只是为了方便！！！
    public int val1, val2;

    public static void main(String[] args) {
        FinalTestEnum INSTANCE = FinalTestEnum.INSTANCE;
        System.out.println(INSTANCE);
        INSTANCE.obj.val1 = 1;
        System.out.println(INSTANCE);
    }

    @Override
    public String toString() {
        return "EnumDemo{" +
                "val1=" + val1 +
                ", val2=" + val2 +
                '}';
    }
}

// 从维护角度上来说：假如新加了一个元素，有可能 pay 方法的计算方式就不适用 default
enum PayrollDay1 {
    MONDAY, TUESDAY, SATURDAY;

    private static final int HOURS_PER_SHIFT = 8;

    double pay(double hoursWorked, double payRate) {
        double basePay = hoursWorked * payRate;

        double overtimePay;
        switch (this) {
            case MONDAY:
            case TUESDAY:
                overtimePay = hoursWorked * payRate / 2;
                // weekdays
            default:
                overtimePay = hoursWorked <= HOURS_PER_SHIFT ? 0 : (hoursWorked - HOURS_PER_SHIFT) * payRate / 2;
        }
        return overtimePay;
    }
}

// 策略枚举模式：不需要 switch 语句或者特定的常量方法实现，更加安全灵活
enum PayrollDay2 {
    MONDAY(PayType.WEEKDAY), TUESDAY(PayType.WEEKDAY),
    SATURDAY(PayType.WEEKEND);

    private final PayType payType;

    // 强制选择一种报酬策略
    PayrollDay2(PayType payType) {
        this.payType = payType;
    }

    double pay(double hoursWorked, double payRate) {
        return payType.pay(hoursWorked, payRate);
    }

    // 策略枚举类型
    private enum PayType {
        WEEKDAY {
            double overtimePay(double hrs, double payRate) {
                return hrs <= HOURS_PER_SHIFT ? 0 : (hrs - HOURS_PER_SHIFT) * payRate / 2;
            }
        }, WEEKEND {
            double overtimePay(double hrs, double payRate) {
                return hrs * payRate / 2;
            }
        };

        private static final int HOURS_PER_SHIFT = 8;

        abstract double overtimePay(double hrs, double payRate);

        double pay(double hoursWorked, double payRate) {
            double basePay = HOURS_PER_SHIFT * payRate;
            return basePay + overtimePay(hoursWorked, payRate);
        }
    }
}

enum FinalTestEnum {
    INSTANCE(new EnumDemo());

    public final EnumDemo obj;

    FinalTestEnum(EnumDemo obj) {
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "FinalTestEnum{" +
                "obj=" + obj +
                '}';
    }
}
