package com.liutao62.functional_programming;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author liutao
 * @date Created in 2020/11/3 21:27
 * @description
 */
public class Practice3_9 {

    /**
     * @param numbers
     * @return
     * @description 编写一个求和函数， 计算流中所有数之和
     */
    public int addUp(Stream<Integer> numbers) {
        return numbers.reduce(0, (a, b) -> a + b);
    }

    /**
     * 编写一个函数，接受艺术家列表作为参数，返回一个字符串列表，其中包含艺术家的
     * 姓名和国籍；
     *
     * @return
     */
    public List<String> getNameAndNationalityOfArtist(List<Artist> artists) {
        return artists.stream().flatMap(artist -> Stream.of(artist.getName(), artist.getNationality()))
                .collect(Collectors.toList());
    }

    public List<Artist> count(List<Artist> artists) {
        return artists.stream().filter(artist -> artist.getMembers().count() < 3).collect(Collectors.toList());
    }
}

