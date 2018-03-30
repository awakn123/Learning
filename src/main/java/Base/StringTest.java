package Base;

import java.util.ArrayList;
import java.util.List;

public class StringTest {
    public static void main(String[] args) {
        String format = "a:%s, b:%s, c:%s, d:%s, e:%s";
        List<String> list = new ArrayList<>(100000);
        long startTime = System.nanoTime();
        for (int i=0;i< 100000;i++) {
            list.add(String.format(format, i+1, i+2, i+3, i+4, i+5));
        }
        long endTime = System.nanoTime();
        System.out.println("format耗时：" + (endTime - startTime));
        list.size();

        list = new ArrayList<>(100000);
        startTime = System.nanoTime();
        for (int i=0;i< 100000;i++) {
            list.add("a:" + (i+1) + ", b:"+(i+2)+", c:" + (i+3) + ", d:" + (i+4) + ", e:" + ( i+5));
        }
        endTime = System.nanoTime();
        System.out.println("相加耗时：" + (endTime - startTime));
        list.size();
    }
}
