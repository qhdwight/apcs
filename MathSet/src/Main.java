import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        MathSet<Integer> set = new MathSet<>();
        System.out.println("--Adding--");
        System.out.println(set.toString());
        System.out.println(set.add(5));
        System.out.println(set.toString());
        System.out.println(set.add(3));
        System.out.println(set.toString());
        System.out.println(set.addAll(Arrays.asList(6, 1)));
        System.out.println(set.toString());
        System.out.println(set.add(4));
        System.out.println(set.toString());
        System.out.println(set.add(4));
        System.out.println("--Contains--");
        System.out.println(set.containsAll(Arrays.asList(5, 3, 6, 1)));
        System.out.println(set.containsAll(Arrays.asList(-2, 3, 8, 1)));
        System.out.println(set.contains(2));
        System.out.println(set.contains(3));
        System.out.println("--Array--");
        System.out.println(Arrays.toString(set.toArray()));
        Integer[] a = new Integer[7];
        System.out.println(Arrays.toString(set.toArray(a)));
        a = new Integer[2];
        System.out.println(Arrays.toString(set.toArray(a)));
        System.out.println("--Remove--");
        System.out.println(set.remove(-2));
        System.out.println(set.toString());
        System.out.println(set.remove(3));
        System.out.println(set.toString());
        System.out.println(set.removeAtIndex(0));
        System.out.println(set.toString());
        System.out.println(set.removeAtIndex(-1));
        System.out.println(set.toString());
        System.out.println(set.removeAll(Arrays.asList(6, 1)));
        System.out.println(set.toString());
        System.out.println("--Clear--");
        set.clear();
        System.out.println(set.toString());
        System.out.println("--Retaining--");
        set.addAll(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println(set.toString());
        System.out.println(set.retainAll(Arrays.asList(1, 2, 3, 4, 5)));
        System.out.println(set.toString());
        System.out.println(set.retainAll(Arrays.asList(-5, -2, 3, 4)));
        System.out.println(set.toString());
        System.out.println("==Union & Intersections==");
        final MathSet<Integer> ms1 = new MathSet<>(Arrays.asList(1, 2, 3, 4, 5)), ms2 = new MathSet<>(Arrays.asList(4, 5, 6, 7, 8));
        System.out.println(ms1.toString());
        System.out.println(ms1.toString());
        System.out.println("--Union--");
        System.out.println(ms1.union(ms2).toString());
        System.out.println("--Intersection--");
        System.out.println(ms1.intersection(ms2).toString());
    }
}
