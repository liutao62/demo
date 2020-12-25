package com.liutao62.functional_programming;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Character.isDigit;

/**
 * @author liutao
 * @date Created in 2020/10/17 11:14
 * @description
 */
public class Demo {
    public static void main(String[] args) {

        StringBuilder sb =new StringBuilder();
        System.out.println(sb.toString());
        System.out.println(sb == null);
        System.out.println(sb.length());
        long begin = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            sb.append(sb.toString());
            insert(sb);
        }
        System.out.println("time : " + (System.nanoTime() - begin));
        sb = new StringBuilder("222222222222222222222222222222222222222222222222222222222222222222222222222222222222222");
        begin = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            sb.append(sb.toString());
            add(sb);
        }
        System.out.println("time : " + (System.nanoTime() - begin));



        String name = "lllll";
        String finalName = name;
        // 赋值1
        name = "qqqq";
        // 引用非 final 变量，但是该变量在既成事实上必须是 final ,即 没有再次赋值，如果再次肤质，用法参见 赋值1
        // Lambda 表达式也被称为 闭包 未赋值的变量与周边环境隔离起来，进而被绑定到一个特定的值。
        Runnable thread = () -> System.out.println("name " + finalName);

        Predicate<Integer> test = x -> x > 0;
        System.out.println(test.test(1));

        List<String> beginningWithNumbers = Stream.of("1a", "1abc", "abc1")
                .filter(value -> isDigit(value.charAt(0)))
                .collect(Collectors.toList());

        beginningWithNumbers.stream().forEach(System.out::print);

        Optional<Integer> min = Stream.of(1, 2, 3, 4).min(Integer::compareTo);

        System.out.println("-----------" + min.get());


        List<String> list = Stream.of("1", "2", "3", "4", "5", "2", "3", "10", "4", "1", "2", "3", "4", "1", "2", "3", "4", "1", "2", "3", "4", "1", "2", "3", "4")
                .collect(Collectors.toList());

        list.forEach(System.out::print);

        System.out.println("------------------------");
        List<String> collect = list.stream().limit(4).collect(Collectors.toList());

        List<Integer> collect1 = Stream.iterate(0, index -> index + 1).limit(5).parallel().collect(Collectors.toList());

        collect1.forEach(System.out::print);
        StringBuffer sql = new StringBuffer();
        sql.append("select * from table where id in ");


        Stream<String> skip = list.stream().skip(5);

        newMethod(list);

    }

    public static void insert(StringBuilder sb){
        sb.insert(0,"q").append("w");
    }

    public static void add(StringBuilder sb){
        sb.insert(0,"q").append("w");
    }


    public static void oldMethod(List<String> list) {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from table where id in ");
        Stream.iterate(0, count -> count + 1).limit(5).forEach(count -> {

            StringBuffer inSql = new StringBuffer();
            String subIds = list.stream().skip(count * 5).limit(5).collect(Collectors.joining("','"));
            if (count == 0) {
                inSql.append("('");
            } else {
                inSql.append(" or id in (");
            }
            inSql.append(subIds);
            inSql.append("')");

            sql.append(inSql);

            inSql = null;
        });

    }

    public static int maxSize = 5;

    public static void newMethod(List<String> list) {
        StringBuilder newSql = new StringBuilder();
        newSql.append("select * from table where");
        int totalCount = list.size() / maxSize;
        for (int count = 0; count < totalCount; count++) {
            String condition = list.stream().skip(count * maxSize).limit(maxSize)
                    .collect(Collectors.joining("','", count != 0 ? " or id in ('" : " id in ('", "')"));

            newSql.append(condition);

        }
        System.out.println(newSql);
    }

//    public Set<String> findLongTracks(List<Album> albums) {
//        Set<String> trackNames = new HashSet<>();
//        for(Album album : albums) {
//            for (Track track : album.getTrackList()) {
//                if (track.getLength() > 60) {
//                    String name = track.getName();
//                    trackNames.add(name);
//                }
//            }
//        }
//        return trackNames;
//    }

}
