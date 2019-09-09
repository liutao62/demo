package com.liutao62.demo;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Aqs extends AbstractQueuedSynchronizer {
    Lock lock = new ReentrantLock();
}
