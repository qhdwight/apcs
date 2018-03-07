package setstuff;

import java.util.*;

public class SetStuff {

    /**
     * Returns an {@link ArrayList} that only contains 17.
     *
     * @return An {@link ArrayList} that only contains 17
     */
    ArrayList<Integer> ret17() {
        return new ArrayList<>(Collections.singletonList(17));
    }

    /**
     * Return the zeroth element of a given {@link ArrayList}.
     *
     * @param al {@link ArrayList} to find the zeroth element
     * @return The zeroth element of the given {@link ArrayList}
     */
    Integer zerothElement(ArrayList<Integer> al){
        return al.size() > 0 ? al.get(0) : null;
    }

    /**
     * Find whether or not a given integer is in a given array.
     *
     * @param a Element to find
     * @param ar Array to find element in
     * @return Whether or not the array contains the given element
     */
    boolean contains(int a, int[] ar){
        for (int q : ar) {
            if (q == a)
                return true;
        }
        return false;
    }

    /**
     * Find whether or not there is a common element in two given integer arrays.
     *
     * @param a First array
     * @param b Second array
     * @return Whether or not there is a common element
     */
    boolean anyContains(int[] a, int[] b){
        for (final int ai : a) {
            for (final int bi : b) {
                if (ai == bi)
                    return true;
            }
        }
        return false;
    }

    /**
     * Find the union of two given arrays, which is an array that contains all elements from both.
     * Elements will not be repeated.
     *
     * @param a First array
     * @param b Second array
     * @return Union of both
     */
    int[] union(int[] a, int[] b){
        final Set<Integer> r = new LinkedHashSet<>();
        for (int q : a)
            r.add(q);
        for (int q : b)
            r.add(q);
        int[] ar = new int[r.size()];
        int c = 0;
        for (int q : r)
            ar[c++] = q;
        return ar;
    }

    /**
     * Find the intersection of two given arrays, which is an array that contains elements found in both.
     * Elements will not be repeated.
     *
     * @param a First array
     * @param b Second array
     * @return Intersection of both
     */
    int[] intersect(int[] a, int[] b){
        final Set<Integer> s1 = new HashSet<>(), s2 = new HashSet<>(), r = new HashSet<>();
        for (int q : a)
            s1.add(q);
        for (int q : b)
            s2.add(q);
        for (int q : s1) {
            if (s2.contains(q))
                r.add(q);
        }
        final int[] ar = new int[r.size()];
        int c = 0;
        for (int q : r)
            ar[c++] = q;
        return ar;
    }

    /**
     * Find the node that has the shortest distance and has not been visited.
     * Keep track of the smallest, and when looping through all of them update if it is smaller.
     * Also keep track of the index so that we can get the name of the node later.
     *
     * @param visited Nodes that have been visited so far
     * @param distance Distances to each node
     * @param nodeName Names of each node
     * @return The name of the node that has the shortest distance and has not been visited.
     */
    String nearestUnvisitedNode(boolean[] visited, int[] distance, String[] nodeName) {
        int smallestIndex = -1, smallest = Integer.MAX_VALUE;
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i] && distance[i] < smallest) {
                smallest = distance[i];
                smallestIndex = i;
            }
        }
        return smallestIndex == -1 ? "" : nodeName[smallestIndex];
    }

    /**
     * Turn three given integers into an {@link ArrayList}.
     *
     * @param a First element
     * @param b Second element
     * @param c Third element
     * @return {@link ArrayList} that contains all three arguments
     */
    ArrayList<Object> listify(int a, int b, int c){
        return new ArrayList<>(Arrays.asList(a, b, c));
    }

    /**
     * Add three elements as an {@link ArrayList} to a given {@link ArrayList} of {@link ArrayList}'s.
     *
     * @param graph Two dimensional {@link ArrayList} representing a graph
     * @param a First element
     * @param b Second element
     * @param c Third element
     * @return Graph after changes
     */
    ArrayList<ArrayList<Object>> addEdge(ArrayList<ArrayList<Object>> graph, int a, int b, int c){
        graph.add(new ArrayList<>(Arrays.asList(a, b, c)));
        return graph;
    }

    /**
     * Given a two dimensional {@link ArrayList} of {@link Integer}'s return the union of each of the first two elements.
     *
     * @param edgelist List representing a graph edge list
     * @return Union of the first two elements of every inner {@link ArrayList}
     */
    ArrayList<Integer> collectNodes(ArrayList<ArrayList<Integer>> edgelist) {
        final Set<Integer> r = new LinkedHashSet<>();
        for (final ArrayList<Integer> al : edgelist) {
            r.add(al.get(0));
            r.add(al.get(1));
        }
        return new ArrayList<>(r);
    }

    /**
     * Create an {@link ArrayList} where every element is infinity except the one at index start.
     *
     * @param n Size of the {@link ArrayList}
     * @param start Index that should not be infinity, instead zero
     * @return {@link ArrayList} containing all infinity except for element at index start
     */
    ArrayList<Integer> setInfinity(int n, int start){
        final ArrayList<Integer> al = new ArrayList<>(n);
        for (int i = 0; i < n; i++)
            al.add(Integer.MAX_VALUE);
        al.set(start, 0);
        return al;
    }
}
