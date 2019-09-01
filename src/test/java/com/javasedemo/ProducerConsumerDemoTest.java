package com.javasedemo;

import org.junit.Test;

public class ProducerConsumerDemoTest {

    Producer producer = new Producer();
    Consumer consumer = new Consumer();

    @Test
    public void synMethod(){
        Thread p = producer;
        Thread c = consumer;
        p.start();
        c.start();
    }
    @Test
    public void lockMethod(){
        Thread p = producer;
        Thread c = consumer;
        p.start();
        c.start();
    }
}