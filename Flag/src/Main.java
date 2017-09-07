import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main {

    public static void main(String[] args) {

        // Create Java window, make sure it closes when you press the exit button
        JFrame frame = new JFrame("AmericanFlag");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        AmericanFlag flag = new AmericanFlag();

        frame.add(flag);

        // Make sure the flag has the correct size inside of the JFrame
        frame.pack();

        frame.setVisible(true);
    }
}
