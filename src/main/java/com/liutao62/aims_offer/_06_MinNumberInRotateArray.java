package com.liutao62.aims_offer;

/**
 * 把一个数组最开始的若干个元素搬到数组的末尾，
 * 我们称之为数组的旋转。 输入一个非减排序的数组的一个旋转，
 * 输出旋转数组的最小元素。
 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
 * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 */
public class _06_MinNumberInRotateArray {

    public int minNumberInRotateArray(int[] array) {
        return array.length == 0 ? 0 : findMin(array.length - 1, 0, array.length >> 1, array);
    }

    public int findMin(int high, int low, int middle, int[] array) {
        if (high - middle == 1 || middle - low < 1) {
            int temp = array[high] < array[low] ? array[high] : array[low];
            return array[middle] < temp ? array[middle] : temp;
        } else {
            return array[middle] < array[array.length - 1] ?
                    findMin(middle, low, (middle + low) >> 1, array) :
                    findMin(high, middle, (middle + high) >> 1, array);
        }
    }
}
