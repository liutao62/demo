package com.liutao62.effective_java;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Date;

/**
 * @author liutao
 * @date Created in 2020/9/21 2:03
 * @description
 */
public class SerializableProxyDemo {
}

final class Period implements Serializable {
    private static final long serialVersionUID = -7105964973975331854L;
    private final Date start, end;

    public Period(Date start, Date end) {
        if (start.compareTo(end) > 0) {
            throw new IllegalArgumentException(start + " after " + end);
        }
        this.start = new Date(start.getTime());
        this.end = new Date(end.getTime());
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    /**
     * @return
     * @description 在序列化之前，将外围类的实例变成了它的序列化代理，这样序列化系统永远不会产生外围实例，但是攻击者可能伪造
     */
    private Object writeReplace() {
        return new PeriodProxy(this);
    }

    /**
     * @param is
     * @throws InvalidObjectException
     * @description 避免攻击者违反该类的约束条件
     */
    private void readObject(ObjectInputStream is) throws InvalidObjectException {
        throw new InvalidObjectException("Proxy required");
    }

    private static class PeriodProxy implements Serializable {
        private static final long serialVersionUID = 117059518953058312L;
        private final Date start, end;

        PeriodProxy(Period period) {
            this.start = period.getStart();
            this.end = period.getEnd();
        }

        /**
         * @description 返回一个逻辑上相当的外围类实例
         * @return
         */
        private Object readResolve() {
            return new Period(start, end);
        }
    }
}
