package Interview22;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NineBoxKeyboardTest {

    @Test
    void findMinimumClickCount() {
        NineBoxKeyboard box = new NineBoxKeyboard();
        Assertions.assertEquals(14, box.findMinimumClickCount("abacadefghibj"));
        Assertions.assertEquals(7, box.findMinimumClickCount("aaaaaaa"));
        Assertions.assertEquals(0, box.findMinimumClickCount(""));
        Assertions.assertEquals(0, box.findMinimumClickCount(null));
        Assertions.assertEquals(16, box.findMinimumClickCount("abcdefghijjjjjj"));
    }
}