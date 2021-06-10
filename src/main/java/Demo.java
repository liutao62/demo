import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.UUID;

/**
 * @author liutao
 * @date Created in 2021/5/13 20:38
 * @description
 */
public class Demo {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        int count = 100;

        System.out.println("---------------- 初始化hashMap容量为100 ------------");
        int resizeCount = 0;
        HashMap<Integer, Object> map = new HashMap<>(count,1);
        Method capacityMethod = map.getClass().getDeclaredMethod("capacity");
        capacityMethod.setAccessible(true);
        int capacity = (int) capacityMethod.invoke(map);
        System.out.println("初始容量：" + capacity);
        for (int i = 0; i < count; i++) {
            map.put(i, UUID.randomUUID());
            int curCapacity = (int) capacityMethod.invoke(map);
            if (curCapacity > capacity) {
                System.out.println("当前容量：" + curCapacity);
                resizeCount++;
                capacity = curCapacity;
            }
        }
        System.out.println("hashMap扩容次数：" + resizeCount);

        System.out.println("---------------- 不初始化hashMap容量 -------------------");
        resizeCount = 0;
        HashMap<Integer, Object> map1 = new HashMap<>();
        Method capacityMethod1 = map1.getClass().getDeclaredMethod("capacity");
        capacityMethod1.setAccessible(true);
        int capacity1 = (int) capacityMethod1.invoke(map1);
        System.out.println("初始容量：" + capacity1);
        for (int i = 0; i < count; i++) {
            map1.put(i, UUID.randomUUID());
            int curCapacity = (int) capacityMethod1.invoke(map1);
            if (curCapacity > capacity1) {
                System.out.println("当前容量：" + curCapacity);
                resizeCount++;
                capacity1 = curCapacity;
            }
        }
        System.out.println("扩容次数：" + resizeCount);
    }
}
