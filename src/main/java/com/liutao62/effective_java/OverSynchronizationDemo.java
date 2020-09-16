package com.liutao62.effective_java;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liutao
 * @date Created in 2020/9/16 23:38
 * @description
 */
public class OverSynchronizationDemo {
    public static void main(String[] args) {
        ObservableSet<Integer> set = new ObservableSet<>(new HashSet<>());
        /*
        version1 直接取消观察者订阅
        set.addObserver(new SetObserver<Integer>() {
            @Override
            public void added(ObservableSet<Integer> set, Integer element) {
                System.out.println(element);
                // 当 notifyElementAdded 调用观察者的 added 方法时，它正处于遍历 observers 的过程
                // added 方法调用可观察集合的 removeObserver 方法导致在遍历列表的过程中将一个元素从列表中删除，这是非法的
                if (element == 23) {
                    set.removeObserver(this);
                }
            }
        });
        */

        set.addObserver(new SetObserver<Integer>() {
            @Override
            public void added(ObservableSet<Integer> set, Integer element) {
                System.out.println(element);

                if (element == 23) {
                    ExecutorService service = Executors.newSingleThreadExecutor();
                    final SetObserver<Integer> observer = this;
                    try {
                        service.submit(new Runnable() {
                            @Override
                            public void run() {
                                // 企图锁定 observers 但是它无法获得该锁，因为主线程已经有锁了，
                                // 但是主线程一直在等待后台线程完成对观察者的删除，即 if (element == 23) 里面的代码
                                set.removeObserver(observer);
                            }
                        }).get();
                    } catch (InterruptedException e) {
                        throw new RuntimeException();
                    } catch (ExecutionException e) {
                        throw new RuntimeException();
                    } finally {
                        service.shutdown();
                    }
                }
            }
        });


        for (int i = 0; i < 100; i++) {
            set.add(i);
        }
    }
}


class ObservableSet<E> extends ForwardingSet<E> {

    public ObservableSet(Set<E> set) {
        super(set);
    }

    private final List<SetObserver<E>> observers = new ArrayList<>();

    public void addObserver(SetObserver<E> observer) {
        synchronized (observers) {
            observers.add(observer);
        }
    }

    public boolean removeObserver(SetObserver<E> observer) {
        synchronized (observers) {
            return observers.remove(observer);
        }
    }

    private void notifyElementAdded(E element) {
        synchronized (observers) {
            for (SetObserver<E> observer : observers) {
                observer.added(this, element);
            }
        }
    }

    @Override
    public boolean add(E e) {
        boolean add = super.add(e);
        if (add) {
            notifyElementAdded(e);
        }
        return add;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean result = false;
        for (E e : c) {
            result |= add(e);
        }
        return result;
    }
}

interface SetObserver<E> {
    void added(ObservableSet<E> set, E element);
}