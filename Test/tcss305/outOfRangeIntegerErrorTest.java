package tcss305;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class outOfRangeIntegerErrorTest {
    outOfRangeIntegerError test = new outOfRangeIntegerError("out of range");
    @Test
    void getMessage() {
        Assertions.assertEquals("out of range", test.getMessage());
    }
}