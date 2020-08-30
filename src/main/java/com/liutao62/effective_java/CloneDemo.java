package com.liutao62.effective_java;

/**
 * @author liutao
 * @date Created in 2020/8/18 22:37
 * @description
 */
public class CloneDemo implements Cloneable {
    private int a;
    // 可变对象
    private /*final*/ int[] arr;

    public CloneDemo(int[] arr) {
        this.arr = arr;
        System.out.println("调用");
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int[] getArr() {
        return arr;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        int[] arr = new int[]{1, 2, 3};
        CloneDemo obj1 = new CloneDemo(arr);
        obj1.setA(10);
        // 不实现 Cloneable 接口 throws CloneNotSupportedException
        Object clone = obj1.clone();
        System.out.println(obj1);

        CloneDemo obj2 = (CloneDemo) clone;
        System.out.println(obj2);
        // obj2.setArr(null);
        System.out.println(obj2);

        // 版本1 引用相同数组
        int[] arr1 = obj1.getArr();
        arr1[0] = 0;
        System.out.println(obj1);
        System.out.println(obj2);

        // 版本1 arr 域与原实例引用相同的数组，修改会产生无意义结果，或者 NNP
        arr1 = obj2.getArr();
        arr1[0] = 2;
        System.out.println(obj1);
        System.out.println(obj2);


        Sub sub = new Sub();
        Sub clone1 = sub.clone();
        System.out.println(sub);
        System.out.println(clone1);
    }

    /**
     * @return
     * @throws CloneNotSupportedException
     * @description 版本1
     */
//    @Override
//    public Object clone() throws CloneNotSupportedException {
//        return super.clone();
//    }

    /**
     * @return
     * @description 版本2 逐域拷贝
     * 1、Clone 方法就是另外一个构造器，必须确保不会伤害到原始对象，并确保正确的创建被克隆对象中的约束条件
     * 2、如果 arr 是 final 的，不能正常工作，Clone 框架与引用可变对象的 final 域的正常用法是不兼容的（clone 方法被禁止给 arr 赋值）
     */
    @Override
    public CloneDemo clone() {
        CloneDemo result = null;
        try {
            result = (CloneDemo) super.clone();
            // 如果加上 final，编译不通过 Cannot assign a value to final variable 'arr'
            result.arr = this.arr.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
        return result;
    }
}

class MyHashTable implements Cloneable {
    private Entry[] buckets;

    private static class Entry {
        final Object key;
        Object value;
        Entry next;

        public Entry(Object key, Object value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        /*
        // 递归方式如果链表过长可能导致栈溢出
        Entry deepCopy() {
            return new Entry(key, value, next == null ? null : next.deepCopy());
        }
        */

        Entry deepCopy() {
            Entry result = new Entry(key, value, next);
            for (Entry p = result; p.next != null; p = p.next) {
                p.next = new Entry(p.next.key, p.next.value, p.next.next);
            }
            return result;
        }
    }

    /**
     * @description
     * @return
     */
    @Override
    public MyHashTable clone() {
        MyHashTable result = null;
        try {
            result = (MyHashTable) super.clone();
            result.buckets = new Entry[this.buckets.length];
            for (int i = 0; i < this.buckets.length; i++) {
                if (this.buckets[i] != null) {
                    result.buckets[i] = this.buckets[i].deepCopy();
                }
            }
        } catch (CloneNotSupportedException e) {
        }
        return result;
    }
}

class Super{
    protected void sayHi(){}
}

class Sub extends Super implements Cloneable{
    @Override
    protected void sayHi() {
        super.sayHi();
    }

    @Override
    public Sub clone() throws CloneNotSupportedException {
        return (Sub) super.clone();
    }
}