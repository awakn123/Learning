package Interview22;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordDistanceTest {

    @Test
    void calculate() {
        WordDistance distance = new WordDistance();
        Assertions.assertEquals(2, distance.calculate("armaze", "amazon"));
    }
}