import java.text.ParseException;

/**
 * @author liutao
 * @date Created in 2020/10/14 16:55
 * @description
 */
public class Test {
    public static void main(String[] args) throws ParseException {
        String str = "032000000401";
        str = str.substring(str.length() - 1);
        System.out.println(str);

        str = "hr_cloud.0401";
        str = str.substring(str.length() - 1);
        System.out.println(str);

        if (false && true || true) {
            System.out.println("-------------------------------------relkjlkj");
        }


    }

}
