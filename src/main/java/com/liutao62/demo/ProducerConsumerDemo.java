package com.liutao62.demo;

public class ProducerConsumerDemo {
    public static void main(String[] args) {
        Thread producer = new Producer();
        Thread consumer = new Consumer();
        producer.start();
        consumer.start();
    }
}

class Producer extends Thread {
    private BOX box = BOX.BOX;

    public BOX getBox() {
        return box;
    }

    public void setBox(BOX box) {
        this.box = box;
    }

    public int produce() {
        if (box.num > 8) return -1;
        synchronized (box) {
            if (box.num < 9) {
                System.out.println("生产者生产 1 个 当前拥有 ： " + ++box.num);
                box.count++;
            }
        }
        return box.num;
    }


    @Override
    public void run() {
        while (box.count < 100) {
            produce();
        }

    }
}

class Consumer extends Thread {

    private BOX box = BOX.BOX;


    public int consume() {
        if (box.num < 1) return -1;
        synchronized (box) {
            if (box.num > 0) {
                System.out.println("消费者消费 1 个 当前     剩余 ： " + --box.num);
            }
        }
        return box.num;
    }


    @Override
    public void run() {
        while (box.count < 100) {
            this.consume();
        }
    }
}

enum BOX {
    BOX,Test;
    int num;
    int count;
}