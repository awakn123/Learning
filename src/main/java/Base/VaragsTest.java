package Base;

import com.google.common.collect.Lists;

import java.util.List;

public class VaragsTest {
    public static void main(String[] args) {
        VaragsTest t = new VaragsTest();
        t.varagsFunc("1", "2");
        List<String> arg = Lists.newArrayList("3", "4");
        t.varagsFunc("0", arg.toArray());
    }

    private void varagsFunc(String o, Object... args) {
        for (Object arg: args) {
            System.out.println(arg);
        }
    }
}
