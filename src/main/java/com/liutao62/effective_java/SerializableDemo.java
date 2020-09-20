package com.liutao62.effective_java;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author liutao
 * @date Created in 2020/9/21 0:22
 * @description
 */
public class SerializableDemo {
}

abstract class AbstractFoo {
    private int x, y;

    private enum State {
        NEW, INITIALIZING, INITIALIZED
    }

    private final AtomicReference<State> init = new AtomicReference<>(State.NEW);

    protected AbstractFoo() {
    }

    public AbstractFoo(int x, int y) {
        initialize(x, y);
    }

    protected final void initialize(int x, int y) {
        if (!init.compareAndSet(State.NEW, State.INITIALIZING)) {
            throw new IllegalStateException("Already initialized");
        }
        this.x = x;
        this.y = y;
        init.set(State.INITIALIZED);
    }

    /**
     * 如果编写不好的子类没有初始化实例，该方法调用就可以快速干净的失败，确保对象的完整性
     */
    private void checkInit() {
        if (init.get() != State.INITIALIZED) {
            throw new IllegalStateException("uninitialized");
        }
    }

    protected final int getX() {
        checkInit();
        return x;
    }

    protected final int getY() {
        checkInit();
        return y;
    }
}

class Foo extends AbstractFoo implements Serializable {

    private static final long serialVersionUID = 3970050998066707186L;

    private void readObject(ObjectInputStream is) throws IOException, ClassNotFoundException {
        is.defaultReadObject();

        int x = is.readInt();
        int y = is.readInt();
        initialize(x, y);
    }

    private void writeObject(ObjectOutputStream os) throws IOException {
        os.defaultWriteObject();

        os.writeInt(getX());
        os.writeInt(getY());
    }
}
