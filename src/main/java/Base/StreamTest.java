package Base;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class StreamTest {
    public static void main(String[] args) {
        List<String> ids = Lists.newArrayList("1", "2", "3", "4");
        System.out.println("reduce:"+ids.stream().reduce((acc, item) -> acc+"," + item).get());
        System.out.println("collect:"+ids.stream().collect(Collectors.joining()));
        System.out.println("collect ,:"+ids.stream().collect(Collectors.joining(",")));
        System.out.println("collect prefix&&suffix:"+ids.stream().collect(Collectors.joining(",", "pre-", "-suf")));
        System.out.println("new collect:"+ ids.stream().collect(Collector.of(
                () -> new StringBuilder(),
                StringBuilder::append,
                StringBuilder::append
        )));
        System.out.println("collect ?:"+ ids.stream().collect(Collector.of(
                ()-> new StringBuilder(),// 初始化
                (builder, item)-> builder.append("?,"),// 累加操作
                (builder, builder2)-> builder.append(builder2)// 并行计算时的合并
        )) + "; wrong");
        System.out.println("join ?:" + ids.stream().map((item)-> "?").collect(Collectors.joining(",")));
    }
}
