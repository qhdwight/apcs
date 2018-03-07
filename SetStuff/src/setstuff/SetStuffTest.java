package setstuff;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class SetStuffTest {

    private SetStuff setStuff = new SetStuff();

    @Test
    void ret17() {
        assertTrue(setStuff.ret17().equals(Collections.singletonList(17)));
    }

    @Test
    void zerothElement() {
        assertEquals(new Integer(5), setStuff.zerothElement(new ArrayList<>(Arrays.asList(5, 16, 2, 6, 2, 1))));
        assertEquals(new Integer(-23), setStuff.zerothElement(new ArrayList<>(Arrays.asList(-23, 34, -1201))));
        assertEquals(new Integer(0), setStuff.zerothElement(new ArrayList<>(Collections.singletonList(0))));
        assertEquals(null, setStuff.zerothElement(new ArrayList<>()));
    }

    @Test
    void contains() {
        assertTrue(setStuff.contains(5, new int[] {-5, 3, 62, 5}));
        assertFalse(setStuff.contains(-3, new int[] {5, 3, 2, 6, 2, 66, 754}));
        assertFalse(setStuff.contains(6, new int[0]));
    }

    @Test
    void anyContains() {
        assertTrue(setStuff.anyContains(new int[] {1, 6, 2, 3}, new int[] {-5, -2, -5, 3}));
        assertFalse(setStuff.anyContains(new int[] {1, 2, 3, 4}, new int[] {5, 6, 7}));
        assertFalse(setStuff.anyContains(new int[] {}, new int[] {5, 2, 3}));
    }

    @Test
    void union() {
        assertArrayEquals(new int[] {1, 2, 3, 4}, setStuff.union(new int[] {1, 2, 3}, new int[] {2, 3, 4}));
        assertArrayEquals(new int[] {6, 3, -213, 152, -6146, -2, 7}, setStuff.union(new int[] {6, 3, -213, 152, -6146}, new int[] {6, -2, 3, 7}));
        assertArrayEquals(new int[] {-1}, setStuff.union(new int[0], new int[] {-1}));
    }

    @Test
    void intersect() {
        assertArrayEquals(new int[] {2, 3}, setStuff.intersect(new int[] {1, 2, 3}, new int[] {2, 3, 4, 5, -21323}));
        assertArrayEquals(new int[0], setStuff.intersect(new int[0], new int[] {1, 25, 123, 1525125, 312312512}));
    }

    @Test
    void nearestUnvisitedNode() {
        assertEquals("B", setStuff.nearestUnvisitedNode(new boolean[] {true, false, false}, new int[] {1, 2, 3}, new String[] {"A", "B", "C"}));
        assertEquals("", setStuff.nearestUnvisitedNode(new boolean[] {true, true, true}, new int[] {5, 2, 3}, new String[]{"A", "B", "C"}));
    }

    @Test
    void listify() {
        assertTrue(Arrays.asList(2, 3, 4).equals(setStuff.listify(2, 3, 4)));
    }

    @Test
    void addEdge() {
        // Crazy stuff
        assertTrue(
                Arrays.asList(
                        new ArrayList<>(Arrays.asList(1, 2, 3)),
                        new ArrayList<>(Arrays.asList(3, 1, 4)),
                        new ArrayList<>(Arrays.asList(4, 5, 6)))
                .equals(
                setStuff.addEdge(
                        new ArrayList<>(Arrays.asList(
                                new ArrayList<>(Arrays.asList(1, 2, 3)),
                                new ArrayList<>(Arrays.asList(3, 1, 4)))),
                        4, 5, 6)));
    }

    @Test
    void collectNodes() {
        assertTrue(
                setStuff.collectNodes(new ArrayList<>(Arrays.asList(
                    new ArrayList<>(Arrays.asList(1, 2, 3)),
                    new ArrayList<>(Arrays.asList(3, 1, 4)))))
                .equals(
                new ArrayList<>(Arrays.asList(1, 2, 3))));
    }

    @Test
    void setInfinity() {
        assertTrue(setStuff.setInfinity(3, 1).equals(Arrays.asList(Integer.MAX_VALUE, 0, Integer.MAX_VALUE)));
    }
}