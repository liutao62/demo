package com.aims_offer;
//给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
public class _12_Power {
    public double Power(double base, int exponent) {
        if(base == 0){
            return 0;
        }
        if (exponent >= 0){
            if (exponent == 0){
                return 1;
            }else {
                return base * Power(base,exponent - 1);
            }
        }
        return 1 / Power(base,-exponent);
    }
}
