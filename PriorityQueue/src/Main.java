import java.util.Arrays;

public class Main {

    public static void main(final String[] args) {
        final PriorityQueue<Integer> queue = new PriorityQueue<>(Arrays.asList(5, 1, 6, 3, 6, 7, 10));
        queue.offer(4);
        queue.offer(0);
        System.out.println(queue);
        System.out.println(queue.poll());
        System.out.println(queue);
        queue.remove(3);
        System.out.println(queue);
        System.out.println(queue.contains(-5));
        System.out.println(queue.contains(7));
        System.out.println(queue.peek());
        System.out.println(queue);
        System.out.println(queue.size());
        System.out.println(Arrays.toString(queue.toArray()));
        queue.clear();
        System.out.println(queue);
        final PriorityQueue<Integer> q1 = new PriorityQueue<>(Arrays.asList(7, 3, 6, 1, 2)), q2 = new PriorityQueue<>(Arrays.asList(5, 16, 2, 5, 7, 3));
        q1.mergeWith(q2);
        System.out.println(q1);
    }
}
