package com.liutao62.aims_offer;


/**
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
 */
public class _20_CustomStack {
    private static int top = -1;
    private static int min = Integer.MAX_VALUE;
    private static int size = 100;
    private int[] array = new int[size];

    public void push(int node) {
        if (min > node) {
            min = node;
        }
        if (top >= size - 10) {
            size = size << 1;
            int[] temp = new int[size];
            for (int i = 0; i < array.length; i++) {
                temp[i] = array[i];
            }
            array = temp;
        }
        array[++top] = node;
    }

    public void pop() {
        array[top] = 0;
        top--;
        min = 2147483647;
        for (int i = 0; i <= top; i++) {
            if (array[i] < min) min = array[i];
        }
    }

    public int top() {
        return array[top];
    }

    public int min() {
        return min;
    }
}
