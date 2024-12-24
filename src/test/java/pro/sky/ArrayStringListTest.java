package pro.sky;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayStringListTest {
    private ArrayStringList arrayStringList;

    @BeforeEach
    public void setUp() {
        arrayStringList = new ArrayStringList();
    }

    @Test
    public void testAdd() {
        String item = "test";

        assertEquals(item, arrayStringList.add(item));
        assertEquals(item, arrayStringList.get(0));
    }

    @Test
    public void testNullFails() {
        assertThrows(NullPointerException.class, () -> arrayStringList.add(null));
        assertThrows(NullPointerException.class, () -> arrayStringList.add(0, null));
        assertThrows(NullPointerException.class, () -> arrayStringList.indexOf(null));
        assertThrows(NullPointerException.class, () -> arrayStringList.lastIndexOf(null));
        assertThrows(NullPointerException.class, () -> arrayStringList.remove(null));
        assertThrows(NullPointerException.class, () -> arrayStringList.set(0, null));
        assertThrows(NullPointerException.class, () -> arrayStringList.contains(null));
    }

    @Test
    public void testOutOfBoundsFails() {
        assertThrows(IndexOutOfBoundsException.class, () -> arrayStringList.add(-1, ""));
        assertThrows(IndexOutOfBoundsException.class, () -> arrayStringList.add(arrayStringList.size() + 1, ""));
        assertThrows(IndexOutOfBoundsException.class, () -> arrayStringList.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> arrayStringList.get(arrayStringList.size()));
        assertThrows(IndexOutOfBoundsException.class, () -> arrayStringList.set(-1, ""));
        assertThrows(IndexOutOfBoundsException.class, () -> arrayStringList.set(arrayStringList.size(), ""));
        assertThrows(IndexOutOfBoundsException.class, () -> arrayStringList.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> arrayStringList.remove(arrayStringList.size()));
    }

    @Test
    public void testAddWithIndex() {
        String item1 = "test1";
        String item2 = "test2";

        arrayStringList.add(0, item1);

        assertEquals(item2, arrayStringList.add(0, item2));
        assertEquals(item2, arrayStringList.get(0));
        assertEquals(item1, arrayStringList.get(1));
    }

    @Test
    public void testSet() {
        String item1 = "test1";
        String item2 = "test2";

        arrayStringList.add(item1);

        assertEquals(item2, arrayStringList.set(0, item2));
        assertEquals(item2, arrayStringList.get(0));
    }

    @Test
    public void testRemove() {
        String item = "test";

        arrayStringList.add(item);

        assertEquals(item, arrayStringList.remove(item));
        assertTrue(arrayStringList.isEmpty());
    }

    @Test
    public void testRemoveWithIndex() {
        String item = "test";

        arrayStringList.add(item);

        assertEquals(item, arrayStringList.remove(0));
        assertTrue(arrayStringList.isEmpty());
    }

    @Test
    public void testContains() {
        String item = "test";

        arrayStringList.add(item);

        assertTrue(arrayStringList.contains(item));
    }

    @Test
    public void testContainsFails() {
        String item = "test";

        assertFalse(arrayStringList.contains(item));
    }

    @Test
    public void testIndexOf() {
        String item = "test";

        arrayStringList.add(item);

        assertEquals(0, arrayStringList.indexOf(item));
        assertEquals(-1, arrayStringList.indexOf("null"));
    }

    @Test
    public void testLastIndexOf() {
        String item = "test";

        arrayStringList.add(item);

        assertEquals(0, arrayStringList.lastIndexOf(item));
        assertEquals(-1, arrayStringList.lastIndexOf("null"));
    }

    @Test
    public void testIndexOfFails() {
        String item = "test";

        assertEquals(-1, arrayStringList.indexOf(item));
    }

    @Test
    public void testLastIndexOfFails() {
        String item = "test";

        assertEquals(-1, arrayStringList.lastIndexOf(item));
    }

    @Test
    public void testGet() {
        String item = "test";

        arrayStringList.add(item);

        assertEquals(item, arrayStringList.get(0));
    }

    @Test
    public void testGetFails() {
        assertThrows(IndexOutOfBoundsException.class, () -> arrayStringList.get(0));
    }

    @Test
    public void testEqualsOK() {
        ArrayStringList otherList = new ArrayStringList();

        assertTrue(arrayStringList.equals(otherList));

        otherList.add("test");
        arrayStringList.add("test");

        assertTrue(arrayStringList.equals(otherList));

        otherList = arrayStringList;

        assertTrue(arrayStringList.equals(otherList));
    }

    @Test
    public void testEqualsFails() {
        ArrayStringList otherList = new ArrayStringList();

        otherList.add("test");

        assertFalse(arrayStringList.equals(otherList));

        assertThrows(NullPointerException.class, () -> arrayStringList.equals(null));

        arrayStringList.add("test1");

        assertFalse(arrayStringList.equals(otherList));
    }

    @Test
    public void testSize() {
        arrayStringList.add("test");

        assertEquals(1, arrayStringList.size());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(arrayStringList.isEmpty());

        arrayStringList.add("test");

        assertFalse(arrayStringList.isEmpty());
    }

    @Test
    public void testClear() {
        arrayStringList.add("test");
        arrayStringList.clear();

        assertTrue(arrayStringList.isEmpty());
    }

    @Test
    public void testToArray() {
        String item = "test";

        arrayStringList.add(item);

        assertArrayEquals(new String[]{item}, arrayStringList.toArray());
    }

    @Test
    public void testCheckIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> arrayStringList.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> arrayStringList.get(0));
    }

    @Test
    public void testGrow() {
        for (int i = 0; i < 20; i++) {
            arrayStringList.add("test" + i);
        }

        assertEquals(20, arrayStringList.size());
    }
}
