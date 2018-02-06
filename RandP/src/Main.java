public class Main {

    public static void main(String[] args) {
        final Randp randp = new Randp(5);
        for (int i = 0; i < 10; i++)
            System.out.println(Integer.toString(randp.nextInt()));
    }
}
