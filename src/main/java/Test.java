import java.text.ParseException;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author liutao
 * @date Created in 2020/10/14 16:55
 * @description
 */
public class Test {
    public static void main(String[] args) throws ParseException {
        String string = "com.yonyou.hrcloud.attend.web.AttendStaffController.queryByCondition(AttendStaffController.java:331)com.yonyou.hrcloud.attend.web.AttendStaffController$$FastClassBySpringCGLIB$$3e2ddbd1.invoke(<generated>)org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:771)org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:749)com.alibaba.druid.support.spring.stat.DruidStatInterceptor.invoke(DruidStatInterceptor.java:73)org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:749)org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:691)";

        String collect = Arrays.stream(string.split("com"))
                .filter(str -> !str.startsWith("org.") && !str.startsWith("java"))
                .filter(str -> !str.startsWith("nc.itf.framework."))
                .filter(str -> !str.startsWith("nc.bs.framework.") && !str.startsWith("nccloud.framework."))
                .collect(Collectors.joining("\n com"));
        System.out.println(collect);


    }

}
