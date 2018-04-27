import java.util.ArrayList;

public class Digits {

    public static void main(String[] args) {
        System.out.println(new Digits(382424));
        System.out.println(new Digits(0));
        System.out.println(new Digits(10021230));
        System.out.println(new Digits(12569).isStrictlyIncreasing());
        System.out.println(new Digits(9581247).isStrictlyIncreasing());
        System.out.println(new Digits(4).isStrictlyIncreasing());
    }

    private ArrayList<Integer> digitList;

    public Digits(int num) {
        digitList = new ArrayList<>();
        while (num >= 0) {
            int digit = num % 10;
            digitList.add(0, digit);
            num /= 10;
            if (num == 0) break;
        }
    }

    public boolean isStrictlyIncreasing() {
        if (digitList.size() <= 1)
            return true;
        for (int i = 0; i < digitList.size() - 1; i++) {
            if (digitList.get(i) > digitList.get(i+1))
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return digitList.toString();
    }
}
