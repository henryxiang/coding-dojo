package dojo.ds;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class LinkedListTest {
    @Test
    public void testAddToList() {
        LinkedList<String> list = new LinkedList<>();
        String[] data = {"A", "B", "C", "D", "E", "F", "G"};
        Arrays.sort(data);
        for (String d : data) list.add(d);
        assertEquals(data.length, list.size());
        for (int i = 0; i < data.length; i++) {
            assertEquals(data[i], list.get(i));
        }
    }

    @Test
    public void removeFromList() {
        LinkedList<String> list = new LinkedList<>();
        String[] data = {"A", "B", "C", "D", "E", "F", "G"};
        for (String d : data) list.add(d);
        list.remove(0);
        assertEquals("B", list.get(0));
        list.remove(list.size()-1);
        assertEquals("F", list.tail.value);
        list.remove(2);
        assertEquals("E", list.get(2));
    }
}