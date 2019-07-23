package com.javasedemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxyDemo {
    public static void main(String[] args) {
        CustomizeProxy proxy = new CustomizeProxy();
        IUserDao dao = (IUserDao) proxy.getProxyObject();
        dao.save();
    }
}


interface IUserDao {
    void save();
}

class UserDaoImpl implements IUserDao {

    @Override
    public void save() {
        System.out.println("保存成功");
    }
}

class MyTransactionManage {
    public void begin() {
        System.out.println("开启事务");
    }

    public void commit() {
        System.out.println("提交事务");
    }

    public void rollback() {
        System.out.println("回滚事务");
    }
}

/**
 * API:
 * Proxy 提供用于创建动态代理类和实例的静态方法，它还是由这些方法创建的所有动态代理类的超类。
 * <p>
 * 创建某一接口 Foo 的代理：
 * <p>
 * InvocationHandler handler = new MyInvocationHandler(...);
 * Class proxyClass = Proxy.getProxyClass(
 * Foo.class.getClassLoader(), new Class[] { Foo.class });
 * Foo f = (Foo) proxyClass.
 * getConstructor(new Class[] { InvocationHandler.class }).
 * newInstance(new Object[] { handler });
 * 或使用以下更简单的方法：
 * Foo f = (Foo) Proxy.newProxyInstance(Foo.class.getClassLoader(),
 * new Class[] { Foo.class },
 * handler);
 */
class CustomizeProxy implements InvocationHandler {

    //演示中直接写死。
    private Object dao = new UserDaoImpl();
    private MyTransactionManage tx = new MyTransactionManage();

    /**
     * @param dao 需要代理的对象实例
     * @description 正常开发中还是通过注入。所以需要setter或有参构造器等方式。
     */
    public void setDao(Object dao) {
        this.dao = dao;
    }

    public void setTx(MyTransactionManage tx) {
        this.tx = tx;
    }

    /**
     * @return 代理类对象
     * @description 根据API文档演示，还是需要获取到代理对象且提供了两种方式。这里直接封装成代理类的方法。
     *
     * CGLIB： 实现接口同名为spring下的。
     *  Enhancer enhancer = new Enhancer();
     *  enhancer.setSuperclass(dao.getclass());
     *  enhancer.setCallback(this);
     *  return enhancer.create();
     */
    public Object getProxyObject() {
        return Proxy.newProxyInstance(dao.getClass().getClassLoader(),  //当前类的类加载器
                dao.getClass().getInterfaces(),   //当前类的接口数组
                this);    //代理类对象
    }

    /**
     * @param proxy  代理类对象
     * @param method 代理的方法
     * @param args
     * @return
     * @throws Throwable
     * @description 可根据真实情况分类代理。如get等不需代理
     *              if(method.getName().startwith(""))等操作进行处理
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object obj = null;
        try {
            this.tx.begin();
            method.invoke(this.dao, args);
            this.tx.commit();
        } catch (Exception e) {
            this.tx.rollback();
        }
        return obj;
    }
}