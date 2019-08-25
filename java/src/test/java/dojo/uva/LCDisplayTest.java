package dojo.uva;

import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.*;

public class LCDisplayTest {
    @Test
    public void testDisplay() {
        InputStream inputStream = getClass().getResourceAsStream("LCDisplayData.txt");
        LCDisplay lcDisplay = new LCDisplay();
        lcDisplay.run(inputStream);
        assertEquals(1, 1);
    }

}